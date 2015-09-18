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
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Registrar Professor.
 *
 * @author SALI
 */
@ManagedBean(name = "registrarProfessorBean")
@ViewScoped
public class ProfessorRegistrarBean {

    // Atributos.
    private Professor professor;
    private ProfessorRN professorRN;
    private String confirmaSenha;

    // Construtor da classe.
    @PostConstruct
    public void init() {
        professor = new Professor();
        professorRN = new ProfessorRN();
        confirmaSenha = "";
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
        return professor.getSenha().equals(confirmaSenha);
    }

    /**
     * Registra o professor no banco de dados.
     *
     */
    public void registrar() {
        if (!ValidacoesUtil.isValidaMatricula(professor.getMatricula())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Informe uma matrícula válida."));
        } else if (ValidacoesUtil.isExistenteMatricula(professor.getMatricula())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Matrícula já cadastrada."));

        } else if (ValidacoesUtil.isExistenteEmail(professor.getEmail())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "E-mail já cadastrado."));

        } else if (!isSenhasIguais()) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "As senhas não conferem."));

        } else {
            try {
                // Depois de tudo está validado, deve-se salvar o professor.
                professorRN.registrarProfessor(professor);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }
            limpar();
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Registro efetuado com sucesso."));
        }
    }
}
