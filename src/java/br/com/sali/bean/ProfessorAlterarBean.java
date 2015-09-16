package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

    // Construtor.
    @PostConstruct
    public void init() {
        professorSelecionado = new Professor();
        professorRN = new ProfessorRN();
        emailAtualDoProfessor = "";
        matriculaAtualDoProfessor = 0;
        confirmaSenha = "";
        disabledBotaoAtualizar = true;

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
        setDisabledBotaoAtualizar(false);

    }

    /**
     * Atualiza o dados do professor no banco de dados.
     */
    public void atualizar() {

        if (!ValidacoesUtil.isValidaMatricula(professorSelecionado.getMatricula())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Informe uma matrícula válida."));
        } else if (ValidacoesUtil.isExistenteMatricula(professorSelecionado.getMatricula())
                && (matriculaAtualDoProfessor != professorSelecionado.getMatricula())) {

            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Matrícula já cadastrada."));

        } else if (ValidacoesUtil.isExistenteEmail(professorSelecionado.getEmail())
                && (!emailAtualDoProfessor.equals(professorSelecionado.getEmail()))) {

            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "E-mail já cadastrado."));

        } else if (!confirmaSenha.equals(professorSelecionado.getSenha())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "As senhas não conferem."));
        } else {
            professorRN.atualizarProfessor(professorSelecionado);
            limpar();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Atualização conluída com sucesso."));
        }
    }
}
