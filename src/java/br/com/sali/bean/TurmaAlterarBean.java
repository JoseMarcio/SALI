package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.TurmaRN;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "turmaAlterarBean")
@ViewScoped
public class TurmaAlterarBean {

    // Atributos.
    private Turma turma;
    private boolean disabledProfessor;
    private boolean disabledAtualizar;
    private TurmaRN turmaRN;
    private String nomeAtualTurma;

    // Construtor
    @PostConstruct
    public void init() {
        turma = new Turma();
        disabledProfessor = true;
        disabledAtualizar = true;
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

    public boolean isDisabledProfessor() {
        return disabledProfessor;
    }

    public void setDisabledProfessor(boolean disabledProfessor) {
        this.disabledProfessor = disabledProfessor;
    }

    public boolean isDisabledAtualizar() {
        return disabledAtualizar;
    }

    public void setDisabledAtualizar(boolean disabledAtualizar) {
        this.disabledAtualizar = disabledAtualizar;
    }

    public String getNomeAtualTurma() {
        return nomeAtualTurma;
    }

    public void setNomeAtualTurma(String nomeAtualTurma) {
        this.nomeAtualTurma = nomeAtualTurma;
    }

    //====================================Métodos ==============================    
    /**
     * Captura o professor selecionado pelo evento.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turmaSelecionada = (Turma) event.getObject();
        setTurma(turmaSelecionada);
        setDisabledProfessor(false);
        setNomeAtualTurma(turmaSelecionada.getNome());

    }

    public void limpar() {
        disabledAtualizar = true;
        disabledProfessor = true;
        turma = new Turma();
        turmaRN = new TurmaRN();
    }

    /**
     * Captura o professor selecionado pelo evento.
     *
     * @param event
     */
    public void eventoSelecaoProfessor(SelectEvent event) {
        Professor professorSelecionado = (Professor) event.getObject();
        getTurma().setProfessor(professorSelecionado);
        setDisabledAtualizar(false);
    }

    public void atualizar() {

        if (turmaRN.isExistenteNome(turma.getNome()) && (!turma.getNome().equals(nomeAtualTurma))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Já existe uma turma com esse nome.", ""));
        } else {
            turmaRN.atualizarTurma(turma);
            limpar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Dados alterados com sucesso.", ""));
        }
    }
}
