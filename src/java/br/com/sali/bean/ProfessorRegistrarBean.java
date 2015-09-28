package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Usuario;
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

/**
 * Managed Bean Registrar Professor.
 *
 * @author SALI
 */
@ManagedBean(name = "registrarProfessorBean")
@ViewScoped
public class ProfessorRegistrarBean implements Serializable {

    // Atributos.
    private Professor professor;
    private Usuario usuarioProfessor;
    private ProfessorRN professorRN;
    private String confirmaSenha;
    private String matriculaString;

    // Construtor da classe.
    @PostConstruct
    public void init() {
        usuarioProfessor = new Usuario();
        professor = new Professor();
        professor.setUsuario(usuarioProfessor);
        professorRN = new ProfessorRN();

        confirmaSenha = "";
        matriculaString = "";
    }

    // ========================= Gets e Sets ===================================
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getMatriculaString() {
        return matriculaString;
    }

    public void setMatriculaString(String matriculaString) {
        this.matriculaString = matriculaString;
    }

    public Usuario getUsuarioProfessor() {
        return usuarioProfessor;
    }

    public void setUsuarioProfessor(Usuario usuarioProfessor) {
        this.usuarioProfessor = usuarioProfessor;
    }

    //=============================== Métodos ==================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Verifica se a senha e a confirmação de senha são iguais. Se forem iguais
     * é retornado "true", senão é retornado "false".
     *
     * @return
     */
    public boolean isSenhasIguais() {
        return professor.getUsuario().getSenha().equals(confirmaSenha);
    }

    /**
     * Registra o professor no banco de dados.
     *
     */
    public void registrar() {
        professor.getUsuario().setEmail(professor.getUsuario().getEmail().toLowerCase());

        if (ValidacoesUtil.soTemEspaco(professor.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "O nome não pode ser vazio.", ""));

        } else if (!ValidacoesUtil.isValidaMatricula(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma matrícula válida.", ""));

        } else if (ValidacoesUtil.isExistenteMatricula(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(professor.getUsuario().getEmail())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(professor.getUsuario().getSenha())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(confirmaSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirma senha inválida.", ""));

        } else if (!isSenhasIguais()) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não conferem.", ""));

        } else {
            try {
                // Depois de tudo está validado, deve-se salvar o professor.
                professor.setMatricula(Integer.parseInt(matriculaString));
                professorRN.registrarProfessor(professor);
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
