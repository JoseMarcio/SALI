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
@ManagedBean(name = "alunoAlterarBean")
@ViewScoped
public class AlunoAlterarBean {

    // Atributos.
    private AlunoRN alunoRN;
    private Aluno alunoSelecionado;
    private String emailAluno;
    private Integer matriculaAluno;
    private String confirmaSenha;
    private boolean disabledAtualizar;

    // Construtor.
    @PostConstruct
    public void init() {
        this.alunoRN = new AlunoRN();
        this.alunoSelecionado = new Aluno();
        this.confirmaSenha = "";
        this.disabledAtualizar = true;
        this.emailAluno = "";
        this.matriculaAluno = 0;
    }

    //========================= Gets e Sets ====================================
    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public boolean isDisabledAtualizar() {
        return disabledAtualizar;
    }

    public void setDisabledAtualizar(boolean disabledAtualizar) {
        this.disabledAtualizar = disabledAtualizar;
    }

    public Integer getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(Integer matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    //======================= Métodos ==========================================
    public void limpar() {
        this.alunoRN = new AlunoRN();
        this.alunoSelecionado = new Aluno();
        this.confirmaSenha = "";
        this.disabledAtualizar = true;
        this.emailAluno = "";
        this.matriculaAluno = 0;
    }

    public void eventoSelecaoAluno(SelectEvent event) {
        Aluno aluno = (Aluno) event.getObject();
        setAlunoSelecionado(aluno);
        setMatriculaAluno(aluno.getMatricula());
        setEmailAluno(aluno.getEmail());
        setDisabledAtualizar(false);
    }
    
    
    public void eventoSelecaoTurma(SelectEvent event){
        Turma turma = (Turma) event.getObject();
        alunoSelecionado.setTurma(turma);
    }
    
    
    public void atualizar(){
        if (!ValidacoesUtil.isValidaMatricula(alunoSelecionado.getMatricula())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma matrícula válida.", ""));
        } else if (ValidacoesUtil.isExistenteMatricula(alunoSelecionado.getMatricula())
                && (matriculaAluno != alunoSelecionado.getMatricula())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(alunoSelecionado.getEmail())
                && (!emailAluno.equals(alunoSelecionado.getEmail()))) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));

        } else if (!confirmaSenha.equals(alunoSelecionado.getSenha())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "As senhas não conferem.", ""));

        }
        else{
            alunoRN.atualizarAluno(alunoSelecionado);
            limpar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Dados atualizados com sucesso.", ""));
        }
    }

}
