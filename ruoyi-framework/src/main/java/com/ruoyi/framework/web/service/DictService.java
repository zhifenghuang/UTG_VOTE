package com.ruoyi.framework.web.service;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.ruoyi.utg.domain.UserSpaceType;
import com.ruoyi.utg.service.IUserSpaceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 *
 * @author ruoyi
 */
@Service("dict")
public class DictService
{
    @Autowired
    private ISysDictTypeService dictTypeService;

    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private IUserSpaceTypeService iUserSpaceTypeService;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getType(String dictType)
    {
        return dictTypeService.selectDictDataByType(dictType);
    }

    public List<SysDictData> getUserSpaceTypeList()
    {
       List<UserSpaceType> userSpaceTypes=iUserSpaceTypeService.selectUserSpaceTypeList(new UserSpaceType());
        List<SysDictData> sysDictDataList=new ArrayList<SysDictData>();
        userSpaceTypes.stream().forEach(userSpaceType -> {
            SysDictData sysDictData=new SysDictData();
            sysDictData.setDictLabel(userSpaceType.getSpaceName());
            sysDictData.setDictValue(userSpaceType.getId().toString());
            sysDictDataList.add(sysDictData);
        });
        return sysDictDataList;
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue)
    {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
