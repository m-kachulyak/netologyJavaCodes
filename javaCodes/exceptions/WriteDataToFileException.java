package ru.netology.mkachulyak.Task_6_7_8_9.exceptions;

public class WriteDataToFileException extends OperationRuntimeException {

    private final String MESSAGE = "Exception while trying to write data in file with path %s ";

    private String path;

    public WriteDataToFileException(String path) {
        super();
        this.path = path;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(path);
    }
}
