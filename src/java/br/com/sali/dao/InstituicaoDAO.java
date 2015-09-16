package br.com.sali.dao;

import br.com.sali.modelo.Instituicao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Realiza as operações relacionadas ao modelo Instituicao.
 *
 * @author SALI
 */
public class InstituicaoDAO extends GenericoDAO<Instituicao> {

    /**
     * Pegar a instituicao do banco de dados por id.
     *
     * @param id
     * @return
     */
    public Instituicao getInstituicaoById(Long id) {
        Criteria criteria = getSessao().createCriteria(Instituicao.class);
        Instituicao instituicao = (Instituicao) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        getTransacao().commit();
        getSessao().close();
        return instituicao;
    }

}
