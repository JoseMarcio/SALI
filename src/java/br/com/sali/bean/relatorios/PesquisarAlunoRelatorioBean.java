package br.com.sali.bean.relatorios;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class PesquisarAlunoRelatorioBean implements Serializable{

    private AlunoRN alunoRN;
    private List<Aluno> listaDeAlunosDaTurma;
    private String filtroDePesquisa;

    @PostConstruct
    public void init() {
        alunoRN = new AlunoRN();
        listaDeAlunosDaTurma = alunoRN.listarAlunosPorTurma(getProfessorConectado().getTurmaAtual());
        filtroDePesquisa = "";
    }

    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Realiza a listagem dos alunos de acordo com o filtro (nome ou matrícula)
     * informado.
     */
    public void pesquisar() {
        listaDeAlunosDaTurma = alunoRN.listarAlunosPorTurmaFiltro(getProfessorConectado().getTurmaAtual(), filtroDePesquisa);
    }

    /**
     * Seleciona o aluno do diálogo de pesquisa.
     *
     * @param aluno
     */
    public void selecionarAluno(Aluno aluno) {
        RequestContext.getCurrentInstance().closeDialog(aluno);
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

        RequestContext.getCurrentInstance().openDialog("/professor/pesquisar-aluno-relatorio", options, null);
    }

    /**
     * Retorna o professor autenticado no momento.
     *
     * @return
     */
    public Professor getProfessorConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        ProfessorRN professorRN = new ProfessorRN();

        Professor professor = professorRN.getProfessorByUsuario(usuario);

        return professor;
    }

    //==========================================================================
    public AlunoRN getAlunoRN() {
        return alunoRN;
    }

    public void setAlunoRN(AlunoRN alunoRN) {
        this.alunoRN = alunoRN;
    }

    public List<Aluno> getListaDeAlunosDaTurma() {
        return listaDeAlunosDaTurma;
    }

    public void setListaDeAlunosDaTurma(List<Aluno> listaDeAlunosDaTurma) {
        this.listaDeAlunosDaTurma = listaDeAlunosDaTurma;
    }

    public String getFiltroDePesquisa() {
        return filtroDePesquisa;
    }

    public void setFiltroDePesquisa(String filtroDePesquisa) {
        this.filtroDePesquisa = filtroDePesquisa;
    }

}
