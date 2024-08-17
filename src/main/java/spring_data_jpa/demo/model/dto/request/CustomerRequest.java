package spring_data_jpa.demo.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_data_jpa.demo.model.Customer;
import spring_data_jpa.demo.model.Email;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private String customerName;

    private String Address;

    private String phoneNumber;
    private String email;
    public Customer toEntity(long  id){
        return new Customer(null, this.customerName, this.Address, this.phoneNumber, new Email(id, this.email));
    }
    public Customer toEntity1(long  id){
        return new Customer(id, this.customerName, this.Address, this.phoneNumber, new Email(id, this.email));
    }

}
