package com.hilamg.common.utils.bscUtils;

import com.hilamg.common.utils.DESUtil;
import org.spongycastle.util.encoders.Hex;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;


public class BscSign {

    public static String sign(StringBuilder msg) {
        String data=msg.toString();
        byte[] bytes = org.springframework.security.crypto.codec.Hex.decode(data);
        byte[] contentHashBytes = Hash.sha3(bytes);
        Credentials credentials = Credentials.create(Environment.code_prikey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        System.out.println(Hex.toHexString(signMessage.getR()));
        System.out.println(Hex.toHexString(signMessage.getS()));
        System.out.println(Hex.toHexString(signMessage.getV()));
        byte[] hash = Hash.sha3(contentHashBytes);
        System.out.println("hash:" + new String(org.springframework.security.crypto.codec.Hex.encode(hash)));
        // 签名后的字符串
        String signStr = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        System.out.println("signStr=" + signStr);
        return signStr;
    }

    public static void main(String[] args) {
//        BigInteger bigInteger=new BigInteger("1");
//        bigInteger
        StringBuilder msg = new StringBuilder();
        msg.append(TypeEncoder.encode(new Uint32(Long.valueOf(1))));
        msg.append(TypeEncoder.encode(new Uint32(Long.valueOf(1))));
        msg.append(TypeEncoder.encode(new Uint32(Long.valueOf(1))));
        Address user = new Address("0x91FbD2fF5AE81fF912AD2E8E90e103274312198d");
        msg.append(TypeEncoder.encode(user));
        Address token = new Address("0x91FbD2fF5AE81fF912AD2E8E90e103274312198d");
        msg.append(TypeEncoder.encode(token));
        msg.append(TypeEncoder.encode(new Uint32(Long.valueOf(1))));
        BscSign.sign(msg);
//        System.out.println(TypeEncoder.encode(new Uint32(Long.valueOf(1))));
//        System.out.println(TypeEncoder.encode(new Uint32(Long.valueOf(1))));
//        Address token = new Address("0x91FbD2fF5AE81fF912AD2E8E90e103274312198d");
//        System.out.println(TypeEncoder.encode(token));
    }

}
//0000000000000000000000000000000000000000000000000000000000000001
//0000000000000000000000000000000000000000000000000000000000000001