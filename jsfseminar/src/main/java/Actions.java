import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RequestScope
@ConversationScoped

public class Actions implements Serializable {


    //@Inject
    private Customer customer = new Customer();
    private List<Customer> customerCache;



    private Map<Integer, Boolean> customersToDelete = new HashMap<>();

    @Inject
    private CustomerService  customerService;

    @Inject
    private Conversation conversation;

    public void startConversation() {
        if ( conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if ( !conversation.isTransient()) {
            conversation.end();
        }
    }

    public String editCustomer() {
        customerService.updateCustomer(customer);
        customerCache = null;
        return "/customer-overview.xhtml";
    }

    public String saveCustomer() {
        try {
            customerService.saveCustomer(customer);
            FacesMessage facesMessage = new  FacesMessage(FacesMessage.SEVERITY_INFO, "Kunde wurde gespeichert", customer.toString());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        } catch (Exception exception) {
            FacesMessage facesMessage = new  FacesMessage(FacesMessage.SEVERITY_ERROR, "Kunde konnte  nicht gespeichert werden", customer.toString());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;

        }
        System.out.println("Actions.saveCustomer");

        return "/customer-overview.xhtml";
    }
    public String deleteCustomer() {
        customerService.deleteCustomer(customer.getId());
        customerCache.remove(customer);
        System.out.println("Actions.deleteCustomer");
        return "/customer-overview.xhtml";
    }

    public List<Customer> getAllCustomers() {
        if ( null == customerCache) {
            return customerCache = customerService.getAllCustomers();
        }
        return customerCache;
    }

    public void updateCustomer() {
        System.out.println("Actions.updateCustomer");
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Map<Integer, Boolean> getCustomersToDelete() {
        return customersToDelete;
    }

    public void setCustomersToDelete(Map<Integer, Boolean> customersToDelete) {
        this.customersToDelete = customersToDelete;
    }

    public String deleteSelectedCustomers() {
        customersToDelete.entrySet().forEach( entry -> {
            if ( entry.getValue().equals(Boolean.TRUE)) {
                customerService.deleteCustomer(entry.getKey());
            }

        });
        customerCache = null;
        return "/customer-overview.xhtml";

    }

    public List<CreditCardProvider> getAllCreditCardProviders() {
        return customerService.getAllCreditCardProviders();
    }
}
