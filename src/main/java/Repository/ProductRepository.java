package Repository;

import Controller.ProductController;
import Model.Product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.NoSuchElementException;


public class ProductRepository {
    private Integer productCount;
    private final String filePath;
    private final Path path;
    private final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository() {
        filePath = "C:\\Users\\User\\IdeaProjects\\Team-Project-\\files\\products.txt";
        path = Path.of(filePath);
    }

    /**
     * Метод устанавливает товар ID основываясь на порядке добавления его в базу
     * Дальше добавляет в базу и выводит товар по его ID
     *
     * @param product объект тип Product
     * @return Product
     */
    public Product add(Product product) {
        logger.debug("starting add product" + product);

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                product.setId(1);
                Files.write(path, (product + "\n").getBytes(), StandardOpenOption.APPEND);
                System.out.println("Товар добавлен: id - " + product.getId());
                logger.info("added product: " + product);
            } else {
                try {

                    product.setId(getById().getLast().getId() + 1);
                    Files.write(path, (product + "\n").getBytes(), StandardOpenOption.APPEND);
                    System.out.println("Товар добавлен: id - " + product.getId());
                    logger.info("added product: " + product);
                } catch (NoSuchElementException e) {
                    product.setId(1);
                    Files.write(path, (product + "\n").getBytes(), StandardOpenOption.APPEND);
                    System.out.println("Товар добавлен: id - " + product.getId());
                    logger.info("added product: " + product);
                }
            }
        } catch (IOException e) {
            logger.warn(String.valueOf(e));
        }
        return product;
    }

    /**
     * Выводит все товары
     *
     * @return LinkedHashMap
     */
    public List<String> getAll() {
        try {
            return Files.readAllLines(path).stream()
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ищет товар по его ID
     *
     * @return Product
     */
    public List<Product> getById() {
        try {
            return Files.readAllLines(path).stream()
                    .map(Product::new)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
