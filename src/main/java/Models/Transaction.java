package Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Setter
@Getter
@Data
public class Transaction {


    @ManyToOne
    @JoinColumn(name = "Account_Id", referencedColumnName = "id")
    Account account;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private Date transactionDate;

}
