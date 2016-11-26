
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Medicamento;
import java.io.Serializable;


public class MedicamentoDAO<T> extends DAOGenerico<Medicamento>implements Serializable {

    public MedicamentoDAO(){
        super();
        super.setClassePersistente(Medicamento.class);
        super.setOrdem("nome");// ordem padrão
    }

}
