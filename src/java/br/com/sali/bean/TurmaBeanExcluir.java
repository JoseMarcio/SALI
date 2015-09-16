package br.com.sali.bean;

import br.com.sali.modelo.Turma;
import br.com.sali.regras.TurmaRN;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Excluir Turma.
 *
 * @author SALI
 */
@ManagedBean(name = "turmaExcluirBean")
@ViewScoped
public class TurmaBeanExcluir {

    // Atributos.
    private Turma turmaSelecionada;
    private TurmaRN turmaRN;
    private boolean disabledBotaoExcluir;

    // Construtor.
    @PostConstruct
    public void init() {
        turmaSelecionada = new Turma();
        turmaRN = new TurmaRN();
        disabledBotaoExcluir = true;
    }

    //============================== Gets e Sets ===============================
    public Turma getTurmaSelecionada() {
        return turmaSelecionada;
    }

    public void setTurmaSelecionada(Turma turmaSelecionada) {
        this.turmaSelecionada = turmaSelecionada;
    }

    public boolean isDisabledBotaoExcluir() {
        return disabledBotaoExcluir;
    }

    public void setDisabledBotaoExcluir(boolean disabledBotaoExcluir) {
        this.disabledBotaoExcluir = disabledBotaoExcluir;
    }

    //============================== Métodos ===================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * É o que deve acontecer no momento em que for selecionado uma turma por
     * meio do diálodo de pesquisa de turmas.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turma = (Turma) event.getObject();
        setTurmaSelecionada(turma);
        setDisabledBotaoExcluir(false);
    }

    /**
     * Exclui o professor selecionado.
     *
     */
    public void excluir() {
        turmaRN.excluirTurma(turmaSelecionada);
        limpar();
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sucesso!", "Turma excluída com sucesso."));
    }
}
