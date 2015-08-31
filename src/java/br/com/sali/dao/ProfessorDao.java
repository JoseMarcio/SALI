package br.com.sali.dao;

import br.com.sali.modelo.Professor;
import br.com.sali.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
     * @return
     * @throws Exception
     */
    public List<Professor> listarProfessorNome(String nome) throws Exception {
        List<Professor> professoresFiltrados = new ArrayList<>();
        return professoresFiltrados;
    }

    /**
     * Retorna uma lista de professores que contenha o número de matrícula que
     * foi informado pelo usuário.
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public List<Professor> listarProfessorMatricula(int matricula) throws Exception {
        List<Professor> professoresFiltrados = new ArrayList<>();
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

    // testes
    List p = new ArrayList();

    public List<Professor> listarProfessor() {
        Professor professor = new Professor();
        professor.setEmail("24");
        professor.setMatrícula(7878);
        professor.setNomeCompleto("Joo");
        professor.setSenha("325325");

        Professor professor1 = new Professor();
        professor1.setEmail("24");
        professor1.setMatrícula(7878);
        professor1.setNomeCompleto("Joo");
        professor1.setSenha("325325");

        Professor professor2 = new Professor();
        professor2.setEmail("24");
        professor2.setMatrícula(7878);
        professor2.setNomeCompleto("Joo");
        professor2.setSenha("325325");

        p.add(professor);
        p.add(professor1);
        p.add(professor2);

        return p;
    }

}
