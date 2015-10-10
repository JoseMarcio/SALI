package br.com.sali.bean;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.dao.QuestaoDAO;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Questao;
import br.com.sali.modelo.Quiz;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.QuizRN;
import br.com.sali.regras.UsuarioRN;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "quizAluno")
@SessionScoped
public class AlunoQuizBean {

    private QuizRN quizRN;
    private List<Quiz> quizesDaTurma;

    private Quiz quizRealizar;

    private int[] respostas;

    @PostConstruct
    public void init() {
        quizRN = new QuizRN();
        quizRealizar = new Quiz();
        quizesDaTurma = quizRN.litarQuizesPorTurma(getAlunoConectado().getTurma());

    }

    public double calcPercentual(int total, int acertos) {
        return (100 * acertos) / total;
    }

    public void concluir() {

        if (getAlunoConectado().getQuizesRealizados().contains(getQuizRealizar().getId())) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage("Você já responder esse Quiz. Não pode responder"
                    + "novamente!"));
        } else {
            
            

            int count = 0;

            for (int i = 0; i < getQuizRealizar().getQuestoes().size(); i++) {
                if (getQuizRealizar().getQuestoes().get(i).getAlternativaCorreta() == respostas[i]) {
                    ++count;
                }
            }

            String text = "Você acertou " + count + " de " + getQuizRealizar().
                    getQuestoes().size() + " Questões.\n Aproveitamento de " + calcPercentual(getQuizRealizar().getQuestoes().size(), count) + "% !";

            
            
            getAlunoConectado().getQuizesRealizados().add(getQuizRealizar().getId());
            
            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.atualizar(getAlunoConectado());
            
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(text));
        }

    }

    /**
     * Retorna o aluno autenticada no momento.
     *
     * @return
     */
    public Aluno getAlunoConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        AlunoRN alunoRN = new AlunoRN();

        Aluno aluno = alunoRN.getAlunoByUsuario(usuario);

        return aluno;
    }

    public String realizarQuiz(Quiz quiz) {

        QuestaoDAO questaoDAO = new QuestaoDAO();
        List<Questao> myQuestoes = questaoDAO.getQuestaoByQuiz(quiz);

        setQuizRealizar(quiz);

        getQuizRealizar().setQuestoes(myQuestoes);

        respostas = new int[getQuizRealizar().getQuestoes().size()];

        return "/aluno/realizar-quiz?faces-redirect=true";
    }

    //==========================================================================
    public Quiz getQuizRealizar() {
        return quizRealizar;
    }

    public void setQuizRealizar(Quiz quizRealizar) {
        this.quizRealizar = quizRealizar;
    }

    public QuizRN getQuizRN() {
        return quizRN;
    }

    public void setQuizRN(QuizRN quizRN) {
        this.quizRN = quizRN;
    }

    public List<Quiz> getQuizesDaTurma() {
        return quizesDaTurma;
    }

    public void setQuizesDaTurma(List<Quiz> quizesDaTurma) {
        this.quizesDaTurma = quizesDaTurma;
    }

    public int[] getRespostas() {
        return respostas;
    }

    public void setRespostas(int[] respostas) {
        this.respostas = respostas;
    }

}
