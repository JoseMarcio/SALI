package br.com.sali.dao;

import br.com.sali.modelo.Quiz;
import br.com.sali.modelo.Turma;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class QuizDAO extends GenericoDAO<Quiz> {

    
    /**
     * Lista os quizes filtrados por turma.
     * 
     * @param turma
     * @return 
     */
    public List<Quiz> getQuizByTurma(Turma turma) {
        Criteria criteria = getSessao().createCriteria(Quiz.class);
        List<Quiz> quizesListados = criteria.add(Restrictions.eq("turma", turma)).list();
        getTransacao().commit();
        getSessao().close();

        return quizesListados;
    }

    
}

