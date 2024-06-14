package datos;

import domain.Persona;
import static datos.Conexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private static final String SQL_SELECT = "SELECT * FROM PERSONA";

    private static final String SQL_INSERT = "INSERT INTO persona (`nombre`, `apellido`, `email`, `telefono`) VALUES (?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?,apellido = ?,email = ?,telefono = ? WHERE id_persona = ?";

    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";


    public List<Persona> seleccionar(){
        Connection conn = null;
        PreparedStatement stm =  null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stm = conn.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();


            while (rs.next()){
                persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setCorreo(rs.getString("email"));
                persona.setTelefono(rs.getString("telefono"));

                personas.add(persona);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
            return personas;
        }
    }

    public int insertar(Persona persona){
        Connection conn= null;
        PreparedStatement stm = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stm = conn.prepareStatement(SQL_INSERT);
            stm.setString(1, persona.getNombre());
            stm.setString(2, persona.getApellido());
            stm.setString(3, persona.getCorreo());
            stm.setString(4, persona.getTelefono());
            registros = stm.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int update (Persona persona){
        Connection conn = null;
        PreparedStatement pst = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            pst = conn.prepareStatement(SQL_UPDATE);
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getCorreo());
            pst.setString(4, persona.getTelefono());
            pst.setInt(5, persona.getIdPersona());
            registros = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;

    }

    public void delete (int id){
        Connection conn =null;
        PreparedStatement pst = null;

        try {
            conn = Conexion.getConnection();
            pst = conn.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

    }


}
