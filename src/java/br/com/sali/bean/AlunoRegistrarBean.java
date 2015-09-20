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
 * Managed Bean Registrar Aluno.
 *
 * @author SALI
 */
@ManagedBean(name = "alunoRegistrarBean")
@ViewScoped
public class AlunoRegistrarBean implements Serializable {

    // Atributos.
    private Aluno aluno;
    private AlunoRN alunoRN;
    private String confirmaSenha;
    private boolean disabledBotaoRegistrar;
    private String matriculaString;

    // Construtor.
    @PostConstruct
    public void init() {
        aluno = new Aluno();
        alunoRN = new AlunoRN();
        confirmaSenha = "";
        disabledBotaoRegistrar = true;
        matriculaString = "";
    }

    //======================Gets e Sets=========================================
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public boolean isDisabledBotaoRegistrar() {
        return disabledBotaoRegistrar;
    }

    public void setDisabledBotaoRegistrar(boolean disabledBotaoRegistrar) {
        this.disabledBotaoRegistrar = disabledBotaoRegistrar;
    }

    public String getMatriuclaString() {
        return matriculaString;
    }

    public void setMatriuclaString(String matriuclaString) {
        this.matriculaString = matriuclaString;
    }

    //=======================Métodos============================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * É o que deve acontecer no momento em que for selecionado uma turma por
     * meio do diálodo de pesquisa de turmas.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turma = (Turma) event.getObject();
        aluno.setTurma(turma);
        setDisabledBotaoRegistrar(false);
    }

    /**
     * Verifica se a senha e a confirmação de senha são iguais. Se forem iguais
     * é retornado "true", senão é retornado "false".
     *
     * @return
     */
    public boolean isSenhasIguais() {
        return aluno.getSenha().equals(confirmaSenha);
    }

    /**
     * Registra o aluno no banco de dados.
     *
     */
    public void registrar() {
        if (!ValidacoesUtil.isValidaMatricula(matriculaString)) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Informe uma matrícula válida."));
        } else if (ValidacoesUtil.isExistenteMatricula(matriculaString)) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Matrícula já cadastrada."));

        } else if (ValidacoesUtil.isExistenteEmail(aluno.getEmail())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "E-mail já cadastrado."));

        } else if (!isSenhasIguais()) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "As senhas não conferem."));
        } else {
            try {
                // Depois de tudo está validado, deve-se salvar o aluno.
                aluno.setMatricula(Integer.parseInt(matriculaString));
                alunoRN.registrarAluno(aluno);
                limpar();
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Registro efetuado com sucesso."));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }

        }
    }
}
