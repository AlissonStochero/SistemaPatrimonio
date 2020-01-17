
package Controller;

import Model.Unidade;
import Model.UnidadeDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class UnidadeBean implements Serializable{
    
    private Unidade unidade = new Unidade();
    private UnidadeDAO unidadeDAO = new UnidadeDAO();
    private List<Unidade> listUnidade;
    
    public void salvarUnidade(){
        unidadeDAO.create(unidade);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Unidade "+unidade.getNome()+" cadastrada com sucesso"));
    }
    
    public List getAllListUnidade(){
        this.setListUnidade(unidadeDAO.getList());
        return this.listUnidade;
    }

    
    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public UnidadeDAO getUnidadeDAO() {
        return unidadeDAO;
    }

    public void setUnidadeDAO(UnidadeDAO unidadeDAO) {
        this.unidadeDAO = unidadeDAO;
    }

    public List<Unidade> getListUnidade() {
        return listUnidade;
    }

    public void setListUnidade(List<Unidade> listUnidade) {
        this.listUnidade = listUnidade;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.unidade);
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
        final UnidadeBean other = (UnidadeBean) obj;
        if (!Objects.equals(this.unidade, other.unidade)) {
            return false;
        }
        return true;
    }

    public UnidadeBean() {
    }
    
}
