package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "alterarProfessorBean")
public class ProfessorAlterarBean {

    private Professor professor = new Professor();
    private ProfessorRN professorRN = new ProfessorRN();
    private String confirmaSenha;
    private boolean temProfessorSelecionado = false;
    private int matriculaAtulProfessor;
    private String emailAtualProfessor;
    private boolean render = Boolean.FALSE;

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public ProfessorAlterarBean() {
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    
    
    
    
    public void selecionaProfessor(Professor professor) {
        temProfessorSelecionado = true;
        this.professor = professor;
        matriculaAtulProfessor = professor.getMatricula();
        emailAtualProfessor = professor.getEmail();

    }

    public void limpar(){
        confirmaSenha = "";
        emailAtualProfessor = "";
        matriculaAtulProfessor = 0;
        professor = new Professor();
        professorRN = new ProfessorRN();
        temProfessorSelecionado = false;
    }
    
    
    public boolean isEmailsIguais() {
        return professor.getEmail().equals(emailAtualProfessor);
    }

    public boolean isMatriculasIguais() {
        return professor.getMatricula() == matriculaAtulProfessor;
    }

    public void atualizar() {
        if (!temProfessorSelecionado) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Nenhum professsor selecionado.", ""));
        } else {

          
            
            if (!ValidacoesUtil.isValidaMatricula(professor.getMatricula())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Informe uma matrícula válida.", ""));
            } else {

            
                if (ValidacoesUtil.isExistenteMatricula(professor.getMatricula()) && !isMatriculasIguais()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Matrícula já cadastrada.", ""));
                } else {

                    if (ValidacoesUtil.isExistenteEmail(professor.getEmail()) && !isEmailsIguais()) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "E-mail já cadastrado.", ""));

                    } else {
                        
                        professorRN.atualizarProfessor(professor);
                        limpar();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Dados alterados com sucesso.", ""));
                        
                    }
                }
            
            
            
            
            }
        
        
        
        
        
        }
    }
    
    
    
    public void exibirForm(){
        setRender(Boolean.TRUE);
    }
    
    
    public void pesquisarProfessor(){
        RequestContext.getCurrentInstance().openDialog("pesquisar-professor");
    }
}
