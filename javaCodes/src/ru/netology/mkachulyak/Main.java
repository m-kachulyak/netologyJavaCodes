//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package ru.netology.mkachulyak;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Представьтесь, пожалуйста: ");
        String userName = scanner.next();
        System.out.println("Приятно познакомиться, " + userName);
        scanner.close();
    }
}