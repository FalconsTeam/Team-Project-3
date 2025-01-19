package Repository;

import Model.Customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerRepository {

    private final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    private final String fileIdPath;
    private final String filePath;
    private Path path;
    private Path pathId;

    public CustomerRepository(String fileIdPath, String filePath) {
        this.fileIdPath = fileIdPath;
        this.filePath = filePath;
        path = Path.of(filePath);
        pathId = Path.of(fileIdPath);
    }

    /**
     * Вызывает метод setId() и присваиват последнее значение переменной ID, роверяет наличие файла в котором
     * хранятся ID(если его нет, создает), после перезаписывает/записывает значение в этом файле.
     * Так же добавляет в соответствующий файл обьект Customer c присвоенным ему ID
     *
     * @param customer Обьект типа Customer с ID = null
     * @return Customer
     */
    public Customer add(Customer customer) {

        try {
            logger.debug("start add customer: " + customer);

            if (!Files.exists(pathId)) {

                Long id = 1L;

                Files.createFile(pathId);
                Files.write(pathId, id.toString().getBytes());
                customer.setId(id);
                Files.write(path, (customer + "\n").getBytes(), StandardOpenOption.APPEND);

                System.out.println("Покупатель добавлен, присвоен ID - " + customer.getId());

                logger.info("customer added");

            } else {
                try {

                    Long id = setId().getLast().getId();

                    Files.write(pathId, id.toString().getBytes());
                    customer.setId(++id);
                    Files.write(path, (customer + "\n").getBytes(), StandardOpenOption.APPEND);

                    System.out.println("Покупатель добавлен, присвоен ID - " + customer.getId());

                    logger.info("customer added");

                } catch (NoSuchElementException e) {

                    Long id = 1L;

                    Files.write(pathId, id.toString().getBytes());
                    customer.setId(id);
                    Files.write(path, (customer + "\n").getBytes(), StandardOpenOption.APPEND);

                    System.out.println("Покупатель добавлен, присвоен ID - " + customer.getId());

                    logger.info("customer added");

                }

            }
        } catch (IOException e) {
            logger.warn(String.valueOf(e));
        }
        return customer;
    }

    /**
     * Делает из файла с покупателями List строк
     *
     * @return List строк
     */
    public List<String> getAll() {
        try {
            logger.debug("getting list of customers");
            return Files.readAllLines(path).stream()
                    .toList();
        } catch (IOException e) {
            logger.warn(String.valueOf(e));
            return null;
        }
    }


    /**
     * Делает из файла с покупателями List, после вызывает конструктор Customer от строк и List`a, и вобратно
     * собирает в List
     *
     * @return List<>Customer</>
     */
    public List<Customer> setId() {
        try {
            return Files.readAllLines(path).stream()
                    .map(Customer::new)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
