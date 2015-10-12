package br.com.sali.dao;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Realiza as operações relacionadas ao modelo Professor.
 *
 * @author SALI
 */
public class ProfessorDAO extends GenericoDAO<Professor> {

    /*
     Ainda não existem métodos próprios desta classe.
     Nem métodos sobrescritos.
     */
    
    
    
     /**
     * Listar professor por turma atual.
     *
     * @param turma
     * @return
     */
    public List<Professor> listarProfessoresPorTurmaAtual(Turma turma) {
        Criteria criteria = getSessao().createCriteria(Professor.class);
        List<Professor> lista = criteria.add(Restrictions.eq("turmaAtual", turma)).list();
        getTransacao().commit();
        getSessao().close();
        return lista;
    }
}
