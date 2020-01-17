
package Controller;

import Model.Departamento;
import Model.DepartamentoDAO;
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
public class DepartamentoBean implements Serializable{
    
    private Departamento depto = new Departamento();
    private DepartamentoDAO deptoDAO = new DepartamentoDAO();
    private List<Departamento> list;
    
    public void salvarDepto(){
        deptoDAO.create(depto);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Departamento "+depto.getNome()+" cadastrado com sucesso"));
    }
    
    public List getAllListDepto(){
        this.setList(deptoDAO.getList());
        return this.list;
    }

    public Departamento getDepto() {
        return depto;
    }

    public void setDepto(Departamento depto) {
        this.depto = depto;
    }

    public DepartamentoDAO getDeptoDAO() {
        return deptoDAO;
    }

    public void setDeptoDAO(DepartamentoDAO deptoDAO) {
        this.deptoDAO = deptoDAO;
    }

    public List<Departamento> getList() {
        return list;
    }

    public void setList(List<Departamento> list) {
        this.list = list;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.depto);
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
        final DepartamentoBean other = (DepartamentoBean) obj;
        if (!Objects.equals(this.depto, other.depto)) {
            return false;
        }
        return true;
    }
    public DepartamentoBean() {
    }
    
    
}
