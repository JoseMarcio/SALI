package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Excluir Professor.
 *
 * @author SALI
 */
@ManagedBean(name = "excluirProfessorBean")
@ViewScoped
public class ProfessorExcluirBean implements Serializable {

    // Atributos.
    private Professor professorSelecionado;
    private ProfessorRN professorRN;
    private boolean disabledBotaoExcluir;
    private boolean renderPainelInformacoes;
    private boolean renderPainelMensagem;
    private String matriculaString;

    // Construtor.
    @PostConstruct
    public void init() {
        professorSelecionado = new Professor();
        professorRN = new ProfessorRN();
        disabledBotaoExcluir = true;
        matriculaString = "";
        renderPainelInformacoes = false;
        renderPainelMensagem = true;
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

    public String getMatriculaString() {
        return matriculaString;
    }

    public void setMatriculaString(String matriculaString) {
        this.matriculaString = matriculaString;
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
        setMatriculaString(Integer.toString(professor.getMatricula()));
        setDisabledBotaoExcluir(false);
        setRenderPainelInformacoes(true);
        setRenderPainelMensagem(false);
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

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não é possível excluir este professor, pois ele é o orientador de alguma(s) turma(s).", ""));
        }

    }

}
