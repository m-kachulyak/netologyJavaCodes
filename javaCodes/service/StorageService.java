package ru.netology.mkachulyak.Task_6_7_8_9.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с хранением данных
 */
public class StorageService<T> {

    private List<T> dataList = new ArrayList<T>();


    public void addData(T data) {
        dataList.add(data);
    }

    public List<T> getAllData() {
        return dataList;
    }


}
