import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class ActionsAjax {
    @Inject
    private CustomerService customerService;
    private Customer customer = new Customer();
    private List<Customer> customerCache;

    public void saveCustomer() {
        try {
            customerService.saveCustomer(customer);
            FacesMessage facesMessage = new  FacesMessage(FacesMessage.SEVERITY_INFO, "Kunde wurde gespeichert", customer.toString());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        } catch (Exception exception) {
            FacesMessage facesMessage = new  FacesMessage(FacesMessage.SEVERITY_ERROR, "Kunde konnte  nicht gespeichert werden", customer.toString());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        }
        System.out.println("ActionsAjax.saveCustomer");

    }

    public void deleteCustomer() {
        customerService.deleteCustomer(customer.getId());
        customerCache.remove(customer);
        System.out.println("Actions.deleteCustomer");
    }
    public List<Customer> getAllCustomers() {
        if ( null == customerCache) {
            return customerCache = customerService.getAllCustomers();
        }
        return customerCache;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
