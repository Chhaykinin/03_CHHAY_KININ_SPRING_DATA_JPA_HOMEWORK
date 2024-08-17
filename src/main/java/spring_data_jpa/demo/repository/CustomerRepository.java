package spring_data_jpa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data_jpa.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
