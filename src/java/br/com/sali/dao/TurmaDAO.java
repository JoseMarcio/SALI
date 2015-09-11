package br.com.sali.dao;

import br.com.sali.modelo.Turma;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class TurmaDAO extends GenericoDAO<Turma> {

    /**
     * Verifica se existe alguma turma com o nome informado. Se tiver retorna
     * True senão retorna False.
     *
     * @param nome
     * @return
     */
    public boolean isExistenteNomeTurma(String nome) {
        Criteria criteria = getSessao().createCriteria(Turma.class);
        Turma turma = (Turma) criteria.add(Restrictions.eq("nome", nome)).uniqueResult();
        getTransacao().commit();
        getSessao().close();

        return turma != null;

    }
}
