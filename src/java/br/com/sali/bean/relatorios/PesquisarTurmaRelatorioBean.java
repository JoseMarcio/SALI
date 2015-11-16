package br.com.sali.bean.relatorios;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.TurmaRN;
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
@ManagedBean(eager = true)
@ViewScoped
public class PesquisarTurmaRelatorioBean implements Serializable{

    private TurmaRN turmaRN;
    private List<Turma> listaDeTurmaDoProfessor;
    private String filtroDePesquisa;

    @PostConstruct
    public void init() {
        turmaRN = new TurmaRN();
        listaDeTurmaDoProfessor = turmaRN.listarTurmasPorProfessor(getProfessorConectado());
        filtroDePesquisa = "";
    }

    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * Realiza a listagem das turmas de acordo com o filtro (nome) informado.
     */
    public void pesquisar() {
        listaDeTurmaDoProfessor = turmaRN.listarTurmasPorProfessorEfiltro(getProfessorConectado(), filtroDePesquisa);
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

        RequestContext.getCurrentInstance().openDialog("/professor/pesquisar-turma-relatorio", options, null);
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

    public TurmaRN getTurmaRN() {
        return turmaRN;
    }

    public void setTurmaRN(TurmaRN turmaRN) {
        this.turmaRN = turmaRN;
    }

    public List<Turma> getListaDeTurmaDoProfessor() {
        return listaDeTurmaDoProfessor;
    }

    public void setListaDeTurmaDoProfessor(List<Turma> listaDeTurmaDoProfessor) {
        this.listaDeTurmaDoProfessor = listaDeTurmaDoProfessor;
    }

    public String getFiltroDePesquisa() {
        return filtroDePesquisa;
    }

    public void setFiltroDePesquisa(String filtroDePesquisa) {
        this.filtroDePesquisa = filtroDePesquisa;
    }
    
    
}
