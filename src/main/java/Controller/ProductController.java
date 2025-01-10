package Controller;

import Model.Product.ProductType;
import Service.ProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;
    private final String NAME_REGEX = "[\\s0-9]";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Метод, который обрабатывает ввод товара и передает команды в сервис
     *
     * @throws InputMismatchException выбрасывает сообщения для пользователя при не верном вводе
     */
    public void start() throws InputMismatchException {
        String name;
        String typeOfProduct;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=====Управление товарами=====");
            System.out.println("1. Добавить товар");
            System.out.println("2. Показать все товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("4. Назад");
            Integer input = scanner.nextInt();

            switch (input) {
                case 1 -> {
                    System.out.println("Введите название товара: ");
                    name = scanner.nextLine();
                    if (name.isBlank()) {
                        name = scanner.nextLine();
                        while (name.replaceAll(NAME_REGEX, "").isBlank()) {
                            System.out.println("Введите правильное имя товара: ");
                            name = scanner.nextLine();
                        }
                    }


                    System.out.println("Выберите товар: FOOD/ELECTRONICS/CLOTHING");
                    System.out.println("FOOD - Продукты");
                    System.out.println("ELECTRONICS - Электроника");
                    System.out.println("CLOTHING - Одежда");
                    typeOfProduct = scanner.nextLine();
                    switch (typeOfProduct) {
                        case "FOOD" -> productService.addProduct(name, ProductType.FOOD, Integer.parseInt(scanner.nextLine()));
                        case "ELECTRONICS" -> productService.addProduct(name, ProductType.ELECTRONICS, Integer.parseInt(scanner.nextLine()));
                        case "CLOTHING" -> productService.addProduct(name, ProductType.CLOTHING, Integer.parseInt(scanner.nextLine()));
                        default -> {
                            while (!(typeOfProduct.equals("FOOD") || typeOfProduct.equals("ELECTRONICS") || typeOfProduct.equals("CLOTHING"))) {
                                System.out.println(new InputMismatchException("Неверный ввод"));
                                typeOfProduct = scanner.nextLine();
                            }
                            productService.addProduct(name, ProductType.valueOf(typeOfProduct), Integer.parseInt(scanner.nextLine()));
                        }
                    }
                }
                case 2 -> productService.getAllProducts();
                case 3 -> {
                    System.out.println("Введите ID товара: ");
                    Integer inputID = scanner.nextInt();
                    productService.getProductById(inputID);
                }
                case 4 -> System.exit(0);
                default -> System.out.println(new InputMismatchException().getMessage());
            }
        }
    }
}
