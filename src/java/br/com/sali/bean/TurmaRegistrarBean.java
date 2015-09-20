package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.TurmaRN;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Registrar Turma.
 *
 * @author SALI
 */
@ManagedBean(name = "turmaRegistrarBean")
@ViewScoped
public class TurmaRegistrarBean implements Serializable {

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
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * É o que deve acontecer no momento em que for selecionado um professor por
     * meio do diálodo de pesquisa de professor.
     *
     * @param event
     */
    public void eventoSelecaoProfessor(SelectEvent event) {
        Professor professorSelecionado = (Professor) event.getObject();
        turma.setProfessor(professorSelecionado);
        setDisabledBotaoRegistrar(false);
    }

    /**
     * Registra o aluno no banco de dados.
     */
    public void registrar() {
        if (turmaRN.isExistenteNome(turma.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe uma turma com esse nome.", ""));

        } else {
            turmaRN.registrarTurma(turma);
            limpar();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Registro efetuado com sucesso."));
        }
    }
}
