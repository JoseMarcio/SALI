package br.com.sali.dao;

import br.com.sali.modelo.MenssagemTopico;
import br.com.sali.modelo.Topico;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class MenssagemDao extends GenericoDAO<MenssagemTopico> {

    /**
     * Lista as menssagens por topico de forum.
     *
     * @param topico
     * @return
     */
    public List<MenssagemTopico> listarMenssagensPorTopico(Topico topico) {
        Criteria criteria = getSessao().createCriteria(MenssagemTopico.class);
        Criterion criterioUm = Restrictions.eq("topico", topico);
        criteria.add(criterioUm);
        List<MenssagemTopico> menssagensDoTopico = criteria.list();
        getTransacao().commit();
        getSessao().close();
        return menssagensDoTopico;
    }

}
