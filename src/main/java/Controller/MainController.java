package Controller;

import Repository.ProductRepository;
import Service.ProductService;

public class MainController {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);

        productController.start();
    }
}
