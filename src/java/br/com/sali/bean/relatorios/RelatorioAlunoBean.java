package br.com.sali.bean.relatorios;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.relatorio.Relatorio;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class RelatorioAlunoBean implements Serializable{

    private StreamedContent relatorio;
    private Aluno alunoSelecionado;
    private AlunoRN alunoRN;
    private boolean disabilitaBtnEmitir;

    @PostConstruct
    public void init() {
        this.alunoSelecionado = new Aluno();
        this.alunoRN = new AlunoRN();
        this.disabilitaBtnEmitir = true;
    }

    /**
     * É o que deve acontecer no momento em que for selecionado um aluno por
     * meio do diálodo de pesquisa de alunos.
     *
     * @param event
     */
    public void eventoSelecaoAluno(SelectEvent event) {
        Aluno aluno = (Aluno) event.getObject();
        setAlunoSelecionado(aluno);
        setDisabilitaBtnEmitir(false);
    }

    //==========================================================================
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
    
    public String teste(){
        return "/professor/inicio-professor";
    }

    /**
     * Pega o relatorio gerado. E retorna ele para o cliente.
     *
     * @return
     */
    public StreamedContent getRelatorio() {
        if (alunoRN.isPossivelGerarRelatorioDesseAluno(this.alunoSelecionado)) {
//            setDisabilitaBtnEmitir(true);
            String nomeRelatorioJasper = "relatorioAluno";
            String nomeDoArquivoDeSaida = "SALI - Relatorio Aluno";
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id_aluno", this.alunoSelecionado.getId());
            Relatorio relatorioJasper = new Relatorio(nomeRelatorioJasper, nomeDoArquivoDeSaida, parametros);

            try {
                this.relatorio = relatorioJasper.gerarRelatorio();
                
                return this.relatorio;
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "+e.getMessage(), "");
                RequestContext.getCurrentInstance().showMessageInDialog(msg);
                return null;
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este aluno ainda não realizou Quiz.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }

    public void setRelatorio(StreamedContent relatorio) {
        this.relatorio = relatorio;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public AlunoRN getAlunoRN() {
        return alunoRN;
    }

    public void setAlunoRN(AlunoRN alunoRN) {
        this.alunoRN = alunoRN;
    }

    public boolean isDisabilitaBtnEmitir() {
        return disabilitaBtnEmitir;
    }

    public void setDisabilitaBtnEmitir(boolean disabilitaBtnEmitir) {
        this.disabilitaBtnEmitir = disabilitaBtnEmitir;
    }

}
