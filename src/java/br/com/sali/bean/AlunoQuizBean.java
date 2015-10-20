package br.com.sali.bean;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.dao.QuestaoDAO;
import br.com.sali.dao.QuizRealizadoDAO;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Questao;
import br.com.sali.modelo.Quiz;
import br.com.sali.modelo.QuizRealizado;
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
    private boolean disabledBtnConcluir;

    @PostConstruct
    public void init() {
        disabledBtnConcluir = false;
        quizRN = new QuizRN();
        quizRealizar = new Quiz();
        quizesDaTurma = quizRN.litarQuizesPorTurma(getAlunoConectado().getTurma());

    }

    /**
     * Calcula o percentual de acerto das questões.
     *
     * @param totalDeQuestoes
     * @param totalDeAcertos
     * @return
     */
    public double calcPercentual(int totalDeQuestoes, int totalDeAcertos) {
        return (100 * totalDeAcertos) / totalDeQuestoes;
    }

    /**
     * Conclui a realização do Quiz.
     *
     */
    public void concluir() {

        int acertos = 0;
        for (int i = 0; i < getQuizRealizar().getQuestoes().size(); i++) {
            if (getQuizRealizar().getQuestoes().get(i).getAlternativaCorreta() == getRespostas()[i]) {
                ++acertos;
            }
        }

        double percentualAcerto = calcPercentual(getQuizRealizar().getQuestoes().size(), acertos);

        String questoesCorretas = acertos + " de " + getQuizRealizar().getQuestoes().size() + " Questões.";

        String msg = "Acertos: " + questoesCorretas + "<br /> <br />Aproveitamento: " + percentualAcerto + "%.";

        QuizRealizado meuQuiz = new QuizRealizado();
        meuQuiz.setIdQuizRealizado(getQuizRealizar().getId());
        meuQuiz.setAlunnoQueRealizouQuiz(getAlunoConectado());
        meuQuiz.setQuestoesCorretas(questoesCorretas);
        meuQuiz.setAproveitamento(percentualAcerto);
        meuQuiz.setNomeQuizRealizado(getQuizRealizar().getTitulo());
        meuQuiz.setRespostas(respostas);

        QuizRealizadoDAO quizRealizadoDAO = new QuizRealizadoDAO();
        List<QuizRealizado> listaRealizados = quizRealizadoDAO.getQuizRealizadoByAluno(getAlunoConectado());
        listaRealizados.add(meuQuiz);

        getAlunoConectado().setQuizesRealizados(listaRealizados);

        quizRealizadoDAO.salvar(meuQuiz);

        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.atualizar(getAlunoConectado());

        setDisabledBtnConcluir(true);

        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Concluído com sucesso!", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(m);

    }

    /**
     * Verifica se o aluno conectado já ealizou o Quiz que ele selecionou, e
     * então se ele já tiver realizado é exibido uma mensagem dizendo que já foi
     * realizado, exibindo o total de questões corretas e o aproveitamento em
     * percentual e desabilitando o botão "Concluir".
     *
     */
    public void exibeMsg() {

        QuizRealizadoDAO quizRealizadoDAO = new QuizRealizadoDAO();
        List<QuizRealizado> lista = quizRealizadoDAO.getQuizRealizadoByAluno(getAlunoConectado());

        if (!lista.isEmpty()) {

            QuizRealizado quizRealizado = new QuizRealizado();

            boolean jaExiste = false;

            for (QuizRealizado q : lista) {

                if (q.getIdQuizRealizado().equals(getQuizRealizar().getId())) {
                    jaExiste = true;
                    quizRealizado = q;
                    break;
                }

            }

            if (jaExiste == true) {
                respostas = quizRealizado.getRespostas();

                setDisabledBtnConcluir(true);

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Você já realizou este Quiz.", "");

                FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_WARN, quizRealizado.getQuestoesCorretas(), "");

                FacesMessage msg3 = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aproveitamento: " + quizRealizado.getAproveitamento() + "%.", "");

                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().addMessage(null, msg2);
                FacesContext.getCurrentInstance().addMessage(null, msg3);
            } else {
                setDisabledBtnConcluir(false);
            }

        }

    }

    
    /**
     * Retorna o aluno autenticado no momento.
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

    /**
     * Seleciona o Quiz desejado para poder realizar ele. 
     * 
     * @param quiz
     * @return 
     */
    public String realizarQuiz(Quiz quiz) {

        QuestaoDAO questaoDAO = new QuestaoDAO();
        List<Questao> myQuestoes = questaoDAO.getQuestaoByQuiz(quiz);

        setQuizRealizar(quiz);

        getQuizRealizar().setQuestoes(myQuestoes);

        respostas = new int[getQuizRealizar().getQuestoes().size()];

        for (int i = 0; i < getQuizRealizar().getQuestoes().size(); i++) {
            respostas[i] = 9;
        }

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

    public boolean isDisabledBtnConcluir() {
        return disabledBtnConcluir;
    }

    public void setDisabledBtnConcluir(boolean disabledBtnConcluir) {
        this.disabledBtnConcluir = disabledBtnConcluir;
    }

}
