package Controller;

import Model.Customer.CustomerType;
import Service.CustomerService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerService;
    private final String NAME_REGEX = "[^a-zA-Z]";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    /**
     * Метод который обрабатывает ввод пользователя и передает команды в сервис
     *
     * @throws InputMismatchException выбрасывает сообщения для пользователся при не верном вводе
     */
    public void start() throws InputMismatchException{
        String name;
        String typeOfCustomer;
        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=====Управление покупателями=====");
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("3. Найти покупателя по ID");
            System.out.println("4. Назад");

            input = scanner.next();

            switch (input) {
                case "1" -> {
                    System.out.println("Введите имя покупателя: ");
                    name = scanner.nextLine();
                    if (name.isBlank()) {
                        name = scanner.nextLine();
                        while (name.replaceAll(NAME_REGEX, "").isBlank()) {
                            System.out.println("Введите правильное имя покупателя: ");
                            name = scanner.nextLine();
                        }
                    }


                    System.out.println("Выберите вы: NEW/REGULAR/VIP");
                    System.out.println("NEW -     новый пользователь");
                    System.out.println("REGULAR - уже заказывали у нас");
                    System.out.println("VIP -     наш VIP клиент");
                    typeOfCustomer = scanner.next().toUpperCase();
                    switch (typeOfCustomer) {
                        case "NEW" -> customerService.addCustomer(name, CustomerType.NEW);
                        case "REGULAR" -> customerService.addCustomer(name, CustomerType.REGULAR);
                        case "VIP" -> customerService.addCustomer(name, CustomerType.VIP);
                        default -> {
                            while (!(typeOfCustomer.equals("NEW") || typeOfCustomer.equals("REGULAR") || typeOfCustomer.equals("VIP"))) {
                                System.out.println(new InputMismatchException("Не верный ввод"));
                                typeOfCustomer = scanner.nextLine().toUpperCase();
                            }
                            customerService.addCustomer(name, CustomerType.valueOf(typeOfCustomer));
                        }
                    }
                }
                case "2" -> customerService.getAllCustomers();
                case "3" -> {
                    System.out.println("Введите ID покупателя: ");
                    Long inputID = scanner.nextLong();
                    if (Character.isLetter(Math.toIntExact(inputID))) {
                        System.out.println(new InputMismatchException("Не верный ввод"));
                        inputID = scanner.nextLong();
                    }
                    customerService.getCustomerById(inputID);
                }
                case "4" -> new MainController().startController();
                default -> System.out.println(new InputMismatchException("Не верный ввод"));
            }
        }
    }
}
