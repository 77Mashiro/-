package com.wzguang;

import java.util.Random;

public class Create {

    // 生成算式
    public String[] createFormula(int r){
        Random random = new Random();
        String[] operator = {"＋","－","×","÷","＝"};

        String[] totalO = new String[1 + random.nextInt(3)];//随机生成操作符号个数（1~3)
        String[] totalF = new String[totalO.length+1];//操作数个数==操作符号个数+1
        String formula = new String();//生成运算式
        //是否有分数
        Boolean hasFraction = false;

        //随机产生操作数：
        for (int i = 0;i < totalF.length;i++) {

            //随机生成0或1用于确定生成分数还是整数
            int fractionOrNot = random.nextInt(2);

            if (fractionOrNot == 0) { //随机数为0，生成整数
                int integralPart = random.nextInt(r+1);
                totalF[i] = String.valueOf(integralPart);
            } else { //随机数只有0和1，这里else就代表随机数是1，生成分数，分数与整数的生成几率相同
                int denominator = 1+random.nextInt(r);//分母（+1是为了分母不为0）
                int molecule = random.nextInt(denominator);//分子部分要小于分母
                int integralPart = random.nextInt(r);//带分数的整数部分

                //化简分数
                if (molecule != 0) {//分子不为0，需进行化简
                    int commonFactor = commonFactor(denominator, molecule);
                    denominator /= commonFactor;
                    molecule /= commonFactor;
                }

                //输出最简分数
                if (integralPart == 0 && molecule > 0) { //整数部分为0,且分子大于0,不需要输出带分数形式
                    totalF[i] = molecule + "/" + denominator;
                    hasFraction = true;
                }
                else if (molecule == 0) {//分子为0，分数变成整数
                    totalF[i] = String.valueOf(integralPart);
                    hasFraction = false;
                }
                else {//输出带分数形式
                    totalF[i] = integralPart + "'" + molecule + "/" + denominator;
                    hasFraction = true;
                }
            }
        }

        //随机生成运算符：
        for (int i = 0;i < totalO.length;i++) {
            if (hasFraction)
                totalO[i] = operator[random.nextInt(2)];//有分数则只进行加减运算
            else
                totalO[i] = operator[random.nextInt(4)];//没有分数则可以进行加减乘除运算
        }

        int choose = totalF.length;//操作数个数
        if (totalF.length != 2 )
            choose = random.nextInt(totalF.length);

        //生成运算式
        for (int i = 0;i < totalF.length;i++) {
            if (i == choose && choose < totalO.length) {
                formula = formula + "(" + totalF[i] + totalO[i] ;
            } else if (i == totalF.length - 1 && i == choose+1 && choose<totalO.length) {
                formula = formula + totalF[i] + ")" + "=";
            } else if (i == choose + 1 && choose < totalO.length) {
                formula = formula + totalF[i] + ")" + totalO[i];
            } else if (i == totalF.length - 1) {
                formula = formula + totalF[i] + "=";
            } else {
                formula = formula + totalF[i] + totalO[i];
            }
        }

        Calculate checkAns = new Calculate();
        String[] ansFormula = checkAns.checkout(formula,3*totalO.length+2+1);

        if (ansFormula!=null)
            return ansFormula;
        return null;
    }

    //求最大公因数
    public int commonFactor(int a,int b) {
        while(true)
        {
            if(a%b == 0)return b;
            int temp = b;
            b = a%b;
            a = temp;
        }
    }

}
