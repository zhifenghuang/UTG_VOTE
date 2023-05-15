package com.ruoyi.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

@Component
public class OSSClientUtil implements InitializingBean {
	private String endpoint="oss-cn-shenzhen.aliyuncs.com";
	private String bucketName="qiyun2021";
	private String fileAddress="https://oss-cn-shenzhen.aliyuncs.com/upload/";
	private String ossFilePath="https://oss-cn-shenzhen.aliyuncs.com/";
	private String accessKeyId="LTAI5tJceJjacnDGuTxTo5Nz";
	private String accessKeySecret="5zBQRXQB32ZjmmXgTCW4Mvk5Py7ScD";
	// 文件存储目录
	private String filedir = "upload/";
	private OSSClient ossClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	public OSSClientUtil() {
	}

	public OSSClientUtil(String bucketName, String fileAddress) {
		this.bucketName = bucketName;
		this.fileAddress = fileAddress;
	}

	/**
	 * 初始化
	 */
	public void init() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	public void init(String endpoint) {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		ossClient.shutdown();
	}

	/**
	 * 上传图片
	 *
	 * @param url
	 */
	public void uploadImg2Oss(String url) {
		File fileOnServer = new File(url);
		FileInputStream fin;
		try {
			fin = new FileInputStream(fileOnServer);
			String[] split = url.split("/");
			this.uploadFile2OSS(fin, split[split.length - 1]);
		} catch (FileNotFoundException e) {
			throw new ServiceException();
		}
	}

	public String uploadImg2Oss(MultipartFile file) {
	    if (file.getSize() > 10 * 1024 * 1024) {
	      throw new ServiceException("上传图片大小不能超过10M！");
	    }
	    String originalFilename = file.getOriginalFilename();
	    String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
	    Random random = new Random();
	    String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
	    try {
	      InputStream inputStream = file.getInputStream();
	      // 把图片读入到内存中
	      BufferedImage bufImg = ImageIO.read(inputStream);
	      // 压缩代码
	      // 存储图片文件byte数组
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      //防止图片变红
	      BufferedImage newBufferedImage = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_INT_RGB);
	      newBufferedImage.createGraphics().drawImage(bufImg, 0, 0, Color.WHITE, null);
	      //先转成jpg格式来压缩,然后在通过OSS来修改成源文件本来的后缀格式
//	      if (".png".equals(substring)) {
//	    	  ImageIO.write(bufImg,"jpg",bos);
//	      } else {
//	    	  ImageIO.write(bufImg,"jpg",bos);
//	      }
		  ImageIO.write(bufImg,substring.substring(1),bos);
	      //获取输出流
	      inputStream = new ByteArrayInputStream(bos.toByteArray());
	      this.uploadFile2OSS(inputStream, name);
	      return name;
	    } catch (Exception e) {
	      throw new ServiceException("图片上传失败");
	    }
	  }

	/**
	 * 获得图片路径
	 *
	 * @param fileUrl
	 * @return
	 */
	public String getImgUrl(String fileUrl) {
		if (!StringUtils.isEmpty(fileUrl)) {
			String[] split = fileUrl.split("/");
			return this.getUrl(this.filedir + split[split.length - 1]);
		}
		return null;
	}

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 *
	 * @param instream
	 *            文件流
	 * @param fileName
	 *            文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public String uploadFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			e.getMessage();
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileAddress+fileName;
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase(".bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase(".gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase(".jpeg") || FilenameExtension.equalsIgnoreCase(".jpg")
				|| FilenameExtension.equalsIgnoreCase(".png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase(".html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase(".txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase(".vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase(".pptx") || FilenameExtension.equalsIgnoreCase(".ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase(".docx") || FilenameExtension.equalsIgnoreCase(".doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase(".xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

	/**
	 * 获得url链接
	 *
	 * @param key
	 * @return
	 */
	public String getUrl(String key) {
		// 设置URL过期时间为30年 3600l* 1000*24*365*30
		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			String[] str = url.toString().split("\\?");
			return str[0];
		}
		return null;
	}

	/**
	 * 获取网络图片流
	 *
	 * @param url
	 * @return
	 */
	public static InputStream getImageStream(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				return inputStream;
			}
		} catch (IOException e) {
			System.out.println("获取网络图片出现异常，图片路径为：" + url);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除单个文件
	 * @param objectName
	 */
	public void delFile(String objectName){
		try {
			if (StringUtils.isBlank(objectName)){
				throw new ServiceException("文件链接不能为空!");
			}
			// 过滤文件链接中的前缀
			objectName = objectName.replace(ossFilePath,"");
			// 创建OSSClient实例。
			OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
			// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
			ossClient.deleteObject(bucketName, objectName);
			// 关闭OSSClient。
			ossClient.shutdown();
		}catch (Exception e) {

		}
	}

}
