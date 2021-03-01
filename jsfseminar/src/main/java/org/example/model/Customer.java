import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@RequestScoped
@Named
@Entity

public class Customer implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(min=3, max = 10, message = " Bitte mindestens {min} und msaximal {max} eingeben!")
    @Pattern(regexp="[a-zA-Z]*")
    private String firstName;

    @NotNull
    @Size(min=3, max = 10)
    @Pattern(regexp="[a-zA-Z]*")
    private String lastName;
    @Past
    private Date dateOfBirth;



    @ManyToOne
    private CreditCardProvider crediCardProvider;

    public Integer getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public CreditCardProvider getCrediCardProvider() {
        return crediCardProvider;
    }

    public void setCrediCardProvider(CreditCardProvider crediCardProvider) {
        this.crediCardProvider = crediCardProvider;
    }
}
