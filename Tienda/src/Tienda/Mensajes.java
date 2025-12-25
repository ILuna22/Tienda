/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//Programa creado por Isaac Yahel Luna Hernandez
package Tienda;
import javax.swing.*;
/**
 *
 * @author Ivanvalle_
 */
public class Mensajes {
  public void mensaje(String mensaje){
  JOptionPane.showMessageDialog(null, mensaje, "NOTIFICACIÓN", JOptionPane.PLAIN_MESSAGE);
  }
  public void mensaje_advertencia(){
  
  }
  public void mensaje_error(String mensaje){
  JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
  }
  public String mensaje_de_pregunta(String mensaje){
  String obtencion =JOptionPane.showInputDialog(mensaje);
  return obtencion;
  }
  public boolean mensaje_confirmacion(String mensaje,String si_m,String no_m){
 boolean opcio =true;
     int opcion = JOptionPane.showConfirmDialog(null, mensaje);
  
  if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, si_m, "Aceptado", JOptionPane.INFORMATION_MESSAGE);
            opcio=true;
  } else if (opcion == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, no_m, "Denegado", JOptionPane.WARNING_MESSAGE);
            opcio=false;
        } else if (opcion == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Acción cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
            opcio=false;
        }
  return opcio;
    }
  }
    

