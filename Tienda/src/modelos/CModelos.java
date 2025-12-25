//Programa creado por Isaac Yahel Luna Hernandez
package modelos;

import gestor_operaciones.CQManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import Tienda.Operaciones;
/**
 * La clase modelo contiene todos los metodos con las consultas necesarias para
 * comunicarse con la base de datos. private Connection conn;
 */
public class CModelos {

    private ArrayList<String[]> resultados;

    private CQManager mng = new CQManager();
// consulta

    public ArrayList<String[]> obtiene_datos_modelo(String tabla_consulta) throws SQLException {
        String consulta = "SELECT * FROM `" + tabla_consulta + "`ORDER BY id" + tabla_consulta + ";";
        return resultados = mng.buscar_objetos(consulta);
        // return resultados;
    }
// inserta

    public void inserta_dato_tabla2(int id, String dato, String tabla) {
        String consulta = "INSERT INTO " + tabla + " (id" + tabla + ", " + tabla + ") VALUES"
                + " ('"
                + id + "','"
                + dato
                + "');";
        try {
            if (mng.inserta_objeto(consulta)) {
               // mensaje("INSERCCION CORRECTA");
            } else {
                //mensaje("INSERCCION INCORRECTA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inserta_dato_tabla4(int id, String dato, String tabla, String fecha, int total) {
        String consulta = "INSERT INTO " + tabla + " (id" + tabla + ", " + tabla + ", fecha, total) VALUES ("
                + "'" + id + "', "
                + "'" + dato + "', "
                + "'" + fecha + "', "
                + "'" + total + "');";

        try {
            if (mng.inserta_objeto(consulta)) {
                //mensaje("INSERCCION CORRECTA");
            } else {
                //mensaje("INSERCCION INCORRECTA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inserta_dato_tabla5(String tabla, int id, int cantidad, int total, String opcion, int eleccion, int producto) {

        String consulta = "INSERT INTO " + tabla + " (id" + tabla + ", cantidad, total, " + opcion + "_id" + opcion + ",Producto_idProducto) VALUES ("
                + "'" + id + "', "
                + "'" + cantidad + "', "
                + "'" + total + "', "
                + "'" + eleccion + "', "
                + "'" + producto + "');";


        try {
            if (mng.inserta_objeto(consulta)) {
                //mensaje("INSERCCION CORRECTA");
            } else {
                //mensaje("INSERCCION INCORRECTA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inserta_dato_tabla_venta(String tabla, int id, String venta, String fecha, int descuento, int total) {
        //INSERT INTO `tienda`.`venta` (`idVenta`, `Venta`, `Fecha`, `Descuento`, `Total`) VALUES ('2', '2', '2', '2', '2');
        String consulta = "INSERT INTO " + tabla + " (id" + tabla + ", " + tabla + ", fecha, descuento, total) VALUES ("
                + "'" + id + "', "
                + "'" + venta + "', "
                + "'" + fecha + "', "
                + "'" + descuento + "', "
                + "'" + total + "');";


        try {
            if (mng.inserta_objeto(consulta)) {
                //mensaje("INSERCCION CORRECTA");
            } else {
                //mensaje("INSERCCION INCORRECTA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inserta_dato_tabla_producto(String tabla, int id, String producto, int cantidad, int precioVenta, int precioCompra, int categoria, int proveedor) {
//     INSERT INTO `tienda`.`producto` (`idProducto`, `Producto`, `Cantidad`, `Precio_venta`, `Precio_compra`, `Categoria_idCategoria`, `Proveedor_idProveedor`) VALUES ('3', 'nom', '3', '12', '22', '1', '1');
        String consulta = "INSERT INTO " + tabla + " (id" + tabla + ",producto, cantidad, precio_venta, precio_compra, categoria_idcategoria, proveedor_idproveedor) VALUES ("
                + "'" + id + "', "
                + "'" + producto + "', "
                + "'" + cantidad + "', "
                + "'" + precioVenta + "', "
                + "'" + precioCompra + "', "
                + "'" + categoria + "', "
                + "'" + proveedor + "');";


        try {
            if (mng.inserta_objeto(consulta)) {
                //mensaje("INSERCCION CORRECTA");
            } else {
                //mensaje("INSERCCION INCORRECTA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// elimina

    public void elimina_dato(int id, String tabla) {
//        DELETE FROM `tienda`.`proveedor` WHERE (`idProveedor` = '13');
        String consulta = "DELETE FROM " + tabla + " WHERE id" + tabla + " = '" + id + "';";
        try {
            boolean a = mng.eliminar_objeto(consulta);
            //System.out.flush();  
            if (a) {

                //mensaje("INSERCCION CORRECTA");
            } else {
                //mensaje("INSERCCION INCORRECTA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualiza_dato(int id, String tabla, String columna, String dato) {
//        UPDATE nombre_de_la_tabla
//SET columna1 = valor1, columna2 = valor2, ...
//WHERE condici�n;

//        UPDATE `tienda`.`categoria` SET `Categoria` = 'Bebida' WHERE (`idCategoria` = '1');
        String consulta = "UPDATE  " + tabla + " SET " + columna + " = "+ dato+ " WHERE (id"+tabla+" = "+id+"  );";
        try {
            boolean a = mng.actualizar_objeto(consulta);
            if (a) {

                //mensaje("INSERCCION CORRECTA");
            } else {
                //mensaje("INSERCCION INCORRECTA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

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
