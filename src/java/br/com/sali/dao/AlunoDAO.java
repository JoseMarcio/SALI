package br.com.sali.dao;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Turma;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Realiza as operações relacionadas ao modelo Aluno.
 *
 * @author SALI
 */
public class AlunoDAO extends GenericoDAO<Aluno> {

    /**
     * Lista os alunos de uma determinada turma.
     * 
     * @param turma
     * @return 
     */
    public List<Aluno> listarAlunosPorTurma(Turma turma){
        Criteria criteria = getSessao().createCriteria(Aluno.class);
        Criterion criterioDeBusca1 = Restrictions.eq("turma", turma);
        criteria.add(criterioDeBusca1);
        return criteria.list();
    }
}
