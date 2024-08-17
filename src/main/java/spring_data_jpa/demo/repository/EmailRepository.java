package spring_data_jpa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_data_jpa.demo.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
