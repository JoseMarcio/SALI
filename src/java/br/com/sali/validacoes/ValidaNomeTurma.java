package br.com.sali.validacoes;

import br.com.sali.regras.TurmaRN;
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
@FacesValidator(value = "validaNomeTurma")
public class ValidaNomeTurma implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String nomeTurma = (String) value;
        TurmaRN turmaRN = new TurmaRN();
        if(turmaRN.isExistenteNome(nomeTurma)){
             FacesContext.getCurrentInstance().addMessage("validaNomeTurma", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Nome de turma já em uso.", ""));
        }        
    }
}
