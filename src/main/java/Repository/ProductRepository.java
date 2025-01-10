package Repository;

import Model.Product.Product;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;



public class ProductRepository {
    private final LinkedHashMap<Integer, Product> productLinkedHashMap;
    private Integer productCount;

    public ProductRepository() {
        this.productLinkedHashMap = new LinkedHashMap<>();
        productCount = 0;
    }

    /**
     * Метод устанавливает товар ID основываясь на порядке добавления его в базу
     * Дальше добавляет в базу и выводит товар по его ID
     *
     * @param product объект тип Product
     * @return Product
     */
    public Product add(Product product) {
        product.setId(++productCount);
        productLinkedHashMap.put(product.getId(), product);
        System.out.println("Товар добавлен, присвоен ID - " + product.getId());
        return product;
    }

    /**
     * Выводит все товары
     *
     * @return LinkedHashMap
     */
    public Map<Integer, Product> getAll() {
        return productLinkedHashMap;
    }

    /**
     * Ищет товар по его ID
     *
     * @param id параметр типа int
     * @return Product
     */
    public Product getById(Integer id) {
        return productLinkedHashMap.get(id);
    }
}
