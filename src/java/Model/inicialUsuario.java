/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author alisson
 */
public class inicialUsuario {
    public static void main(String[] args) {
        
        Usuario usuario = new Usuario();
        UsuarioDAO userDAO= new UsuarioDAO();
        
        usuario.setNome_usuario("alisson");
        usuario.setSenha("123");
        
        userDAO.create(usuario);
        
    }
}
