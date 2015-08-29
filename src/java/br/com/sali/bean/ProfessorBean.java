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
public class ProfessorBean {

    private String campoPesquisarMatricula;
    private String campoPesquisarNome;

    public String getCampoPesquisarMatricula() {
        return campoPesquisarMatricula;
    }

    public ProfessorBean() {
        this.campoPesquisarNome = "";
        this.campoPesquisarMatricula = "";
    }

    public void setCampoPesquisarMatricula(String campoPesquisarMatricula) {
        this.campoPesquisarMatricula = campoPesquisarMatricula;
    }

    public String getCampoPesquisarNome() {
        return campoPesquisarNome;
    }

    public void setCampoPesquisarNome(String campoPesquisarNome) {
        this.campoPesquisarNome = campoPesquisarNome;
    }

    public String registrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha confirmada Incorretamente", ""));
        return "professor_manter";
    }

    public String excluir() {
        return "";
    }

    public String alterar() {
        return "";
    }

    public void pesquisar() {
          if(campoPesquisarMatricula.equals("") && campoPesquisarNome.equals("")){
              FacesContext facesContext = FacesContext.getCurrentInstance();
              facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe 'UM CAMPO' para concluir a pesquisa!",null));
             
          }
          else if(!campoPesquisarMatricula.equals("") && !campoPesquisarNome.equals("")){
              FacesContext facesContext = FacesContext.getCurrentInstance();
              facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe 'SOMENTE' um dos campos para concluir a pesquisa!",null));
             
          }
          else{
             
          }
          
    }

}
