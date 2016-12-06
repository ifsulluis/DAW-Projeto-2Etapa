package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.ConsultaExames;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.util.UtilMensagens;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@ManagedBean(name = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable {

    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    private PacienteDAO<Paciente> daoPaciente;
    private MedicoDAO<Medico> daoMedico;
    private Exame exame;
    private Boolean novoExame;

    public ControleConsulta() {
        dao = new ConsultaDAO<>();
        daoPaciente = new PacienteDAO<>();
        daoMedico = new MedicoDAO<>();
    }
    
    
    
    public void imprimeConsulta(Integer id){
        objeto = dao.localizar(id);
        List<Consulta> listaConsulta = new ArrayList<>();
        listaConsulta.add(objeto);
        HashMap parametros = new HashMap();
        parametros.put("listaExames", objeto.getExames());
        UtilRelatorios.imprimeRelatorio("relatorioConsultaJavaBeans", parametros,
                listaConsulta);
    }

    

    public String listar() {
        return "/privado/consulta/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Consulta();
        objeto.setData(Calendar.getInstance());
        // capturando o contexto do faces
        FacesContext context = FacesContext.getCurrentInstance();
        // extraindo a requisição do contexto
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // extraindo a sessão da requisição
        
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            UtilMensagens.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao persistir: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao recuperar obejto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.localizar(id);
            dao.remover(objeto);
            UtilMensagens.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro a remover objeto: " + e.getMessage());
        }
    }

    public void novoExame() {
        exame = new Exame();
        novoExame = true;
    }

    public void alterarExame(int index) {
        exame = objeto.getExames().get(index);
        novoExame = false;
    }

    public void salvarExame() {
        if (novoExame) {
            objeto.adicionarExame(exame);
        } else {
            
        }
        UtilMensagens.mensagemInformacao("Operação realizada com sucesso");
    }

   
    public void removerExame(int index) {
        objeto.removerExame(index);
        UtilMensagens.mensagemInformacao("Item removido com sucesso");
    }

    public ConsultaDAO getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Boolean getNovoExame() {
        return novoExame;
    }

    public void setNovoExame(Boolean novoExame) {
        this.novoExame = novoExame;
    }

    public PacienteDAO<Paciente> getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO<Paciente> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public MedicoDAO<Medico> getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO<Medico> daoMedico) {
        this.daoMedico = daoMedico;
    }

}