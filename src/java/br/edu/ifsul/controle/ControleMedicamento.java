package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleMedicamento")
@ViewScoped
public class ControleMedicamento implements Serializable {
    private MedicamentoDAO<Medicamento> dao;
    private Medicamento objeto;
    
    
    
    public ControleMedicamento(){
        dao = new MedicamentoDAO<>();
        }
    
    public String listar(){
        return "/privado/medicamento/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Medicamento();        
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
        

    public MedicamentoDAO getDao() {
        return dao;
    }

    public void setDao(MedicamentoDAO dao) {
        this.dao = dao;
    }

    public Medicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) {
        this.objeto = objeto;
    }

 
}
