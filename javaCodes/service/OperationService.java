package ru.netology.mkachulyak.Task_6_7_8_9.service;


import ru.netology.mkachulyak.Task_6_7_8_9.model.operations.Operation;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с операциями
 */
public class OperationService {

    private StorageService<Operation> operationStorageService = new StorageService<>();


    public void addNewOperation(Operation operation) {
        operationStorageService.addData(operation);
    }

    public Optional<Operation> findOperation(int operationId) {
        return operationStorageService.getAllData().stream().filter(operation -> operation.getId() == operationId)
                .findFirst();
    }

    public List<Operation> findAll() {
        return operationStorageService.getAllData();
    }

}
