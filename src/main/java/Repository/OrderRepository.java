package Repository;

import Model.Customer.Customer;
import Model.Order.Order;
import Model.Order.OrderStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OrderRepository {
    private Order order;
    private Customer customer;
    private Path path;
    private String filePath = "C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\orders.txt";

    public OrderRepository(Path path) {
        path = Path.of(filePath);
    }

    public Order create(Long id, List<Integer> idList) {
//        order = new Order(id, OrderStatus.NEW /*, idList*/); //TODO принимать List с ID товаров
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, (order + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public List toList(Path path) {
        try {
            return Files.readAllLines(path).stream()
//                    .map(Customer::new) //TODO переобразовывать файл с продуктами в List
//
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
