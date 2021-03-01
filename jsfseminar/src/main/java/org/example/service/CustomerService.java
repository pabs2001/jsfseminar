import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class CustomerService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveCustomer( Customer customer) {
        entityManager.persist(customer);

    }

    public void deleteCustomer( Integer id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);

    }

    public void updateCustomer(Customer customer) {
        entityManager.merge( customer);

    }

    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public List<CreditCardProvider>  getAllCreditCardProviders() {
        return entityManager.createQuery("SELECT c FROM CreditCardProvider c", CreditCardProvider.class).getResultList();
    }
}
