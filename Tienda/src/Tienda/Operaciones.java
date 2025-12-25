//Programa creado por Isaac Yahel Luna Hernandez
package Tienda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.util.*;
import modelos.CModelos;

public class Operaciones {

    //============================================================================================
    //                                                     INICIO PARAMETROS
    //============================================================================================  
    CModelos modelo = new CModelos();
    Scanner sc = new Scanner(System.in);
    String tabla[] = {"categoria", "compra", "detalle_compra", "detalle_perdida", "detalle_venta", "perdida", "producto", "proveedor", "venta", "usuario"};
    int numconsulta[] = {2, 4, 5, 5, 5, 4, 7, 2, 5, 4};
    int seleccion = 0;
    ArrayList<String[]> cambio = new ArrayList<>();

    //============================================================================================
    //                                                     FIN PARAMETROS
    //============================================================================================  
    public String conseguir_color(int valor) throws SQLException {
        ArrayList<String[]> Tabla = new ArrayList<>();
        Tabla = Consultar(9);
        String fila[] = Tabla.get(0);

        return fila[valor];
    }

    public ArrayList<String[]> Consultar(int seleccion) throws SQLException {
        //Obtenemos tabla
        ArrayList<String[]> proveedores = modelo.obtiene_datos_modelo(tabla[seleccion]);
        //Imprimimos tabla
        for (String[] provedor : proveedores) {
            for (int i = 0; i < provedor.length; i++) {
            }
        }
        return proveedores;
    }

    public void Insertar_Producto(String lista[]) throws SQLException {
        // Insertar un nuevo producto
        modelo.inserta_dato_tabla_producto("producto", Integer.parseInt(lista[0]), lista[1], 0, Integer.parseInt(lista[2]), Integer.parseInt(lista[3]), Integer.parseInt(lista[4]), Integer.parseInt(lista[5]));
    }

    public void Insertar(ArrayList<String[]> lista, String tablaElegida, String dato) throws SQLException {
        // Insertar un nuevo proveedor o categoria
        int id = Integer.parseInt(lista.get(lista.size() - 1)[0]) + 1;
        modelo.inserta_dato_tabla2(id, dato, tablaElegida);
        
    }

    public int buscar_tabla(String tablaElegida) {
        int numero = 0;
        for (int i = 0; i < tabla.length; i++) {
            if (tablaElegida.equals(tabla[i])) {
                numero = i;
            }
        }
        return numero;
    }

    public void Insertar_venta(String tablaElegida, ArrayList<String[]> cuenta, int total) throws SQLException {
        // Insertar un nuevo proveedor
        //BDDATOS PUEDE SER VENTAS PERDIDAS O COMPRAS
        ArrayList<String[]> BDdatos2 = new ArrayList<>();
        BDdatos2 = Consultar(buscar_tabla("detalle_" + tablaElegida));
//        int descuento = 0;
        int id2 = Integer.parseInt(BDdatos2.get(BDdatos2.size() - 1)[0]) + 1;

        ArrayList<String[]> BDdatos = new ArrayList<>();
        BDdatos = Consultar(buscar_tabla(tablaElegida));
        int descuento = 0;
        int id = Integer.parseInt(BDdatos.get(BDdatos.size() - 1)[0]) + 1;
        String dato;
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Crear un formato personalizado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Formatear la fecha
        String fecha = fechaActual.format(formato);
        // Imprimir la fecha formateada
        //PRIMERO ES HACER LA VENTA
        int numeroDeVenta = 1;

        ///VENTAAAAAAAAAAAAAAAAAAA
        //Obtener numero de venta
        for (int i = 0; i < BDdatos.size(); i++) {
            String fila[] = BDdatos.get(i);
            if (fila[2].equals(fecha)) {

                numeroDeVenta += 1;
            }
        }
        if (tablaElegida.equals("venta")) {
            modelo.inserta_dato_tabla_venta(tablaElegida, id, numeroDeVenta + "", fecha, descuento, total);
        } else {
            modelo.inserta_dato_tabla4(id, numeroDeVenta + "", tablaElegida, fecha, total);
        }

        String opcion = "detalle_" + tablaElegida;
        //consultar para ingresar cantidad
//                    cambio = Consultar(); aytudaaaaaaaaaaaaaaaaaaaaaaaa
        // necesitamos ahora los numeros no los nombres por lo que sustituiremos el nombre por id
        ArrayList<String[]> productos = new ArrayList<>();
        productos = Consultar(buscar_tabla("producto"));
        for (int i = 0; i < cuenta.size(); i++) {

            String fila[] = cuenta.get(i);

            for (int j = 0; j < productos.size(); j++) {
                String fila2[] = productos.get(j);
                //buscamos producto, si coinciden remplazamos 
                if (fila[0].equals(fila2[1])) {
                    fila[0] = fila2[0];
                    cuenta.set(i, fila);
                }
            }
        }
//        System.out.println("hayy "+productos.size()+cuenta.size());
//verificacion de cuenta
        for (int i = 0; i < cuenta.size(); i++) {

            String fila[] = cuenta.get(i);
            for (int j = 0; j < fila.length; j++) {

            }

            for (String[] identificador : productos) {
                if (identificador[0].equals(fila[0])) {
                    if (tablaElegida.equals("venta")) {
                        //actualizar cantidad de productos
                        int cantidadRestadav = Integer.parseInt(identificador[2]) - Integer.parseInt(fila[1]);
                        Actualizar(Integer.parseInt(fila[0]), "producto", "cantidad", cantidadRestadav + "");
//                        total = cantidad * Integer.parseInt(identificador[3]);
                    } else {
                        //actualizar cantidad de productos
                        if (tablaElegida.equals("compra")) {
                            int cantidadRestadac = Integer.parseInt(identificador[2]) + Integer.parseInt(fila[1]);
                            Actualizar(Integer.parseInt(fila[0]), "producto", "cantidad", cantidadRestadac + "");

                        } else {
                            int cantidadRestadap = Integer.parseInt(identificador[2]) - Integer.parseInt(fila[1]);
                            Actualizar(Integer.parseInt(fila[0]), "producto", "cantidad", cantidadRestadap + "");

                        }
//                        total = cantidad * Integer.parseInt(identificador[4]);
                    }
                }
            }
//            modelo.inserta_dato_tabla5(tablaElegida, id, cantidad, total, opcion, seleccion, producto);

            modelo.inserta_dato_tabla5("detalle_" + tablaElegida, id2, Integer.parseInt(fila[1]), Integer.parseInt(fila[3]), tablaElegida, id, Integer.parseInt(fila[0]));
            id2 += 1;
        }

    }

    public void Eliminar(String tablaElegida, int id) {
        modelo.elimina_dato(id, tablaElegida);

    }

    public void Actualizar(int id, String tabla, String columna, String dato) {
        modelo.actualiza_dato(id, tabla, columna, dato);

    }

}
