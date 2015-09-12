package br.com.sali.dao;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
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

    public boolean isExisteProfessorTurma(Professor professor) {
        Criteria criteria = getSessao().createCriteria(Turma.class);
        List<Turma> turmas = criteria.add(Restrictions.eq("professor", professor)).list();
        getTransacao().commit();
        getSessao().close();

        if (turmas.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<Turma> listar(Class<Turma> myClass, String filtro) {
        List<Turma> resultados = new ArrayList<>();
        Criteria criteria = getSessao().createCriteria(myClass);
        Criterion criterioDeBusca = Restrictions.ilike("nome", "%" + filtro + "%");
        resultados = criteria.add(criterioDeBusca).list();
        getTransacao().commit();
        getSessao().close();
        return resultados;
    }

}
