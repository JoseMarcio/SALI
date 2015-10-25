package br.com.sali.dao;

import br.com.sali.modelo.Licao;
import br.com.sali.modelo.Turma;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class LicaoDao extends GenericoDAO<Licao> {

    /**
     * Lista as lições por turma.
     *
     * @param turma
     * @return
     */
    public List<Licao> listarLicoesPorTuma(Turma turma) {
        Criteria criteria = getSessao().createCriteria(Licao.class);
        Criterion criterio1 = Restrictions.eq("turma", turma);
        criteria.add(criterio1);
        return criteria.list();
    }
    
     /**
     * Verifica se existe alguma lição com o nome informado. Se existir é
     * retornado "true", senão existir, é retornado "false".
     *
     * @param nome
     * @param turma
     * @return
     */
    public boolean isExistenteNomeLição(String nome, Turma turma) {
        Criteria criteria = getSessao().createCriteria(Licao.class);
        Criterion criterio1 = Restrictions.eq("turma", turma);
        Criterion criterio2 = Restrictions.eq("tituloLicao", nome).ignoreCase();
        criteria.add(criterio1);
        criteria.add(criterio2);
        List<Licao> licoes =  criteria.list();
        getTransacao().commit();
        getSessao().close();

        return !licoes.isEmpty();
    }
}
