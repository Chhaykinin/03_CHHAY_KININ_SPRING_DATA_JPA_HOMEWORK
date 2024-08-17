package spring_data_jpa.demo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false ,cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Product product;

    @ManyToOne(optional = false ,cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    @JsonBackReference
    private Order order;

    @Column(nullable = false)
    private Integer quantity;
}
