package ru.netology.mkachulyak.Task_6_7_8_9;

import ru.netology.mkachulyak.Task_6_7_8_9.exceptions.ReadDataFromFileException;
import ru.netology.mkachulyak.Task_6_7_8_9.exceptions.WriteDataToFileException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationService {

    public OperationData readDataFromFile (String path) {
        Path file = Paths.get(path);

        try (InputStream inputStream = Files.newInputStream(file)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (OperationData) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ReadDataFromFileException(path);
        }

    }

    public void  writeDataToFile (String path, OperationData operationData) {
        Path file = Paths.get(path);

        try (OutputStream outputStream = Files.newOutputStream(file)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(operationData);

        } catch (IOException e) {
            throw new WriteDataToFileException(path);
        }
    }

}
