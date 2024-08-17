package spring_data_jpa.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_data_jpa.demo.model.response.ProductResponse;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int unitPrice;

    private String description;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ProductOrder> productOrders;
    public ProductResponse toResponse() {
        return new ProductResponse(
                this.productId,
                this.productName,
                this.unitPrice,
                this.description
        );
    }
}
