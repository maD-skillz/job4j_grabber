package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь файла.");
        new DirFileCache("C:\\Test").load(scanner.next());
    }
}

