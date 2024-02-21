package ru.netology.mkachulyak;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String transactionType;
        double amount;
        String date;

        System.out.print("Введите тип транзакции (покупка/продажа): ");
        transactionType = scanner.next();

        System.out.print("Введите сумму транзакции: ");
        amount = scanner.nextDouble();

        System.out.print("Введите дату транзакции: ");
        date = scanner.next();

        System.out.println("Информация о транзакции:");
        System.out.println("Тип: " + transactionType);
        System.out.println("Сумма: " + amount);
        System.out.println("Дата: " + date);

        scanner.close();
    }
}

