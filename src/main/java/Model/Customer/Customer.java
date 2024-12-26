package Model.Customer;

import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private CustomerType customerType;

    public Customer(Long id, String name, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.customerType = customerType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return getId() + ";" + getName() + ";" + getCustomerType();
    }

    public Customer(String customerFromFile) {
        String[] parts = customerFromFile.split(";");
        this.id = Long.parseLong(parts[0]);
        this.name = parts[1];
        this.customerType = CustomerType.valueOf(parts[2]);
    }
}

