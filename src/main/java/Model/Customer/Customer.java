package Model.Customer;

import java.util.Objects;

public class Customer {

    private Integer id;
    private String name;
    private CustomerType customerType;

    public Customer(Integer id, String name, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.customerType = customerType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
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
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && customerType == customer.customerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, customerType);
    }

    @Override
    public String toString() {
        return "Клиент: " + name + " | " + "type: " + customerType;
    }
}

