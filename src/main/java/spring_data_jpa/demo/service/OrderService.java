package spring_data_jpa.demo.service;
import org.springframework.stereotype.Service;
import spring_data_jpa.demo.model.Order;
import spring_data_jpa.demo.model.dto.request.OrderRequest;
import spring_data_jpa.demo.model.enums.OrderStatus;
import spring_data_jpa.demo.model.response.OrderResponse;

@Service
public interface OrderService {
    OrderResponse createOrder(Long customerId, OrderRequest orderRequest);

    Order findOrderById(Long id);

    Order findOrderByCustomerId(Long customerId);

    Order updateStatus(Long orderId, OrderStatus status);
}
