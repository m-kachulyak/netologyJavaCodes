package ru.netology.mkachulyak.Task_6_7_8_9.service;

import ru.netology.mkachulyak.Task_6_7_8_9.model.Statement;

import java.util.List;
import java.util.stream.Collectors;

public class StatementService {

    private StorageService<Statement> statementStorageService = new StorageService<>();

    public void addOperationToClientStatement(int customerId, int operationId) {
        List<Statement> statementList = statementStorageService.getAllData();
        statementList.add(new Statement(customerId,operationId));
    }

    public List <Integer> getOperationsIdList(int clientId) {
        return statementStorageService.getAllData().stream().filter(statement -> statement.getCustomerId() == clientId)
                .map(Statement::getOperationId)
                .collect(Collectors.toList());

    }

    public List<Statement> findAll(){
        return statementStorageService.getAllData();
    }
}
