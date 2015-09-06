package br.com.sali.validacoes;

import br.com.sali.dao.ProfessorDAO;
import br.com.sali.modelo.Professor;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author SALI
 */
@FacesValidator(value = "validaEmailProfessor")
public class ValidadorEmailProfessor implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        ProfessorDAO professorDAO = new ProfessorDAO();
        if (professorDAO.isExistenteEmail(Professor.class, email)) {
             FacesContext.getCurrentInstance().addMessage("validaEmailProfessor", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));
        }
    }

}
