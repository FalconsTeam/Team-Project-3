package Model.Product;

import Model.Customer.Customer;
import Model.Customer.CustomerType;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private ProductType productType;

    public Product(Integer id, String name, ProductType productType) {
        this.id = id;
        this.name = name;
        this.productType = productType;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && productType == product.productType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productType);
    }

    @Override
    public String toString() {
        return "Клиент: " + name + " | " + "type: " + productType;
    }
}
