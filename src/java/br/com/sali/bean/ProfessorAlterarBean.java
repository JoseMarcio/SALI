package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.UnsupportedEncodingException;
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
public class ProfessorAlterarBean {

    // Atributos.
    private Professor professorSelecionado;
    private ProfessorRN professorRN;
    private String emailAtualDoProfessor;
    private Integer matriculaAtualDoProfessor;
    private String confirmaSenha;
    private boolean disabledBotaoAtualizar;
    private String matriculaString;

    // Construtor.
    @PostConstruct
    public void init() {
        professorSelecionado = new Professor();
        professorRN = new ProfessorRN();
        emailAtualDoProfessor = "";
        matriculaAtualDoProfessor = 0;
        confirmaSenha = "";
        disabledBotaoAtualizar = true;
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

    public boolean isDisabledBotaoAtualizar() {
        return disabledBotaoAtualizar;
    }

    public void setDisabledBotaoAtualizar(boolean disabledBotaoAtualizar) {
        this.disabledBotaoAtualizar = disabledBotaoAtualizar;
    }

    public String getMatriculaString() {
        return matriculaString;
    }

    public void setMatriculaString(String matriculaString) {
        this.matriculaString = matriculaString;
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
        setEmailAtualDoProfessor(professor.getEmail());
        setMatriculaString(Integer.toString(professor.getMatricula()));
        setDisabledBotaoAtualizar(false);
    }

    /**
     * Atualiza o dados do professor no banco de dados.
     */
    public void atualizar() {

        if (!ValidacoesUtil.isValidaMatricula(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma matrícula válida.", ""));

        } else if (ValidacoesUtil.isExistenteMatricula(matriculaString)
                && !(Integer.toString(matriculaAtualDoProfessor)).equals(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(professorSelecionado.getEmail())
                && (!emailAtualDoProfessor.equals(professorSelecionado.getEmail()))) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado.", ""));

        } else if (!confirmaSenha.equals(professorSelecionado.getSenha())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não conferem.", ""));

        } else {
            try {
                professorSelecionado.setMatricula(Integer.parseInt(matriculaString));
                professorRN.atualizarProfessor(professorSelecionado);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }
            limpar();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Atualização conluída com sucesso."));
        }
    }
}
