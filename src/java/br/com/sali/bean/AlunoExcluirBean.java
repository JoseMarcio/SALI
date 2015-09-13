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
 *
 * @author SALI
 */
@ManagedBean(name = "alunoExcluirBean")
@ViewScoped
public class AlunoExcluirBean {

    private AlunoRN alunoRN;
    private Aluno alunoSelecionado;
    private boolean disabledExcluir;

    @PostConstruct
    public void init() {
        alunoRN = new AlunoRN();
        alunoSelecionado = new Aluno();
        disabledExcluir = true;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public boolean isDisabledExcluir() {
        return disabledExcluir;
    }

    public void setDisabledExcluir(boolean disabledExcluir) {
        this.disabledExcluir = disabledExcluir;
    }

    //=========================== Métodos ======================================
    public void limpar() {
        alunoRN = new AlunoRN();
        alunoSelecionado = new Aluno();
        disabledExcluir = true;
    }

    public void eventoSelecaoAluno(SelectEvent event) {
        Aluno aluno = (Aluno) event.getObject();
        setAlunoSelecionado(aluno);
        setDisabledExcluir(false);
    }

    /**
     * Exclui o aluno selecionado.
     *
     */
    public void excluir() {
        alunoRN.excluirAluno(alunoSelecionado);
        limpar();
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Aluno excluido com sucesso."));
    }
}
