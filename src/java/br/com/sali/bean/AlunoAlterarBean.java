package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.AlunoRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Alterar Aluno.
 *
 * @author SALI
 */
@ManagedBean(name = "alunoAlterarBean")
@ViewScoped
public class AlunoAlterarBean implements Serializable {

    // Atributos.
    private AlunoRN alunoRN;
    private Aluno alunoSelecionado;
    private String emailAtualAluno;
    private Integer matriculaAtualAluno;
    private String confirmaSenha;
    private boolean disabledBotaoAtualizar;

    // Construtor.
    @PostConstruct
    public void init() {
        alunoRN = new AlunoRN();
        alunoSelecionado = new Aluno();
        confirmaSenha = "";
        disabledBotaoAtualizar = true;
        emailAtualAluno = "";
        matriculaAtualAluno = 0;
    }

    //========================= Gets e Sets ====================================
    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public String getEmailAtualAluno() {
        return emailAtualAluno;
    }

    public void setEmailAtualAluno(String emailAtualAluno) {
        this.emailAtualAluno = emailAtualAluno;
    }

    public Integer getMatriculaAtualAluno() {
        return matriculaAtualAluno;
    }

    public void setMatriculaAtualAluno(Integer matriculaAtualAluno) {
        this.matriculaAtualAluno = matriculaAtualAluno;
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

    //======================= Métodos ==========================================
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
        setMatriculaAtualAluno(aluno.getMatricula());
        setEmailAtualAluno(aluno.getEmail());
        setDisabledBotaoAtualizar(false);
    }

    /**
     * É o que deve acontecer no momento em que for selecionado uma turma por
     * meio do diálodo de pesquisa de turma.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turma = (Turma) event.getObject();
        alunoSelecionado.setTurma(turma);
    }

    /**
     * Atualiza o dados do aluno no banco de dados.
     *
     */
    public void atualizar() {
        if (!ValidacoesUtil.isValidaMatricula(alunoSelecionado.getMatricula())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Informe uma matrícula válida."));

        } else if (ValidacoesUtil.isExistenteMatricula(alunoSelecionado.getMatricula())
                && (matriculaAtualAluno != alunoSelecionado.getMatricula())) {

            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Matrícula já cadastrada."));

        } else if (ValidacoesUtil.isExistenteEmail(alunoSelecionado.getEmail())
                && (!emailAtualAluno.equals(alunoSelecionado.getEmail()))) {

            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "E-mail já cadastrado."));

        } else if (!confirmaSenha.equals(alunoSelecionado.getSenha())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "As senhas não conferem."));

        } else {
            try {
                alunoRN.atualizarAluno(alunoSelecionado);
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
