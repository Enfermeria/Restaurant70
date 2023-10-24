/*
 	Trabajo práctico final de la Guía 6 del curso Desarrollo de Apps
	Universidad de La Punta en el marco del proyecto Argentina Programa 4.0

	Integrantes:
		John David Molina Velarde
		Leticia Mores
		Enrique Germán Martínez
		Carlos Eduardo Beltrán
 */
package vistas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Administracion extends javax.swing.JFrame {
	private final String fondo = "/imagenes/admResto1744x1140.jpg";
	
	
	public Administracion() {
		//this.setContentPane(new PanelImagenFondo());
		initComponents();
	}

	
	
	/**
	 * desvuelve el escritorio para que sea manipulado desde afuera
	 * @return el JDesktopPane
	 */
	public javax.swing.JDesktopPane getEscritorio(){
		return escritorio;
	} //getEscritorio
	
	/*
		//esto es lo que hace el codigo generado con el escritorio en initComponents()
		escritorio = new javax.swing.JDesktopPane();
	
		//luego al final
		 escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        escritorio.setPreferredSize(new java.awt.Dimension(1024, 725));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1187, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(botonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1187, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );

        pack();
	*/
	
	/**
	 * Para poder poner el ícono de la aplicación en la ventana
	 * @return 
	 */	
	@Override
	public Image getIconImage() { // defino el icono del jFrame
		Image retValue = Toolkit.getDefaultToolkit().
				getImage(ClassLoader.getSystemResource("imagenes/iconoComida.png")); //icono de la aplicación
		return retValue;
	} // getIconImage
	
	
	
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonera = new javax.swing.JPanel();
        btnProductos = new javax.swing.JButton();
        btnMesas = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnServicios = new javax.swing.JButton();
        btnFacturacion = new javax.swing.JButton();
        // =========== esto es código mío previo ===============
        ImageIcon icono = new ImageIcon(getClass().getResource(fondo));
        Image imagen = icono.getImage();
        // =========== fin codigo mio previo  ===============
        escritorio = new javax.swing.JDesktopPane(){
            //============ empieza codigo mio interno ================
            public void paintComponent(Graphics g){
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
            //============ fin código mio interno =================
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        btnProductos.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/adm_comida_180x98.jpg"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setToolTipText("");
        btnProductos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProductos.setBorderPainted(false);
        btnProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnMesas.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        btnMesas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/adm_mesa_180x98.jpg"))); // NOI18N
        btnMesas.setText("Mesas");
        btnMesas.setToolTipText("");
        btnMesas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMesas.setBorderPainted(false);
        btnMesas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMesas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesasActionPerformed(evt);
            }
        });

        btnPedidos.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        btnPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/adm_chef_180x98.jpg"))); // NOI18N
        btnPedidos.setText("Pedidos");
        btnPedidos.setToolTipText("");
        btnPedidos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPedidos.setBorderPainted(false);
        btnPedidos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPedidos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });

        btnServicios.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        btnServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/adm_mozo_180x98.jpg"))); // NOI18N
        btnServicios.setText("Servicios");
        btnServicios.setToolTipText("");
        btnServicios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnServicios.setBorderPainted(false);
        btnServicios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnServicios.setMaximumSize(new java.awt.Dimension(187, 157));
        btnServicios.setMinimumSize(new java.awt.Dimension(187, 157));
        btnServicios.setPreferredSize(new java.awt.Dimension(187, 157));
        btnServicios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosActionPerformed(evt);
            }
        });

        btnFacturacion.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        btnFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/adm_facturacion_180x98.jpg"))); // NOI18N
        btnFacturacion.setText("Facturación");
        btnFacturacion.setToolTipText("");
        btnFacturacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFacturacion.setBorderPainted(false);
        btnFacturacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFacturacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botoneraLayout = new javax.swing.GroupLayout(botonera);
        botonera.setLayout(botoneraLayout);
        botoneraLayout.setHorizontalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMesas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFacturacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        botoneraLayout.setVerticalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnProductos)
                .addGap(18, 18, 18)
                .addComponent(btnMesas)
                .addGap(18, 18, 18)
                .addComponent(btnPedidos)
                .addGap(18, 18, 18)
                .addComponent(btnServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        escritorio.setPreferredSize(new java.awt.Dimension(1024, 725));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1187, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(botonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1187, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * Carga una imagen en el fondo del escritorio.
	 */
	private void mostrarFondo(){
//		escritorio.setLayer(fondo, javax.swing.JLayeredPane.DEFAULT_LAYER);
//		javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
//        escritorio.setLayout(escritorioLayout);
//        escritorioLayout.setHorizontalGroup(
//            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        escritorioLayout.setVerticalGroup(
//            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
	} // mostrarFondo
	
	
	
    private void btnMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesasActionPerformed
        escritorio.removeAll(); // cierro todas las ventanas del escritorio
		mostrarFondo(); // recargo la foto del fondo
		escritorio.repaint();
		
		CrudMesas crudMesas = new CrudMesas(); // creo un internal Frame
		crudMesas.setVisible(true); // lo pongo visible
		
		escritorio.add(crudMesas); // lo pongo en el escritorio
		escritorio.moveToFront(crudMesas); //pongo la ventana al frente:
    }//GEN-LAST:event_btnMesasActionPerformed

	
	
	
    private void btnServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosActionPerformed
		escritorio.removeAll(); // cierro todas las ventanas del escritorio
		mostrarFondo(); // recargo la foto del fondo
		escritorio.repaint();
		
		CrudServicios crudServicios = new CrudServicios(); // creo un internal Frame
		crudServicios.setVisible(true); // lo pongo visible
		
		escritorio.add(crudServicios); // lo pongo en el escritorio
		escritorio.moveToFront(crudServicios); //pongo la ventana al frente:
    }//GEN-LAST:event_btnServiciosActionPerformed

	
	
	
    private void btnFacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturacionActionPerformed
        escritorio.removeAll(); // cierro todas las ventanas del escritorio
		mostrarFondo(); // recargo la foto del fondo
		escritorio.repaint();
		
		//Prueba prueba = new Prueba(); // creo un internal Frame
		//prueba.setVisible(true); // lo pongo visible
		
		//escritorio.add(prueba); // lo pongo en el escritorio
		//escritorio.moveToFront(prueba); //pongo la ventana al frente:     
		
