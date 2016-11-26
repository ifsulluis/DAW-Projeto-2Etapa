
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;


public class EspecialidadeDAO<T> extends DAOGenerico<Especialidade>implements Serializable {

    public EspecialidadeDAO(){
        super();
        super.setClassePersistente(Especialidade.class);
        super.setOrdem("descricao");// ordem padrão
    }

}
