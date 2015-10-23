package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Questao;
import br.com.sali.modelo.Quiz;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.QuizRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.ValidacoesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
    private boolean habilitaBotaoInserir;
    private boolean habilitaBotaoTerminar;
    private boolean habilitaBotaoGerar;

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

        habilitaBotaoInserir = false;
        habilitaBotaoTerminar = true;
        habilitaBotaoGerar = true;

    }

    //==========================================================================
    /**
     * Limpa a questão atual.
     *
     */
    public void limparQuestao() {
        pergunta = "";
        alternativaA = "";
        alternativaB = "";
        alternativaC = "";
        alternativaD = "";
        alternativaCorreta = -1;

    }

    /**
     * Direciona a página de inserir questões.
     */
    public void inserirQuestoes() {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        nav.performNavigation("/professor/inserir-questao-quiz?faces-redirect=true");

    }

    /**
     * Direciona a página de gerar Quiz.
     */
    public void terminarQuestoes() {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        nav.performNavigation("/professor/gerar-quiz?faces-redirect=true");

    }

    /**
     * Reinicia a bean. Como se fosse chamar o construtor nesse método.
     *
     */
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
        habilitaBotaoGerar = true;

        habilitaBotaoInserir = false;
        habilitaBotaoTerminar = true;

    }

    /**
     * Salva o Quiz no banco de dados.
     *
     */
    public void gerar() {

        if (ValidacoesUtil.soTemEspaco(this.quiz.getTitulo())) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Título inválido.", "");
            FacesContext.getCurrentInstance().addMessage(null, m);
        } else {

            if (quiz.getQuestoes().size() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe perguntas para o quiz.", ""));
            } else {
                quiz.setTitulo(quiz.getTitulo().toUpperCase());

                Turma turma = getProfessorConectado().getTurmaAtual();

                quiz.setTurma(turma);

                quizRN.salvar(quiz);

                limparBean();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Quiz gerado com sucesso!", ""));

            }
        }

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
     * Insere uma nova questão no Quiz que está sendo criado.
     */
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
            setAlternativaC(alternativaC.toUpperCase());
            setAlternativaD(alternativaD.toUpperCase());

            Questao questao = new Questao();
            String[] alternativas = {alternativaA, alternativaB, alternativaC, alternativaD};

            questao.setPergunta(pergunta);
            questao.setAlternativas(alternativas);
            questao.setAlternativaCorreta(alternativaCorreta);

            questao.setQuiz(quiz);

            quiz.getQuestoes().add(questao);

            if (quiz.getQuestoes().size() >= 9) {
                setHabilitaBotaoInserir(true);
            }

            if (quiz.getQuestoes().size() > 0) {
                setHabilitaBotaoTerminar(false);
                setHabilitaBotaoGerar(false);
            } else {
                setHabilitaBotaoTerminar(true);
                setHabilitaBotaoGerar(true);
            }

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

    public boolean isHabilitaBotaoInserir() {
        return habilitaBotaoInserir;
    }

    public void setHabilitaBotaoInserir(boolean habilitaBotaoInserir) {
        this.habilitaBotaoInserir = habilitaBotaoInserir;
    }

    public boolean isHabilitaBotaoTerminar() {
        return habilitaBotaoTerminar;
    }

    public void setHabilitaBotaoTerminar(boolean habilitaBotaoTerminar) {
        this.habilitaBotaoTerminar = habilitaBotaoTerminar;
    }

    public boolean isHabilitaBotaoGerar() {
        return habilitaBotaoGerar;
    }

    public void setHabilitaBotaoGerar(boolean habilitaBotaoGerar) {
        this.habilitaBotaoGerar = habilitaBotaoGerar;
    }

}
