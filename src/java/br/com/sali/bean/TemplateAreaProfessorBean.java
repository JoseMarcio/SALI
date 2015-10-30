package br.com.sali.bean;

import br.com.sali.dao.ProfessorDAO;
import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateProfessor")
@ViewScoped
public class TemplateAreaProfessorBean implements Serializable {

    private Turma turmaContextoAtual;
    private List<Turma> turmasProfessor;
    private boolean renderSelect;

    @PostConstruct
    public void init() {
        renderSelect = true;
        turmasProfessor = new ArrayList<>();
        turmaContextoAtual = new Turma();

        for (Turma t : getTurmasProfessoAtual()) {
            turmasProfessor.add(t);
        }

        if (getProfessorConectado().getTurmaAtual() == null && !turmasProfessor.isEmpty()) {
            ProfessorDAO professorDAO = new ProfessorDAO();
            Professor p = new Professor();
            p = getProfessorConectado();
            p.setTurmaAtual(turmasProfessor.get(0));
            professorDAO.atualizar(p);
        }

        if (getProfessorConectado().getTurmaAtual() == null && turmasProfessor.isEmpty()) {
            renderSelect = false;
        }
    }

    public boolean isRenderSelect() {
        return renderSelect;
    }

    public void setRenderSelect(boolean renderSelect) {
        this.renderSelect = renderSelect;
    }

    public Turma getTurmaContextoAtual() {
        return turmaContextoAtual;
    }

    public void setTurmaContextoAtual(Turma turmaContextoAtual) {
        this.turmaContextoAtual = turmaContextoAtual;
    }

    public List<Turma> getTurmasProfessor() {
        return turmasProfessor;
    }

    public void setTurmasProfessor(List<Turma> turmasProfessor) {
        this.turmasProfessor = turmasProfessor;
    }

    // ==========================================================================
    /**
     * Direciona página Inicial.
     *
     * @return
     */
    public String irInicioProfessor() {
        return "/professor/inicio-professor?faces-redirect=true";
    }

    /**
     * Direciona alterar senha.
     *
     * @return
     */
    public String irAlterarSenha() {
        return "/professor/alterar-senha?faces-redirect=true";
    }

    /**
     * Direciona a tela de lições.
     *
     * @return
     */
    public String irLicoes() {
        return "/professor/licoes?faces-redirect=true";
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

    /**
     * Direciona gerar quiz.
     *
     * @return
     */
    public String irGerarQuiz() {
        return "/professor/gerar-quiz?faces-redirect=true";
    }

    /**
     * Lista todas as Turmas do professor atual.
     *
     * @return
     */
    public List<Turma> getTurmasProfessoAtual() {
        TurmaDAO turmaDAO = new TurmaDAO();

        return turmaDAO.getTurmaProfessor(getProfessorConectado());
    }

    /**
     * Atualiza a turma atual do professor conectado.
     */
    public void atualizaTurmaAtual() {
        if (turmaContextoAtual == null) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Informe uma turma válida."));
            turmaContextoAtual = getProfessorConectado().getTurmaAtual();
        } else {

            Professor p = new Professor();
            p = getProfessorConectado();

            p.setTurmaAtual(turmaContextoAtual);
            ProfessorDAO professorDAO = new ProfessorDAO();

            professorDAO.atualizar(p);

            FacesContext context = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

            nav.performNavigation("/professor/inicio-professor?faces-redirect=true");

        }

    }

    /**
     * Direciona a listagem de quizes.
     *
     * @return
     */
    public String irListarQuiz() {
        return "/professor/lista-quiz?faces-redirect=true";
    }

    /**
     * Direciona a gerar relatório aluno.
     *
     * @return
     */
    public String irRelatorioAluno() {
        return "/professor/gerar-relatorio-aluno?faces-redirect=true";
    }

    /**
     * Direciona a gerar relatório turma.
     *
     * @return
     */
    public String irRelatorioTurma() {
        return "/professor/gerar-relatorio-turma?faces-redirect=true";
    }

    /**
     * Direciona a anexar lições.
     *
     * @return
     */
    public String irAnexarLicao() {
        return "/professor/anexar-licao?faces-redirect=true";
    }

    /**
     * Direciona a ver lições.
     *
     * @return
     */
    public String irVerLicao() {
        return "/professor/lista-licao?faces-redirect=true";
    }
    
    
    /**
     * Direciona a tópicos.
     *
     * @return
     */
    public String irTopicos() {
        return "/professor/topicos?faces-redirect=true";
    }

}
