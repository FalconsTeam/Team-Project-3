package Repository;

import Model.Customer.Customer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class CustomerRepository {

    private final String fileName = "customers.txt";
    private final String fileIdPath = "C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\customers_id.txt";
    private final String filePath = "C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\" + fileName;
    private Path path;
    private Path pathId;
    private Customer customer;

    public CustomerRepository() {
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
            Long id = setId().getLast().getId();
            if (!Files.exists(pathId)) {
                Files.createFile(pathId);
            }
            Files.write(pathId, id.toString().getBytes());
            customer.setId(++id);
            Files.write(path, (customer + "\n").getBytes(), StandardOpenOption.APPEND);
            System.out.println("Покупатель добавлен, присвоен ID - " + customer.getId());
        } catch (IOException e) {
            System.out.println(e);
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
            return Files.readAllLines(path).stream()
                    .toList();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }


    /**Делает из файла с покупателями List, после вызывает конструктор Customer от строк и List`a, и вобратно
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
