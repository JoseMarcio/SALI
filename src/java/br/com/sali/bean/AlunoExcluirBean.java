package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.regras.AlunoRN;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Excluir Aluno.
 *
 * @author SALI
 */
@ManagedBean(name = "alunoExcluirBean")
@ViewScoped
public class AlunoExcluirBean {

    // Atributos.
    private AlunoRN alunoRN;
    private Aluno alunoSelecionado;
    private boolean disabledBotaoExcluir;

    // Construtor.
    @PostConstruct
    public void init() {
        alunoRN = new AlunoRN();
        alunoSelecionado = new Aluno();
        disabledBotaoExcluir = true;
    }

    //============================ Gets e Sets =================================
    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public boolean isDisabledBotaoExcluir() {
        return disabledBotaoExcluir;
    }

    public void setDisabledBotaoExcluir(boolean disabledBotaoExcluir) {
        this.disabledBotaoExcluir = disabledBotaoExcluir;
    }

    //=========================== Métodos ======================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * É o que deve acontecer no momento em que for selecionado um aluno por
     * meio do diálodo de pesquisa de alunos.
     *
     * @param event
     */
    public void eventoSelecaoAluno(SelectEvent event) {
        Aluno aluno = (Aluno) event.getObject();
        setAlunoSelecionado(aluno);
        setDisabledBotaoExcluir(false);
    }

    /**
     * Exclui o aluno selecionado.
     *
     */
    public void excluir() {
        alunoRN.excluirAluno(alunoSelecionado);
        limpar();
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sucesso!", "Aluno excluído com sucesso."));
    }
}
