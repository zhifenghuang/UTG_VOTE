package com.hilamg.common.utils.bscUtils;

import com.hilamg.api.dto.out.WebProposalLog;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * 基于ERC20的代币
 */
public class TokenClient {

	private static Web3j web3j;

	private static Admin admin;

	private static String fromAddress = "0xfF96E23b44baAD550860B4264a81EB1A6f95218C";

	private static String contractAddress = "0x354acdcf829931f8fb09728f485a061d3a6b7ba5";

	private static String emptyAddress = "0x0000000000000000000000000000000000000000";

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
//		admin = Admin.build(new HttpService(Environment.RPC_URL));
//		getTokenBalance(web3j, fromAddress, contractAddress);9
		System.out.println("获取代币名称"+getTokenName(web3j, contractAddress));
//		System.out.println("查询代币精度"+getTokenDecimals(web3j, contractAddress));
//		System.out.println("查询代币符号"+getTokenSymbol(web3j, contractAddress));
//		System.out.println("获取代币发行总量"+getTokenTotalSupply(web3j, contractAddress));
//		System.out.println("查询代币余额"+(getTokenBalance(web3j,fromAddress, contractAddress)).divide(BigInteger.valueOf(getTokenDecimals(web3j, contractAddress))));
//		System.out.println(sendTokenTransaction(
//				fromAddress, "yzw",
//				"0x6c0f49aF552F2326DD851b68832730CB7b6C0DaF", contractAddress,
//				BigInteger.valueOf(100000)));

//		int decimals=TokenClient.getTokenDecimals(web3j, contrmactAddress);
//		BigDecimal balance = new BigDecimal(TokenClient.getTokenBalance(web3j, fromAddress, contractAddress));
//		balance=balance.divide(BigDecimal.TEN.pow(decimals));
//		BigDecimal TokenTotalSupply= new BigDecimal(getTokenTotalSupply(web3j,contractAddress));
//		BigDecimal balance=TokenTotalSupply.divide(BigDecimal.TEN.pow(decimals));
//		System.out.println(balance);
	}

	/**
	 * 查询代币余额
	 */
	public static BigInteger getTokenBalance(Web3j web3j, String fromAddress, String contractAddress) {

		String methodName = "balanceOf";
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();
		Address address = new Address(fromAddress);
		inputParameters.add(address);

		TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
		};
		outputParameters.add(typeReference);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddress, data);

		EthCall ethCall;
		BigInteger balanceValue = BigInteger.ZERO;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			balanceValue = (BigInteger) results.get(0).getValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return balanceValue;
	}

	/**
	 * 查询代币名称
	 *
	 * @param web3j
	 * @param contractAddress
	 * @return
	 */
	public static String getTokenName(Web3j web3j, String contractAddress) {
		String methodName = "name";
		String name = null;
		String fromAddr = emptyAddress;
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();

		TypeReference<Utf8String> typeReference = new TypeReference<Utf8String>() {
		};
		outputParameters.add(typeReference);

		Function function = new Function(methodName, inputParameters, outputParameters);

		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddr, contractAddress, data);

		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			name = results.get(0).getValue().toString();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * 查询代币符号
	 *
	 * @param web3j
	 * @param contractAddress
	 * @return
	 */
	public static String getTokenSymbol(Web3j web3j, String contractAddress) {
		String methodName = "symbol";
		String symbol = null;
		String fromAddr = emptyAddress;
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();

		TypeReference<Utf8String> typeReference = new TypeReference<Utf8String>() {
		};
		outputParameters.add(typeReference);

		Function function = new Function(methodName, inputParameters, outputParameters);

		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddr, contractAddress, data);

		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			symbol = results.get(0).getValue().toString();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return symbol;
	}

	/**
	 * 查询代币精度
	 *
	 * @param web3j
	 * @param contractAddress
	 * @return
	 */
	public static int getTokenDecimals(Web3j web3j, String contractAddress) throws ExecutionException, InterruptedException {
		String methodName = "decimals";
		String fromAddr = emptyAddress;
		int decimal = 0;
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();

		TypeReference<Uint8> typeReference = new TypeReference<Uint8>() {
		};
		outputParameters.add(typeReference);

		Function function = new Function(methodName, inputParameters, outputParameters);

		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddr, contractAddress, data);

		EthCall ethCall;
