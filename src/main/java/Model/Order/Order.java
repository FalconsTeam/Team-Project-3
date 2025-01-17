package Model.Order;

import Model.Customer.Customer;

import java.util.Arrays;
import java.util.Objects;

public class Order {
    private OrderStatus orderStatus;
    private Long orderId;
    private Customer customer;
    private Long customerId;
    private String[] productId;

    public String[] getProductId() {
        return productId;
    }

    public void setProductId(String[] productId) {
        this.productId = productId;
    }

    public Order(Long orderId, Long customerId, OrderStatus orderStatus, String... productId) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.productId = productId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderStatus == order.orderStatus && Objects.equals(orderId, order.orderId) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatus, orderId, customer);
    }

    @Override
    public String toString() {
        return getOrderId() + ";" + getCustomerId() + ";" + getOrderStatus() + ";" + Arrays.toString(getProductId()).replaceAll("[^0-9,]", "");
    }

    public Order(String order) {
        String[] partsOfOrder = order.split(";");
        this.orderId = Long.valueOf(partsOfOrder[0]);
        this.customerId = Long.valueOf(partsOfOrder[1]);
        this.orderStatus = OrderStatus.valueOf(partsOfOrder[2]);
        this.productId = partsOfOrder[3].split(",");
    }

}
