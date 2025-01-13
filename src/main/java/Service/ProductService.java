package Service;

import Model.Product.Product;
import Model.Product.ProductType;
import Repository.ProductRepository;
import Exception.ProductNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Метод создает объект тип Product с id NULL и вызывает метод добавления add() из класса ProductRepository
     *
     * @param productName принимает параметр типа String - имя товара
     * @param productType тип принимаемого значения ProductType(enum) FOOD/ELECTRONIC/CLOTHING
     * @return Product
     */
    public Product addProduct(String productName, ProductType productType, Double price) {
        Product product = new Product(null,productName,price,productType);
        productRepository.add(product);
        return product;
    }

    /**
     * Метод проверят что в базе данных есть товары и выводит их с помощью Map.Entry
     *
     * @return LinkedHashMap
     */
    public List<String> getAllProducts() {
        if (productRepository.getAll().isEmpty()) {
            System.out.println("Список товаров пуст!");
        } else {
            productRepository.getAll().forEach(System.out::println);
        }
        return productRepository.getAll();
    }

    /**
     * Находит товар по ID
     *
     * @param id параметр типа int. Присваивается в ProductRepository добавлении в базу.
     *           При окончании регистрации пользователю показывается ID товара
     * @return Product
     * @throws ProductNotFoundException выбрасываемое исключение при ненахождении товара по его ID
     */
    public List getProductById(Integer id) {

        if (productRepository.getAll() == null || productRepository.getAll().size() < id) {
            System.out.println(new ProductNotFoundException("Такого товара нет"));
            return null;
        } else {
            for (String str : productRepository.getAll()) {
                String[] partsOfCustomers = str.split(";");
                if (Long.parseLong(partsOfCustomers[0]) == id) {
                    System.out.println(str);
                }
            }
            return productRepository.getAll();
        }
    }

    private static final Logger log = Logger.getLogger(ProductService.class.getName());

}
