package Controller;

import Repository.CustomerRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;
import Service.CustomerService;
import Service.OrderService;
import Service.ProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {
    CustomerRepository customerRepository = new CustomerRepository();
    CustomerService customerService = new CustomerService(customerRepository);
    CustomerController customerController = new CustomerController(customerService);

    ProductRepository productRepository = new ProductRepository();
    ProductService productService = new ProductService(productRepository);
    ProductController productController = new ProductController(productService);

    OrderRepository orderRepository = new OrderRepository();
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
                default -> System.out.println(new InputMismatchException("Не верный ввод"));
            }
        }
    }
}
