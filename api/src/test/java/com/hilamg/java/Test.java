package com.hilamg.java;

import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Test {
    public static void main(String[] args) {
        String tokenAmount = (new BigDecimal(10).multiply(BigDecimal.TEN.pow(18))).setScale(0).toString();
//        new BigInteger(tokenAmount);
        System.out.println(TypeEncoder.encode(new Uint32(new BigInteger("0"))));
        System.out.println(TypeEncoder.encode(new Uint32(new BigInteger("1"))));
    }
}
