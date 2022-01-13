package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {

    private Scanner input = new Scanner(System.in);

    public void display() throws IOException {
        System.out.println("-- Actions --");
        System.out.println(
                "Select an option: \n"
                        + "  1) Для выбора пути нажмите 1.\n"
                        + "  2) Чтобы загурзить содержимое файла в кэш нажмите 2.\n"
                        + "  3) Чтобы получить содержимое файла из кэша нажмите 3.\n"
                        + "  4)Для выхода нажмите 4.\n "
        );

        int selection = input.nextInt();
        input.nextLine();

        switch (selection) {
            case 1:
                new DirFileCache("C:\\Test").load(input.next());
                break;
            case 2:
                new DirFileCache("C:\\Test").put(input.next(), input.next());
                break;
            case 3:
                new DirFileCache("C:\\Test").get(input.next());
                break;
            case 4:
                System.out.println("Выход.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }
    public static void main(String[] args) throws IOException {
        Emulator emulator = new Emulator();
        emulator.display();
    }
}

