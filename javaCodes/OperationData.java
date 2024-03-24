package ru.netology.mkachulyak.Task_6_7_8_9;

import lombok.Getter;
import ru.netology.mkachulyak.Task_6_7_8_9.model.Customer;
import ru.netology.mkachulyak.Task_6_7_8_9.model.operations.Operation;

import java.io.Serializable;
import java.util.Arrays;

@Getter
public class OperationData implements Serializable {
    public static final int CUSTOMERS_SIZE = 5;
    public static final int OPERATIONS_SIZE = 50;
    public static final int MAX_OPERATIONS_COUNT = 1;


    private final Operation[] operations = new Operation[OPERATIONS_SIZE];
    private final Customer[] customers = new Customer[CUSTOMERS_SIZE];
    /**
     * statement[{id клиента из массива Customer[] customers}][{порядковый номер в разрезе клиента}] = id операции
     * из массива Operation[] operations ;
     */
    private final int[][] statement = new int[CUSTOMERS_SIZE][MAX_OPERATIONS_COUNT];


    @Override
    public String toString() {
        return "OperationData{" +
                "statement=" + Arrays.toString(statement) +
                '}';
    }
}
