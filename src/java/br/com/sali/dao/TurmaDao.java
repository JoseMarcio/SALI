
package br.com.sali.dao;

import br.com.sali.modelo.Turma;
import br.com.sali.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public void registrar(Turma turma) throws Exception{
       Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        
        sessao.saveOrUpdate(turma);
        transacao.commit();
        sessao.close();
               
    }
    
    /**
     * Exclui uma turma do banco de dados.
     * 
     * @param turma
     * @throws Exception 
     */
    public void excluir(Turma turma) throws Exception{
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        
        sessao.delete(turma);
        transacao.commit();
        sessao.close();
    }
    
    
    /**
     * Lista as turma de acordo com o nome informado.
     * @param nome
     * @return
     * @throws Exception 
     */
    public List<Turma> listarTurmaNome(String nome) throws Exception{
        List<Turma> alunosFiltrados = new ArrayList<>();
        return alunosFiltrados;
    }
    
}
