package spring_data_jpa.demo.service.implemetation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring_data_jpa.demo.model.Product;
import spring_data_jpa.demo.model.dto.request.ProductRequest;
import spring_data_jpa.demo.model.response.ProductResponse;
import spring_data_jpa.demo.repository.ProductRepository;
import spring_data_jpa.demo.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public void DeleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public List<ProductResponse> findAllProduct(Pageable pageable) {
        // Fetch paginated products from repository
        Page<Product> productPage = productRepository.findAll(pageable);

        // Convert Product entities to ProductResponse DTOs
        return productPage.getContent().stream()
                .map(product -> new ProductResponse(
                        product.getProductId(),
                        product.getProductName(),
                        product.getUnitPrice(),
                        product.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        // Convert ProductRequest to Product entity
        Product product = productRequest.toEntity();

        // Save the Product entity
        Product savedProduct = productRepository.save(product);

        // Return the saved product as a ProductResponse
        return savedProduct.toResponse();
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest productRequest) {

        Product product = productRequest.toEntity(id);
        // Save the updated product
        Product updatedProduct = productRepository.save(product);

        // Return the updated product as a ProductResponse
        return updatedProduct.toResponse();
    }
}
