package br.com.sali.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validaMatriculaProfessor")
public class ProfessorValidaMatricula implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String email = (String) value;
        //AlunoDAO alunoDAO = new AlunoDAOImpl();  
        if (true) {
            FacesContext.getCurrentInstance().addMessage("validaMatriculaProfessor", new FacesMessage("Matrícula já pertence a um usuário."));
        }
    }
}
