package com.hilamg.java.Lucky;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrizeMathRandom {
    public int getPrizeIndex(List<Prize> prizes) {
        DecimalFormat df = new DecimalFormat("######0.00");
        int random = -1;
        try {
            //计算总权重
            double sumWeight = 0;
            for (Prize p : prizes) {
                sumWeight += p.getPrize_weight();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < prizes.size(); i++) {
                d2 += Double.parseDouble(String.valueOf(prizes.get(i).getPrize_weight())) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(prizes.get(i - 1).getPrize_weight())) / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        return random;
    }
    public static void main(String[] agrs) {
        int i = 0;
        PrizeMathRandom a = new PrizeMathRandom();
        int[] result=new int[4];
        List<Prize> prizes = new ArrayList<Prize>();

        Prize p1 = new Prize();
        p1.setPrize_name("范冰冰海报");
        p1.setPrize_weight(1);//奖品的权重设置成1
        prizes.add(p1);

        Prize p2 = new Prize();
        p2.setPrize_name("上海紫园1号别墅");
        p2.setPrize_weight(2);//奖品的权重设置成2
        prizes.add(p2);

        Prize p3 = new Prize();
        p3.setPrize_name("奥迪a9");
        p3.setPrize_weight(3);//奖品的权重设置成3
        prizes.add(p3);

        Prize p4 = new Prize();
        p4.setPrize_name("双色球彩票");
        p4.setPrize_weight(4);//奖品的权重设置成4
        prizes.add(p4);

        System.out.println("抽奖开始");
        for (i = 0; i < 10000; i++)// 打印100个测试概率的准确性
        {
            int selected=a.getPrizeIndex(prizes);
            System.out.println("第"+i+"次抽中的奖品为："+prizes.get(selected).getPrize_name());
            result[selected]++;
            System.out.println("--------------------------------");
        }
        System.out.println("抽奖结束");
        System.out.println("每种奖品抽到的数量为：");
        System.out.println("一等奖："+result[0]);
        System.out.println("二等奖："+result[1]);
        System.out.println("三等奖："+result[2]);
        System.out.println("四等奖："+result[3]);
    }
}
