
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FuncionarioDAO implements Serializable{
    private Session sessao;
    private Transaction trans;
    private List<Funcionario> list;
    
    public void create(Funcionario funcionario) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(funcionario);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public Funcionario getById(int id){
        System.out.println(id);
        Funcionario func = new Funcionario();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            return func = (Funcionario) sessao.get(Funcionario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return func;
    }
    
    public List<Funcionario> getList() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(Funcionario.class);
            this.list = cri.list();
            return list;

        } finally {

            sessao.close();
        }

    }
    
    public void removeFuncionario(Funcionario funcionario) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.delete(funcionario);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public void updateFuncionario(Funcionario funcionario) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(funcionario);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
}
