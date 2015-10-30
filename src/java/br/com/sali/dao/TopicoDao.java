package br.com.sali.dao;

import br.com.sali.modelo.Topico;
import br.com.sali.modelo.Turma;
import java.awt.image.RescaleOp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class TopicoDao extends GenericoDAO<Topico> {

    /**
     * Lista os tópicos por turma.
     *
     * @param turma
     * @return
     */
    public List<Topico> listarTopicosPorTurma(Turma turma) {
        Criteria criteria = getSessao().createCriteria(Topico.class);
        Criterion criterioUm = Restrictions.eq("turma", turma);
        criteria.add(criterioUm);
        List<Topico> topicosDaTurma = criteria.list();
        getTransacao().commit();
        getSessao().close();
        return topicosDaTurma;
    }

    /**
     * Verifica se existe algum tópico com o nome informado. Se existir é
     * retornado "true", senão existir, é retornado "false".
     *
     * @param nome
     * @param turma
     * @return
     */
    public boolean isExistenteNomeTopico(String nome, Turma turma) {
        Criteria criteria = getSessao().createCriteria(Topico.class);
        Topico topico = (Topico) criteria.add(Restrictions.eq("nome", nome).ignoreCase()).add(Restrictions.eq("turma", turma)).uniqueResult();
        getTransacao().commit();
        getSessao().close();

        // Se o titulo resultante da listagem de um único resultado (ignorando o Case Sensitive, 
        // e listando pelo nome), for DIFERENTE DE NULA, é por que existe algum topico com
        // o nome, se for nulo, é por que não existe.
        return topico != null;
    }
}
