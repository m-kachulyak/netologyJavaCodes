package ru.netology.mkachulyak.Task_6_7_8_9.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    private int id;
    private String name;
    private String login;
}
