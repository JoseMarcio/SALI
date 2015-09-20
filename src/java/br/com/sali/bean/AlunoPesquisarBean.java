package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.regras.AlunoRN;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Pesquisar Aluno.
 *
 * @author SALI
 */
@ManagedBean(name = "alunoPesquisarBean", eager = true)
@ViewScoped
public class AlunoPesquisarBean implements Serializable {

    // Atributos.
    private AlunoRN alunoRN;
    private List<Aluno> listaDeAlunos;
    private String filtroDePesquisa;

    // Construtor.
    @PostConstruct
    public void init() {
        alunoRN = new AlunoRN();
        listaDeAlunos = alunoRN.listarTodos();
        filtroDePesquisa = "";
    }

    //======================= Gets e Sets ======================================
    public List<Aluno> getListaDeAlunos() {
        return listaDeAlunos;
    }

    public void setListaDeAlunos(List<Aluno> listaDeAlunos) {
        this.listaDeAlunos = listaDeAlunos;
    }

    public String getFiltroDePesquisa() {
        return filtroDePesquisa;
    }

    public void setFiltroDePesquisa(String filtroDePesquisa) {
        this.filtroDePesquisa = filtroDePesquisa;
    }

    //========================== Métodos =======================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Realiza a listagem de alunos de acordo com o filtro (nome ou matrícula)
     * informado.
     */
    public void pesquisar() {
        listaDeAlunos = alunoRN.listarAlunosPorFiltro(filtroDePesquisa);
    }

    /**
     * Seleciona um aluno do diálogo de pesquisa.
     *
     * @param aluno
     */
    public void selecionarAluno(Aluno aluno) {
        RequestContext.getCurrentInstance().closeDialog(aluno);
    }

    /**
     * Abre o diálogo de pesquisa de alunos.
     */
    public void abrirDialogoPesquisa() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);

        RequestContext.getCurrentInstance().openDialog("pesquisar-aluno", options, null);
    }
}
