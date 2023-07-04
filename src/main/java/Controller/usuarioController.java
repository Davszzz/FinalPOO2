/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.usuario;
import Services.usuarioRepository;
import java.util.List;

/**
 *
 * @author Davs
 */
public class usuarioController {
    public List<usuario> getAllUsuController()
    {
        return new usuarioRepository().getAllUsers();
    }
    public boolean LoginEstablecido(String usu, String pas)
    {
        return new usuarioRepository().EstadoLogin(usu, pas);
    }
    public void Añadir(usuario user)
    {
       new usuarioRepository().addUser(user);
    }
    public int BuscarCod(String nombre)
    {
         return new usuarioRepository().ObtenerCodigo(nombre);
            
     }
    public usuario ObjetoLogin (String usu, String pas)
    {
        return new usuarioRepository().ObjetoLogin(usu, pas);
    }
    public void Editar(usuario usu)
    {
        new usuarioRepository().updateUser(usu);
    }
    
}
