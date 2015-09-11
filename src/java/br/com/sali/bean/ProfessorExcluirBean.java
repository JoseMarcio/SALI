package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import javax.annotation.PostConstruct;
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

    // Atributos.
    private Professor professorSelecionado;
    private ProfessorRN professorRN;
    private boolean isProfessorSelecionado;

    
    // Construtor.
    @PostConstruct
    public void init(){
        this.professorSelecionado = new Professor();
        this.professorRN = new ProfessorRN();
        this.isProfessorSelecionado = false;
    }
    
    
    //====================== Gets e Sets =======================================

    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;
    }

    public boolean isIsProfessorSelecionado() {
        return isProfessorSelecionado;
    }

    public void setIsProfessorSelecionado(boolean isProfessorSelecionado) {
        this.isProfessorSelecionado = isProfessorSelecionado;
    }
    
    //===================== Métodos ============================================
    
    
    /**
     * Limpa os atributos da bean.
     */
    public void limpar(){
        this.professorSelecionado = new Professor();
        this.professorRN = new ProfessorRN();
        this.isProfessorSelecionado = false;
    }
    

    /**
     * Exclui o professor selecionado.
     *
     */
    public void excluir() {
        if (isIsProfessorSelecionado()) {

            if (this.professorRN.excluirProfessor(this.professorSelecionado)) {
                limpar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Professor excluido com sucesso.", ""));
                
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Não é possível excluir o professor. Ele está em " + this.professorSelecionado.getTurmas().size() + " Turmas.", ""));

            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Selecione um professor.", ""));
        }
    }

}
