package br.com.sali.bean;

import br.com.sali.dao.ProfessorDao;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validaMatProf")
public class ProfessorValidaMatricula implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        int matricula = (int) value;
        ProfessorDao professorDao = new ProfessorDao();
        if (professorDao.isExisteMatricula(matricula)) {
            FacesContext.getCurrentInstance().addMessage("validaMatProf", new FacesMessage("Matrícula já pertence a um usuário."));
        }
    }
}
