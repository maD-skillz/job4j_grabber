package ru.job4j.design.isp;

public class PrintMenu implements MenuPrinter {


    @Override
    public void print(Menu menu) {
        System.out.println(menu);
    }
}
