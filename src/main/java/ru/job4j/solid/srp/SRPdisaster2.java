package ru.job4j.solid.srp;

public class SRPdisaster2 implements SrpInterface {
    @Override
    public int sumGenerate(int num) {
       return num + num;
    }

    @Override
    public void print(int sum) {
        System.out.println(sumGenerate(sum));
    }
}
