package Service;

import Exception.CustomerException.CustomerNotFoundException;
import Model.Customer.Customer;
import Model.Customer.CustomerType;
import Reporitory.CustomerRepository;

import java.util.Map;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(String customerName, CustomerType customerType) {
        Customer customer = new Customer(null, customerName, customerType);
        customerRepository.add(customer);
        return customer;
    }

    public Map<Integer, Customer> getAllCustomers() {
        for (Map.Entry<Integer, Customer> entry : customerRepository.allCustomers().entrySet()) {
            System.out.println("id - " + entry.getKey() + " / " + "Имя - " + entry.getValue());
        }
        return customerRepository.allCustomers();
    }

    public Customer getCustomerById(Integer id) throws CustomerNotFoundException {
        CustomerNotFoundException customerNotFoundException = new CustomerNotFoundException("Такого пользователя нет");

        if (customerRepository.getById(id) == null) {
            System.out.println(customerNotFoundException.getMessage());
            return null ;
        } else {
            return customerRepository.getById(id);
        }
    }
}
