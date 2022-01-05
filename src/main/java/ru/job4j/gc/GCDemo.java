package ru.job4j.gc;

public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        UserGC[] data = new UserGC[100000000];
        for (int i = 1; i <= data.length; i++) {
           data[i] = new UserGC(i, "User#" + i);
        }
        for (int i = data.length - 1; i > 4000000; i--) {
            data[i] = null;
        }
        for (int i = 1; i < 10000000; i++) {
            data[1000000 + i] = new UserGC(i, "User#" + i);
        }
        for (int i = data.length - 1; i > 400000; i--) {
            data[i] = null;
        }
        for (int i = 1; i < 100000; i++) {
            data[1000000 + i] = new UserGC(i, "User#" + i);
        }
        for (int i = data.length - 1; i > 400000; i--) {
            data[i] = null;
        }
        for (int i = 1; i < 100000; i++) {
            data[1000000 + i] = new UserGC(i, "User#" + i);
        }
        for (int i = data.length - 1; i > 400000; i--) {
            data[i] = null;
        }
        for (int i = 1; i < 10000000; i++) {
            data[1000000 + i] = new UserGC(i, "User#" + i);
        }
        info();
    }
}
