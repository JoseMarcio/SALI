package br.com.sali.validacoes;

import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.regras.ProfessorRN;
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
@FacesValidator(value = "validaEmail")
public class ValidadorEmail implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        
        ProfessorRN professorRN = new ProfessorRN();
        AlunoRN alunoRN = new AlunoRN();
        InstituicaoRN instituicaoRN = new InstituicaoRN();
        
        if (professorRN.isExistenteEmail(email)) {
             FacesContext.getCurrentInstance().addMessage("validaEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));
        }
        else if(alunoRN.isExistenteEmail(email)){
            FacesContext.getCurrentInstance().addMessage("validaEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));
        }
        else if(instituicaoRN.isExistenteEmail(email)){
             FacesContext.getCurrentInstance().addMessage("validaEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));
        }
    }

}
