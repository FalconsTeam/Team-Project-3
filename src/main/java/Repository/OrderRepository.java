package Repository;

import Model.Order.Order;
import Model.Order.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderRepository {
    private final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private Path path;
    private String filePath;

    public OrderRepository(String filePath) {
        this.filePath = filePath;
        path = Path.of(filePath);
    }


    /**
     * Создает новый заказ и записывает его в файл orders.txt
     * @param customerId ID покупателя
     * @param idArr массив типа String в котором хранятся ID продуктов
     * @return
     */
    public Order create(Long customerId, String[] idArr) {

        logger.debug("start creating order");

        try {

            try {
                Order order = new Order((toList().getLast().getOrderId() + 1), customerId, OrderStatus.NEW, idArr);
                if (!Files.exists(path)) {
                    Files.createFile(path);
                }

                Files.write(path, (order + "\n").getBytes(), StandardOpenOption.APPEND);
                System.out.println("Оформлен новый заказ: " + order);

                logger.info("finish creating order: " + order);

            } catch (NoSuchElementException e) {
                Order order = new Order(1L, customerId, OrderStatus.NEW, idArr);

                Files.write(path, (order + "\n").getBytes(), StandardOpenOption.APPEND);
                System.out.println("Оформлен новый заказ: " + order);

                logger.info("finish creating order: " + order);
            }

        } catch (IOException e) {
            logger.warn(String.valueOf(e));
        }
        return null;
    }

    /**
     * Преобразовует файл в список строк
     * @return Список String
     */
    public List<String> getAll() {
        try {

            logger.debug("getting all orders");

            return Files.readAllLines(path).stream()
                    .toList();

        } catch (IOException e) {
            logger.warn(String.valueOf(e));
            return null;
        }
    }


    /**
     * Преобразовует файл в список заказов <Order>
     * @return Список заказов <Order>
     */
    public List<Order> toList() {
        try {

            return Files.readAllLines(path).stream()
                    .map(Order::new)
                    .toList();

        } catch (IOException e) {
            logger.warn(String.valueOf(e));
            return null;
        }
    }


    /**
     * Метод меняет статус заказа
     * @param str Новый статус в виде строки
     * @param idOrder ID заказа
     */
    public void reset(String str, Long idOrder) {
        try {

            logger.debug("starting reset order status to: " + str);

            List<String> listString = List.copyOf(getAll());
            Files.delete(path);
            Files.createFile(path);

            for (Long i = 0L; i < listString.size(); i++) {

                if (idOrder.equals(i+1)) {

                    String[] firstPart = listString.get(Math.toIntExact(i)).split(";");
                    firstPart[2] = str;
                    String finalPart = String.join(";", firstPart);

                    Files.write(path, (finalPart + "\n").getBytes(), StandardOpenOption.APPEND);

                } else {

                    Files.write(path, (listString.get(Math.toIntExact(i)) + "\n").getBytes(), StandardOpenOption.APPEND);

                }
            }

            logger.info("new order status: " + str);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
