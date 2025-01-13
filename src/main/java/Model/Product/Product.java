package Model.Product;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private final ProductType productType;
    private Double price;

    public Product(Integer id, String name, Double price, ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && productType == product.productType && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productType, price);
    }

    @Override
    public String toString() {
        return getId() + ";" + getName() + ";" + getPrice() + ";" + getProductType();
    }

    public Product(String productStr) {
        String[] parts = productStr.split(";");
        this.id = Integer.parseInt(parts[0]);
        this.name = parts[1];
        this.price = Double.parseDouble(parts[2]);
        this.productType = ProductType.valueOf(parts[3]);
    }
}
