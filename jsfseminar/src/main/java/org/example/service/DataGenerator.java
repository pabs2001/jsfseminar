import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DataGenerator {

    @PersistenceContext
    private EntityManager entityManager;


    @PostConstruct
    public void initData() {
        if (entityManager.createQuery("FROM CreditCardProvider").getResultList().isEmpty()) {

            CreditCardProvider crediCardProvider01 = new CreditCardProvider();
            crediCardProvider01.setProviderName("American Express");
            crediCardProvider01.setProductName("American Express Card");
            entityManager.persist(crediCardProvider01);

            CreditCardProvider crediCardProvider02 = new CreditCardProvider();
            crediCardProvider02.setProviderName("VISA Inc");
            crediCardProvider02.setProductName("Visa Card");
            entityManager.persist(crediCardProvider02);

            CreditCardProvider crediCardProvider03 = new CreditCardProvider();
            crediCardProvider03.setProviderName("Mastercard");
            crediCardProvider03.setProductName("Master Card");
            entityManager.persist(crediCardProvider03);
        }

    }
}
