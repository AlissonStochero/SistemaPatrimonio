package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

public class UsuarioDAO implements Serializable {

    private Session sessao;
    private Transaction trans;
    private List<Usuario> list;

    /**
     * metodo de validação de usuario de acordo com o banco
     *
     * @param a
     * @return Usuario pe
     */
    public Usuario login(int a) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        Usuario pe = new Usuario();
        return pe = (Usuario) sessao.get(Usuario.class, a);
    }

    public Usuario read(int id) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        Usuario pe = new Usuario();
        return pe = (Usuario) sessao.get(Usuario.class, id);
    }

    public void create(Usuario pessoa) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(pessoa);
            trans.commit();
        } catch (PersistenceException e) {
        } finally {
            sessao.close();
        }
    }

    public void delete(Usuario pessoa) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.delete(pessoa);
            trans.commit();
        }//em caso de erro, o catch é acionado 
        catch (PersistenceException e) {
            trans.rollback();
            //System.out.println(e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void update(Usuario pessoa) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(pessoa);
            trans.commit();
        } catch (PersistenceException e) {
        } finally {
            sessao.close();
        }
    }

    public List<Usuario> getList() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = sessao.createCriteria(Usuario.class);
        return list = cri.list();
    }

    public List<Object> getListUsuarios() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        SQLQuery qry = sessao.createSQLQuery("SELECT pessoa.* from pessoa order by id_pessoa desc");
        return qry.list();
    }
    
    /**
     * Metodo que valida no banco de dados se existe o usuario e senha informados
     * @param usuario
     * @param senha
     * @return boolean
     */
    public boolean testaUuarioByLoginESenha(String usuario, String senha) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        SQLQuery qry = sessao.createSQLQuery("select * from usuario where nome_usuario = :usuario and senha = :senha"); //cria o sql 
        qry.setParameter("usuario", usuario); // insere o as informações no sql ( :usuario e :senha )
        qry.setParameter("senha", senha);

        if (qry.list().size()> 0) {

            qry.setResultTransformer(Transformers.aliasToBean(Usuario.class)); // pega o resultado da query tranformando em um obj Usuario
            Usuario u = new Usuario();
            u = (Usuario) qry.list().get(0); // pega o objeto na posição 0 da lista
            
            if (u.getId_usuario().intValue() > 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false; //se o retorno do banco for zero.
        }

    }

}
