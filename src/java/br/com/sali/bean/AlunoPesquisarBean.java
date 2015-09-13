package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.regras.AlunoRN;
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
@ManagedBean(name = "alunoPesquisarBean")
@ViewScoped
public class AlunoPesquisarBean {

    // Atributos.
    private AlunoRN alunoRN;
    private List<Aluno> listaDeAluno;
    private String filtro;

    // Construtor.
    @PostConstruct
    public void init() {
        this.alunoRN = new AlunoRN();
        this.listaDeAluno = alunoRN.listarTodos();
        this.filtro = "";
    }

    //======================= Gets e Sets ======================================
    public List<Aluno> getListaDeAluno() {
        return listaDeAluno;
    }

    public void setListaDeAluno(List<Aluno> listaDeAluno) {
        this.listaDeAluno = listaDeAluno;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    //========================== Métodos =======================================
    /**
     * Limpar os atributos da bean.
     */
    public void limpar() {
        this.alunoRN = new AlunoRN();
        this.listaDeAluno = alunoRN.listarTodos();
        this.filtro = "";
    }

    /**
     * Realizar a listagem de alunos de acordo com o filtro informado.
     */
    public void pesquisar() {
        this.listaDeAluno = this.alunoRN.listarAlunos(this.filtro);
    }
    
    /**
     * Seleciona o aluno do diálogo de pesquisa. 
     * @param aluno
     */
    public void selecionarAluno(Aluno aluno){
        RequestContext.getCurrentInstance().closeDialog(aluno);
    }
    
    
    /**
     * Abre o diálogo de pesquisa de alunos.
     */
    public void abrirDialogoPesquisa(){
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        
        RequestContext.getCurrentInstance().openDialog("pesquisar-aluno", options, null);
    }
}
