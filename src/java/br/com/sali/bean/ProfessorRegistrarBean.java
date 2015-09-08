package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "registrarProfessorBean")
public class ProfessorRegistrarBean {

    private ProfessorRN professorRN = new ProfessorRN();
    private Professor professor = new Professor();
    private String confirmaSenha;

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

    /**
     * Verifica se a senha e a confirmação de senha são iguais.
     *
     * @return
     */
    public boolean senhasIguais() {
        return professor.getSenha().equals(confirmaSenha);
    }

    /**
     * Zera as informações dos objetos da bean.
     */
    public void limparBean() {
        confirmaSenha = "";
        professor = new Professor();
        professorRN = new ProfessorRN();
    }

    /**
     * Registra o professor informado na tela no banco de dados.
     *
     */
    public void registrar() {
        if(!ValidacoesUtil.isValidaMatricula(professor.getMatricula())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma matrícula válida.", ""));
        }
        else if (ValidacoesUtil.isExistenteMatricula(professor.getMatricula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(professor.getEmail())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));

        } else if (!senhasIguais()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "As senhas não conferem.", ""));

        } else {
            professorRN.registrarProfessor(professor);
            limparBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Professor registrado com sucesso.", ""));
        }
    }
}
