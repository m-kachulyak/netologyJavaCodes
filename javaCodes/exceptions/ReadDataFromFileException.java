package ru.netology.mkachulyak.Task_6_7_8_9.exceptions;

public class ReadDataFromFileException extends OperationRuntimeException {

    private final String MESSAGE = "Exception while trying to read data from file with path %s ";

    private String path;

    public ReadDataFromFileException(String path) {
        super();
        this.path = path;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(path);
    }
}
