package datos;
import  static datos.Conexion.*;

import domain.Persona;
import domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDao {

    private static final String SQL_SELECT = "SELECT * FROM USUARIO";
    private static final String SQL_INSERT="INSERT INTO usuario (`usuario`,`password`) VALUES (?,?)";
    private static final String SQL_UPDATE="UPDATE usuario SET usuario = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE="DELETE FROM usuario WHERE id_usuario = ?";


    //Select
    public List<Usuario> select(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            pst = conn.prepareStatement(SQL_SELECT);
            rs = pst.executeQuery();
            while (rs.next()){
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuarios.add(usuario);
            }
        }catch (SQLException e){
            e.printStackTrace(System.out);
        }finally {
            try {
                close(rs);
                close(pst);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return usuarios;
    }


    //insert
    public int insert (Usuario usuario){
        Connection conn = null;
        PreparedStatement pst = null;
        int cambios=0;
        try {
            conn = Conexion.getConnection();
            pst = conn.prepareStatement(SQL_INSERT);
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getPassword());
            pst.executeUpdate();
            cambios++;
        }catch (SQLException e){


        }
        return cambios;
    }


    //Update
    public int update(Usuario usuario){
        Connection conn = null;
        PreparedStatement pst = null;
        int cambios=0;
        try {
            conn = Conexion.getConnection();
            pst = conn.prepareStatement(SQL_UPDATE);
            pst.setString(1,usuario.getUsuario());
            pst.setString(2,usuario.getPassword());
            pst.setInt(3,usuario.getIdUsuario());
            pst.executeUpdate();
            cambios++;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cambios;
    }


    public void delete(Usuario usuario){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Conexion.getConnection();
            pst = conn.prepareStatement(SQL_DELETE);
            pst.setInt(1,usuario.getIdUsuario());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace(System.out);
        }

    }
    //Delete

}
