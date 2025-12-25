/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//Programa creado por Isaac Yahel Luna Hernandez
package Tienda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RadialGradientPaint;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/*
 * @author Ivanvalle_
 */
public class Reportes extends javax.swing.JFrame {

    Mensajes cmensaje = new Mensajes();
    Inventario inventario = new Inventario();
    Operaciones opera = new Operaciones();
    Ajuste aju = new Ajuste();
    String color;
    Color PanelOpcionColor;
    Color FondoOpcionColor;
    Color colorpordefectofondo = new Color(215, 235, 235);
    Color colorpordefectobarra = new Color(39, 77, 96);
    ArrayList<String[]> venta = new ArrayList<String[]>();
    String fecha;
    String opciones[] = {"Ventas diarias", "Venta mensuales", "Ventas anuales"};
    String opciongrafica = "1";
    int dias []=new int[31];
    int mes []=new int[12];
    int anual []=new int[40];
    ArrayList<String[]>opeee = new ArrayList<>();
String usuario;
    /**
     * Creates new form Repor
     */
    public Reportes() {
        initComponents();
        try {
            usuario = opera.conseguir_color(3);
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        opeee.add(opciones);
        for (int i = 0; i < opciones.length; i++) {
            jComboBox1.addItem(opciones[i]);
        }

        PanelOpcionColor = aju.mandarfechaycolores(2);
        FondoOpcionColor = aju.mandarfechaycolores(3);
        jPBARRA.setBackground(PanelOpcionColor);
        jPFONDO.setBackground(FondoOpcionColor);
        jPReport.setBackground(FondoOpcionColor);
        jPanel1.setBackground(PanelOpcionColor);
        this.setLocationRelativeTo(null);
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formatear la fecha
        fecha = fechaActual.format(formato);
        jLFecha.setText(fecha + "");
        try {
            venta = opera.Consultar(8);
            mostrar_grafica(jpgrafica, "1",dias,"Dia: ",fecha.substring(0, 2));
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrar_grafica(JPanel panell, String opcion,int []datosgrafica, String a, String b) throws SQLException {
        
        generar_grafica(venta, jComboBox1.getSelectedIndex());
        panell.removeAll();
        panell.revalidate();

        if (opcion.equals("1")) {
            DefaultPieDataset datos = new DefaultPieDataset();
            // nombre valor 
            for (int i = 0; i < datosgrafica.length; i++) {
                if(datosgrafica[i]!=0){
                    
                datos.setValue((i+1)+"", datosgrafica[i]);
                }
            }
            JFreeChart grafico_circular = ChartFactory.createPieChart(
                    (String)jComboBox1.getSelectedItem(), //nombre del grafico
                    datos, //datos
                    true, //nombre de las categorias
                    true, //herramientas
                    false //generacion de url
            );
            ChartPanel panel = new ChartPanel(grafico_circular); //
            panel.setMouseWheelEnabled(true);                             //para que se pueda mover la rueda del raton
            panel.setPreferredSize(new Dimension(400, 300));         //asignamos dimension
            panell.setLayout(new BorderLayout());
            panell.add(panel, BorderLayout.NORTH);
            pack();
        } else {
            DefaultCategoryDataset datos = new DefaultCategoryDataset();
            for (int i = 0; i < datosgrafica.length; i++) {
                if(datosgrafica[i]!=0){
                    
            datos.setValue(datosgrafica[i], a+ b+ "", (i+1)+"");
            
                }
            }
            JFreeChart barras = ChartFactory.createBarChart3D((String)jComboBox1.getSelectedItem(), //Nombre del grafico
                    (String)jComboBox1.getSelectedItem(), //Nombre de las barras o columnas
                    "Cantidad $",
                    datos, //Datos del grafico
                    PlotOrientation.VERTICAL, //Orientacion
                    true, //barras individuales por color
                    true, //Texto emergente
                    false //url del grafico
            );
            ChartPanel panel = new ChartPanel(barras); //
            panel.setMouseWheelEnabled(true);                             //para que se pueda mover la rueda del raton
            panel.setPreferredSize(new Dimension(400, 300));         //asignamos dimension
            panell.setLayout(new BorderLayout());
            panell.add(panel, BorderLayout.NORTH);
            pack();

        }
        repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPFONDO = new javax.swing.JPanel();
        jPBARRA = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPReport = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jpgrafica = new javax.swing.JPanel();
        Bgrafica = new javax.swing.JButton();
        jBgraficasbarra = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLFecha = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPFONDO.setBackground(new java.awt.Color(215, 235, 235));
        jPFONDO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPFONDO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPBARRA.setBackground(new java.awt.Color(39, 77, 96));
        jPBARRA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listas-de-verificacion.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPBARRA.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 64, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventas (1).png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPBARRA.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 114, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bolsa-de-dinero (1).png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPBARRA.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 164, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/verificar (1).png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPBARRA.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 214, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/grafico-de-torta.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPBARRA.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 264, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Inicio");
        jPBARRA.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 30, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/casa (4).png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPBARRA.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Inventario");
        jPBARRA.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 75, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ventas");
        jPBARRA.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Perdidas");
        jPBARRA.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Compras");
        jPBARRA.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel15.setText("Reportes");
        jPBARRA.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jPReport.setBackground(new java.awt.Color(215, 235, 235));

        javax.swing.GroupLayout jPReportLayout = new javax.swing.GroupLayout(jPReport);
        jPReport.setLayout(jPReportLayout);
        jPReportLayout.setHorizontalGroup(
            jPReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPReportLayout.setVerticalGroup(
            jPReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPBARRA.add(jPReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 130, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/configuraciones.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPBARRA.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ajustes");
        jPBARRA.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/q3.png"))); // NOI18N
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPBARRA.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, 130));

        jPFONDO.add(jPBARRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 550));

        jPFONDO.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 150, -1));

        javax.swing.GroupLayout jpgraficaLayout = new javax.swing.GroupLayout(jpgrafica);
        jpgrafica.setLayout(jpgraficaLayout);
        jpgraficaLayout.setHorizontalGroup(
            jpgraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jpgraficaLayout.setVerticalGroup(
            jpgraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        jPFONDO.add(jpgrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 500, 310));

        Bgrafica.setText("GRAFICAR PASTEL");
        Bgrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BgraficaActionPerformed(evt);
            }
        });
        jPFONDO.add(Bgrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jBgraficasbarra.setText("GRAFICAR BARRAS");
        jBgraficasbarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBgraficasbarraActionPerformed(evt);
            }
        });
        jPFONDO.add(jBgraficasbarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLFecha.setBackground(new java.awt.Color(255, 255, 255));
        jLFecha.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLFecha.setText("02/12/2024");
        jLFecha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, -1));

        jPFONDO.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 80, 20));

        jLabel19.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel19.setText("?");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPFONDO.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 520, 20, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Grafica");
        jPFONDO.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Reportes");
        jPFONDO.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tipo de Grafica");
        jPFONDO.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPFONDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPFONDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public  void limpiar_tablas(int []arreglo){
        for (int i = 0; i <arreglo.length; i++) {
            arreglo[i]=0;
        }
    }
    public void grafica_inicio(){
    
    }
    public int[] generar_grafica(ArrayList<String[]> lista, int op) {
        int total = 0;
        
        //opcion del dia
        if (op == 0) {
            for (int i = 0; i < venta.size(); i++) {
                String fila[] = venta.get(i);
                //mismo dia
                for (int j = 0; j < dias.length; j++) {
                if ((j+1)== Integer.parseInt(fila[2].substring(0,2))&&fecha.substring(3,5).equals(fila[2].substring(3,5))) {
                    dias[j]+=Integer.parseInt(fila[4]);
                    
                   }
                }
            }
        }
        if(op==1){
            for (int i = 0; i < venta.size(); i++) {
                String fila[] = venta.get(i);
                //mismo dia
                for (int j = 0; j < mes.length; j++) {
                if ((j+1)== Integer.parseInt(fila[2].substring(3,5))&&fecha.substring(8,10).equals(fila[2].substring(8,10))) {
                    mes[j]+=Integer.parseInt(fila[4]);
                    
                   }
                }
            }
            
        }
        if(op==2){
            for (int i = 0; i < venta.size(); i++) {
                String fila[] = venta.get(i);
                //mismo dia
                for (int j = 20; j < anual.length; j++) {
                if ((j+1)== Integer.parseInt(fila[2].substring(8,10))) {
                    anual[j]+=Integer.parseInt(fila[4]);
                    
                   }
                }
            }
            
        }
        
        return dias;
    }
    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Inicio newframe = new Inicio();
        newframe.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Inventario newframe = new Inventario();
        if(aju.contraseña(newframe)== true){
            dispose();   
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        Ventas newframe = new Ventas();
        if(aju.contraseña(newframe)== true){
            dispose();   
        }
        
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        Perdidas newframe = new Perdidas();
        if(aju.contraseña(newframe)== true){
            dispose();   
        }
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Comprar newframe = new Comprar();
        if(aju.contraseña(newframe)== true){
            dispose();   
        }
        
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Reportes newframe = new Reportes();
        newframe.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        Ajuste newframe = new Ajuste();
        newframe.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void BgraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BgraficaActionPerformed
        limpiar_tablas(dias);
        limpiar_tablas(mes);
        limpiar_tablas(anual);
        try {
            //       dias= generar_grafica(venta, 1);
            if(jComboBox1.getSelectedIndex()==0){
            mostrar_grafica(jpgrafica, "1",dias,"Dia: ",fecha.substring(0, 2));
                
            }else if(jComboBox1.getSelectedIndex()==1){
            mostrar_grafica(jpgrafica, "1",mes,"Mes: ", fecha.substring(3,5));
                
            }else if(jComboBox1.getSelectedIndex()==2){
            mostrar_grafica(jpgrafica, "1",anual,"Año", fecha.substring(8,10));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BgraficaActionPerformed

    private void jBgraficasbarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBgraficasbarraActionPerformed
       try {
            //       dias= generar_grafica(venta, 1);
            if(jComboBox1.getSelectedIndex()==0){
            mostrar_grafica(jpgrafica, "2",dias,"Mes: ",fecha.substring(3, 5));
                
            }else if(jComboBox1.getSelectedIndex()==1){
            mostrar_grafica(jpgrafica, "2",mes,"Año: ", fecha.substring(6,10));
                
            }else if(jComboBox1.getSelectedIndex()==2){
            mostrar_grafica(jpgrafica, "2",anual,"Ciclo: ", "Ciclo 2020-2040");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBgraficasbarraActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        cmensaje.mensaje("Si tienes alguna duda con tu inicio, "
            + "puede checar el manual de usuario, punto 6.-Reportes");
    }//GEN-LAST:event_jLabel19MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bgrafica;
    private javax.swing.JButton jBgraficasbarra;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLFecha;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPBARRA;
    private javax.swing.JPanel jPFONDO;
    private javax.swing.JPanel jPReport;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpgrafica;
    // End of variables declaration//GEN-END:variables
}
