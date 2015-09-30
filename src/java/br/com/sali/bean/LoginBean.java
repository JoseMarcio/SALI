package br.com.sali.bean;

import br.com.sali.regras.InstituicaoRN;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Managed Bean Login.
 *
 * @author SALI
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

    public void carregarInstituicao() {
        InstituicaoRN instituicaoRN = new InstituicaoRN();
    }
    
    
}
