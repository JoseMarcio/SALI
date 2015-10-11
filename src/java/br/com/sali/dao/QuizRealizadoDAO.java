package br.com.sali.dao;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.QuizRealizado;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class QuizRealizadoDAO extends GenericoDAO<QuizRealizado> {

    public List<QuizRealizado> getQuizRealizadoByAluno(Aluno aluno) {
        Criteria criteria = getSessao().createCriteria(QuizRealizado.class);
        List<QuizRealizado> quizes = criteria.add(Restrictions.eq("alunnoQueRealizouQuiz", aluno)).list();
        getTransacao().commit();
        getSessao().close();

        return quizes;

    }
}
