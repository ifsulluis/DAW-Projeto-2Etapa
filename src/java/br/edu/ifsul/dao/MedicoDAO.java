
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Medico;
import java.io.Serializable;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
public class MedicoDAO<T> extends DAOGenerico<Medico>implements Serializable {

    public MedicoDAO(){
        super();
        super.setClassePersistente(Medico.class);
        super.setOrdem("nome");// ordem padrão
    }

}
