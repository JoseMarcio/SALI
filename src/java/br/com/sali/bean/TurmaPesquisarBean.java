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
 * Managed Bean Pesquisar Turma.
 *
 * @author SALI
 */
@ManagedBean(name = "turmaPesquisarBean")
@ViewScoped
public class TurmaPesquisarBean {

    // Atributos.
    private TurmaRN turmaRN;
    private List<Turma> listaDeTurmas;
    private String filtroDePesquisa;

    // Construtor.
    @PostConstruct
    public void init() {
        turmaRN = new TurmaRN();
        listaDeTurmas = turmaRN.listarTodas();
        filtroDePesquisa = "";
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
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Realiza a listagem de turmas de acordo com o filtro (nome)
     * informado.
     */
    public void pesquisar() {
        listaDeTurmas = turmaRN.listarTurmasPorFiltro(filtroDePesquisa);
    }

    /**
     * Seleciona a turma do diálogo de pesquisa.
     *
     * @param turma
     */
    public void selecionarTurma(Turma turma) {
        RequestContext.getCurrentInstance().closeDialog(turma);
    }

    /**
     * Abre o diálogo de pesquisa de turmas.
     */
    public void abrirDialogoPesquisa() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);

        RequestContext.getCurrentInstance().openDialog("pesquisar-turma", options, null);
    }
}
