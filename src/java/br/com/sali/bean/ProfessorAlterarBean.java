package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "alterarProfessorBean")
public class ProfessorAlterarBean {

    // Atributos.
    private Professor professorSelecionado;
    private ProfessorRN professorRN;
    private String emailDoProfessor;
    private Integer matriculaDoProfessor;
    private String confirmaSenha;
    private boolean professorFoiSelecioado;

    // Construtor.
    @PostConstruct
    public void init() {
        this.professorSelecionado = new Professor();
        this.professorRN = new ProfessorRN();
        this.emailDoProfessor = "";
        this.matriculaDoProfessor = 0;
        this.confirmaSenha = "";
        this.professorFoiSelecioado = false;
    }

    //================ Gets e Sets =============================================
    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;
    }

    public String getEmailDoProfessor() {
        return emailDoProfessor;
    }

    public void setEmailDoProfessor(String emailDoProfessor) {
        this.emailDoProfessor = emailDoProfessor;
    }

    public Integer getMatriculaDoProfessor() {
        return matriculaDoProfessor;
    }

    public void setMatriculaDoProfessor(Integer matriculaDoProfessor) {
        this.matriculaDoProfessor = matriculaDoProfessor;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public boolean isProfessorFoiSelecioado() {
        return professorFoiSelecioado;
    }

    public void setProfessorFoiSelecioado(boolean professorFoiSelecioado) {
        this.professorFoiSelecioado = professorFoiSelecioado;
    }

    //=========================== Métodos ======================================
    /**
     * Limpar atributos da bean.
     */
    public void limpar() {
        this.professorSelecionado = new Professor();
        this.professorRN = new ProfessorRN();
        this.emailDoProfessor = "";
        this.matriculaDoProfessor = 0;
        this.confirmaSenha = "";
        this.professorFoiSelecioado = false;
    }

    /**
     * Atualiza o professor selecionado. Ou seja, é necessário um professor
     * estar selecionado para que o mesmo possa ser alterado.
     */
    public void atualizar() {
        if (!isProfessorFoiSelecioado()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Selecione um professor.", ""));
        } else {

            if (!ValidacoesUtil.isValidaMatricula(this.professorSelecionado.getMatricula())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Informe uma matrícula válida.", ""));
            } else if (ValidacoesUtil.isExistenteMatricula(this.professorSelecionado.getMatricula())
                       && (this.matriculaDoProfessor != this.professorSelecionado.getMatricula())) {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Matrícula já cadastrada.", ""));

            } else if (ValidacoesUtil.isExistenteEmail(this.professorSelecionado.getEmail())
                      && (!this.emailDoProfessor.equals(this.professorSelecionado.getEmail()) ) ) {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "E-mail já cadastrado.", ""));

            } else if (!this.confirmaSenha.equals(this.professorSelecionado.getSenha())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "As senhas não conferem.", ""));

            } else {
                // Depois de tudo está validado, deve-se executar esse código.
                this.professorRN.registrarProfessor(this.professorSelecionado);
                limpar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Dados alterados com sucesso.", ""));
            }

        }
    }
}
