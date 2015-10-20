package br.com.sali.dao;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Turma;
import br.com.sali.util.ValidacoesUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Realiza as operações relacionadas ao modelo Aluno.
 *
 * @author SALI
 */
public class AlunoDAO extends GenericoDAO<Aluno> {

    /**
     * Lista os alunos de uma determinada turma.
     *
     * @param turma
     * @return
     */
    public List<Aluno> listarAlunosPorTurma(Turma turma) {
        Criteria criteria = getSessao().createCriteria(Aluno.class);
        Criterion criterioDeBusca1 = Restrictions.eq("turma", turma);
        criteria.add(criterioDeBusca1);
        return criteria.list();
    }

    /**
     * Lista os alunos de uma determinada turma e filtro.
     *
     * @param turma
     * @param filtro
     * @return
     */
    public List<Aluno> listarAlunosDaTurmaPorFiltro(Turma turma, String filtro) {
        Criteria criteria = getSessao().createCriteria(Aluno.class);

        if (ValidacoesUtil.soContemNumeros(filtro)) {
            // Quando o filtro conter somente números é porque ele é uma matrícula.
            // Então é realizado a listagem por matrícula.
            Criterion criterioDeBusca1 = Restrictions.eq("turma", turma);
            Criterion criterioDeBusca2 = Restrictions.eq("matricula", Integer.parseInt(filtro));
            List<Aluno> resultados = criteria.add(criterioDeBusca1).add(criterioDeBusca2).list();
            getTransacao().commit();
            getSessao().close();
            return resultados;
        } else {
            // Quando o filtro "NÃO CONTER" somente números é porque ele é um nome.
            // Então é realizado a listagem por nome.
            // Ignorando Case Sensitive, e buscando por nomes que "CONTENHAM" o filtro, e
            // não por nomes exatamente iguais ao filtro.
            Criterion criterioDeBusca1 = Restrictions.eq("turma", turma);
            Criterion criterioDeBusca2 = Restrictions.ilike("nome", "%" + filtro + "%");
            List<Aluno> resultados = criteria.add(criterioDeBusca1).add(criterioDeBusca2).list();
            getTransacao().commit();
            getSessao().close();
            return resultados;
        }
    }
}
