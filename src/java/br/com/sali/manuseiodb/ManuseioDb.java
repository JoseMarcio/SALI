package br.com.sali.manuseiodb;

import br.com.sali.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SALI
 */
public abstract class ManuseioDb {
    
    private Session sessao;
    private Transaction transacao;
    
    
    /**
     * Abre a sessão com o banco de dados.
     */
    private void abrirSessao(){
        sessao = HibernateUtil.getSessionFactory().openSession();
    }
    
    /**
     * Abre a transação com o banco de dados.
     */
    private void abrirTransacao(){
        transacao = sessao.beginTransaction();
    }

    /**
     * Pega a sessão. Se ela for nula ou estiver fechada, a mesma é aberta.
     * @return 
     */
    public Session getSessao(){
        if(sessao == null || !sessao.isOpen()){
            abrirSessao();
        }
        return sessao;
    }
    
    /**
     * Retorna a transação.
     * @return 
     */
    public Transaction getTransacao(){
        abrirTransacao();
        return transacao;
    }
}
