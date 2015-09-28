package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
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
 * Managed Bean Registrar Aluno.
 *
 * @author SALI
 */
@ManagedBean(name = "alunoRegistrarBean")
@ViewScoped
public class AlunoRegistrarBean implements Serializable {

    // Atributos.
    private Aluno aluno;
    private Usuario usuarioAluno;
    private AlunoRN alunoRN;
    private String confirmaSenha;
    private boolean disabledBotaoRegistrar;
    private String matriculaString;

    // Construtor.
    @PostConstruct
    public void init() {
        usuarioAluno = new Usuario();
        aluno = new Aluno();
        aluno.setUsuario(usuarioAluno);
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
        return aluno.getUsuario().getSenha().equals(confirmaSenha);
    }

    /**
     * Registra o aluno no banco de dados.
     *
     */
    public void registrar() {
        aluno.getUsuario().setEmail(aluno.getUsuario().getEmail().toLowerCase());

        if (ValidacoesUtil.soTemEspaco(aluno.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "O nome não pode ser vazio.", ""));

        } else if (!ValidacoesUtil.soTemLetras(aluno.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de nome inválido.", ""));

        } else if (!ValidacoesUtil.isValidaMatricula(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma matrícula válida.", ""));

        } else if (ValidacoesUtil.isExistenteMatricula(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(aluno.getUsuario().getEmail())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(aluno.getUsuario().getSenha())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(confirmaSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirma senha inválida.", ""));

        } else if (!isSenhasIguais()) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não conferem.", ""));

        } else {
            try {
                // Depois de tudo está validado, deve-se salvar o aluno.
                aluno.setMatricula(Integer.parseInt(matriculaString));
                alunoRN.registrarAluno(aluno);
                limpar();
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Registro efetuado com sucesso."));
            } catch (NoSuchAlgorithmException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }

        }
    }
}
