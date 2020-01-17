/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
    
    private Usuario usuario = new Usuario();
    private UsuarioDAO userDAO=new UsuarioDAO();
    
    
    public void login(){
        Boolean ok =userDAO.testaUuarioByLoginESenha(usuario.getNome_usuario(), usuario.getSenha());
        if(ok==true){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../sistema/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("blah.jsf");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        };
    }
    
    public String logout(){
        return "../login/index";
    }
    
    public void create(){
        if(!usuario.getNome_usuario().isEmpty()){
            userDAO.create(usuario);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UsuarioDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UsuarioBean() {
    }
    
}
