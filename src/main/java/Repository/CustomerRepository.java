package Repository;

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

    /**
     * Метод устанавливает позльзователю ID сновываясь на порядке добавления его в базу
     * Дальше добавляет в базу и выводит пользователю его ID
     *
     * @param customer oбьект тип Customer
     * @return Customer
     */
    public Customer add(Customer customer) {
        customer.setId(++customerCount);
        customerLinkedHashMap.put(customer.getId(), customer);
        System.out.println("Покупатель добавлен, присвоен ID - " + customer.getId());
        return customer;
    }

    /**
     * Выводи всех пользователей
     *
     * @return LinkedHashMap
     */
    public Map<Integer, Customer> getAll() {
        return customerLinkedHashMap;
    }

    /**
     * Ищет пользователся по его ID
     *
     * @param id параметр типа int
     * @return Customer
     */
    public Customer getById(Integer id) {
        return customerLinkedHashMap.get(id);
    }
}
