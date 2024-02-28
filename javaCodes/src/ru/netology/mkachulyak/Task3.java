package ru.netology.mkachulyak;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] transactionTypes = new String[5];
        double[] amounts = new double[5];
        int[] transactionIds = new int[5];
        LocalDate[] dates = new LocalDate[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Введите тип транзакции " + (i + 1) + ": ");
            transactionTypes[i] = scanner.next();

            System.out.print("Введите сумму транзакции " + (i + 1) + ": ");
            amounts[i] = scanner.nextDouble();

            System.out.print("Введите ID транзакции " + (i + 1) + ": ");
            transactionIds[i] = scanner.nextInt();

            System.out.print("Введите дату транзакции " + (i + 1) + " в формате (ГГГГ-ММ-ДД): ");
            String inputDate = scanner.next();
            dates[i] = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        System.out.println("Информация о всех транзакциях:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Транзакция " + (i + 1) + ": " + transactionTypes[i] + ", " + amounts[i] + ", " + transactionIds[i] + ", " + dates[i]);
        }

        scanner.close();
    }
}


