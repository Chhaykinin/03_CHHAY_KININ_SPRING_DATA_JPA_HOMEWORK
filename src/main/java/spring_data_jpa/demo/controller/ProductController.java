package spring_data_jpa.demo.controller;

import lombok.AllArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_data_jpa.demo.model.Product;
import spring_data_jpa.demo.model.dto.request.ProductRequest;
import spring_data_jpa.demo.model.response.APIResponse;
import spring_data_jpa.demo.model.response.ProductResponse;
import spring_data_jpa.demo.service.ProductService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<APIResponse<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse=productService.addProduct(productRequest);
        APIResponse<ProductResponse> response = APIResponse.<ProductResponse> builder()
                .message("A new product is inserted successfully.")
                .status(HttpStatus.CREATED)
                .payload(productResponse)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> findProductById(@PathVariable Long id){
        ProductResponse pro= productService.findProductById(id);
        APIResponse<ProductResponse> response= APIResponse.<ProductResponse> builder()
                .message("Get product with id "+ id +" successfully.")
                .payload(pro)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> updateProductById(@PathVariable Long id,@RequestBody ProductRequest productRequest){
        ProductResponse pro = productService.updateProductById(id,productRequest);
        APIResponse<ProductResponse> response= APIResponse.<ProductResponse> builder()
                .message("update product by id successfully")
                .payload(pro)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> DeleteProductById(@PathVariable Long id){
        productService.DeleteProductById(id);
        APIResponse<ProductResponse> response= APIResponse.<ProductResponse> builder()
                .message("Delete product by "+id+" successfully")
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<ProductResponse>>> findAllProduct(@RequestParam(defaultValue = "1") Integer pageNo,
                                                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                                                             @RequestParam(defaultValue = "productId") String sortBy,
                                                                             @RequestParam(defaultValue = "ASC") String sortDirection
                                                                      ){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        APIResponse<List<ProductResponse>> response=APIResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .message("Find all products successfully")
                .payload(productService.findAllProduct(pageable))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
