package Service;

import Exception.CustomerNotFoundException;
import Model.Customer.Customer;
import Model.Customer.CustomerType;
import Repository.CustomerRepository;

import java.util.List;

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
     * Метод проверят что в базе данных есть пользователи и выводит их
     *
     * @return List<String></String>
     */
    public List<String> getAllCustomers() {
        if (customerRepository.getAll().isEmpty()) {
            System.out.println("Нет зарегестрированых пользователей");
        } else {
            customerRepository.getAll().forEach(System.out::println);
        }
        return customerRepository.getAll();
    }

    /**
     * Находит покупателя по ID.
     *
     * @param id параметр типа int.
     * @return List строк
     * @throws CustomerNotFoundException выбрасываемое исключение при ненахождении пользователя по его ID
     */
    public List<String> getCustomerById(Long id) {
        if (customerRepository.getAll() == null || customerRepository.getAll().size() < id) {
            System.out.println(new CustomerNotFoundException("Такого пользователя нет"));
            return null;
        } else {
            for (String str : customerRepository.getAll()) {
                String[] partsOfCustomers = str.split(";");
                if (Long.parseLong(partsOfCustomers[0]) == id) {
                    System.out.println(str);
                }
            }
            return customerRepository.getAll();
        }
    }
}
