package br.com.sali.bean;

import br.com.sali.modelo.Professor;
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
    private Professor professor;
    private String valorPesquisa;

    public TurmaBean() {
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
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
            String tetxo = "NOME: " + nomeTurma;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tetxo, ""));
        }

    }

    /**
     * Pesquiusar uma turma.
     *
     */
    public void pesquisar() {
        String tetxo = "NOME DIGITADO: " + valorPesquisa;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tetxo, ""));
    }

}
