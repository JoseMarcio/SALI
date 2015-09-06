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
@FacesValidator(value = "validaMatriculaProfessor")
public class ValidadorMatriculaProfessor implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer matricula = (Integer) value;
        ProfessorDAO professorDAO = new ProfessorDAO();
        if (matricula == 0) {
            FacesContext.getCurrentInstance().addMessage("validaMatriculaProfessor", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A matrícula não pode ser '0'.", ""));
        }
        if (professorDAO.isExisteEssaMatricula(Professor.class, Integer.toString(matricula))) {
            FacesContext.getCurrentInstance().addMessage("validaMatriculaProfessor", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Matrícula já cadastrada.", ""));
        }
    }

}
