package br.com.sali.dao;

import br.com.sali.manuseiodb.ManuseioDb;
import br.com.sali.util.ValidacoesUtil;
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
     * Gera uma lista de objetos de acordo o tipo de classe e filtro informado.
     * Podendo ser uma matrícula(no caso de professor e aluno) ou um nome (no
     * caso de professor, aluno e turma).
     *
     * @param myClass
     * @param filtro
     * @return
     */
    public List<T> listarPorFiltro(Class<T> myClass, String filtro) {
        Criteria criteria = getSessao().createCriteria(myClass);

        if (ValidacoesUtil.soContemNumeros(filtro)) {
            // Quando o filtro conter somente números é porque ele é uma matrícula.
            // Então é realizado a listagem por matrícula.
            Criterion criterioDeBusca = Restrictions.eq("matricula", Integer.parseInt(filtro));
            List<T> resultados = criteria.add(criterioDeBusca).list();
            getTransacao().commit();
            getSessao().close();
            return resultados;
        } else {
            // Quando o filtro "NÃO CONTER" somente números é porque ele é um nome.
            // Então é realizado a listagem por nome.
            // Ignorando Case Sensitive, e buscando por nomes que "CONTENHAM" o filtro, e
            // não por nomes exatamente iguais ao filtro.
            Criterion criterioDeBusca = Restrictions.ilike("nome", "%" + filtro + "%");
            List<T> resultados = criteria.add(criterioDeBusca).list();
            getTransacao().commit();
            getSessao().close();
            return resultados;
        }
    }

    /**
     * Lista todos objetos disponíveis.
     *
     * @param myClass
     * @return
     */
    public List<T> listarTodos(Class<T> myClass) {
        Criteria criteria = getSessao().createCriteria(myClass);
        List<T> resultados = criteria.list();
        getTransacao().commit();
        getSessao().close();
        return resultados;
    }

    /**
     * Verifica se a matrícula informada já existe no banco de dados. Se a
     * matrícula informada já existir, é retornado "true", senão existir, é
     * retornado "false".
     *
     * @param myClass
     * @param matricula
     * @return
     */
    public boolean isExisteEssaMatricula(Class<T> myClass, String matricula) {
        return !listarPorFiltro(myClass, matricula).isEmpty();
    }

    /**
     * Verifica se o e-mail informado já existe no banco de dados. Se existir, é
     * retornado "true", senão existir, é retornado "false".
     *
     * @param myClass
     * @param email
     * @return
     */
    public boolean isExistenteEmail(Class<T> myClass, String email) {
        Criteria criteria = getSessao().createCriteria(myClass);
        Criterion criterioDeBusca = Restrictions.eq("email", email).ignoreCase();
        if (criteria.add(criterioDeBusca).list().isEmpty()) {
            /*
             Se a lista acima for vazia, é por que não existe nenhum usuário no 
             banco de dados com o email igual ao que foi informado como parâmetro. 
             Então se não existe, deve-se retorna "FALSE".
             */
            getTransacao().commit();
            getSessao().close();
            return false;
        } else {
            /*
             Se a lista acima conter elementos (não for vazia), é por que existe algum
             usuário no  banco de dados com o email igual ao que foi informado como parâmetro. 
             Neste caso, como existe, deve-se retorna "TRUE".
             */
            getTransacao().commit();
            getSessao().close();
            return true;
        }

    }
    
     /**
     * Pegar o objeto do banco de dados por id.
     *
     * @param myClass
     * @param id
     * @return
     */
    public Object getObjectById(Class<T> myClass, Long id) {
        Criteria criteria = getSessao().createCriteria(myClass);
        Object object = (Object) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        getTransacao().commit();
        getSessao().close();
        return object;
    }

}
