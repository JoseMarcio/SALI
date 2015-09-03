package br.com.sali.dao;

import br.com.sali.modelo.Professor;
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
public class ProfessorDao {

    /**
     * Registra um professor no banco de dados.
     *
     * @param professor
     * @throws Exception
     */
    public void registrar(Professor professor) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.save(professor);
        transacao.commit();
        sessao.close();
    }

    /**
     * Lista todos os professores que contenham em seu nome o nome informado
     * pelo usuário.
     *
     * @param nome
     * @return
     * @throws Exception
     */
    public List<Professor> listarProfessorNome(String nome) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criteria criteria = sessao.createCriteria(Professor.class);
        Criterion filtro = Restrictions.like("nomeCompleto", "%" + nome + "%");
        criteria.add(filtro);
        List<Professor> professoresFiltrados = criteria.list();
        
        transacao.commit();
        sessao.close();
        return professoresFiltrados;
    }

    /**
     * Retorna o professo que contenha o número de matrícula que foi informado
     * pelo usuário.
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public List<Professor> listarProfessorMatricula(int matricula) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criteria criteria = sessao.createCriteria(Professor.class);
        Criterion filtro = Restrictions.eq("matricula", matricula);
        criteria.add(filtro);
        List<Professor> professoresFiltrados = criteria.list();
        
        transacao.commit();
        sessao.close();
        
        return professoresFiltrados;
    }

    /**
     * Excluir um professor do banco de dados.
     *
     * @param professor
     * @throws Exception
     */
    public void excluir(Professor professor) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.delete(professor);
        transacao.commit();
        sessao.close();
    }

    /**
     * Atualiza os dados de um professor do banco de dados
     *
     * @param professor
     * @throws Exception
     */
    public void atualizar(Professor professor) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.update(professor);
        transacao.commit();
        sessao.close();
    }

    /**
     * Verifica se a matrícula informada já existe no banco de dados.
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public boolean isExistenteMatricula(int matricula) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criterion filtro = Restrictions.eq("matricula", matricula);
        Criteria criteria = sessao.createCriteria(Professor.class);
        criteria.add(filtro);

        List<Professor> professoresFiltrados = criteria.list();

        if (professoresFiltrados.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Verifica se email informado já existe no banco de dados.
     *
     * @param email
     * @return
     */
    public boolean isExistenteEmail(String email) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criterion filtro = Restrictions.eq("email", email);
        Criteria criteria = sessao.createCriteria(Professor.class);
        criteria.add(filtro);
        List<Professor> professoresFiltrados = criteria.list();

        if (professoresFiltrados.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
