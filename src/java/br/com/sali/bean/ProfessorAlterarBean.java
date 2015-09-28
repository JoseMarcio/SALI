package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Alterar Professor.
 *
 * @author SALI
 */
@ManagedBean(name = "alterarProfessorBean")
@ViewScoped
public class ProfessorAlterarBean implements Serializable {

    // Atributos.
    private Professor professorSelecionado;
    private ProfessorRN professorRN;
    private String emailAtualDoProfessor;
    private Integer matriculaAtualDoProfessor;
    private String confirmaSenha;
    private boolean renderPainelAlterarProfessor;
    private boolean renderBotaoAlterarProfessor;
    private String matriculaString;
    private boolean renderPainelMensagem;

    // Construtor.
    @PostConstruct
    public void init() {
        professorSelecionado = new Professor();
        professorRN = new ProfessorRN();
        emailAtualDoProfessor = "";
        matriculaAtualDoProfessor = 0;
        confirmaSenha = "";
        renderPainelAlterarProfessor = false;
        renderBotaoAlterarProfessor = false;
        renderPainelMensagem = true;
        matriculaString = "";

    }

    //================ Gets e Sets =============================================
    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;
    }

    public String getEmailAtualDoProfessor() {
        return emailAtualDoProfessor;
    }

    public void setEmailAtualDoProfessor(String emailAtualDoProfessor) {
        this.emailAtualDoProfessor = emailAtualDoProfessor;
    }

    public Integer getMatriculaAtualDoProfessor() {
        return matriculaAtualDoProfessor;
    }

    public void setMatriculaAtualDoProfessor(Integer matriculaAtualDoProfessor) {
        this.matriculaAtualDoProfessor = matriculaAtualDoProfessor;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public boolean isRenderPainelAlterarProfessor() {
        return renderPainelAlterarProfessor;
    }

    public void setRenderPainelAlterarProfessor(boolean renderPainelAlterarProfessor) {
        this.renderPainelAlterarProfessor = renderPainelAlterarProfessor;
    }

    public boolean isRenderPainelMensagem() {
        return renderPainelMensagem;
    }

    public void setRenderPainelMensagem(boolean renderPainelMensagem) {
        this.renderPainelMensagem = renderPainelMensagem;
    }

    public String getMatriculaString() {
        return matriculaString;
    }

    public void setMatriculaString(String matriculaString) {
        this.matriculaString = matriculaString;
    }

    public boolean isRenderBotaoAlterarProfessor() {
        return renderBotaoAlterarProfessor;
    }

    public void setRenderBotaoAlterarProfessor(boolean renderBotaoAlterarProfessor) {
        this.renderBotaoAlterarProfessor = renderBotaoAlterarProfessor;
    }

    //=========================== Métodos ======================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * É o que deve acontecer no momento em que for selecionado um professor por
     * meio do diálodo de pesquisa de professores.
     *
     * @param event
     */
    public void eventoSelecaoProfessor(SelectEvent event) {
        Professor professor = (Professor) event.getObject();
        setProfessorSelecionado(professor);
        setMatriculaAtualDoProfessor(professor.getMatricula());
        setEmailAtualDoProfessor(professor.getUsuario().getEmail());
        setMatriculaString(Integer.toString(professor.getMatricula()));
        setRenderPainelMensagem(false);
        setRenderPainelAlterarProfessor(true);
        setRenderBotaoAlterarProfessor(true);

    }

    /**
     * Atualiza o dados do professor no banco de dados.
     */
    public void atualizar() {
        professorSelecionado.getUsuario().setEmail(professorSelecionado.getUsuario().getEmail().toLowerCase());

        if (ValidacoesUtil.soTemEspaco(professorSelecionado.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "O nome não pode ser vazio.", ""));

        } else if (!ValidacoesUtil.soTemLetras(professorSelecionado.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de nome inválido.", ""));

        } else if (!ValidacoesUtil.isValidaMatricula(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma matrícula válida.", ""));

        } else if (ValidacoesUtil.isExistenteMatricula(matriculaString)
                && !(Integer.toString(matriculaAtualDoProfessor)).equals(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(professorSelecionado.getUsuario().getEmail())
                && (!emailAtualDoProfessor.equals(professorSelecionado.getUsuario().getEmail()))) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(professorSelecionado.getUsuario().getSenha())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(confirmaSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirma senha inválida.", ""));

        } else if (!confirmaSenha.equals(professorSelecionado.getUsuario().getSenha())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não conferem.", ""));

        } else {
            try {
                professorSelecionado.setMatricula(Integer.parseInt(matriculaString));
                professorRN.atualizarProfessor(professorSelecionado);
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Atualização concluída com sucesso."));
            } catch (NoSuchAlgorithmException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }

        }
    }
}
