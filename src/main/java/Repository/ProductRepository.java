package Repository;

import Model.Product.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;


public class ProductRepository {
    private Integer productCount;
    private final String filePath;
    private final Path path;

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
        product.setId(getById().getLast().getId() + 1);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, (product + "\n").getBytes(), StandardOpenOption.APPEND);
            System.out.println("Товар добавлен: id - " + product.getId());
        } catch (IOException e) {
            System.out.println(e);
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
