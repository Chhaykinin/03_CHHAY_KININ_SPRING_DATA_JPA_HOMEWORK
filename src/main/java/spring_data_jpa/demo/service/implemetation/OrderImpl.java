package spring_data_jpa.demo.service.implemetation;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring_data_jpa.demo.model.Customer;
import spring_data_jpa.demo.model.Order;
import spring_data_jpa.demo.model.Product;
import spring_data_jpa.demo.model.ProductOrder;
import spring_data_jpa.demo.model.dto.request.OrderRequest;
import spring_data_jpa.demo.model.enums.OrderStatus;
import spring_data_jpa.demo.model.response.OrderResponse;
import spring_data_jpa.demo.repository.CustomerRepository;
import spring_data_jpa.demo.repository.OrderRepository;
import spring_data_jpa.demo.repository.ProductRepository;
import spring_data_jpa.demo.service.OrderService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    @Override
    public OrderResponse createOrder(Long customerId, OrderRequest orderRequest) {
        // Validate and process the order request
        validateOrderRequest(orderRequest);

        // Fetch customer and product from repositories
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Product product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Create Order entity
        Order order = new Order();
        order.setOrderData(LocalDateTime.now()); // Setting current date and time
        order.setTotalAmount(calculateTotalAmount(product, orderRequest.getQuantity()));
        order.setStatus(OrderStatus.PENDING); // Set initial status
        order.setCustomer(customer);

        // Create ProductOrder entities
        Set<ProductOrder> productOrders = new HashSet<>();
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setOrder(order);
        productOrder.setQuantity(orderRequest.getQuantity());
        productOrders.add(productOrder);

        order.setProductOrders(productOrders);

        // Save the order to the repository
        Order savedOrder = orderRepository.save(order);

        // Create and return OrderResponse
        return new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getOrderData(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getProductOrders().stream()
                        .map(ProductOrder::getProduct)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Order findOrderByCustomerId(Long customerId) {
        // Assuming that there's a method in your repository to find orders by customer ID
        return orderRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for customer with id: " + customerId));

    }

    @Override
    public Order updateStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    private void validateOrderRequest(OrderRequest orderRequest) {
        // Implement validation logic (e.g., check if the quantity is positive)
        if (orderRequest.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        // Additional validation logic can be added here
    }

    private Float calculateTotalAmount(Product product, Integer quantity) {
        // Assuming Product has a method getPrice() to get the product price
        return product.getUnitPrice() * quantity.floatValue();
    }
}
