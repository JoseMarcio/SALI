package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Classe gerenciadora da view Registrar Professor.
 *
 * @author SALI
 */
@ManagedBean(name = "registrarProfessorBean")
@ViewScoped
public class ProfessorRegistrarBean {

    // Atributos.
    private Professor professor;
    private ProfessorRN professorRN;
    private String confirmaSenha;

    // Construtor da classe.
    @PostConstruct
    public void init() {
        this.professor = new Professor();
        this.professorRN = new ProfessorRN();
        this.confirmaSenha = "";
    }

    // ========================= Gets e Sets ===================================
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    
    //=============================== Métodos ==================================
    
    /**
     * Reinicia os atributos da bean.
     */
    public void limparBean() {
        this.confirmaSenha = "";
        this.professor = new Professor();
        this.professorRN = new ProfessorRN();
    }
    
    
    
    /**
     * Verifica se a senha e a confirmação de senha são iguais.
     *
     * @return
     */
    public boolean isSenhasIguais() {
        return this.professor.getSenha().equals(this.confirmaSenha);
    }

    
    

    /**
     * Registra o professor no banco de dados, de acordo com as regras definidas
     * na classe ProfessorRN.
     *
     */
    public void registrar() {
        if (!ValidacoesUtil.isValidaMatricula(this.professor.getMatricula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma matrícula válida.", ""));
        } else if (ValidacoesUtil.isExistenteMatricula(this.professor.getMatricula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(this.professor.getEmail())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));

        } else if (!isSenhasIguais()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "As senhas não conferem.", ""));

        } else {
            // Depois de tudo está validado, deve-se executar esse código.
            this.professorRN.registrarProfessor(this.professor);
            limparBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Professor registrado com sucesso.", ""));
        }
    }
}
