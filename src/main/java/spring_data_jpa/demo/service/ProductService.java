package spring_data_jpa.demo.service;

import org.springframework.data.domain.Pageable;
import spring_data_jpa.demo.model.Product;
import spring_data_jpa.demo.model.dto.request.ProductRequest;
import spring_data_jpa.demo.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
//    Product updateProductById(Long id, ProductRequest productRequest);

//    void DeleteProductById(Long id);

    List<ProductResponse> findAllProduct(Pageable pageable);

    ProductResponse findProductById(Long id);

    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProductById(Long id, ProductRequest productRequest);

    void DeleteProductById(Long id);
}
