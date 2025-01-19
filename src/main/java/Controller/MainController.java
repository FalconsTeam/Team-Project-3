package Controller;

import Repository.CustomerRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;
import Service.CustomerService;
import Service.OrderService;
import Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);


    CustomerRepository customerRepository = new CustomerRepository("C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\customers_id.txt", "C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\customers.txt");
    CustomerService customerService = new CustomerService(customerRepository);
    CustomerController customerController = new CustomerController(customerService);

    ProductRepository productRepository = new ProductRepository("C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\products.txt");
    ProductService productService = new ProductService(productRepository);
    ProductController productController = new ProductController(productService);

    OrderRepository orderRepository = new OrderRepository("C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\orders.txt");
    OrderService orderService = new OrderService(orderRepository);
    OrderController orderController = new OrderController(orderService);

    /**
     * УПРАВЛЕНИЕ ВСЕМИ УПРАВЛЕНИЯМИ
     */
    public void startController() {
        while (true) {
            System.out.println("===== Главное меню =====");
            System.out.println("1. Управление товарами");
            System.out.println("2. Управление покупателями");
            System.out.println("3. Управление заказами");
            System.out.println("4. Выход");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            switch (input) {
                case "1" -> productController.start();
                case "2" -> customerController.start();
                case "3" -> orderController.start();
                case "4" -> System.exit(0);
                default -> logger.warn(String.valueOf(new InputMismatchException("Не верный ввод")));
            }
        }
    }
}
