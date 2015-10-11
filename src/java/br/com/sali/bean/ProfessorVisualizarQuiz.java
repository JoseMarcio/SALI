package br.com.sali.bean;

import br.com.sali.dao.QuestaoDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Questao;
import br.com.sali.modelo.Quiz;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.QuizRN;
import br.com.sali.regras.UsuarioRN;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "quizProfessorListarQuiz")
@SessionScoped
public class ProfessorVisualizarQuiz {

    private List<Quiz> quizesTurmaAtual;
    private QuizRN quizRN;
    private Quiz quizExibir;

    @PostConstruct
    public void init() {
        quizRN = new QuizRN();
        quizExibir = new Quiz();
        quizesTurmaAtual = quizRN.litarQuizesPorTurma(getProfessorConectado().getTurmaAtual());
    }

    
    /**
     * Seleciona o Quiz desejado, e direciona para a página de "visualização"
     * de Quiz.
     * 
     * @param quiz
     * @return 
     */
    public String selecionarQuiz(Quiz quiz) {
        QuestaoDAO questaoDAO = new QuestaoDAO();
        List<Questao> myQuestoes = questaoDAO.getQuestaoByQuiz(quiz);

        setQuizExibir(quiz);
        getQuizExibir().setQuestoes(myQuestoes);

        return "/professor/visualizacao-quiz?faces-redirect=true";
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
    public List<Quiz> getQuizesTurmaAtual() {
        return quizesTurmaAtual;
    }

    public void setQuizesTurmaAtual(List<Quiz> quizesTurmaAtual) {
        this.quizesTurmaAtual = quizesTurmaAtual;
    }

    public Quiz getQuizExibir() {
        return quizExibir;
    }

    public void setQuizExibir(Quiz quizExibir) {
        this.quizExibir = quizExibir;
    }

}
