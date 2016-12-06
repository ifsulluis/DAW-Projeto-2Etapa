
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Paciente;
import java.io.Serializable;


public class PacienteDAO<T> extends DAOGenerico<Paciente>implements Serializable {

    public PacienteDAO(){
        super();
        super.setClassePersistente(Paciente.class);
        super.setOrdem("nome");// ordem padrão
    }

}
