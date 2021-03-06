package br.com.sali.util;

import br.com.sali.dao.UsuarioDao;
import br.com.sali.modelo.Turma;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.TopicoRN;
import br.com.sali.regras.TurmaRN;

/**
 * Trata das validações necessárias ao sistema.
 *
 * @author SALI
 */
public class ValidacoesUtil {

    /**
     * Verifica se só contém números. Se tiver somente números na String é
     * retornado "true", senão é retornado "false".
     *
     * @param texto
     * @return
     */
    public static boolean soContemNumeros(String texto) {
        if (texto == null) {
            return false;
        }
        for (char letra : texto.toCharArray()) {
            if (letra < '0' || letra > '9') {
                return false;
            }
        }
        return true;

    }

    /**
     * Verifica se só tem letras. Se tiver somente letras na String é retornado
     * "true", senão é retornado "false".
     *
     * @param texto
     * @return
     */
    public static boolean soTemLetras(String texto) {
        return texto.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü ]*$");
    }

    /**
     * Verifica se só tem espaço. Se tiver somente espaços na String é retornado
     * "true", senão é retornado "false".
     *
     * @param texto
     * @return
     */
    public static boolean soTemEspaco(String texto) {
        return texto.trim().isEmpty();
    }

    /**
     * Verifica se tem algum espaço no texto. Se tiver algum espaço no texto é
     * retornado "true", senão é retornado "false".
     *
     * @param texto
     * @return
     */
    public static boolean temEspacoNoTexto(String texto) {
        return texto.contains(" ");
    }

    /**
     * Verifica se a matrícula informada já existe cadastrada para algum
     * usuário. Se a matrícula já estiver cadastrada para algum usuário é
     * retornado "true", senão é retornado "false".
     *
     * @param matricula
     * @return
     *
     */
    public static boolean isExistenteMatricula(String matricula) {
        AlunoRN alunoRN = new AlunoRN();
        ProfessorRN professorRN = new ProfessorRN();
        if (alunoRN.isExisteEssaMatricula(matricula)) {
            return true;
        } else {
            return professorRN.isExisteEssaMatricula(matricula);
        }
    }

    /**
     * Verifica se já existe um tópico com o mesmo nome que está sendo
     * informado.
     *
     * @param nome
     * @param turma
     * @return
     */
    public static boolean isExistenteNomeTopico(String nome, Turma turma) {
        TopicoRN topicoRN = new TopicoRN();
        return topicoRN.isExiteNomeTopico(nome,turma);
    }

    /**
     * Verifica se o email já se encontra cadastrado para algum usuário. Se o
     * email já estiver em uso é retornado "true", senão é retornado "false".
     *
     * @param email
     * @return
     */
    public static boolean isExistenteEmail(String email) {
        UsuarioDao usuarioDao = new UsuarioDao();
        return usuarioDao.isExistenteEmail(Usuario.class, email);
    }

    /**
     * Verifica se a matrícula é válida. Para a matrícula ser válida é
     * necessário que ela seja um número inteiro maior que "0"
     *
     * @param matriculaString
     * @return
     */
    public static boolean isValidaMatricula(String matriculaString) {
        Integer matricula = Integer.parseInt(matriculaString);
        return matricula > 0;
    }

    /**
     * Verifica se o nome(da turma) informado já existe cadastrado no banco de
     * dados. Se existir é retornado "true", senão existir é retornado "false".
     *
     * @param nomeInformado
     * @return
     */
    public static boolean isExistenteNomeDaTurma(String nomeInformado) {
        TurmaRN turmaRN = new TurmaRN();
        return turmaRN.isExistenteNome(nomeInformado);
    }
}
