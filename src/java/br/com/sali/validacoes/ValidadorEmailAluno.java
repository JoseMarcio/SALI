package br.com.sali.validacoes;

import br.com.sali.regras.AlunoRN;
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
@FacesValidator(value = "validaEmailAluno")
public class ValidadorEmailAluno implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        AlunoRN alunoRN = new AlunoRN();
        if (alunoRN.isExistenteEmail(email)) {
             FacesContext.getCurrentInstance().addMessage("validaEmailAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));
        }
    }
    
    
}
