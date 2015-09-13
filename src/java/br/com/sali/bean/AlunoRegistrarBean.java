package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.AlunoRN;
import br.com.sali.util.ValidacoesUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "alunoRegistrarBean")
@ViewScoped
public class AlunoRegistrarBean {

    // Atributos.
    private Aluno aluno;
    private AlunoRN alunoRN;
    private String confirmaSenha;
    private boolean disabledRegistrar;

    // Construtor.
    @PostConstruct
    public void init() {
        aluno = new Aluno();
        alunoRN = new AlunoRN();
        confirmaSenha = "";
        disabledRegistrar = true;
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

    public boolean isDisabledRegistrar() {
        return disabledRegistrar;
    }

    public void setDisabledRegistrar(boolean disabledRegistrar) {
        this.disabledRegistrar = disabledRegistrar;
    }
    
    

    //=======================Métodos============================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limparBean() {
        aluno = new Aluno();
        alunoRN = new AlunoRN();
        confirmaSenha = "";
        disabledRegistrar = true;
    }
    
    
    /**
     * Captura a turma selecionado pelo evento.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turma = (Turma) event.getObject();
        this.aluno.setTurma(turma);
        setDisabledRegistrar(false);
    }

    /**
     * Verifica se a senha e a confirmação de senha são iguais.
     *
     * @return
     */
    public boolean isSenhasIguais() {
        return this.aluno.getSenha().equals(this.confirmaSenha);
    }

    /**
     * Registra o aluno no banco de dados, de acordo com as regras definidas na
     * classe AlunoRN.
     *
     */
    public void registrar() {
        if (!ValidacoesUtil.isValidaMatricula(this.aluno.getMatricula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma matrícula válida.", ""));
        } else if (ValidacoesUtil.isExistenteMatricula(this.aluno.getMatricula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(this.aluno.getEmail())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));

        } else if (!isSenhasIguais()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "As senhas não conferem.", ""));

        } else {
            // Depois de tudo está validado, deve-se executar esse código.
            this.alunoRN.registrarAluno(this.aluno);
            limparBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aluno registrado com sucesso.", ""));

        }
    }
}
