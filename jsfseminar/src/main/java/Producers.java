import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.Date;

@RequestScoped
public class Producers {

    @Produces
    @Named("actions")
    @ConversationScoped
    public Actions createTestAction(@New Actions actions) {
        if (actions.getCustomer().getFirstName() == null) {
            actions.getCustomer().setFirstName("Rudi");
        }
        if (actions.getCustomer().getLastName() == null) {
            actions.getCustomer().setLastName("Ratlos");
        }
        if (actions.getCustomer().getDateOfBirth() == null) {
            actions.getCustomer().setDateOfBirth(new Date());
        }
        return actions;
    }
}
