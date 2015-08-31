
import br.com.sali.dao.ProfessorDao;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validaEmailProfessor")
public class ValidaEmailProfessor implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String email = (String) value;
        ProfessorDao professorDao = new ProfessorDao();
        if (professorDao.isExiste(email)) {
            FacesContext.getCurrentInstance().addMessage("validaEmailProfessor", new FacesMessage("Email já em uso!"));;
        }
    }
}
