import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import java.util.Date;

@FacesComponent("UIServerDate")
public class UIServerDate extends UINamingContainer {

    public Date getServerDate() {
        return new Date();
    }
}
