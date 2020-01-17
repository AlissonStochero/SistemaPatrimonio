
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alisson
 */
public class DepartamentoDAO implements Serializable{
    
    private Session sessao;
    private Transaction trans;
    private List<Departamento> list;
    
    public void create(Departamento depto) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(depto);
            trans.commit();
        } catch (PersistenceException e) {
        } finally {
            sessao.close();
        }
    }
    
    public Departamento getById(int id){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Departamento depto = new Departamento();
            return depto = (Departamento) sessao.get(Departamento.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return null;
    }
    
    public List<Departamento> getList() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(Departamento.class);
            this.list = cri.list();
            return list;

        } finally {

            sessao.close();
        }

    }
}
