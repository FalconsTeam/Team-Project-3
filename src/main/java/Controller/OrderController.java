package Controller;

import Service.OrderService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    /**
     * Метод который обрабатывает ввод пользователя и передает команды в сервис
     *
     * @throws InputMismatchException выбрасывает сообщения для пользователся при не верном вводе
     */
    public void start() {

        while (true) {
            System.out.println("===== Управление заказами =====");
            System.out.println("1. Создать заказ");
            System.out.println("2. Показать все заказы");
            System.out.println("3. Изменить статус заказа");
            System.out.println("4. Назад");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.next().toUpperCase();

            switch (input) {

                case "1" -> {
                    System.out.println("Введите ID покупателя: ");
                    try {
                        input = scanner.next();
                        orderService.CreateOrder(Integer.valueOf(input));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    start();
                }
                case "2" -> orderService.getAllOrders();
                case "3" -> {
                    System.out.println("Введите новый статус: ");
                    do {
                        input = scanner.next().toUpperCase();
                        if (!(input.equals("NEW") || input.equals("PROCESSING") || input.equals("CANCELLED") || input.equals(
                                "COMPLETE"))) {
                            System.out.println("Невозможно изменить статус заказа");
                            start();
                        }

                        orderService.changeOrderStatus(input);
                        start();
                    } while ((input = scanner.nextLine().toUpperCase()).isBlank());
                    start();
                }
                case "4" -> new MainController().startController();
                default -> System.out.println(new InputMismatchException("Не верный ввод"));
            }
        }
    }
}
