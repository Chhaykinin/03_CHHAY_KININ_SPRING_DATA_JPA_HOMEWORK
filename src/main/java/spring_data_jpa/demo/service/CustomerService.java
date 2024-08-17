package spring_data_jpa.demo.service;
import org.springframework.data.domain.Pageable;
import spring_data_jpa.demo.model.Customer;
import spring_data_jpa.demo.model.dto.request.CustomerRequest;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(CustomerRequest customerRequest);
    Customer updateCustomer(Long id, CustomerRequest customerRequest);

    Customer getCustomer(Long id);

    void deleteCustomer(Long id);

    List<Customer> getAllCustomers(Pageable pageable);
}
