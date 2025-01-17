package Service;

import Exception.CustomerNotFoundException;
import Model.Customer.Customer;
import Model.Customer.CustomerType;
import Repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

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
        logger.debug("start add customer: " + customerName);
        Customer customer = new Customer(null, customerName, customerType);
        customerRepository.add(customer);
        logger.info("added customer: " + customer);
        return customer;
    }

    /**
     * Метод проверят что в базе данных есть пользователи и выводит их
     *
     * @return List<String></String>
     */
    public List<String> getAllCustomers() {
        logger.debug("getting all customers");
        if (customerRepository.getAll().isEmpty()) {
            System.out.println("Нет зарегестрированых пользователей");
        } else {
            customerRepository.getAll().forEach(System.out::println);
        }
        logger.info("finish getting customers");
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
        logger.debug("finding customer by ID: " + id);
        if (customerRepository.getAll() == null || customerRepository.getAll().size() < id) {
            logger.warn(String.valueOf(new CustomerNotFoundException("Такого пользователя нет")));
            return null;
        } else {
            for (String str : customerRepository.getAll()) {
                String[] partsOfCustomers = str.split(";");
                if (Long.parseLong(partsOfCustomers[0]) == id) {
                    System.out.println(str);
                    logger.info("finishing finding customer by ID: " + id);
                }
            }
            return customerRepository.getAll();
        }
    }
}
