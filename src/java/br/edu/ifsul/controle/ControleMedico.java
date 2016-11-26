package br.edu.ifsul.controle;




import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleMedico")
@ViewScoped
public class ControleMedico implements Serializable {
    private MedicoDAO<Medico> dao;
    private Medico objeto;
    private EspecialidadeDAO<Especialidade> daoEspecialidade;
    
    public ControleMedico(){
        dao = new MedicoDAO<>();
        daoEspecialidade = new EspecialidadeDAO<>();
    }
    
    public String listar(){
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Medico();        
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }        
        if(persistiu){
            UtilMensagens.mensagemInformacao(dao.getMensagem());            
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());            
        }                        
    }    
    
    public void editar(Integer id){
        try {
            objeto = dao.localizar(id);            
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: "+UtilMensagens.getMensagemErro(e));            
        }
    }
    
    public void remover(Integer id){
        try {
            objeto = dao.localizar(id);
            if(dao.remover(objeto)){
                UtilMensagens.mensagemInformacao(dao.getMensagem());
            } else {
                UtilMensagens.mensagemErro(dao.getMensagem());
            }            
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: "+UtilMensagens.getMensagemErro(e));            
        }
    }    
        

    public MedicoDAO getDao() {
        return dao;
    }

    public void setDao(MedicoDAO dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
}
