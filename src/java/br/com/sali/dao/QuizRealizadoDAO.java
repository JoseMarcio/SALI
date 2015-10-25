package br.com.sali.dao;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.QuizRealizado;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class QuizRealizadoDAO extends GenericoDAO<QuizRealizado> {

    /**
     * Lista todos os "QuizesRealizados"(Classe que trata de guardar as
     * informações sobre os quizes já realizados por um determinado aluno) do
     * aluno informado.
     *
     * @param aluno
     * @return
     */
    public List<QuizRealizado> getQuizRealizadoByAluno(Aluno aluno) {
        Criteria criteria = getSessao().createCriteria(QuizRealizado.class);
        List<QuizRealizado> quizes = criteria.add(Restrictions.eq("alunnoQueRealizouQuiz", aluno)).list();
        getTransacao().commit();
        getSessao().close();

        return quizes;

    }

    /**
     * Lista os quizes realizados de um aluno filtrado por quiz.
     *
     * @param aluno
     * @param idQuizRealizado
     * @return
     */
    public List<QuizRealizado> listarQuizRealizadosPorAlunoEquiz(Aluno aluno, Long idQuizRealizado) {
        Criteria criteria = getSessao().createCriteria(QuizRealizado.class);
        Criterion criterioDeBusca1 = Restrictions.eq("alunnoQueRealizouQuiz", aluno);
        Criterion criterioDeBusca2 = Restrictions.eq("idQuizRealizado", idQuizRealizado);
        criteria.add(criterioDeBusca1);
        criteria.add(criterioDeBusca2);
        List<QuizRealizado> quizes = criteria.list();
        getTransacao().commit();
        getSessao().close();
        return quizes;
    }
    
    /**
     * Lista todos os quizes realizados de um aluno.
     *
     * @param aluno
     * @return
     */
    public List<QuizRealizado> listarQuizRealizadosPorAluno(Aluno aluno) {
        Criteria criteria = getSessao().createCriteria(QuizRealizado.class);
        Criterion criterioDeBusca1 = Restrictions.eq("alunnoQueRealizouQuiz", aluno);
        criteria.add(criterioDeBusca1);
        List<QuizRealizado> quizes = criteria.list();
         getTransacao().commit();
        getSessao().close();
        return quizes;
    }
}
