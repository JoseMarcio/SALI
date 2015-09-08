package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "excluirProfessorBean")
public class ProfessorExcluirBean {

    private Professor professor = new Professor();
    private ProfessorRN professorRN = new ProfessorRN();

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void professorSelecionado(SelectEvent event) {
        Professor pf = (Professor) event.getObject();
        setProfessor(pf);
    }

    
    
    /**
     * Exclui o professor selecionado.
     * 
     * @return 
     */
    public String excluir(){
        if(professorRN.excluirProfessor(professor)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Professor excluido com sucesso.", ""));
             
             return "excluir-professor";
        }
        else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não é possível excluir o professor. Ele está em "+professor.getTurmas().size()+" Turmas.", ""));
             
             return "excluir-professor";
        }
    }

}
