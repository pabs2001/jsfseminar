import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

//@FacesConverter("cardConverter")
@RequestScoped
@Named
public class CreditCardProviderConverter implements Converter<CreditCardProvider> {

    @Inject
    private CustomerService customerService;

    @Override
    public CreditCardProvider getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return customerService.
                getAllCreditCardProviders()
                .stream()
                .filter(provider -> provider.getId().equals(Integer.parseInt(id)))
                .findFirst()
                .get();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, CreditCardProvider creditCardProvider) {
        return creditCardProvider.getId().toString();
    }
}
