package Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cardNumber;

    private Double CreditLimit;

    @ManyToOne
    @JoinColumn(name = "Customer_Id", referencedColumnName = "id")
    Customer customer;

}
