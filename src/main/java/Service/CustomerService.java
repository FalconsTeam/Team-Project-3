package Service;

import Exception.CustomerNotFoundException;
import Model.Customer.Customer;
import Model.Customer.CustomerType;
import Repository.CustomerRepository;

import java.util.Map;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Метод создает обьект тип Customer с id NULL и вызывает метод добавления add() из класса CustomerRepository
     *
     * @param customerName принимает параметр типа String - имя пользователя
     * @param customerType тип принимаемого значения CustomerType(enum) NEW/REGULAR/VIP
     * @return Customer
     */
    public Customer addCustomer(String customerName, CustomerType customerType) {
        Customer customer = new Customer(null, customerName, customerType);
        customerRepository.add(customer);
        return customer;
    }

    /**
     * Метод проверят что в базе данных есть пользователи и выводит их с помощью Map.Entry
     *
     * @return LinkedHashMap
     */
    public Map<Integer, Customer> getAllCustomers() {
        if (customerRepository.getAll().isEmpty()) {
            System.out.println("Нет зарегестрированых пользователей");
        }
        for (Map.Entry<Integer, Customer> entry : customerRepository.getAll().entrySet()) {
            System.out.println("id - " + entry.getKey() + " / " + entry.getValue());
        }
        return customerRepository.getAll();
    }

    /**
     * Находит покупателя по ID
     *
     * @param id параметр типа int. Присваивается в CustomerRepository добавлении в базу.
     *           При окончании регистрации пользователю показывается его ID
     * @return Customer
     * @throws CustomerNotFoundException выбрасываемое исключение при ненахождении пользователя по его ID
     */
    public Customer getCustomerById(Integer id) throws CustomerNotFoundException {

        if (customerRepository.getById(id) == null) {
            System.out.println(new CustomerNotFoundException("Такого пользователя нет"));
            return null;
        } else {
            System.out.println(customerRepository.getById(id));
            return customerRepository.getById(id);
        }
    }
}
