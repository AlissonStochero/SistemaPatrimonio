
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
public class TipoDepreciacaoDAO implements Serializable{
    private Session sessao;
    private Transaction trans;
    private List<TipoDepreciacao> list;
    
    public void create(TipoDepreciacao tipoDep) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(tipoDep);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public List<TipoDepreciacao> getList() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(TipoDepreciacao.class);
            this.list = cri.list();
            return list;

        } finally {

            sessao.close();
        }

    }
    
    public TipoDepreciacao getById(int id){
        TipoDepreciacao tipoDep = new TipoDepreciacao();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            return tipoDep = (TipoDepreciacao) sessao.get(TipoDepreciacao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return tipoDep;
    }
}
