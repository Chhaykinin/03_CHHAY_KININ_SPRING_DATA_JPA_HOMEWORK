
package spring_data_jpa.demo.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String Address;

    @Column(nullable = false)
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "email_id")
    private Email email;
    public Customer toResponse(){
        return new Customer(this.customerId, this.customerName, this.Address, this.phoneNumber, this.email);
    }

}
