package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "excluirProfessorBean")
@ViewScoped
public class ProfessorExcluirBean {

    // Atributos.
    private Professor professorSelecionado;
    private ProfessorRN professorRN;
    private boolean disabledBotaoExcluir;

    // Construtor.
    @PostConstruct
    public void init() {
        this.professorSelecionado = new Professor();
        this.professorRN = new ProfessorRN();
        this.disabledBotaoExcluir = true;
    }

    //====================== Gets e Sets =======================================
    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;

    }

    public boolean isDisabledBotaoExcluir() {
        return disabledBotaoExcluir;
    }

    public void setDisabledBotaoExcluir(boolean disabledBotaoExcluir) {
        this.disabledBotaoExcluir = disabledBotaoExcluir;
    }

    //===================== Métodos ============================================
    /**
     * Captura o professor selecionado pelo evento.
     *
     * @param event
     */
    public void eventoSelecaoProfessor(SelectEvent event) {
        Professor professor = (Professor) event.getObject();
        setProfessorSelecionado(professor);
        setDisabledBotaoExcluir(false);

    }

    /**
     * Limpa os atributos da bean.
     */
    public void limpar() {
        this.professorSelecionado = new Professor();
        this.professorRN = new ProfessorRN();
        this.disabledBotaoExcluir = true;
    }

    /**
     * Exclui o professor selecionado.
     *
     */
    public void excluir() {
        if (this.professorRN.excluirProfessor(this.professorSelecionado)) {
            limpar();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Professor excluido com sucesso.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        } else {
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Não é possível excluir o professor. Ele está em "
                   + "lotado em turmas.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }

    }

}
