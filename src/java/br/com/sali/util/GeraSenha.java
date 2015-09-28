package br.com.sali.util;

import java.util.UUID;

/**
 *
 * @author SALI
 */
public class GeraSenha {

    /**
     * Gera uma string aleatoria.
     *
     *
     * @return
     */
    public static String novaSenha() {
        UUID uuid = UUID.randomUUID();
        String textoAleatorio = uuid.toString();
        return textoAleatorio.substring(0, 10);
    }

}
