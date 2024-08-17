package spring_data_jpa.demo.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring_data_jpa.demo.model.Customer;
import spring_data_jpa.demo.model.Email;
import spring_data_jpa.demo.model.dto.request.CustomerRequest;
import spring_data_jpa.demo.repository.CustomerRepository;
import spring_data_jpa.demo.repository.EmailRepository;
import spring_data_jpa.demo.service.CustomerService;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;
    @Override
    public Customer saveCustomer(CustomerRequest customerRequest) {
       Email email=  emailRepository.save(new Email(null,customerRequest.getEmail()));
        return customerRepository.save(customerRequest.toEntity(email.getId()));
    }

    @Override
    public Customer updateCustomer(Long id, CustomerRequest customerRequest) {
        customerRepository.findById(id);
        Email email=  emailRepository.save(new Email(id,customerRequest.getEmail()));
        return customerRepository.save(customerRequest.toEntity1(email.getId()));
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).getContent();
    }
}
