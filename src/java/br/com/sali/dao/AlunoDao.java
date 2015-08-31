
package br.com.sali.dao;

import br.com.sali.modelo.Aluno;
import br.com.sali.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SALI
 */
public class AlunoDao {
    
    
    /**
     * Salva um aluno no banco de dados.
     * 
     * @param aluno
     * @throws Exception 
     */
    public void registrar(Aluno aluno) throws Exception{
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        
        sessao.saveOrUpdate(aluno);
        transacao.commit();
        sessao.close();
    }
    
    /**
     * Excluir um aluno do banco de dados.
     * 
     * @param aluno
     * @throws Exception 
     */
    public void exlcuir(Aluno aluno) throws Exception{
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        
        sessao.delete(aluno);
        transacao.commit();
        sessao.close();
    }
    
    /**
     * Lista os alunos de acordo com o nome informado.
     * @param nome
     * @return
     * @throws Exception 
     */
    public List<Aluno> listarAlunosNome(String nome) throws Exception{
        List<Aluno> alunosFiltrados = new ArrayList<>();
        
        return alunosFiltrados;
    }
    
    /**
     * Lista os alunos de acordo com a matrócula informada.
     * 
     * @param matricula
     * @return
     * @throws Exception 
     */
    public List<Aluno> listarAlunosMatricula(int matricula) throws Exception{
        List<Aluno> alunosFiltrados = new ArrayList<>();
        
        return alunosFiltrados;
    }
}
