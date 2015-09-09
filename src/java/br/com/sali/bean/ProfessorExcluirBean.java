package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "excluirProfessorBean")
public class ProfessorExcluirBean {

    private Professor professor = new Professor();
    private ProfessorRN professorRN = new ProfessorRN();
    private boolean temProfessorSelecionado = false;

    public ProfessorExcluirBean() {
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * lIMPRA BEAN
     */
    public void limpar() {
        professor = new Professor();
        professorRN = new ProfessorRN();
    }

    public void selecionarProfessor(Professor professor) {
        this.professor = professor;
        temProfessorSelecionado = true;
    }
    
    public void chooseCar() {
        RequestContext.getCurrentInstance().openDialog("pesquisar_professor");
    }

    /**
     * Exclui o professor selecionado.
     *
     */
    public void excluir() {
        if (temProfessorSelecionado) {

            if (professorRN.excluirProfessor(professor)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Professor excluido com sucesso.", ""));
                limpar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Não é possível excluir o professor. Ele está em " + professor.getTurmas().size() + " Turmas.", ""));

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Nenhum professsor selecionado.", ""));
        }
    }

}
