package br.com.sali.dao;

import br.com.sali.modelo.Aluno;
import br.com.sali.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * OPERAÇÕES BANCO DE DADOS (ALUNO)
 *
 * @author SALI 
 */
public class AlunoDao {

    /**
     * Registra um aluno no banco de dados.
     *
     * @param aluno
     * @throws Exception
     */
    public void registrar(Aluno aluno) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.save(aluno);
        transacao.commit();
        sessao.close();
    }

    /**
     * Lista todos os alunos que contenham em seu nome o nome informado
     * pelo usuário.
     *
     * @param nome
     * @return
     * @throws Exception
     */
    public List<Aluno> listarAlunoNome(String nome) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criteria criteria = sessao.createCriteria(Aluno.class);
        Criterion filtro = Restrictions.like("nomeCompleto", "%" + nome + "%");
        criteria.add(filtro);
        List<Aluno> alunosFiltrados = criteria.list();
        
        transacao.commit();
        sessao.close();
        return alunosFiltrados;
    }

    /**
     * Retorna o aluno que contenha o número de matrícula que foi informado
     * pelo usuário.
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public List<Aluno> listarAlunoMatricula(int matricula) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        Criteria criteria = sessao.createCriteria(Aluno.class);
        Criterion filtro = Restrictions.eq("matricula", matricula);
        criteria.add(filtro);
        List<Aluno> alunosFiltrados = criteria.list();
        
        transacao.commit();
        sessao.close();
        
        return alunosFiltrados;
    }

    /**
     * Excluir um aluno do banco de dados.
     *
     * @param aluno
     * @throws Exception
     */
    public void excluir(Aluno aluno) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.delete(aluno);
        transacao.commit();
        sessao.close();
    }

    /**
     * Atualiza os dados de um professor do banco de dados
     *
     * @param aluno
     * @throws Exception
     */
    public void atualizar(Aluno aluno) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();

        sessao.update(aluno);
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
        Criteria criteria = sessao.createCriteria(Aluno.class);
        criteria.add(filtro);

        List<Aluno> alunosFiltrados = criteria.list();

        if (alunosFiltrados.isEmpty()) {
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
        Criteria criteria = sessao.createCriteria(Aluno.class);
        criteria.add(filtro);
        List<Aluno> alunosFiltrados = criteria.list();

        if (alunosFiltrados.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
