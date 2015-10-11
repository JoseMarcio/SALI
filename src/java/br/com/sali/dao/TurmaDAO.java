package br.com.sali.dao;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Realizar operações referentes ao modelo Turma. Herda do DAO Generico, para
 * que possa realiar as operações no banco de dados.
 *
 * @author SALI
 */
public class TurmaDAO extends GenericoDAO<Turma> {

    /**
     * Verifica se existe alguma turma com o nome informado. Se existir é
     * retornado "true", senão existir, é retornado "false".
     *
     * @param nome
     * @return
     */
    public boolean isExistenteNomeTurma(String nome) {
        Criteria criteria = getSessao().createCriteria(Turma.class);
        Turma turma = (Turma) criteria.add(Restrictions.eq("nome", nome).ignoreCase()).uniqueResult();
        getTransacao().commit();
        getSessao().close();

        // Se a turma resultante da listagem de um único resultado (ignorando o Case Sensitive, 
        // e listando pelo nome), for DIFERENTE DE NULA, é por que existe alguma turma com
        // o nome, se for nula, é por que não existe.
        return turma != null;
    }

    /**
     * Verifica se existe turmas com o professor informado. Se existir, é
     * retornado "true", senão existir, é retornado "false".
     *
     * @param professor
     * @return
     */
    public boolean isExisteProfessorTurma(Professor professor) {
        Criteria criteria = getSessao().createCriteria(Turma.class);
        List<Turma> turmasListadas = criteria.add(Restrictions.eq("professor", professor)).list();
        getTransacao().commit();
        getSessao().close();

        /*
         Se a lista de turmas for vazia é porque o professor informado não está
         em nenhuma turma que esteja salva no banco de dados. 
         */
        return !turmasListadas.isEmpty();
    }

    /**
     * Lista as turmas por "NOME" (Somente).
     *
     * @param myClass
     * @param filtro
     * @return
     */
    @Override
    public List<Turma> listarPorFiltro(Class<Turma> myClass, String filtro) {
        Criteria criteria = getSessao().createCriteria(myClass);
        Criterion criterioDeBusca = Restrictions.ilike("nome", "%" + filtro + "%");
        List<Turma> resultados = criteria.add(criterioDeBusca).list();
        getTransacao().commit();
        getSessao().close();
        return resultados;
    }

    
    /**
     * Lista as turmas que possuem o professor informado.
     * 
     * @param professor
     * @return 
     */
    public List<Turma> getTurmaProfessor(Professor professor) {
        Criteria criteria = getSessao().createCriteria(Turma.class);
        List<Turma> turmasListadas = criteria.add(Restrictions.eq("professor", professor)).list();
        getTransacao().commit();
        getSessao().close();

        return turmasListadas;
    }

}
