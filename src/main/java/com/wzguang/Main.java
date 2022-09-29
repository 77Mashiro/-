package com.wzguang;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {/*若不输入指令直接按回车键，
                       则默认生成10条运算数在10以内的算术题
                      */
            int n = 10;
            int r = 10;
            String submitPath = null;
            String answersPath = null;

            try {
                // 输入指令
                System.out.println("请输入需要执行的指令：");
                Scanner command = new Scanner(System.in);
                String arr[] = command.nextLine().split("\\s"); //把输入的字符串以\\s为条件分割成一个String数组

                if (arr.length > 1) {
                    for (int i = 0; i < arr.length; i = i + 2) {
                        switch (arr[i]) {
                            case "-n":
                                n = Integer.parseInt(arr[i + 1]);
                                if (n > 10000 || n < 1) {
                                    System.out.println("输入的n范围错误，这是生成题目的数量，请输入大于0的整数");
                                    return;
                                }
                                break;
                            case "-r":
                                r = Integer.parseInt(arr[i + 1]);
                                if (r < 1) {
                                    System.out.println("输入的r范围错误，这是题目中的数值范围，请输入大于0的整数");
                                    return;
                                }
                                break;
                            case "-e":
                                submitPath = arr[i + 1];
                                if (submitPath == null) {
                                    System.out.println("输入错误！！！找不到题目文件的路径");
                                    return;
                                }
                                break;
                            case "-a":
                                answersPath = arr[i + 1];
                                if (answersPath == null) {
                                    System.out.println("输入错误！！！找不到答案文件的路径");
                                    return;
                                }
                                break;
                            default:
                                System.out.println("输入的指令错误！！！");
                                break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("输入的指令错误，请重新输入：");
            }

            System.out.println("n: " + n + ", r: " + r);
            FlieWR makefile = new FlieWR();
            if (submitPath != null && answersPath != null)
                makefile.createGradeFile(submitPath,answersPath);
            else
                makefile.createProblemSet(n,r);
        }
    }
}
