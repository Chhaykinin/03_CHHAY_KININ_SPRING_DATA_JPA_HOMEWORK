package spring_data_jpa.demo.model.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long productId;
    private String productName;
    private int unitPrice;
    private String description;
    public ProductResponse(Long productId, String productName, int unitPrice, String description) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.description = description;
    }
}
