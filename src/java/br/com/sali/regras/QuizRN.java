package br.com.sali.regras;

import br.com.sali.dao.QuizDAO;
import br.com.sali.modelo.Quiz;
import java.util.List;

/**
 *
 * @author SALI
 */
public class QuizRN {

    private final QuizDAO quizDAO;

    public QuizRN() {
        quizDAO = new QuizDAO();
    }

    /**
     * Salva um novo quiz no banco de dados.
     *
     * @param quiz
     */
    public void salvar(Quiz quiz) {
        quizDAO.salvar(quiz);
    }

    /**
     * Retorna todos os quizes cadastrados.
     *
     * @return
     */
    public List<Quiz> listarTodos() {
        return quizDAO.listarTodos(Quiz.class);
    }

    /**
     * Pega um quiz pelo id;
     *
     * @param id
     * @return
     */
    public Quiz getQuizById(Long id) {
        return (Quiz) quizDAO.getObjectById(Quiz.class, id);
    }
}
