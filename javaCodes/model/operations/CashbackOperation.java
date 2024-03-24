package ru.netology.mkachulyak.Task_6_7_8_9.model.operations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.mkachulyak.Task_6_7_8_9.ConsolePrintable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbackOperation extends Operation implements ConsolePrintable, Serializable {
    private int cashbackAmount;

    public CashbackOperation(int id, BigDecimal value, LocalDateTime time, int cashbackAmount) {
        super(id, value, time);
        this.cashbackAmount = cashbackAmount;
    }

    public void print() {
        super.print();
        System.out.println("Размер кэшбека: " + cashbackAmount);
    }
}