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
@FacesValidator(value = "validaMatriculaAluno")
public class ValidaMatriculaAluno implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer matricula = (Integer) value;
        AlunoRN alunoRN = new AlunoRN();
        if (matricula == 0) {
            FacesContext.getCurrentInstance().addMessage("validaMatriculaAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A matrícula não pode ser '0'.", ""));
        }
        if (alunoRN.isExisteEssaMatricula(Integer.toString(matricula))) {
            FacesContext.getCurrentInstance().addMessage("validaMatriculaAluno", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Matrícula já cadastrada.", ""));
        }
    }
}
