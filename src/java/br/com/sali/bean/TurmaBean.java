package br.com.sali.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@RequestScoped
public class TurmaBean {

    private String nomeTurma;
    private String professor;
    private String valorPesquisa;

    public TurmaBean() {
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getValorPesquisa() {
        return valorPesquisa;
    }

    public void setValorPesquisa(String valorPesquisa) {
        this.valorPesquisa = valorPesquisa;
    }
    
    

    /**
     * Registrar a turma.
     */
    public void registrar() {
        if (this.professor.equals("0")) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um professor!", ""));
        } else {
            //Execute comandos.
        }

    }
    
    /**
     * Pesquiusar uma turma.
     * 
     */
    public void pesquisar(){
        
    }

}
