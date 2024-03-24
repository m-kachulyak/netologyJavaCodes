package ru.netology.mkachulyak.Task_6_7_8_9.model.operations;

import lombok.Data;
import ru.netology.mkachulyak.Task_6_7_8_9.ConsolePrintable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanOperation extends Operation implements ConsolePrintable, Serializable {
    private int loanId;

    public LoanOperation(int id, BigDecimal value, LocalDateTime time, int loanId) {
        super(id, value, time);
        this.loanId = loanId;
    }

    public void print() {
        super.print();
        System.out.println("Идентификатор кредитного счета " + loanId);
    }
}