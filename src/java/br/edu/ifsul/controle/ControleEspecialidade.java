
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleEspecialidade")
@SessionScoped
public class ControleEspecialidade implements Serializable {
    private EspecialidadeDAO<Especialidade> dao;
    private Especialidade objeto;
    
    public ControleEspecialidade(){
        dao = new EspecialidadeDAO<>();
    }
    
    public String listar(){
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Especialidade();
        return "formulario";
    }
    
    public String salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }        
        if(persistiu){
            UtilMensagens.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());
            return "formulario";
        }                        
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: "+UtilMensagens.getMensagemErro(e));
            return "listar";
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
        

    public EspecialidadeDAO getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }
}
