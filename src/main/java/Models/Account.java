package Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount ;

    private Integer AccountNumber;

    @ManyToOne
    @JoinColumn(name = "Customer_Id", referencedColumnName = "id")
    Customer customer;

}
