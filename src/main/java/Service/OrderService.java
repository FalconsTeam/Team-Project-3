package Service;

import Model.Order.OrderStatus;
import Repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public void CreateOrder(Long id) {
    }

    public void getAllOrders() {

    }

    public OrderStatus changeOrderStatus() {
        return null;
    }
}
