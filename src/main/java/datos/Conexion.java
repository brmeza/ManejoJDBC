package datos;

import java.sql.*;


/**
 * clase encargada de la conexion
 */
public class Conexion {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/test?useSSL=false&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);

    }

    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }

    public static void close(PreparedStatement stm) throws SQLException{
        stm.close();
    }

    public static void close (Connection con) throws SQLException{
        con.close();
    }

}
