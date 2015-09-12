package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
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
@ManagedBean(name = "turmaRegistrarBean")
@ViewScoped
public class TurmaRegistrarBean {

    // Atributos.
    private Turma turma;
    private TurmaRN turmaRN;
    private boolean disabledBotaoRegistrar;

    // Construtor.
    @PostConstruct
    public void init() {
        turma = new Turma();
        turmaRN = new TurmaRN();
        disabledBotaoRegistrar = true;
    }

    //==========================Gets e Sets=====================================
    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public boolean isDisabledBotaoRegistrar() {
        return disabledBotaoRegistrar;
    }

    public void setDisabledBotaoRegistrar(boolean disabledBotaoRegistrar) {
        this.disabledBotaoRegistrar = disabledBotaoRegistrar;
    }

    //===========================Métodos========================================
    /**
     * Captura o professor selecionado pelo evento.
     *
     * @param event
     */
    public void eventoSelecaoProfessor(SelectEvent event) {
        Professor professorSelecionado = (Professor) event.getObject();
        turma.setProfessor(professorSelecionado);
        setDisabledBotaoRegistrar(false);
    }

    /**
     * Reinicia os atributos da classe.
     */
    public void limpar() {
        this.turma = new Turma();
        this.turmaRN = new TurmaRN();

    }

    /**
     * Registra uma nova turma no banco de dados.
     */
    public void registrar() {
        if (turmaRN.isExistenteNome(turma.getNome())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Já existe uma turma com esse nome.", ""));
        } else {
            turmaRN.registrarTurma(turma);
            limpar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Turma registrada com sucesso.", ""));
        }

    }
}
