package Conroller;

import Exception.CustomerException.CustomerNotFoundException;
import Model.Customer.CustomerType;
import Reporitory.CustomerRepository;
import Service.CustomerService;

public class CustomerController {
    private CustomerService customerService;

    public static void main(String[] args) throws CustomerNotFoundException {
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);

        customerService.addCustomer("Gleb", CustomerType.NEW);
        customerService.getAllCustomers();
        customerService.getCustomerById(2);

        //Проверка работы всех методов
    }
}
