package Reporitory;

import Model.Customer.Customer;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerRepository {
    private final LinkedHashMap<Integer, Customer> customerLinkedHashMap;
    private Integer customerCount;

    public CustomerRepository() {
        this.customerLinkedHashMap = new LinkedHashMap<>();
        customerCount = 0;
    }

    public Map<Integer, Customer> add(Customer customer) {
        customer.setId(++customerCount);
        customerLinkedHashMap.put(customer.getId(), customer);
        return customerLinkedHashMap;
    }

    public Map<Integer, Customer> allCustomers() {
        return customerLinkedHashMap;
    }

    public Customer getById(Integer id) {
        return customerLinkedHashMap.get(id);
    }
}
