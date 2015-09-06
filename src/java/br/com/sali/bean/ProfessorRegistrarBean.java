package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
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
     * @return 
     */
    public String registrar() {
        if (!senhasIguais()) {
            FacesContext.getCurrentInstance().addMessage("validaMatriculaProfessor", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Senhas informadas não conferem.", ""));
            return "registrar_professor";
        } else {
            professorRN.registrarProfessor(professor);
            limparBean();
            FacesContext.getCurrentInstance().addMessage("validaMatriculaProfessor", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Professor registrado com sucesso.", ""));
            return "registrar_professor";
        }
    }
}