//		CrudCategorias crudCategorias = new CrudCategorias(); // creo un internal Frame
//		crudCategorias.setVisible(true); // lo pongo visible
//		
//		escritorio.add(crudCategorias); // lo pongo en el escritorio
//		escritorio.moveToFront(crudCategorias); //pongo la ventana al frente:       
    }//GEN-LAST:event_btnFacturacionActionPerformed

	
	
	
    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        escritorio.removeAll(); // cierro todas las ventanas del escritorio
		mostrarFondo(); // recargo la foto del fondo
		escritorio.repaint();
		
		CrudProductos crudProductos = new CrudProductos(escritorio); // creo un internal Frame
		crudProductos.setVisible(true); // lo pongo visible
		
		escritorio.add(crudProductos); // lo pongo en el escritorio
		escritorio.moveToFront(crudProductos); //pongo la ventana al frente:
    }//GEN-LAST:event_btnProductosActionPerformed

	
	
	
    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
        escritorio.removeAll(); // cierro todas las ventanas del escritorio
		mostrarFondo(); // recargo la foto del fondo
		escritorio.repaint();
		
		GestionPedidos gestionPedidos = new GestionPedidos(); // creo un internal Frame
		gestionPedidos.setVisible(true); // lo pongo visible
		
		escritorio.add(gestionPedidos); // lo pongo en el escritorio
		escritorio.moveToFront(gestionPedidos); //pongo la ventana al frente:
    }//GEN-LAST:event_btnPedidosActionPerformed

	
	
	
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
			java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Administracion().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonera;
    private javax.swing.JButton btnFacturacion;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnServicios;
    private javax.swing.JDesktopPane escritorio;
    // End of variables declaration//GEN-END:variables
	
	public PanelImagenFondo escritorio2;
	
	public class PanelImagenFondo extends JDesktopPane {
		public void paint(Graphics g) {
			ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/Resto1280x836.jpg"));
			g.drawImage(imagen.getImage(), 100, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		} //paint
	} //PanelImagenFondo

} //Administracion

