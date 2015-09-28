package br.com.sali.bean;

import br.com.sali.regras.InstituicaoRN;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Managed Bean Login.
 *
 * @author SALI
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    public void carregarInstituicao() {
        InstituicaoRN instituicaoRN = new InstituicaoRN();
    }
}
