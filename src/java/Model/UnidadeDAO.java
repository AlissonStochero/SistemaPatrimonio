/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alisson
 */
public class UnidadeDAO {
    private Session sessao;
    private Transaction trans;
    private List<Unidade> list;
    
    public void create(Unidade unidade) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(unidade);
            trans.commit();
        } catch (PersistenceException e) {
        } finally {
            sessao.close();
        }
    }
    
    public List<Unidade> getList() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(Unidade.class);
            this.list = cri.list();
            return list;

        } finally {

            sessao.close();
        }

    }
    
    public Unidade getById(int id){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Unidade unidade = new Unidade();
            return unidade = (Unidade) sessao.get(Unidade.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return null;
    }
}
