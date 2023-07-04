/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Model.usuario;
import java.util.List;

/**
 *
 * @author Davs
 */
public interface Iusuario {
    
    List<usuario> getAllUsers();
    void addUser(usuario user);
    void removeUser(usuario user);
    void updateUser(usuario user);
    boolean EstadoLogin (String usu, String pas);
    usuario ObjetoLogin (String usu, String pas);
    int ObtenerCodigo(String usu);

    
    
}
