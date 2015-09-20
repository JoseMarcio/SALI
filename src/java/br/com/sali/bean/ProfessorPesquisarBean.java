package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.regras.ProfessorRN;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Pesquisar Professor.
 *
 * @author SALI
 */
@ManagedBean(name = "pesquisarProfessorBean")
@ViewScoped
public class ProfessorPesquisarBean implements Serializable{

    // Atributos.
    private ProfessorRN professorRN;
    private List<Professor> listaDeProfessores;
    private String filtroDePesquisa;

    // Construtor.
    @PostConstruct
    public void init() {
        professorRN = new ProfessorRN();
        listaDeProfessores = professorRN.listarTodos();
        filtroDePesquisa = "";
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
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Realiza a listagem de professores de acordo com o filtro (nome ou matrícula)
     * informado.
     */
    public void pesquisar() {
        listaDeProfessores = professorRN.listarProfessoresPorFiltro(filtroDePesquisa);
    }

    /**
     * Seleciona o professor do diálogo de pesquisa.
     *
     * @param professor
     */
    public void selecionarProfessor(Professor professor) {
        RequestContext.getCurrentInstance().closeDialog(professor);
    }

    /**
     * Abre o diálogo de pesquisa de profesores.
     */
    public void abrirDialogoPesquisa() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);

        RequestContext.getCurrentInstance().openDialog("pesquisar-professor", options, null);
    }
}
