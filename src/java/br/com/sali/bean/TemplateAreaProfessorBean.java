package br.com.sali.bean;

import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateProfessor")
@RequestScoped
public class TemplateAreaProfessorBean {

    private Turma turmaContextoAtual;
    
    @PostConstruct
    public void init(){
        turmaContextoAtual = new Turma();
        turmaContextoAtual = getProfessorConectado().getTurmaAtual();
    }

    public Turma getTurmaContextoAtual() {
        return turmaContextoAtual;
    }

    public void setTurmaContextoAtual(Turma turmaContextoAtual) {
        this.turmaContextoAtual = turmaContextoAtual;
    }
    
    
    
    
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
     * Retorna o professor autenticada no momento.
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

    
    public List<Turma> getTurmasProfessoAtual(){
        TurmaDAO turmaDAO = new TurmaDAO();
        return  turmaDAO.getTurmaProfessor(getProfessorConectado());
    }
    
    public void eventoMudancaTurma(){
              
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Information","KJjgfk"));
    }
        
}
