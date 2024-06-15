package test;

import datos.UsuarioDao;
import domain.Usuario;

import java.util.List;

public class testUsuario {

    public static void main(String[] args) {

        UsuarioDao usuarioDao  = new UsuarioDao();


        Usuario usuarioNew = new Usuario(2,"david","sssaa");

        usuarioDao.delete(usuarioNew);


        //mostrar usuarios
        List<Usuario> UsuarioList = usuarioDao.select();

        for (Usuario usuario : UsuarioList) {
            System.out.println(usuario.getUsuario());
        }


    }
}
