package ru.netology.mkachulyak.Task_6_7_8_9.service;

import ru.netology.mkachulyak.Task_6_7_8_9.model.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final StorageService <Customer> customerStorageService = new StorageService<>();

    public void addCustomer(Customer customer) {
        customerStorageService.addData(customer);
    }

    public Optional<Customer> findCustomer(int customerId) {
        return customerStorageService.getAllData().stream().filter(c -> c.getId() == customerId).findFirst();
    }

    public List<Customer> findAll(){
        return customerStorageService.getAllData();
    }

}
