package ru.netology.mkachulyak.Task_6_7_8_9.service;

import ru.netology.mkachulyak.Task_6_7_8_9.OperationData;
import ru.netology.mkachulyak.Task_6_7_8_9.SerializationService;
import ru.netology.mkachulyak.Task_6_7_8_9.exceptions.CustomerOperationOutOfBoundException;
import ru.netology.mkachulyak.Task_6_7_8_9.exceptions.OperationOutOfBoundException;
import ru.netology.mkachulyak.Task_6_7_8_9.exceptions.ReadDataFromFileException;
import ru.netology.mkachulyak.Task_6_7_8_9.model.Customer;
import ru.netology.mkachulyak.Task_6_7_8_9.model.Statement;
import ru.netology.mkachulyak.Task_6_7_8_9.model.operations.CashbackOperation;
import ru.netology.mkachulyak.Task_6_7_8_9.model.operations.LoanOperation;
import ru.netology.mkachulyak.Task_6_7_8_9.model.operations.Operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class IOService {

    private final CustomerService customerService= new CustomerService();

    private final OperationService operationService = new OperationService();

    private final StatementService statementService = new StatementService();

    private final SerializationService serializationService = new SerializationService();

    private final String PATH_TO_SAVE_DATA = "operationData.txt";

    public void start() {
        loadOperationData();
        printMainInfo();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "0" -> addCustomer(scanner);
                case "1" -> addNewOperation(scanner);
                case "2" -> printOperations(scanner);
                case "3" -> saveOperationData();
                case "4" -> exit();
            }
            printMainInfo();
        }
    }

    private void printMainInfo() {
        System.out.println("Выберите команду: \n" +
                "0 - Добавить пользователя\n" +
                "1 - Добавить операцию\n" +
                "2 - Вывести список операция пользователя\n" +
                "3 - Сохранить данные\n" +
                "4 - Выход");
    }


    private int addCustomer(Scanner scanner) {
        System.out.println("Добавление нового пользователя");
        System.out.println("Введите id:");
        int i = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите имя пользователя:");
        String name = scanner.nextLine();
        System.out.println("Введите логин пользователя:");
        String login = scanner.nextLine();
        customerService.addCustomer(Customer.builder()
                .id(i)
                .name(name)
                .login(login)
                .build());
        return i;
    }


    private int addNewOperation(Scanner scanner) {
        System.out.println("Добавление новой операции");
        int userId = checkUser(scanner);
        System.out.println("Введите id операции:");
        int operationId = Integer.parseInt(scanner.nextLine());
        if (operationId < 1) {
            System.out.println("Id операции должно быть больше нуля! Повторите добавление операции");
            return -1;
        }
        System.out.println("Введите сумму перевода:");
        BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        System.out.println("Какой тип операции вы хотите добавить? \n"
                + "0 - обычная операция \n"
                + "1 - операция с кэшбеком \n"
                + "2 - кредитная операция");
        String type = scanner.nextLine();
        switch (type) {
            case "0" -> addSimpleOperation(userId, operationId, sum);
            case "1" -> {
                System.out.println("Введите размер кэшбека");
                int cashback = Integer.parseInt(scanner.nextLine());
                addCashbackOperation(userId, operationId, sum, cashback);
            }
            case "2" -> {
                System.out.println("Введите id кредитного счета");
                int loanId = Integer.parseInt(scanner.nextLine());
                addLoanOperation(userId, operationId, sum, loanId);
            }
        }


        return operationId;
    }

    private void addSimpleOperation(int userId, int operationId, BigDecimal sum) {
        try {
            operationService.addNewOperation(new Operation(operationId, sum, LocalDateTime.now()));
            statementService.addOperationToClientStatement(userId,operationId);
        } catch (OperationOutOfBoundException | CustomerOperationOutOfBoundException e) {
            System.err.println(e.getMessage());
        }

    }

    private void addCashbackOperation(int userId, int operationId, BigDecimal sum, int cashbackAmound) {
        try {
            operationService.addNewOperation(new CashbackOperation(operationId, sum, LocalDateTime.now(), cashbackAmound));
            statementService.addOperationToClientStatement(userId,operationId);
        } catch (CustomerOperationOutOfBoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void addLoanOperation(int userId, int operationId, BigDecimal sum, int loanId) {
        try {
            operationService.addNewOperation(new LoanOperation(operationId, sum, LocalDateTime.now(), loanId));
            statementService.addOperationToClientStatement(userId,operationId);
        } catch (CustomerOperationOutOfBoundException e) {
            System.err.println(e.getMessage());
        }

    }

    private int checkUser(Scanner scanner) {
        System.out.println("Введите id пользователя:");
        int i = Integer.parseInt(scanner.nextLine());
        if (customerService.findCustomer(i).isEmpty()) {
            System.out.println("Пользователь не найден! Введите новое id или создайте пользователя. Добавить нового пользователя - 0. Ввести id заново - 1");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                i = addCustomer(scanner);
            }
            if (choice == 1) {
                checkUser(scanner);
            }
        }
        return i;

    }

    private void printOperations(Scanner scanner) {
        try {
            int customerId = checkUser(scanner);
            statementService.getOperationsIdList(customerId)
                    .stream()
                    .map(operationService::findOperation)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(Operation::print);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void loadOperationData(){
        try {
            OperationData operationData = serializationService.readDataFromFile(PATH_TO_SAVE_DATA);
            Arrays.stream(operationData.getOperations()).filter(Objects::nonNull).forEach(operationService::addNewOperation);
            Arrays.stream(operationData.getCustomers()).filter(Objects::nonNull).forEach(customerService::addCustomer);
            for (int i = 0; i < operationData.getStatement().length; i++) {
                    for (int j = 0; j < operationData.getStatement()[i].length; j++) {
                        if(operationData.getStatement()[i][j] != 0){
                            statementService.addOperationToClientStatement(i, operationData.getStatement()[i][j]);
                        }
                    }
            }
        } catch (ReadDataFromFileException e){
            System.out.println("Файл ещё ни разу не был сохранен");
        }
    }
    private void saveOperationData(){
        OperationData operationData = new OperationData();
        operationService.findAll().forEach(o -> operationData.getOperations()[o.getId()] = o);
        customerService.findAll().forEach(c -> operationData.getCustomers()[c.getId()] = c);
        List<Statement> all = statementService.findAll();
        for (int i = 0; i < all.size(); i++) {
            operationData.getStatement()[all.get(i).getCustomerId()][i] = all.get(i).getOperationId();
        }
        serializationService.writeDataToFile(PATH_TO_SAVE_DATA, operationData);
    }

    private void exit() {
        System.exit(1);
    }
}
