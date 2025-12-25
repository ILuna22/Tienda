//Programa creado por Isaac Yahel Luna Hernandez
package gestor_operaciones;

import java.sql.*;
import conexion_DB.CConector;
import java.util.*;
import javax.swing.*;

public class CQManager {

    //////////////////        ATRIBUTOS       ////////////////////////////
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private final CConector conector = new CConector();
    ArrayList<String[]> resultados;

    //////////////////////////// METDOS ////////////////////////////////
    //LA CLASE QUERY MANAGER SE ESPECIALIZA EN EJECUTAR LAS 4 OPERACICIONES BASICAS DE UN CRUD//
    //CREAR(CREATE), OBTENER(RETRIEVE), ACTUALIZAR(UPDATE) Y BORRAR (DELATE)
    public ArrayList<String[]> buscar_objetos(String consulta) throws SQLException {
        //1.- ABRIR CONECCION//
        conn = conector.conectar();
        try {
            resultados = new ArrayList<>();
            //CREA UN OBJETO QUE SERVIRA PARA CONTROLAR LA COSNULTA 
            stmt = conn.createStatement();
            //2.- EJECUTAR QUERY
            rs = stmt.executeQuery(consulta);
            //3.- VERIFICAR LOS DATOS 
            if (rs == null) {
                mensaje_error("ELEMENTOS NO ENCONTRADOS");
                //AÑADIR LOS ELEMENTOS NO ENCONTRADOS AL ARRAYLIST
            } else {
                // Si el ResultSet no es nulo, significa que hay datos devueltos por la consulta
                // Obtenemos los metadatos del ResultSet, que nos proporcionan informaci�n sobre las columnas
                ResultSetMetaData metaData = rs.getMetaData();

                // Obtenemos el n�mero de columnas presentes en el resultado de la consulta
                int columnCount = metaData.getColumnCount();

                // Iteramos sobre cada fila del ResultSet. Esto contin�a hasta que rs.next() devuelve false
                while (rs.next()) {
                    // Creamos un array de tama�o din�mico basado en la cantidad de columnas
                    String[] fila = new String[columnCount];

                    // Iteramos sobre las columnas de la fila actual
                    for (int i = 1; i <= columnCount; i++) { // Las columnas en JDBC se indexan desde 1
                        // Obtenemos el valor de la columna actual (i) como una cadena
                        fila[i - 1] = rs.getString(i);
                    }

                    // Agregamos la fila completa (array) a la lista de resultados
                    resultados.add(fila);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // Liberar recursos
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
            }
            if (stmt != null) try {
                stmt.close();
            } catch (Exception e) {
            }
            conector.cerrar_conexion(conn); // Cerrar conexi�n
        }
        return resultados;
    }

    public boolean inserta_objeto(String consulta) throws SQLException {
        conn = conector.conectar();
        boolean retorno = true;
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.executeUpdate();

            return retorno;
        } catch (Exception e) {
            mensaje_error("Error: " + e.getMessage());
        } finally {
            if (conn != null && !conn.isClosed()) {
                conector.cerrar_conexion(conn);

            }
        }
        return retorno;
    }

    public boolean eliminar_objeto(String consulta) throws SQLException {
        conn = conector.conectar();
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.executeUpdate();

            return true;
        } catch (Exception e) {
            mensaje_error("Error: " + e.getMessage());
        } finally {
            conector.cerrar_conexion(conn);
        }
        return false;
    }

    public boolean actualizar_objeto(String consulta) throws SQLException {
        conn = conector.conectar();
        try {
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.executeUpdate();

            return true;
        } catch (Exception e) {
            mensaje_error("Error: " + e.getMessage());
        } finally {
            conector.cerrar_conexion(conn);
        }
        return false;
    }

    public void imprime_resultados(ArrayList<String[]> resultados) {
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println(i + " " + Arrays.toString(resultados.get(i)));
        }
        System.out.println(".........................................");
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
