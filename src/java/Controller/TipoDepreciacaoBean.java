
package Controller;

import Model.TipoDepreciacao;
import Model.TipoDepreciacaoDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class TipoDepreciacaoBean implements Serializable{
    private TipoDepreciacao tipoDep = new TipoDepreciacao();
    private TipoDepreciacaoDAO tipoDepDAO = new TipoDepreciacaoDAO();
    private List<TipoDepreciacao> list = tipoDepDAO.getList();
    private String id;
    
    public void salvaTipo(){
        this.tipoDepDAO.create(this.tipoDep);
    }
    
    public void pegaTipoDep(){
        List a = tipoDepDAO.pegaDepreciacao();
        System.out.println(a.equals(tipoDep)+"-----------------------------------------------------------------------------------------");
    }
    
    public List getAllTipo(){
        this.setList(tipoDepDAO.getList());
        return this.list;
    }
    
    public TipoDepreciacao retornaTipoDep(int id){
        this.tipoDep = this.tipoDepDAO.getById(id);
        System.out.println(this.tipoDep.getId_tipo()+"-----------buscando tipo dep-------------------------------------------------------------------");
        return this.tipoDep;
    }

    public TipoDepreciacao getTipoDep() {
        return tipoDep;
    }

    public void setTipoDep(TipoDepreciacao tipoDep) {
        this.tipoDep = tipoDep;
    }

    public TipoDepreciacaoDAO getTipoDepDAO() {
        return tipoDepDAO;
    }

    public void setTipoDepDAO(TipoDepreciacaoDAO tipoDepDAO) {
        this.tipoDepDAO = tipoDepDAO;
    }

    public List<TipoDepreciacao> getList() {
        return list;
    }

    public void setList(List<TipoDepreciacao> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public TipoDepreciacaoBean() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.tipoDep);
        hash = 83 * hash + Objects.hashCode(this.tipoDepDAO);
        hash = 83 * hash + Objects.hashCode(this.list);
        return hash;
    }

 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoDepreciacaoBean other = (TipoDepreciacaoBean) obj;
        if (!Objects.equals(this.tipoDep, other.tipoDep)) {
            return false;
        }
        return true;
    }

    
    
}
