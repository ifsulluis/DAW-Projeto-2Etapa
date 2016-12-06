package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Consulta;
import java.io.Serializable;



public class ConsultaDAO<T> extends DAOGenerico<Consulta> implements Serializable {
 
    public ConsultaDAO() {
        super();
        super.setClassePersistente(Consulta.class);
    }
    
    
}
