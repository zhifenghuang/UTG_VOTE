package com.hilamg.common.utils.bscUtils;

import com.hilamg.api.dto.out.WebProposalLog;
import com.hilamg.api.entity.UserProposalLog;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class BscTest {

    private static Web3j web3j;

    private static Admin admin;

//    private static String fromAddress = "0xfF96E23b44baAD550860B4264a81EB1A6f95218C";

    private static String contractAddress = "0x5cf441e208ac45423d4689c2302c3e8cfc9059cc";

    private static String emptyAddress = "0x0000000000000000000000000000000000000000";

    public static boolean getTokenTotalSupply(Web3j web3j, Long id) {
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

    public static void main(String[] args) {
        web3j = Web3j.build(new HttpService(Environment.RPC_URL));
        System.out.println("返回结果" + getTokenTotalSupply(web3j, 61L));
//        Address address=new Address("0x0000000000000000000000000000000000000000");
//        System.out.println(address.getValue());
//        BigDecimal shuliang =new BigDecimal(Math.pow(Double.valueOf(10), Double.valueOf(18)));
//        System.out.println((shuliang));
//        UserProposalLog userProposalLog=new UserProposalLog();
//        System.out.println(System.currentTimeMillis());
//        System.out.println(1654830642L*1000L);
//        userProposalLog.setCreateDate(new Date(new Long(1654830642*1000)));
//        System.out.println(userProposalLog.getCreateDate());

//        String test="ux31f21854FdA47D020138ad092Fc2b8D78938346c";
//        String profex=test.substring(0,2);
//        if(profex.equals("ux") ||profex.equals("Ux")){
//            test="0x"+test.substring(2,test.length());
//        }
//        System.out.println(test);

    }
}
