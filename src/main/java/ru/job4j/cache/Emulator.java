package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    private final Scanner input = new Scanner(System.in);

    private static final int LOAD_ACTION = 1;
    private static final int PUT_ACTION = 2;
    private static final int GET_ACTION = 3;
    private static final int EXIT_ACTION = 4;

    public void display() throws Exception {
        System.out.println("-- Actions --");
        System.out.println(
                "Select an option: \n"
                        + "  1) Для выбора пути нажмите 1.\n"
                        + "  2) Чтобы загурзить содержимое файла в кэш нажмите 2.\n"
                        + "  3) Чтобы получить содержимое файла из кэша нажмите 3.\n"
                        + "  4) Для выхода нажмите 4.\n "
        );

        int selection = input.nextInt();
        input.nextLine();
        DirFileCache dirFileCache = new DirFileCache(input.next());

        switch (selection) {
            case LOAD_ACTION:
                dirFileCache.load(input.next());
                break;
            case PUT_ACTION:
                dirFileCache.put(input.next(), input.next());
                break;
            case GET_ACTION:
                dirFileCache.get(input.next());
                break;
            case EXIT_ACTION:
                System.out.println("Выход.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }
    public static void main(String[] args) throws Exception {
        Emulator emulator = new Emulator();
        emulator.display();
    }
}

