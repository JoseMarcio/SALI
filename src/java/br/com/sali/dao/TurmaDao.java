package br.com.sali.dao;

import br.com.sali.modelo.Turma;
import br.com.sali.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SALI
 */
public class TurmaDao {

    /**
     * Registra uma turma no banco de dados.
     *
     * @param turma
     * @throws Exception
     */
    public void registrar(Turma turma) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.save(turma);
        transacao.commit();
        sessao.close();
    }

    /**
     * Lista todos as turmas que contenham em seu nome o nome informado
     * pelo usuário.
     *
     * @param nome
     * @return
     * @throws Exception
     */
    public List<Turma> listarTurmaNome(String nome) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criteria criteria = sessao.createCriteria(Turma.class);
        Criterion filtro = Restrictions.like("nome", "%" + nome + "%");
        criteria.add(filtro);
        List<Turma> turmasFiltradas = criteria.list();
        
        transacao.commit();
        sessao.close();
        return turmasFiltradas;
    }

    /**
     * Excluir uma turma do banco de dados.
     *
     * @param turma
     * @throws Exception
     */
    public void excluir(Turma turma) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.delete(turma);
        transacao.commit();
        sessao.close();
    }

    /**
     * Atualiza os dados de uma turma do banco de dados
     *
     * @param turma
     * @throws Exception
     */
    public void atualizar(Turma turma) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.update(turma);
        transacao.commit();
        sessao.close();
    }

    /**
     * Verifica se a matrícula informada já existe no banco de dados.
     *
     * @param nome
     * @return
     * @throws Exception
     */
    public boolean isExistenteNomeTurma(String nome) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criterion filtro = Restrictions.eq("nome", nome).ignoreCase();
        Criteria criteria = sessao.createCriteria(Turma.class);
        criteria.add(filtro);

        List<Turma> turmasFiltradas = criteria.list();

        if (turmasFiltradas.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
