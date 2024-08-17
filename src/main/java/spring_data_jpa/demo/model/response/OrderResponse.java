package spring_data_jpa.demo.model.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_data_jpa.demo.model.Product;
import spring_data_jpa.demo.model.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDateTime orderData;
    private Float totalAmount;
    private OrderStatus status;
    private List<Product> productList;
}
