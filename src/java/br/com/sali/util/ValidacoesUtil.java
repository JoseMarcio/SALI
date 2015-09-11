package br.com.sali.util;

import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.regras.ProfessorRN;

/**
 *
 * @author SALI
 */
public class ValidacoesUtil {

    /**
     * Verifica se só contém números.
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
     * Verifica se só tem letras.
     *
     * @param texto
     * @return
     */
    public static boolean soTemLetras(String texto) {
        return texto.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$");
    }

    /**
     * Verifica se a matrícula informada já existe cadastrada para algum
     * usuário.
     *
     * @param matricula
     * @return
     *
     */
    public static boolean isExistenteMatricula(int matricula) {
        AlunoRN alunoRN = new AlunoRN();
        ProfessorRN professorRN = new ProfessorRN();
        if (alunoRN.isExisteEssaMatricula(Integer.toString(matricula))) {
            return true;
        } else {
            return professorRN.isExisteEssaMatricula(Integer.toString(matricula));
        }
    }

    /**
     * Verifica se o e-mail já se ecnotra cadastrado para algum usuário.
     *
     * @param email
     * @return
     */
    public static boolean isExistenteEmail(String email) {
        AlunoRN alunoRN = new AlunoRN();
        ProfessorRN professorRN = new ProfessorRN();
        InstituicaoRN instituicaoRN = new InstituicaoRN();

        if (instituicaoRN.isExistenteEmail(email)) {
            return true;
        } else if (professorRN.isExistenteEmail(email)) {
            return true;
        } else {
            return alunoRN.isExistenteEmail(email);
        }
    }

    /**
     * Verifica se a matrícula é um maior que "0".
     *
     * @param matricula
     * @return
     */
    public static boolean isValidaMatricula(int matricula) {
        return matricula >= 1;
    }

}
