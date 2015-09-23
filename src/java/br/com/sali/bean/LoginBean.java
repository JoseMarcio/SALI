package br.com.sali.bean;

import br.com.sali.modelo.Instituicao;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.util.CriptografiaUtil;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Login.
 *
 * @author SALI
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    // Atributos;
    private InstituicaoRN instituicaoRN = new InstituicaoRN();
    private Instituicao instituicao = new Instituicao();

    private String email;
    private String senha;
    private String emailRecuperar;

    // Construtor.
    @PostConstruct
    public void init() {
        if (instituicaoRN.getInstituicoCadastrada() == null || instituicaoRN.getInstituicoCadastrada().getId() == 0) {
            try {
                instituicaoRN.criaInstituicao();
                email = "";
                senha = "";
                emailRecuperar = "";
                setInstituicao(instituicaoRN.getInstituicoCadastrada());
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }

        } else {
            setInstituicao(instituicaoRN.getInstituicoCadastrada());
            email = "";
            senha = "";
            emailRecuperar = "";
        }
    }

    //============================ Gest e Sets =================================
    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmailRecuperar() {
        return emailRecuperar;
    }

    public void setEmailRecuperar(String emailRecuperar) {
        this.emailRecuperar = emailRecuperar;
    }

    //============================ Métodos =====================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Autentica o usuário.
     *
     *
     * @return
     */
    public String autenticarUsuario() {
        return "/instituicao/inicio-instituicao?faces-redirect=true";
    }

    public void recuperarSenha() {

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uma nova senha de acesso foi enviada para o e-mail informado", ""));
    }
}
