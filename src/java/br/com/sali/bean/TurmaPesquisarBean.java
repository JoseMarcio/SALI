package br.com.sali.bean;

import br.com.sali.modelo.Turma;
import br.com.sali.regras.TurmaRN;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "turmaPesquisarBean")
@ViewScoped
public class TurmaPesquisarBean {
    
    private TurmaRN turmaRN;
    private List<Turma> listaDeTurmas;
    private String filtroDePesquisa;

    
    // Construtor.
    @PostConstruct
    public void init(){
        this.turmaRN = new TurmaRN();
        this.listaDeTurmas = turmaRN.listarTodas();
        this.filtroDePesquisa = "";
    }
    
    //================== Gets e Sets ===========================================

    public List<Turma> getListaDeTurmas() {
        return listaDeTurmas;
    }

    public void setListaDeTurmas(List<Turma> listaDeTurmas) {
        this.listaDeTurmas = listaDeTurmas;
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
        this.turmaRN = new TurmaRN();
        this.listaDeTurmas = turmaRN.listarTodas();
        setFiltroDePesquisa("");
    }

    
    /**
     * Filtrar as turmas por nome.
     */
    public void pesquisar() {
        this.listaDeTurmas = turmaRN.listarTurmas(filtroDePesquisa);
    }    
    
    
    /**
     * Seleciona a turma do diálogo de pesquisa.
     * @param turma 
     */
    public void selecionarTurma(Turma turma){
        RequestContext.getCurrentInstance().closeDialog(turma);
    }
    
    
    /**
     * Abre o diálogo de pesquisa de turmas.
     */
    public void abrirDialogoPesquisa(){
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        
        RequestContext.getCurrentInstance().openDialog("pesquisar-turma", options, null);
    }
}
