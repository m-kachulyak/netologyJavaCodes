package ru.netology.mkachulyak.Task_6_7_8_9.exceptions;

public class CustomerOperationOutOfBoundException extends OperationRuntimeException {

    private final String MESSAGE = "Exception while trying to save operation %s to customer %s.";

    private int customerId;

    private int operationId;

    public CustomerOperationOutOfBoundException(int customerId, int operationId) {
        super();
        this.customerId = customerId;
        this.operationId = operationId;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(operationId,customerId);
    }
}
