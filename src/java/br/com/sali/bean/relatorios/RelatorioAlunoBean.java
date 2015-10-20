package br.com.sali.bean.relatorios;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.relatorio.Relatorio;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
public class RelatorioAlunoBean {

    private StreamedContent relatorio;
    private Aluno alunoSelecionado;
    private AlunoRN alunoRN;
    private boolean selecionou;

    @PostConstruct
    public void init() {
        this.alunoSelecionado = new Aluno();
        this.alunoRN = new AlunoRN();
        this.selecionou = true;
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
        selecionou = false;
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

    public StreamedContent getRelatorio() {
        if (alunoRN.isPossivelGerarRelatorioDesseAluno(this.alunoSelecionado)) {
            String nomeRelatorioJasper = "relatorioAluno";
            String nomeDoArquivoDeSaida = "Relatorio Aluno";
            Map<String, Object> parametros = new HashMap<>();

            Turma turma = getProfessorConectado().getTurmaAtual();

            parametros.put("id_turma_aluno", turma.getId());
            parametros.put("id_aluno", this.alunoSelecionado.getId());
            parametros.put("nome_aluno", this.alunoSelecionado.getNome());
            parametros.put("matricula_aluno", this.alunoSelecionado.getMatricula());
            parametros.put("turma_aluno", turma.getNome());

            Relatorio relatorioGerado = new Relatorio(nomeRelatorioJasper, nomeDoArquivoDeSaida, parametros);

            try {
                FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exceção!", this.alunoSelecionado.getNome());
                RequestContext.getCurrentInstance().showMessageInDialog(msgs);
                this.relatorio = relatorioGerado.gerarRelatorio();
                return this.relatorio;
            } catch (Exception ex) {
                FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exceção!", ex.getMessage());
                RequestContext.getCurrentInstance().showMessageInDialog(msgs);
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

    public boolean isSelecionou() {
        return selecionou;
    }

    public void setSelecionou(boolean selecionou) {
        this.selecionou = selecionou;
    }

}
