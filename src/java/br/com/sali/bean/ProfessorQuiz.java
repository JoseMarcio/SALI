package br.com.sali.bean;

import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Questao;
import br.com.sali.modelo.Quiz;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.QuizRN;
import br.com.sali.util.ValidacoesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "quizProfessor")
@SessionScoped
public class ProfessorQuiz {

    private QuizRN quizRN;
    private Quiz quiz;

    private String pergunta;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private int alternativaCorreta;
    private List<Questao> questoesInseridas;

    @PostConstruct
    public void init() {
        quizRN = new QuizRN();
        quiz = new Quiz();

        pergunta = "";
        alternativaA = "";
        alternativaB = "";
        alternativaC = "";
        alternativaD = "";
        alternativaCorreta = -1;
        questoesInseridas = new ArrayList<>();
        quiz.setQuestoes(questoesInseridas);

    }

    //==========================================================================
    public void limparQuestao() {
        pergunta = "";
        alternativaA = "";
        alternativaB = "";
        alternativaC = "";
        alternativaD = "";
        alternativaCorreta = -1;

    }

    public void inserirQuestoes() {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        nav.performNavigation("/professor/inserir-questao-quiz?faces-redirect=true");

    }

    public void limparBean() {
        quizRN = new QuizRN();
        quiz = new Quiz();

        pergunta = "";
        alternativaA = "";
        alternativaB = "";
        alternativaC = "";
        alternativaD = "";
        alternativaCorreta = -1;

        questoesInseridas = new ArrayList<>();
        quiz.setQuestoes(questoesInseridas);
    }

    public void gerar() {
        if (quiz.getQuestoes().size() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível salvar um quiz que "
                    + "não tenha nenhuma pergunta", ""));
        } else {

            TurmaDAO turmaDAO = new TurmaDAO();
            Turma turma = (Turma) turmaDAO.getObjectById(Turma.class, Integer.toUnsignedLong(1));

            turma.getQuizesDaTurma().add(quiz);

            turmaDAO.atualizar(turma);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "quiz salvo", ""));
            limparBean();
        }

    }

    public void inserirQuestaoNoQuiz() {

        if (ValidacoesUtil.soTemEspaco(pergunta)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe a pergunta", ""));

        } else if (ValidacoesUtil.soTemEspaco(alternativaA)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe todas as alternativas", ""));

        } else if (ValidacoesUtil.soTemEspaco(alternativaB)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe todas as alternativas", ""));

        } else if (ValidacoesUtil.soTemEspaco(alternativaC)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe todas as alternativas", ""));

        } else if (ValidacoesUtil.soTemEspaco(alternativaD)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe todas as alternativas", ""));

        } else {
            setPergunta(pergunta.toUpperCase());
            setAlternativaA(alternativaA.toUpperCase());
            setAlternativaB(alternativaB.toUpperCase());
            setAlternativaB(alternativaC.toUpperCase());
            setAlternativaB(alternativaD.toUpperCase());

            Questao questao = new Questao();
            String[] alternativas = {alternativaA, alternativaB, alternativaC, alternativaD};

            questao.setPergunta(pergunta);
            questao.setAlternativas(alternativas);
            questao.setAlternativaCorreta(alternativaCorreta);

            quiz.getQuestoes().add(questao);

            limparQuestao();
        }

    }

    //=============================================================================================
    public QuizRN getQuizRN() {
        return quizRN;
    }

    public void setQuizRN(QuizRN quizRN) {
        this.quizRN = quizRN;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public List<Questao> getQuestoesInseridas() {
        return questoesInseridas;
    }

    public void setQuestoesInseridas(List<Questao> questoesInseridas) {
        this.questoesInseridas = questoesInseridas;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public int getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(int alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }

    public void abrirDialogoInserir() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        //options.put("contentHeight", 320);

        RequestContext.getCurrentInstance().openDialog("/professor/inserir-questao-quiz", options, null);
    }
}
