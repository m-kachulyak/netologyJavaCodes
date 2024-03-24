package ru.netology.mkachulyak.Task_6_7_8_9.model.operations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.netology.mkachulyak.Task_6_7_8_9.ConsolePrintable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements ConsolePrintable, Serializable {
    private int id;
    private BigDecimal value;
    private LocalDateTime time;

    public void print(){
        System.out.println("Сумма операции : " + value + "; Время проведения операции: " + time);
    }
}
