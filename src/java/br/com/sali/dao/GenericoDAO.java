package br.com.sali.dao;

import br.com.sali.manuseiodb.ManuseioDb;
import br.com.sali.util.ValidacoesUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Recebe uma classe como parâmetro para realizar as operações com ela.
 *
 * @author SALI
 * @param <T>
 */
public class GenericoDAO<T> extends ManuseioDb {

    /**
     * Salva um determinado objeto.
     *
     * @param t
     */
    public void salvar(T t) {
        getSessao().save(t);
        getTransacao().commit();
        getSessao().close();
    }

    /**
     * Atualiza os dados de um determinado objeto.
     *
     * @param t
     */
    public void atualizar(T t) {
        getSessao().update(t);
        getTransacao().commit();
        getSessao().close();
    }

    /**
     * Exclui um objeto passado por parâmetro.
     *
     * @param t
     */
    public void excluir(T t) {
        getSessao().delete(t);
        getTransacao().commit();
        getSessao().close();
    }

    /**
     * Gera uma lista de objetos de acordo o tipo de classe informado e o filtro
     * que foi informado. Podendo ser uma matrícula ou um nome.
     *
     * @param myClass
     * @param filtro
     * @return
     */
    public List<T> listar(Class<T> myClass, String filtro) {
        getSessao();
        
        List<T> resultados = new ArrayList<>();
        Criteria criteria = getSessao().createCriteria(myClass);
        if (ValidacoesUtil.soContemNumeros(filtro)) {
            Criterion criterioDeBusca = Restrictions.eq("matricula", Integer.parseInt(filtro));
            resultados = criteria.add(criterioDeBusca).list();
            getTransacao().commit();
            getSessao().close();
            return resultados;
        } else {
            Criterion criterioDeBusca = Restrictions.ilike("nome", "%"+filtro+"%");
            resultados = criteria.add(criterioDeBusca).list();
            getTransacao().commit();
            getSessao().close();
            return resultados;
        }
    }

    /**
     * Verifica se a matrícula informada já existe no banco de dados.
     *
     * @param myClass
     * @param matricula
     * @return
     */
    public boolean isExisteEssaMatricula(Class<T> myClass, String matricula) {
        if (listar(myClass, matricula).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Verifica se o e-mail informado já existe no banco de dados.
     *
     * @param myClass
     * @param email
     * @return
     */
    public boolean isExistenteEmail(Class<T> myClass, String email) {
        Criteria criteria = getSessao().createCriteria(myClass);
        Criterion criterioDeBusca = Restrictions.eq("email", email);
        if(criteria.add(criterioDeBusca).list().isEmpty()){
            getTransacao().commit();
            getSessao().close();
            return false;
        }
        else{
            getTransacao().commit();
            getSessao().close();
            return true;
        }

    }

    /**
     * Verfica se o nome informado já existe no banco de dados.
     *
     * @param myClass
     * @param nome
     * @return
     */
    public boolean isExistenteNome(Class<T> myClass, String nome) {
        if (listar(myClass, nome).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