//		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			decimal = Integer.parseInt(results.get(0).getValue().toString());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
		return decimal;
	}

	/**
	 * 查询代币发行总量
	 *
	 * @param web3j
	 * @param contractAddress
	 * @return
	 */
	public static BigInteger getTokenTotalSupply(Web3j web3j, String contractAddress) {
		String methodName = "totalSupply";
		String fromAddr = emptyAddress;
		BigInteger totalSupply = BigInteger.ZERO;
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();

		TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
		};
		outputParameters.add(typeReference);

		Function function = new Function(methodName, inputParameters, outputParameters);

		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddr, contractAddress, data);

		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			totalSupply = (BigInteger) results.get(0).getValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return totalSupply;
	}

	/**
	 * 代币转账
	 */
	public static String sendTokenTransaction(String fromAddress, String password, String toAddress, String contractAddress, BigInteger amount) {
		String txHash = null;

		try {
			PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(
					fromAddress, password, BigInteger.valueOf(10)).send();
			if (personalUnlockAccount.accountUnlocked()) {
				String methodName = "transfer";
				List<Type> inputParameters = new ArrayList<>();
				List<TypeReference<?>> outputParameters = new ArrayList<>();

				Address tAddress = new Address(toAddress);

				Uint256 value = new Uint256(amount);
				inputParameters.add(tAddress);
				inputParameters.add(value);

				TypeReference<Bool> typeReference = new TypeReference<Bool>() {
				};
				outputParameters.add(typeReference);

				Function function = new Function(methodName, inputParameters, outputParameters);

				String data = FunctionEncoder.encode(function);

				EthGetTransactionCount ethGetTransactionCount = web3j
						.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).sendAsync().get();
				BigInteger nonce = ethGetTransactionCount.getTransactionCount();
				BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(5), Convert.Unit.GWEI).toBigInteger();

				Transaction transaction = Transaction.createFunctionCallTransaction(fromAddress, nonce, gasPrice,
						BigInteger.valueOf(60000), contractAddress, data);

				EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).sendAsync().get();
				txHash = ethSendTransaction.getTransactionHash();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txHash;
	}


	public static Boolean getisExsitStatus(Long id,Web3j web3j) {
		String methodName = "isExsit";
		BigInteger totalSupply = BigInteger.ZERO;
		List<Type> inputParameters = new ArrayList<>();
		inputParameters.add(new Uint256(id));
		List<TypeReference<?>> outputParameters = new ArrayList<>();

		TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
		};
		outputParameters.add(typeReference);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);
		String fromAddress = Environment.contractAddress.toLowerCase(Locale.ROOT);
		String toAddress = Environment.contractAddress.toLowerCase(Locale.ROOT);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddress, toAddress, data);
		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			totalSupply = (BigInteger) results.get(0).getValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		if(totalSupply.equals(BigInteger.ZERO)){
			return false;
		}else{
			return true;
		}
	}


	public static int getCountByProposal(Web3j web3j, Long proposal) {
		String methodName = "counts";
		BigInteger totalSupply = BigInteger.ZERO;
		List<Type> inputParameters = new ArrayList<>();
		inputParameters.add(new Uint256(proposal));
		List<TypeReference<?>> outputParameters = new ArrayList<>();

		TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
		};
		outputParameters.add(typeReference);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);
		String fromAddress = Environment.contractAddress.toLowerCase(Locale.ROOT);
		String toAddress = Environment.contractAddress.toLowerCase(Locale.ROOT);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddress, toAddress, data);

		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			totalSupply = (BigInteger) results.get(0).getValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return totalSupply.intValue();
	}


	public static WebProposalLog getProposalLog(Web3j web3j, Long proposal,Long index) {
		String methodName = "voteRecords";
		String userAddress="";
		BigInteger amount=BigInteger.ZERO;
		BigInteger option=BigInteger.ZERO;
		BigInteger time=BigInteger.ZERO;
		List<Type> inputParameters = new ArrayList<>();
		//提案id
		inputParameters.add(new Uint256(proposal));
		//投票个数
		inputParameters.add(new Uint256(index));
		List<TypeReference<?>> outputParameters = new ArrayList<>();

		//投票地址
		TypeReference<Address> inAddress = new TypeReference<Address>() {
		};
		//投票数量
		TypeReference<Uint256> inAmount = new TypeReference<Uint256>() {
		};
		//选项
		TypeReference<Uint256> inOption = new TypeReference<Uint256>() {
		};
		//时间
		TypeReference<Uint256> inTime = new TypeReference<Uint256>() {
		};
		outputParameters.add(inAddress);
		outputParameters.add(inAmount);
		outputParameters.add(inOption);
		outputParameters.add(inTime);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);
		String fromAddress = Environment.contractAddress.toLowerCase(Locale.ROOT);
		String toAddress = Environment.contractAddress.toLowerCase(Locale.ROOT);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddress, toAddress, data);
		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			userAddress = (String) results.get(0).getValue();
			amount = (BigInteger) results.get(1).getValue();
			option = (BigInteger) results.get(2).getValue();
			time = (BigInteger) results.get(3).getValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		WebProposalLog webProposalLog=new WebProposalLog();
		webProposalLog.setAddress(userAddress)
				.setAmount(amount)
				.setTime(time.longValue())
				.setOption(option.intValue());
		return webProposalLog;
	}

}
