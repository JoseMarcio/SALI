package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.TurmaRN;
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
@ManagedBean(name = "turmaExcluirBean")
@ViewScoped
public class TurmaBeanExcluir {

    // Atributos.
    private Turma turmaSelecionado;
    private TurmaRN turmaRN;
    private boolean disabledBotaoExcluir;

    @PostConstruct
    public void init() {
        turmaSelecionado = new Turma();
        turmaRN = new TurmaRN();
        disabledBotaoExcluir = true;
    }

    public Turma getTurmaSelecionado() {
        return turmaSelecionado;
    }

    public void setTurmaSelecionado(Turma turmaSelecionado) {
        this.turmaSelecionado = turmaSelecionado;
    }

    public boolean isDisabledBotaoExcluir() {
        return disabledBotaoExcluir;
    }

    public void setDisabledBotaoExcluir(boolean disabledBotaoExcluir) {
        this.disabledBotaoExcluir = disabledBotaoExcluir;
    }

    //====================================Gets e Sets===========================
    /**
     * Captura o professor selecionado pelo evento.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turma = (Turma) event.getObject();
        setTurmaSelecionado(turma);
        setDisabledBotaoExcluir(false);

    }

    /**
     * Limpa os atributos da bean.
     */
    public void limpar() {
        turmaSelecionado = new Turma();
        turmaRN = new TurmaRN();
        disabledBotaoExcluir = true;
    }

    /**
     * Exclui o professor selecionado.
     *
     */
    public void excluir() {
        turmaRN.excluirTurma(turmaSelecionado);
        limpar();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Turma excluida com sucesso.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);

    }
}
