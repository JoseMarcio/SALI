package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.TurmaRN;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Alterar Turma.
 *
 * @author SALI
 */
@ManagedBean(name = "turmaAlterarBean")
@ViewScoped
public class TurmaAlterarBean {

    // Atributos.
    private Turma turma;
    private boolean disabledBotaoProfessor;
    private boolean disabledBotaoAtualizar;
    private TurmaRN turmaRN;
    private String nomeAtualTurma;

    // Construtor
    @PostConstruct
    public void init() {
        turma = new Turma();
        disabledBotaoProfessor = true;
        disabledBotaoAtualizar = true;
        turmaRN = new TurmaRN();
        nomeAtualTurma = "";
    }

    //=====================Gets e Sets==========================================
    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public boolean isDisabledBotaoProfessor() {
        return disabledBotaoProfessor;
    }

    public void setDisabledBotaoProfessor(boolean disabledBotaoProfessor) {
        this.disabledBotaoProfessor = disabledBotaoProfessor;
    }

    public boolean isDisabledBotaoAtualizar() {
        return disabledBotaoAtualizar;
    }

    public void setDisabledBotaoAtualizar(boolean disabledBotaoAtualizar) {
        this.disabledBotaoAtualizar = disabledBotaoAtualizar;
    }

    public String getNomeAtualTurma() {
        return nomeAtualTurma;
    }

    public void setNomeAtualTurma(String nomeAtualTurma) {
        this.nomeAtualTurma = nomeAtualTurma;
    }

    //====================================Métodos ==============================    
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
        Turma turmaSelecionada = (Turma) event.getObject();
        setTurma(turmaSelecionada);
        setDisabledBotaoProfessor(false);
        setNomeAtualTurma(turmaSelecionada.getNome());

    }

    /**
     * É o que deve acontecer no momento em que for selecionado um professor por
     * meio do diálodo de pesquisa de professores.
     *
     * @param event
     */
    public void eventoSelecaoProfessor(SelectEvent event) {
        Professor professorSelecionado = (Professor) event.getObject();
        getTurma().setProfessor(professorSelecionado);
        setDisabledBotaoAtualizar(false);
    }

    /**
     * Atualiza o dados da turma no banco de dados.
     */
    public void atualizar() {

        if (turmaRN.isExistenteNome(turma.getNome()) && (!turma.getNome().equals(nomeAtualTurma))) {
           RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Já existe uma turma com esse nome."));
        } else {
            turmaRN.atualizarTurma(turma);
            limpar();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Atualização conluída com sucesso."));
        }
    }
}
