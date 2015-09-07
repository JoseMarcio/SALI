package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "pesquisarProfessorBean")
public class ProfessorPesquisarBean {
    
    ProfessorRN professorRN = new ProfessorRN();
    List<Professor> professoresFiltrados = new ArrayList<>();
    private String filtroDePesquisa;

    public List<Professor> getProfessoresFiltrados() {
        return professoresFiltrados;
    }

    public void setProfessoresFiltrados(List<Professor> professoresFiltrados) {
        this.professoresFiltrados = professoresFiltrados;
    }

    public String getFiltroDePesquisa() {
        return filtroDePesquisa;
    }

    public void setFiltroDePesquisa(String filtroDePesquisa) {
        this.filtroDePesquisa = filtroDePesquisa;
    }
    
    
    /**
     * Limpa os dados do bean.
     */
    public void limparBean(){
        filtroDePesquisa = "";
        professorRN = new ProfessorRN();
        professoresFiltrados = new ArrayList<>();
    }
    
    /**
     * Lista os professores do banco de dados de acordo com
     * o filtro informado.
     */
    public void pesquisar(){
        professoresFiltrados = professorRN.listarProfessores(filtroDePesquisa);
    }
    
    
}
