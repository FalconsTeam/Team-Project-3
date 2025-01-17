package Service;

import Exception.OrderNotFoundException;
import Model.Order.OrderStatus;
import Repository.CustomerRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final String ID_REGEX = "[^0-9,]";
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.customerService = new CustomerService(new CustomerRepository());
        this.productService = new ProductService(new ProductRepository());
    }


    /**Метод создает новый заказ, проверяя все вводные данные
     *
     * @param customerId int ID покупателя
     */
    public void CreateOrder(Integer customerId) {

        try {
            Scanner scanner = new Scanner(System.in);
            while (customerService.getCustomerById(Long.valueOf(customerId)) == null || customerId == 0) {
                System.out.println("Введите верное ID пользователя: ");

                customerId = scanner.nextInt();

            }
            Long input = Long.valueOf(customerId);

            System.out.println("Введите ID товаров через запяту: ");
            String prodID = scanner.next();
            String[] prodIdArr = prodID.split(",");

            for (String id : prodIdArr) {

                while (prodID.replaceAll(ID_REGEX, "").isBlank() ||
                        productService.getProductById(Integer.valueOf(id)) == null) {
                    CreateOrder(customerId);
                    return;
                }
            }

            orderRepository.create(input, prodIdArr);


        } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                 NullPointerException e) {
            System.out.println(e);
        }

    }


    /**
     * Выводит все имеющиеся заказы
     * @return Список заказов в виде строк
     */
    public List<String> getAllOrders() {
        for (String ordersStr : orderRepository.getAll()) {

            String[] ordersParts = ordersStr.split(";");

            System.out.print("Покупатель: ");
            customerService.getCustomerById(Long.parseLong(ordersParts[1]));
            System.out.println("Статус заказа: " + ordersParts[2]);
            System.out.println("-----------------------------------");
            System.out.println("Товары: ");


            for (int i = 3; i < ordersParts.length; i++) {
                String[] products = ordersParts[i].split(",");
                for (String prod : products) {
                    productService.getProductById(Integer.parseInt(prod));
                }
            }

            System.out.println("===================================");


        }

        return orderRepository.getAll();
    }

    /**
     * Меняет статус выбраного заказа
     * @param str Статус на который нужно изменить
     * @return сообщение о смене статуса
     */
    public String changeOrderStatus(String str) {
        try {
            System.out.println("Введите ID заказа: ");
            Scanner sc = new Scanner(System.in);
            Long idOrder = sc.nextLong();

            if (idOrder > orderRepository.getAll().size()) {
                System.out.println(new OrderNotFoundException("Заказ не найден"));

            } else {

                if (orderRepository.toList().get((int) (idOrder - 1)).getOrderStatus() == OrderStatus.COMPLETE) {
                    System.out.println("Невозможно изменить статус заказа");
                    return null;

                } else {

                    orderRepository.reset(str, idOrder);
                    System.out.println("Статус заказа изменен");
                    System.out.println(orderRepository.toList().get((int) (idOrder - 1)));
                    return "Новый статус заказа" + str;

                }
            }


        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return "";
    }
}