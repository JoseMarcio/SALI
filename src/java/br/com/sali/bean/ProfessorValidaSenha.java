package br.com.sali.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validaSenhaProfessor")
public class ProfessorValidaSenha implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String email = (String) value;
        //AlunoDAO alunoDAO = new AlunoDAOImpl();  
        if (true) {
            FacesContext.getCurrentInstance().addMessage("validaEmailProfessor", new FacesMessage("As senhas não coincidem."));
        }
    }
}