package br.com.sali.dao;

import br.com.sali.modelo.Questao;
import br.com.sali.modelo.Quiz;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class QuestaoDAO extends GenericoDAO<Questao>{
    
    
    
    /**
     * Lista todas as questões de um quiz.
     * 
     * @param quiz
     * @return 
     */
    public List<Questao> getQuestaoByQuiz(Quiz quiz) {
        Criteria criteria = getSessao().createCriteria(Questao.class);
        List<Questao> questoesListadas = criteria.add(Restrictions.eq("quiz", quiz)).list();
        getTransacao().commit();
        getSessao().close();

        return questoesListadas;
    }

}
