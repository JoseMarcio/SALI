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
    
    
    public void registrar(Professor professor) throws Exception{
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        
        sessao.saveOrUpdate(professor);
        transacao.commit();
        sessao.close();
    }
    
    
    
    
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
    
    public List<Professor> excluir(Professor professor){
        p.remove(professor);
        
        return this.p;
    }
}
