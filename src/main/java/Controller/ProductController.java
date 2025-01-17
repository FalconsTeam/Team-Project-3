package Controller;

import Model.Product.ProductType;
import Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;
    private final String NAME_REGEX = "[\\s0-9]";
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

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
        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=====Управление товарами=====");
            System.out.println("1. Добавить товар");
            System.out.println("2. Показать все товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("4. Назад");
            input = scanner.next();

            switch (input) {
                case "1" -> {
                    System.out.println("Введите название товара: ");
                    name = scanner.nextLine();
                    if (name.isBlank()) {
                        name = scanner.nextLine();
                        while (name.replaceAll(NAME_REGEX, "").isBlank()) {
                            logger.warn(String.valueOf(new InputMismatchException("Не верный ввод")));
                            System.out.println("Введите правильное имя товара: ");
                            name = scanner.nextLine();
                        }
                    }

                    System.out.println("Выберите товар: FOOD/ELECTRONICS/CLOTHING");
                    System.out.println("FOOD - Продукты");
                    System.out.println("ELECTRONICS - Электроника");
                    System.out.println("CLOTHING - Одежда");
                    typeOfProduct = scanner.nextLine().toUpperCase();

                    switch (typeOfProduct) {
                        case "FOOD" -> {
                            try {

                                System.out.println("Введите цену за еденицу товара: ");
                                String price = scanner.next();
                                while (price.replaceAll("[^0-9]", "").isBlank()) {
                                    System.out.println("Введите цену за еденицу товара: ");
                                    price = scanner.next();
                                }
                                productService.addProduct(name, ProductType.FOOD, Double.parseDouble(price));
                            } catch (NumberFormatException e) {
                                logger.warn(String.valueOf(e));
                            }
                        }
                        case "ELECTRONICS" -> {
                            try {

                                System.out.println("Введите цену за еденицу товара: ");
                                String price = scanner.next();
                                while (price.replaceAll("[^0-9]", "").isBlank()) {
                                    System.out.println("Введите цену за еденицу товара: ");
                                    price = scanner.next();
                                }
                                productService.addProduct(name, ProductType.ELECTRONICS, Double.parseDouble(price));
                            } catch (NumberFormatException e) {
                                logger.warn(String.valueOf(e));
                            }
                        }
                        case "CLOTHING" -> {
                            try {

                                System.out.println("Введите цену за еденицу товара: ");
                                String price = scanner.next();
                                while (price.replaceAll("[^0-9]", "").isBlank()) {
                                    System.out.println("Введите цену за еденицу товара: ");
                                    price = scanner.next();
                                }
                                productService.addProduct(name, ProductType.CLOTHING, Double.parseDouble(price));
                            } catch (NumberFormatException e) {
                                logger.warn(String.valueOf(e));
                            }
                        }
                        default -> {
                            while (!(typeOfProduct.equalsIgnoreCase("FOOD") || typeOfProduct.equalsIgnoreCase(
                                    "ELECTRONICS") || typeOfProduct.equalsIgnoreCase("CLOTHING"))) {
                                logger.warn(String.valueOf(new InputMismatchException("Не верный ввод")));
                                typeOfProduct = scanner.nextLine().toUpperCase();
                            }
                            try {

                                System.out.println("Введите цену за еденицу товара: ");
                                String price = scanner.next();
                                while (price.replaceAll("[^0-9]", "").isBlank()) {
                                    System.out.println("Введите цену за еденицу товара: ");
                                    price = scanner.next();
                                }
                                productService.addProduct(name, ProductType.valueOf(typeOfProduct), Double.parseDouble(price));
                            } catch (NumberFormatException e) {
                                logger.warn(String.valueOf(e));
                            }
                        }
                    }
                }
                case "2" -> productService.getAllProducts();
                case "3" -> {
                    try {
                        System.out.println("Введите ID товара: ");
                        Integer inputID = scanner.nextInt();
                        productService.getProductById(inputID);
                    } catch (InputMismatchException e) {
                        logger.warn(String.valueOf(e));
                    }
                }
                case "4" -> new MainController().startController();
                default -> logger.warn(String.valueOf(new InputMismatchException("Не верный ввод")));
            }
        }
    }
}
