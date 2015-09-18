package br.com.sali.bean;

import br.com.sali.modelo.Instituicao;
import br.com.sali.regras.InstituicaoRN;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Managed Bean Login.
 *
 * @author SALI
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {

    // Atributos;
    private InstituicaoRN instituicaoRN;
    private Instituicao instituicao;

    private String email;
    private String senha;
    private String emailRecuperar;
    
   
    

    // Construtor.
    @PostConstruct
    public void init() {
        instituicaoRN = new InstituicaoRN();
        if (instituicaoRN.getInstituicoCadastrada() == null || instituicaoRN.getInstituicoCadastrada().getId() == 0) {
            instituicaoRN.criaInstituicao();
            setInstituicao(instituicaoRN.getInstituicoCadastrada());
            email = "";
            senha = "";
            emailRecuperar = "";
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
    public void limpar(){
           init();
    }
    
 
    /**
     * Autentica o usuário.
     *
     *
     */
    public String autenticarUsuario() {
        return "inicio-instituicao";
       // FacesContext fc = FacesContext.getCurrentInstance();
       // fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Acesso Concedido!\nE-mail: " + getEmail() + "\nSenha: " + getSenha(), ""));
    }
    
    
    
    public void recuperarSenha(){
        
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uma nova senha de acesso foi enviada para o e-mail informado",""));
    }
}
