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
 * Managed Bean Excluir Professor.
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
     * É o que deve acontecer no momento em que for selecionado um professor por
     * meio do diálodo de pesquisa de professores.
     *
     * @param event
     */
    public void eventoSelecaoProfessor(SelectEvent event) {
        Professor professor = (Professor) event.getObject();
        setProfessorSelecionado(professor);
        setDisabledBotaoExcluir(false);
    }

    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Exclui o professor selecionado.
     *
     */
    public void excluir() {
        if (professorRN.excluirProfessor(professorSelecionado)) {
            limpar();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Professor excluído com sucesso."));

        } else {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Não é possível excluir este professor, pois ele é o orientador de alguma(s) turma(s)."));
        }

    }

}
