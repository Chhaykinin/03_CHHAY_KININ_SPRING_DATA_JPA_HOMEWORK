package spring_data_jpa.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_data_jpa.demo.model.Customer;
import spring_data_jpa.demo.model.dto.request.CustomerRequest;
import spring_data_jpa.demo.model.response.APIResponse;
import spring_data_jpa.demo.service.CustomerService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<APIResponse<Customer>> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer=customerService.saveCustomer(customerRequest);
        APIResponse<Customer> response = APIResponse.<Customer> builder()
                .message("Insert customer successfully")
                .payload(customer)
                .status(HttpStatus.CREATED)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        Customer customer =customerService.updateCustomer(id,customerRequest);
        APIResponse<Customer> response = APIResponse.<Customer> builder()
                .message("update customer successful")
                .payload(customer)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> getCustomer(@PathVariable Long id) {
        Customer customer=customerService.getCustomer(id);
        APIResponse<Customer> response = APIResponse.<Customer> builder()
                .message("Find customer by id successful")
                .payload(customer)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }








    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        APIResponse<Customer> response= APIResponse.<Customer> builder()
                .message("Delete customer by "+ id +"id successfully")
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<Customer>>> getAllCustomers(@RequestParam(defaultValue = "1") Integer pageNo,
                                                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                                                      @RequestParam(defaultValue = "customerId") String sortBy,
                                                                      @RequestParam(defaultValue = "ASC") String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
        APIResponse<List<Customer>> response =APIResponse.<List<Customer>>builder()
                .status(HttpStatus.OK)
                .message("Find all customer have been successfully")
                .creationDate(new Date())
                .payload(customerService.getAllCustomers(pageable))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }
}
