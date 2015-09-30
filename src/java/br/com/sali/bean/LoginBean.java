package br.com.sali.bean;

import br.com.sali.regras.InstituicaoRN;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Login.
 *
 * @author SALI
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
    
    private String emailInformado;

    public String getEmailInformado() {
        return emailInformado;
    }

    public void setEmailInformado(String emailInformado) {
        this.emailInformado = emailInformado;
    }
    
        
    

    public void carregarInstituicao() {
        InstituicaoRN instituicaoRN = new InstituicaoRN();
    }
    
    
    public void recuperarEmail(){
    
        RequestContext fc = RequestContext.getCurrentInstance();
        fc.showMessageInDialog(new FacesMessage(emailInformado));
    }
    
    
}
