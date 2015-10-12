package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.TurmaRN;
import br.com.sali.regras.UsuarioRN;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Excluir Turma.
 *
 * @author SALI
 */
@ManagedBean(name = "turmaExcluirBean")
@ViewScoped
public class TurmaBeanExcluir implements Serializable {

    // Atributos.
    private Turma turmaSelecionada;
    private TurmaRN turmaRN;
    private boolean disabledBotaoExcluir;
    private boolean renderPainelInformacoes;
    private boolean renderPainelMensagem;

    // Construtor.
    @PostConstruct
    public void init() {
        turmaSelecionada = new Turma();
        turmaRN = new TurmaRN();
        disabledBotaoExcluir = true;
        renderPainelInformacoes = false;
        renderPainelMensagem = true;
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

    public boolean isRenderPainelInformacoes() {
        return renderPainelInformacoes;
    }

    public void setRenderPainelInformacoes(boolean renderPainelInformacoes) {
        this.renderPainelInformacoes = renderPainelInformacoes;
    }

    public boolean isRenderPainelMensagem() {
        return renderPainelMensagem;
    }

    public void setRenderPainelMensagem(boolean renderPainelMensagem) {
        this.renderPainelMensagem = renderPainelMensagem;
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
        setRenderPainelInformacoes(true);
        setRenderPainelMensagem(false);
    }

   
    /**
     * Exclui o professor selecionado.
     *
     */
    public void excluir() {
        if (turmaRN.excluirTurma(turmaSelecionada)) {
            limpar();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Turma excluída com sucesso."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível excluir essa turma."
                    + " Pois esta é a turma atual de Prof(a) "+turmaSelecionada.getProfessor().getNome()+".", ""));
        }

    }
}
