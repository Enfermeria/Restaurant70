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

import accesoadatos.ItemData;
import accesoadatos.MesaData;
import accesoadatos.PedidoData;
import accesoadatos.ProductoData;
import entidades.Item;
import entidades.Mesa;
import entidades.Pedido;
import entidades.Servicio;
import entidades.Producto;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

//para el RowsRenderer
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
//fin imports para el RowsRenderer

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Meseros extends javax.swing.JFrame {
	Servicio mesero;
	public static LinkedHashMap<Integer, Mesa> mapaMesas;
	public static LinkedHashMap<Integer, Pedido> mapaPedidos;
	DefaultTableModel modeloTablaMesas, modeloTablaPedidos, modeloTablaItems, modeloTablaProductos;
	ItemData itemData = new ItemData(); //conecto con la BD
	MesaData mesaData = new MesaData(); //conecto con la BD
	PedidoData pedidoData = new PedidoData(); //conecto con la BD
	ProductoData productoData = new ProductoData();
	
	
	
	public Meseros(Servicio mesero) {
		this.mesero = mesero;
		initComponents();
		
		modeloTablaMesas   = (DefaultTableModel) tablaMesas.getModel();
		modeloTablaPedidos = (DefaultTableModel) tablaPedidos.getModel();
		modeloTablaItems   = (DefaultTableModel) tablaItems.getModel();
		modeloTablaProductos=(DefaultTableModel) tablaProductos.getModel();
		
		//elijo alineacion centro para pedidos
		DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
		alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER
		tablaPedidos.getColumnModel().getColumn(0).setCellRenderer(alinear);

		//cambio los renderer para las tablaMesas y tablaItems para así elegir colores diferentes según el estado
		tablaMesas.getColumnModel().getColumn(0).setCellRenderer(new RendererMesas());
		tablaItems.getColumnModel().getColumn(3).setCellRenderer(new RendererItems());
		
		cargarMesas();
		cargarPedidos();
		cargarProductos();
	}

	/**
	 * cargo el mapa de mesas y la tabla de mesas que corresponden a ese mesero.
	 */
	private void cargarMesas(){
		//Obtengo la lista de meses que corresponden a este mesero.
		List<Mesa> listaMesas = mesaData.getListaMesasXCriterioDeBusqueda(-1, -1, null, mesero.getIdServicio(), MesaData.OrdenacionMesa.PORIDMESA);
		
		//genero un mapa con las mesas de este mesero.
		mapaMesas = new LinkedHashMap();
		listaMesas.stream().forEach(mesa -> mapaMesas.put(mesa.getIdMesa(), mesa));
		
		int filaSeleccionada = tablaMesas.getSelectedRow(); //conservo la anterior mesa seleccionada
		
		//borro las filas de la tabla mesas
		for (int fila = modeloTablaMesas.getRowCount() -  1; fila >= 0; fila--)
			modeloTablaMesas.removeRow(fila);
		
		//cargo esas mesas a la tabla de mesas
		for (Mesa mesa : listaMesas) {
			modeloTablaMesas.addRow(new Object[] {
				mesa.getIdMesa()
			} );
		}
		
		//si la fila que tenia seleccionada sigue siendo válida
		if (filaSeleccionada >= 0 && filaSeleccionada < tablaMesas.getRowCount())
			tablaMesas.setRowSelectionInterval(filaSeleccionada, filaSeleccionada); //restauro la fila que tenía seleccionada
		//else
		//	tablaMesas.removeRowSelectionInterval(0, tablaMesas.getRowCount()-1); // borro todas las selecciones de la tabla de pedidos
		
		cargarPedidos();
	} //cargar mesas
	
	
	
	/**
	 * Me devuelve el idMesa que se seleccionó de la tabla. Si no hay ninguna 
	 * seleccionada, devuelve 0;
	 * @return 
	 */
	private int getIdMesaSeleccionada(){
		int filaSeleccionada = tablaMesas.getSelectedRow();
		if (filaSeleccionada != -1)
			return (Integer) tablaMesas.getValueAt(filaSeleccionada, 0);
		else
			return 0;
	} //getIdMesaSeleccionada
	
	
	
	
	/**
	 * cargo el mapa de mesas y la tabla de mesas que corresponden a ese mesero
	 */
	private void cargarPedidos(){
		//Obtengo la lista de pedidos que corresponden a esa mesa.
		List<Pedido> listaPedidos = pedidoData.getListaPedidosXCriterioDeBusqueda(
		//	idPedido, idMesa,				  idMesero, fechaDesde, fechaHasta, pagado,  OrdenacionPedido ordenacion
			-1,		 getIdMesaSeleccionada(), -1,		null,		null,		false,	PedidoData.OrdenacionPedido.PORIDPEDIDO);
		
		//System.out.println("Lista pedidos: " + listaPedidos); //debug
		
		//genero un mapa con los pedidos de esa mesa.
		mapaPedidos = new LinkedHashMap();
		listaPedidos.stream().forEach( pedido -> mapaPedidos.put(pedido.getIdPedido(), pedido) );
		
		//int filaSeleccionada = tablaPedidos.getSelectedRow(); //conservo la anterior mesa seleccionada
		
		//borro las filas de la tabla mesas
		for (int fila = modeloTablaPedidos.getRowCount() -  1; fila >= 0; fila--)
			modeloTablaPedidos.removeRow(fila);
		
		//cargo esos pedidos a la tabla de pedidos
		listaPedidos.stream().forEach(pedido -> modeloTablaPedidos.addRow(new Object[] {
				pedido.getIdPedido()
			} ) 
		);
		
		
		//si la fila que tenia seleccionada sigue siendo válida
		//if (filaSeleccionada >= 0 && filaSeleccionada < tablaPedidos.getRowCount() )
		//	tablaPedidos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada); //restauro la fila que tenía seleccionada
		//else
		//	tablaPedidos.removeRowSelectionInterval(0, tablaPedidos.getRowCount()-1); // borro todas las selecciones de la tabla de pedidos
		
		cargarItems();
	} //cargarPedidos
	
	
	
	
	/**
	 * Me devuelve el idPedido que se seleccionó de la tabla. Si no hay ninguno 
	 * seleccionado, devuelve 0;
	 * @return 
	 */
	private int getIdPedidoSeleccionado(){
		int filaSeleccionada = tablaPedidos.getSelectedRow();
		if (filaSeleccionada != -1)
			return (Integer) tablaPedidos.getValueAt(filaSeleccionada, 0);
		else
			return 0;
	} //getIdPedidoSeleccionado
	
	
	
	
	/**
	 * cargo el lista de items y la tabla de items que corresponden a ese pedido
	 */
	private void cargarItems(){
		//Obtengo la lista de items que corresponden a ese pedido.
		List<Item> listaItems = itemData.getListaItemsXCriterioDeBusqueda(
		// idItem, idProducto, idPedido,				estado,		ordenacion	
			-1,		 -1,	   getIdPedidoSeleccionado(), null,	ItemData.OrdenacionItem.PORIDITEM);
		
		//System.out.println("Lista items: " + listaItems); //debug
		
		
		//borro las filas de la tabla items
		for (int fila = modeloTablaItems.getRowCount() -  1; fila >= 0; fila--)
			modeloTablaItems.removeRow(fila);
		
		//cargo esos pedidos items a la tabla de items
		listaItems.stream().forEach(item -> modeloTablaItems.addRow(new Object[] {
				item.getIdItem(),
				productoData.getProducto(item.getIdProducto()),
				item.getCantidad(),
				item.getEstado()
			} ) 
		);
		
		
		//si la fila que tenia seleccionada sigue siendo válida
		//if (filaSeleccionada >= 0 && filaSeleccionada < tablaPedidos.getRowCount() )
		//	tablaPedidos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada); //restauro la fila que tenía seleccionada
		//else
		//	tablaPedidos.removeRowSelectionInterval(0, tablaPedidos.getRowCount()-1); // borro todas las selecciones de la tabla de pedidos
	} //cargarItems
	
	
	
		
	/**
	 * cargo el lista de productos y la tabla de productos
	 */
	private void cargarProductos(){
		//Obtengo la lista de productos activos
		List<Producto> listaProductos = productoData.getListaProductosXCriterioDeBusqueda(
			//idProducto, nombre, stock, precio, disponible, idCategoria, despachadoPor, ordenacion){ 
			-1,				"",	   -1,	 -1.0,	 true,		   -1,			-1,			ProductoData.OrdenacionProducto.PORIDPRODUCTO);
		
		
		//borro las filas de la tabla productos
		for (int fila = modeloTablaProductos.getRowCount() -  1; fila >= 0; fila--)
			modeloTablaProductos.removeRow(fila);
		
		//cargo esos productos a la tabla de productos
		listaProductos.stream().forEach(producto -> modeloTablaProductos.addRow(new Object[] {
				producto,
				producto.getDescripcion(),
				producto.getPrecio(),
				producto.getIdCategoria(),
				producto.getDespachadoPor()
			} ) 
		);
		
		
		//si la fila que tenia seleccionada sigue siendo válida
		//if (filaSeleccionada >= 0 && filaSeleccionada < tablaPedidos.getRowCount() )
		//	tablaPedidos.setRowSelectionInterval(filaSeleccionada, filaSeleccionada); //restauro la fila que tenía seleccionada
		//else
		//	tablaPedidos.removeRowSelectionInterval(0, tablaPedidos.getRowCount()-1); // borro todas las selecciones de la tabla de pedidos
	} //cargarItems
	
	
	
	/**
	 * Cuando no hay un item seleccionado se deshabilitan los botones relacionados a los items
	 */
	private void deshabilitarBotonesItems(){
		btnAumentar.setEnabled(false);
		btnDisminuir.setEnabled(false);
		btnSolicitarProducto.setEnabled(false);
		btnServirProducto.setEnabled(false);
		btnSacar.setEnabled(false);
		btnIncluir.setEnabled(false);
	}
	
	
	
	/**
	 * Cuando se selecciona un item seleccionado se habilitan los botones relacionados a los items
	 */
	private void habilitarBotonesItems(){
		btnAumentar.setEnabled(true);
		btnDisminuir.setEnabled(true);
		btnSolicitarProducto.setEnabled(true);
		btnServirProducto.setEnabled(true);
		btnSacar.setEnabled(true);
		//btnIncluir.setEnabled(true);
	}
	
	
	
	//=======================================================================================
	//=======================================================================================
	
	
	
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMesas = new javax.swing.JScrollPane();
        tablaMesas = new javax.swing.JTable();
        panelPedidos = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        panelItems = new javax.swing.JScrollPane();
        tablaItems = new javax.swing.JTable();
        botonera = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        btnSacar = new javax.swing.JButton();
        btnAumentar = new javax.swing.JButton();
        btnDisminuir = new javax.swing.JButton();
        btnSolicitarProducto = new javax.swing.JButton();
        btnServirProducto = new javax.swing.JButton();
        panelProductos = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaMesas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Mesa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaMesas.setRowHeight(48);
        tablaMesas.getTableHeader().setReorderingAllowed(false);
        tablaMesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMesasMouseClicked(evt);
            }
        });
        panelMesas.setViewportView(tablaMesas);
        if (tablaMesas.getColumnModel().getColumnCount() > 0) {
            tablaMesas.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaPedidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                "Pedido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPedidos.setRowHeight(48);
        tablaPedidos.getTableHeader().setReorderingAllowed(false);
        tablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPedidosMouseClicked(evt);
            }
        });
        panelPedidos.setViewportView(tablaPedidos);
        if (tablaPedidos.getColumnModel().getColumnCount() > 0) {
            tablaPedidos.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Cant", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaItemsMouseClicked(evt);
            }
        });
        panelItems.setViewportView(tablaItems);
        if (tablaItems.getColumnModel().getColumnCount() > 0) {
            tablaItems.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaItems.getColumnModel().getColumn(0).setMaxWidth(20);
            tablaItems.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaItems.getColumnModel().getColumn(1).setMaxWidth(250);
            tablaItems.getColumnModel().getColumn(2).setPreferredWidth(50);
            tablaItems.getColumnModel().getColumn(2).setMaxWidth(50);
            tablaItems.getColumnModel().getColumn(3).setPreferredWidth(140);
            tablaItems.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/flecha_izquierda32x32 .png"))); // NOI18N
        btnIncluir.setText("Incluir Producto");
        btnIncluir.setEnabled(false);
        btnIncluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIncluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnSacar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/flecha_derecha32x32.png"))); // NOI18N
        btnSacar.setText("Sacar Producto");
        btnSacar.setEnabled(false);
        btnSacar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSacar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarActionPerformed(evt);
            }
        });

        btnAumentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mas32x32.png"))); // NOI18N
        btnAumentar.setText("Aumentar Cantidad");
        btnAumentar.setEnabled(false);
        btnAumentar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAumentar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAumentarActionPerformed(evt);
            }
        });

        btnDisminuir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menos32x32.png"))); // NOI18N
        btnDisminuir.setText("Disminuir Cantidad");
        btnDisminuir.setEnabled(false);
        btnDisminuir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDisminuir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDisminuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisminuirActionPerformed(evt);
            }
        });

        btnSolicitarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/condimento32x32.png"))); // NOI18N
        btnSolicitarProducto.setText("Solicitar Producto");
        btnSolicitarProducto.setEnabled(false);
        btnSolicitarProducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSolicitarProducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSolicitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarProductoActionPerformed(evt);
            }
        });

        btnServirProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesero32x32.png"))); // NOI18N
        btnServirProducto.setText("Servir producto");
        btnServirProducto.setEnabled(false);
        btnServirProducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnServirProducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnServirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServirProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botoneraLayout = new javax.swing.GroupLayout(botonera);
        botonera.setLayout(botoneraLayout);
        botoneraLayout.setHorizontalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSacar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAumentar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisminuir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSolicitarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnServirProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        botoneraLayout.setVerticalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSacar)
                .addGap(88, 88, 88)
                .addComponent(btnAumentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDisminuir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(btnSolicitarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnServirProducto)
                .addContainerGap())
        );

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Producto", "Descripcion", "Precio", "Categoría", "Despachado por"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        panelProductos.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(120);
            tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(20);
            tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelItems)
                            .addComponent(botonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelProductos))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	
	
	//=======================================================================================
	//=======================================================================================
	
	
    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        if (tablaProductos.getSelectedRow() == -1 || tablaPedidos.getSelectedRow() == -1){ // si hay alguna fila seleccionada
            btnIncluir.setEnabled(false); // deshabilito botón incluir
			return;
        }
		
        int[] arregloFilasProductosSeleccionados = tablaProductos.getSelectedRows();
		for (int numfilaProductos:arregloFilasProductosSeleccionados){
			int idProducto = ((Producto)tablaProductos.getValueAt(numfilaProductos, 0)).getIdProducto();//obtengo el producto

			//recorro la tabla de items para ver si está ese producto en la tabla (y que sea anotado)
			int numfilaItems = 0;
			while (numfilaItems < tablaItems.getRowCount() && 
					!(((Producto)tablaItems.getValueAt(numfilaItems, 1)).getIdProducto() == idProducto && 
					((Item.EstadoItem)tablaItems.getValueAt(numfilaItems, 3)) == Item.EstadoItem.ANOTADO ) )
				numfilaItems++;
			
			//ahora salio porque lo encontro o termino la tabla
			if ( numfilaItems >= tablaItems.getRowCount() )  //no lo encontro... hay que agregarlo
				itemData.altaItem( new Item(idProducto, getIdPedidoSeleccionado(), 1, Item.EstadoItem.ANOTADO) ); //agrego el item en la bd
			else {// lo encontró, hay que aumentar la cantidad
				Item item = itemData.getItem((Integer)tablaItems.getValueAt(numfilaItems, 0));
				item.setCantidad(item.getCantidad()+1);
				itemData.modificarItem(item);
			}
			cargarItems(); //actualizo los items y tabla de items
        } //for 
    }//GEN-LAST:event_btnIncluirActionPerformed

	
	
	
    private void btnSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarActionPerformed
        if (tablaItems.getSelectedRow() == -1){ 
            deshabilitarBotonesItems(); 
			return;
        }
		
		int[] arregloFilasItemsSeleccionados = tablaItems.getSelectedRows();
		boolean bajeAlgunItem=false;
        for (int numfilaItems:arregloFilasItemsSeleccionados) { //recorro todas las filas seleccionadas de la tablaItems
            int idItem = (Integer)tablaItems.getValueAt(numfilaItems, 0);//averiguamos el idItem
			Item item = itemData.getItem(idItem);
			
			if (item.getCantidad() > 1) { // si hay varios, disminuyo la cantidad
				item.setCantidad(item.getCantidad()-1);
				itemData.modificarItem(item);
			} else { // solo hay uno, lo elimino
				itemData.bajaItem(item);
				bajeAlgunItem = true;
			}
		} //for
		cargarItems();
		if (bajeAlgunItem)
			deshabilitarBotonesItems();
		else { // como no hubo ninguna baja, restauro las filas que tenia seleccionadas
			for (int fila:arregloFilasItemsSeleccionados)
				tablaItems.addRowSelectionInterval(fila, fila);
		}
    }//GEN-LAST:event_btnSacarActionPerformed

	
	
	
    private void btnAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarActionPerformed
		if (tablaItems.getSelectedRow() == -1){ 
			deshabilitarBotonesItems(); 
			return;
		}
			
		int[] arregloFilasItemsSeleccionados = tablaItems.getSelectedRows();
		for (int numfilaItems:arregloFilasItemsSeleccionados) { //recorro todas las filas seleccionadas de la tablaItems
			int idItem = (Integer)tablaItems.getValueAt(numfilaItems, 0);//averiguamos el idItem
			Item item = itemData.getItem(idItem);
			
			item.setCantidad(item.getCantidad()+1);
			itemData.modificarItem(item);
		} //for
		cargarItems();
		//restauro las filas que tenia seleccionadas
		for (int fila:arregloFilasItemsSeleccionados)
			tablaItems.addRowSelectionInterval(fila, fila);
    }//GEN-LAST:event_btnAumentarActionPerformed

	
	
	
    private void btnDisminuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisminuirActionPerformed
        btnSacarActionPerformed(evt);
    }//GEN-LAST:event_btnDisminuirActionPerformed

	
	
	
    private void btnSolicitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitarProductoActionPerformed

	
	
	
    private void btnServirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServirProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnServirProductoActionPerformed

	
	
	
    private void tablaMesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMesasMouseClicked
        cargarPedidos();
		deshabilitarBotonesItems();
    }//GEN-LAST:event_tablaMesasMouseClicked

	
	
    private void tablaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPedidosMouseClicked
        cargarItems();
		deshabilitarBotonesItems();
		if (tablaProductos.getSelectedRow() != -1)
			btnIncluir.setEnabled(true);
    }//GEN-LAST:event_tablaPedidosMouseClicked

	
	
    private void tablaItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaItemsMouseClicked
        habilitarBotonesItems();
    }//GEN-LAST:event_tablaItemsMouseClicked

	
	
    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        if (tablaPedidos.getSelectedRow() != -1)
			btnIncluir.setEnabled(true);
    }//GEN-LAST:event_tablaProductosMouseClicked

	
	
	
	//=========================================================================================
	//=========================================================================================
	
	
	
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
			java.util.logging.Logger.getLogger(Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Meseros(null).setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonera;
    private javax.swing.JButton btnAumentar;
    private javax.swing.JButton btnDisminuir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSacar;
    private javax.swing.JButton btnServirProducto;
    private javax.swing.JButton btnSolicitarProducto;
    private javax.swing.JScrollPane panelItems;
    private javax.swing.JScrollPane panelMesas;
    private javax.swing.JScrollPane panelPedidos;
    private javax.swing.JScrollPane panelProductos;
    private javax.swing.JTable tablaItems;
    private javax.swing.JTable tablaMesas;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables


	
	//========================================================================================================
	//========================================================================================================
	
	
	
	/**
	 * Renderer de celdas de la tablaMesas. Pone el color según el estado de la 
	 * mesa (Libre, Ocupada, Atendida). La info la obtiene de mapaMesas;
	 *  en la definicion de la tabla se pone
	 *		tabla.getColumnModel().getColumn(3).setCellRenderer(new generalRenderer());
	 *	de esa manera aplica el renderer a la columna, los colores son diferentes si la celda esta seleccionada o no.
	 */
	class RendererMesas extends JLabel implements TableCellRenderer {    
		//Font f = new Font( "Helvetica",Font.PLAIN,10 );
		Color colorSeleccionado = new Color(184,207,229); //new Color(0,120,215); //new Color(117, 204, 169);
		Color colorGeneral = Color.BLUE; //new Color(255,255,255); //new Color(225, 244, 238);
		Color colorLibre = Color.WHITE;
		Color colorOcupada = Color.RED;
		Color colorAtendida = Color.GREEN;

		public RendererMesas() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean isSelected, boolean hasFocus, int row, int column) {
			setHorizontalAlignment(CENTER);
			if (isSelected) {
				setBackground(colorSeleccionado);
			} else if ( mapaMesas.get((Integer)valor).getEstado() == Mesa.EstadoMesa.LIBRE ) {
				setBackground(colorLibre);
			} else if ( mapaMesas.get((Integer)valor).getEstado() == Mesa.EstadoMesa.OCUPADA ) {
				setBackground(colorOcupada);
			} else if ( mapaMesas.get((Integer)valor).getEstado() == Mesa.EstadoMesa.ATENDIDA ) {
				setBackground(colorAtendida);
			} else {
				setBackground(colorGeneral);
			}
			try {
				//setFont(f);
				setText(valor.toString());
			} catch (NullPointerException npe) {
				System.out.println(valor.toString());
				setText("0");
			}
			return this;
		}
	} //class rendererMesas
	
	
	
	/**
	 * Renderer de celdas de la tablaMesas. Pone el color según el estado de la 
	 * mesa (Libre, Ocupada, Atendida). La info la obtiene de mapaMesas;
	 *  en la definicion de la tabla se pone
	 *		tabla.getColumnModel().getColumn(3).setCellRenderer(new generalRenderer());
	 *	de esa manera aplica el renderer a la columna, los colores son diferentes si la celda esta seleccionada o no.
	 */
	class RendererItems extends JLabel implements TableCellRenderer {    
		//Font f = new Font( "Helvetica",Font.PLAIN,10 );
		Color colorSeleccionado = new Color(184,207,229); //new Color(0,120,215); //new Color(117, 204, 169);
		Color colorGeneral = Color.WHITE; //new Color(255,255,255); //new Color(225, 244, 238);
		Color colorAnotado = Color.WHITE;
		Color colorSolicitado = Color.YELLOW;
		Color colorDespachado = Color.RED;
		Color colorEntregado = Color.GREEN;

		public RendererItems() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean isSelected, boolean hasFocus, int row, int column) {
			//setHorizontalAlignment(CENTER);
			if (isSelected)
				setBackground(colorSeleccionado);
			else
				setBackground(colorGeneral);
			
			if (column == 3) { //es la columna del estado	
				if ( (Item.EstadoItem)valor == Item.EstadoItem.ANOTADO ) 
					setBackground(colorAnotado);
				else if ( (Item.EstadoItem)valor == Item.EstadoItem.SOLICITADO ) 
					setBackground(colorSolicitado);
				else if ( (Item.EstadoItem)valor == Item.EstadoItem.DESPACHADO ) 
					setBackground(colorDespachado);
				else if ( (Item.EstadoItem)valor == Item.EstadoItem.ENTREGADO ) 
					setBackground(colorEntregado);
				else 
					setBackground(Color.MAGENTA); //esto nunca debería pasar.
			}
			
			try {
				//setFont(f);
				setText(valor.toString());
			} catch (NullPointerException npe) {
				System.out.println(valor.toString());
				setText("0");
			}
			return this;
		}
	} //class rendererItems
	
} //class Meseros


