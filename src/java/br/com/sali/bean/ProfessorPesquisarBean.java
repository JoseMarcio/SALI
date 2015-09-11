package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "pesquisarProfessorBean")
public class ProfessorPesquisarBean {
    
    private ProfessorRN professorRN;
    private List<Professor> listaDeProfessores;
    private String filtroDePesquisa;

    
    // Construtor.
    @PostConstruct
    public void init(){
        this.professorRN = new ProfessorRN();
        this.listaDeProfessores = professorRN.listarTodosProfessores();
        this.filtroDePesquisa = "";
    }
    
    //================== Gets e Sets ===========================================

    public List<Professor> getListaDeProfessores() {
        return listaDeProfessores;
    }

    public void setListaDeProfessores(List<Professor> listaDeProfessores) {
        this.listaDeProfessores = listaDeProfessores;
    }

    public String getFiltroDePesquisa() {
        return filtroDePesquisa;
    }

    public void setFiltroDePesquisa(String filtroDePesquisa) {
        this.filtroDePesquisa = filtroDePesquisa;
    }
    
    
    //====================== Métodos ===========================================
    
    /**
     * Limpa os atributos da bean.
     */
    public void limpar() {
        this.professorRN = new ProfessorRN();
        this.filtroDePesquisa = "";
        this.listaDeProfessores = professorRN.listarTodosProfessores();
    }

    
    /**
     * Filtrar os professores por nome ou matrícula.
     */
    public void pesquisar() {
        this.listaDeProfessores = professorRN.listarProfessores(filtroDePesquisa);
    }    
    
    
    /**
     * Seleciona o professor do diálogo de pesquisa.
     * @param professor 
     */
    public void selecionarProfessor(Professor professor){
        RequestContext.getCurrentInstance().closeDialog(professor);
    }
    
    
    /**
     * Abre o diálogo de pesquisa de profesores.
     */
    public void abrirDialogoPesquisa(){
        RequestContext.getCurrentInstance().openDialog("pesquisar-professor");
    }
}
