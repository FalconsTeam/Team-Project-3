package Service;

import Model.Product.Product;
import Model.Product.ProductType;
import Repository.ProductRepository;
import Exception.ProductNotFoundException;

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
    public Product addProduct(String productName, ProductType productType) {
        Product product = new Product(null, productName, productType);
        productRepository.add(product);
        return product;
    }

    /**
     * Метод проверят что в базе данных есть товары и выводит их с помощью Map.Entry
     *
     * @return LinkedHashMap
     */
    public Map<Integer, Product> getAllProducts() {
        if (productRepository.getAll().isEmpty()) {
            System.out.println("Нет зарегистрированных товаров");
        }
        for (Map.Entry<Integer, Product> entry : productRepository.getAll().entrySet()) {
            System.out.println("id - " + entry.getKey() + " / " + entry.getValue());
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
    public Product getProductById(Integer id) throws ProductNotFoundException {

        if (productRepository.getById(id) == null) {
            System.out.println(new ProductNotFoundException("Такого пользователя нет"));
            return null;
        } else {
            System.out.println(productRepository.getById(id));
            return productRepository.getById(id);
        }
    }

    private static final Logger log = Logger.getLogger(ProductService.class.getName());

}
