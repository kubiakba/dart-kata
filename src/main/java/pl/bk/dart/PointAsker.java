package pl.bk.dart;

import java.util.Scanner;

class PointAsker {

    private final Scanner scanner;

    public PointAsker() {
        scanner = new Scanner(System.in);
    }

    public int ask() {
        return scanner.nextInt();
    }

}
