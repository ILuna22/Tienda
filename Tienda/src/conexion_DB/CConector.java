//Programa creado por Isaac Yahel Luna Hernandez
package conexion_DB;


import java.sql.*;
import javax.swing.JOptionPane;
public class CConector {
    
    //************************** ATRIBUTOS ********************
    private static Connection conn;
    private static String  DB = "tienda";
    private static String  USER= "root";
    private static String  PSW = "8523";
    private static String  PORT = "3306";
    private static String  URL = "jdbc:mysql://localhost:"+PORT+"/"+DB;
            
    //************************** METODOS ********************//
    public Connection conectar (){
    conn = null;
        try {
            //CREANDO UNA NUEVA CONEXIÓN //
            conn = DriverManager.getConnection(URL, USER, PSW);
        //VERIFIca si hubo un problema con la conexión//
            if (conn==null) {
                mensaje_error("ERROR: CONEXION INCORRECTA");
                
            }
        } catch (Exception e) {
            mensaje_error(""+e);
        }
        return conn; 
    }
    
    public void cerrar_conexion(Connection conn) throws SQLException{
    conn.close();
    }
        private void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "NOTIFICACIÓN", JOptionPane.PLAIN_MESSAGE);
    }

    private void mensaje_error(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    private void mensaje_advertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
    }
    
}
