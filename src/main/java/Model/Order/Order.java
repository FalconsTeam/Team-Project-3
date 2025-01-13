package Model.Order;

import Model.Customer.Customer;

import java.util.List;
import java.util.Objects;

public class Order {
    private OrderStatus orderStatus;
    private Long orderId;
    private Customer customer;
    private Long customerId;
//    private Product product


    public Order(Long customerId, OrderStatus orderType, List<Integer> productId) {
        this.orderStatus = orderType;
        this.customerId = customerId;
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
        return getOrderId() + ";" + getCustomerId() + ";" + getOrderStatus();
    }

}
