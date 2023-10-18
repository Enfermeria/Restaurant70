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
import accesoadatos.ServicioData;
import entidades.Categoria;
import entidades.Item;
import entidades.Mesa;
import entidades.Pedido;
import entidades.Servicio;
import entidades.Producto;

import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

//para el RowsRenderer
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.TableCellRenderer;
import utiles.Utils;
//fin imports para el RowsRenderer

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Despacho extends javax.swing.JFrame {
	private Servicio servicio;
	private static LinkedHashMap<Integer, Pedido> mapaPedidos;
	private static LinkedHashMap<Integer, Servicio> mapaServicios;
	private static LinkedHashMap<Integer, Producto> mapaProductos;
	private static LinkedHashMap<Integer, Item> mapaItems;
	private DefaultTableModel modeloTablaItems;
	private ItemData itemData = new ItemData(); //conecto con la BD
	private PedidoData pedidoData = new PedidoData();
	private ProductoData productoData = new ProductoData();
	private ServicioData servicioData = new ServicioData();
	
	
	
	public Despacho(Servicio servicio) {
		this.servicio = servicio;
		initComponents();
		
		modeloTablaItems   = (DefaultTableModel) tablaItems.getModel();
		
		//defino el renderer de la tabla de items para poder cambiar las características de la tabla
		for (int columna = 0; columna <=3; columna++)
			tablaItems.getColumnModel().getColumn(columna).setCellRenderer(new RendererItems());
		
		// cargo los datos
		cargarServicios();
		cargarPedidos();
		cargarItems();
		mostrarLabelsEncabezamientoItems();
	} // constructor de Meseros
	
	
	
	
	/** cargo el mapa de Servicios (cocina, bar, etc) */
	private void cargarServicios(){
		//Obtengo la lista de servicios que despachan productos (cocina, bar, etc.)
		List<Servicio> listaServicios = servicioData.getListaServiciosXCriterioDeBusqueda(
				-1, "", "", -1, Servicio.TipoServicio.MESERO, ServicioData.OrdenacionServicio.PORIDSERVICIO);
		
		//genero un mapa con las categorias
		mapaServicios = new LinkedHashMap();
		listaServicios.stream().forEach(servicio -> mapaServicios.put(servicio.getIdServicio(), servicio));
	} //cargarServicios
	
	
	/** cargo el mapa de Productos */
	private void cargarProductos(){
		//Obtengo la lista de productos
		List<Producto> listaProductos = productoData.getListaProductosXCriterioDeBusqueda(
				//idProducto, nombre, stock, precio, disponible, idCategoria, despachadoPor, ordenacion
				-1,			   "",	   -1,   -1.0,	  true,		  -1,			-1,				ProductoData.OrdenacionProducto.PORNOMBRE);
		
		//genero un mapa con los productos
		mapaProductos = new LinkedHashMap();
		listaProductos.stream().forEach(producto -> mapaProductos.put(producto.getIdProducto(), producto));
	} //cargarProductos
	
	
	
	
	/** cargo el mapa de mesas y la tabla de mesas que corresponden a ese mesero  */
	private void cargarPedidos(){
		PedidoData pedidoData = new PedidoData();
		
		//Obtengo la lista de pedidos activos 
		List<Pedido> listaPedidos = pedidoData.getListaPedidosXCriterioDeBusqueda(
		//	idPedido, idMesa,	idMesero, fechaDesde, fechaHasta,	  estado,						OrdenacionPedido ordenacion
			-1,		 -1,		-1,			null,		null,		Pedido.EstadoPedido.ACTIVO,	PedidoData.OrdenacionPedido.PORIDPEDIDO);
		
		//genero un mapa con los pedidos de esa mesa.
		mapaPedidos = new LinkedHashMap();
		listaPedidos.stream().forEach( pedido -> mapaPedidos.put(pedido.getIdPedido(), pedido) );
	} //cargarPedidos
	
	
	
	/**
	 * cargo la lista de items
	 */
	private void cargarItems(){
		//Obtengo la lista de items cancelados.
		List<Item> listaItems = itemData.getListaItemsXCriterioDeBusqueda(
		// idItem, idProducto, idPedido, estado,						ordenacion	
			-1,		 -1,	    -1,		  Item.EstadoItem.CANCELADO,	ItemData.OrdenacionItem.PORIDITEM);
		
		//obtengo la lista de items solicitados
		List<Item> listaItemsSolicitados = itemData.getListaItemsXCriterioDeBusqueda(
		// idItem, idProducto, idPedido, estado,						ordenacion	
			-1,		 -1,	    -1,		  Item.EstadoItem.SOLICITADO,	ItemData.OrdenacionItem.PORIDITEM);
		
		//al final de la lista de cancelados pongo los solicitados para que queden en al misma lista.
		listaItems.addAll(listaItemsSolicitados); // concateno cancelados y solicitados en la misma lista.
		
		//cargo la lista de items al mapaItems
		listaItems.stream().filter(item -> 
				mapaProductos.get(item.getIdProducto()).getDespachadoPor()->servicio.getIdServicio()).forEach(itemFiltrado -> mapaItems.put(itemFiltrado., value));
		)
				forEach(item -> mapaItems.put(item.getIdItem(), item));
		
		lista.stream().filter(elemento->elemento.getImporte()->300)
			.findFirst()
			.get();
		
		//borro las filas de la tabla items
		for (int fila = modeloTablaItems.getRowCount() -  1; fila >= 0; fila--)
			modeloTablaItems.removeRow(fila);
		
		//cargo esos pedidos items a la tabla de items
		listaItems.stream().forEach(item -> {
			Pedido pedido = mapaPedidos.get(item.getIdPedido());
			modeloTablaItems.addRow(new Object[] {
				item.getIdItem(),									//idItem
				mapaProductos.get(item.getIdProducto()).toString(),//producto
				item.getCantidad(),									//cantidad
				item.getEstado(),									//estado
				pedido,												//pedido
				mapaServicios.get(pedido.getIdMesero()).toString(), //Mesero
				pedido.getIdMesa()									//idMesa
			} ); 
		} );
	} //cargarItems
	
	
	
	/** dada la fila de tablaItems devuelve el IdItem */
	private int tablaItemsGetIdItem(int numfila) {
		return (Integer)tablaItems.getValueAt(numfila, 0);
	} // tablaItemsGetIdItem
	
	
	
	/** dada la fila de tablaItems devuelve el Producto */
	private Producto tablaItemsGetProducto(int numfila) {
		return mapaProductos.get(mapaItems.get(tablaItemsGetIdItem(numfila)).getIdProducto());
	} //tablaItemsGetProducto
	
	
	
	/** dada la fila de tablaItems devuelve la Cantidad */
	private int tablaItemsGetCantidad(int numfila) {
		return (Integer)tablaItems.getValueAt(numfila, 2);
	} // tablaItemsGetCantidad
	
	
	
	/** dada la fila de tablaItems, devuelve el Estado */
	private Item.EstadoItem tablaItemsGetEstado(int numfila) {
		return (Item.EstadoItem)tablaItems.getValueAt(numfila, 3);
	} //tablaItemsGetEstado
	
	
	/** dada la fila de tablaItems, devuelve el pedido */
	private Pedido tablaItemsGetPedido(int numfila) {
		int idPedido = (Integer)tablaItems.getValueAt(numfila, 4);
		return mapaPedidos.get(idPedido);
	} //tablaItemsGetPedido
	

	
	
	/**
	 * Cuando no hay un itemSeleccionado seleccionado se deshabilitan los botones relacionados a los items
	 */
	private void deshabilitarBotonesItems(){
		btnAumentar.setEnabled(false);
		btnDisminuir.setEnabled(false);
		btnSolicitarItem.setEnabled(false);
		btnServirItem.setEnabled(false);
		btnIncluir.setEnabled(false);
		btnCancelarItem.setEnabled(false);
	} //deshabilitarBotonesItems
	
	
	
	/**
	 * Cuando se selecciona un item se habilitan los botones relacionados a los items
	 */
	private void habilitarBotonesItems(){
		btnAumentar.setEnabled(true);
		btnDisminuir.setEnabled(true);
		btnSolicitarItem.setEnabled(true);
		btnServirItem.setEnabled(true);
		//btnIncluir.setEnabled(true);
		btnCancelarItem.setEnabled(true);
	} //habilitarBotonesItems
	
	
	
	/**
	 * Cuando no hay un pedido seleccionado se deshabilitan los botones de pedidos
	 * (excepto el de alta pedidos)
	 */
	private void deshabilitarBotonesPedidos(){
		btnCancelarPedido.setEnabled(false);
		btnPagarPedido.setEnabled(false);
		//btnAltaPedido.setEnabled(false);
	} //deshabilitarBotonesPedidos
	
	
	
	/**
	 * Cuando se selecciona un item se habilitan los botones relacionados a los items
	 */
	private void habilitarBotonesPedidos(){
		btnCancelarPedido.setEnabled(true);
		btnPagarPedido.setEnabled(true);
		//btnAltaPedido.setEnabled(true);
	} //habilitarBotonesPedidos
	
	
	
	/**
	 * Muestra los datos del encabezamiento con el mesero, la mesa seleccionada
	 * y su estado, el pedido seleccionado y su fecha/hora, así como el total 
	 * del importe de los items de dicho pedido.
	 */
	private void mostrarLabelsEncabezamientoItems(){
		//mesero
		lblMesero.setText(mesero.getIdServicio() + ": " + mesero.getNombreServicio());
		
		//mesa
		if (tablaMesas.getSelectedRow() != -1) { // hay mesa seleccionada
			int idMesa = tablaMesasGetIdMesaSeleccionada();
			Mesa mesa = tablaMesasGetMesaSeleccionada();
			lblMesa.setText( idMesa + ": " + mesa.getEstado() );
		} else
			lblMesa.setText(" ");
		
		//pedido
		if (tablaPedidos.getSelectedRow() != -1) { // hay pedido seleccionada
			int idPedido = tablaPedidosGetIdPedidoSeleccionado();
			Pedido pedido = tablaPedidosGetPedidoSeleccionado();
			lblPedido.setText( idPedido + ": " + Utils.localDateTime2String(pedido.getFechaHora()) );
		} else
			lblPedido.setText("                  ");
		
		//importe
		//pedido
		if (tablaPedidos.getSelectedRow() != -1) { // hay pedido seleccionada
			lblImporte.setText("$" + calcularImportePedido());
		} else
			lblImporte.setText("           ");

	} // mostrarLabelsEncabezamiento
	
	
	
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

        panelItems = new javax.swing.JScrollPane();
        tablaItems = new javax.swing.JTable();
        botoneraVertical = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        btnAumentar = new javax.swing.JButton();
        btnDisminuir = new javax.swing.JButton();
        btnSolicitarItem = new javax.swing.JButton();
        btnServirItem = new javax.swing.JButton();
        btnCancelarItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelItems.setBackground(new java.awt.Color(153, 153, 255));

        tablaItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Cant", "Estado", "Id Pedido", "Mesero", "Mesa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaItems.setRowHeight(32);
        tablaItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaItemsMouseClicked(evt);
            }
        });
        panelItems.setViewportView(tablaItems);
        if (tablaItems.getColumnModel().getColumnCount() > 0) {
            tablaItems.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaItems.getColumnModel().getColumn(0).setMaxWidth(20);
            tablaItems.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaItems.getColumnModel().getColumn(1).setMaxWidth(250);
            tablaItems.getColumnModel().getColumn(2).setPreferredWidth(50);
            tablaItems.getColumnModel().getColumn(2).setMaxWidth(50);
            tablaItems.getColumnModel().getColumn(3).setPreferredWidth(80);
            tablaItems.getColumnModel().getColumn(3).setMaxWidth(140);
            tablaItems.getColumnModel().getColumn(4).setPreferredWidth(30);
            tablaItems.getColumnModel().getColumn(5).setPreferredWidth(200);
            tablaItems.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        botoneraVertical.setBackground(new java.awt.Color(153, 153, 255));

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/flecha_izquierda1_32x32 .png"))); // NOI18N
        btnIncluir.setText("Incluir Producto");
        btnIncluir.setEnabled(false);
        btnIncluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIncluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
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

        btnSolicitarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/itemSolicitar1_32x32.png"))); // NOI18N
        btnSolicitarItem.setText("Solicitar Item");
        btnSolicitarItem.setEnabled(false);
        btnSolicitarItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSolicitarItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSolicitarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarItemActionPerformed(evt);
            }
        });

        btnServirItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesero1_32x32.png"))); // NOI18N
        btnServirItem.setText("Servir Item");
        btnServirItem.setEnabled(false);
        btnServirItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnServirItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnServirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServirItemActionPerformed(evt);
            }
        });

        btnCancelarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar3_32x32.png"))); // NOI18N
        btnCancelarItem.setText("Cancelar Item");
        btnCancelarItem.setEnabled(false);
        btnCancelarItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelarItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botoneraVerticalLayout = new javax.swing.GroupLayout(botoneraVertical);
        botoneraVertical.setLayout(botoneraVerticalLayout);
        botoneraVerticalLayout.setHorizontalGroup(
            botoneraVerticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraVerticalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botoneraVerticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnServirItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSolicitarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDisminuir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAumentar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        botoneraVerticalLayout.setVerticalGroup(
            botoneraVerticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraVerticalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAumentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDisminuir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnSolicitarItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnServirItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarItem)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelItems, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(botoneraVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(432, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botoneraVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelItems, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	
	
	//=======================================================================================
	//=======================================================================================
	
	
	
	/**
	 * Incorpora todos los productos de las filas seleccionadas de la tabla de productos.
	 * Para cada uno de ellos, si el producto no está entre los items, lo incorpora al final
 Si el producto ya está en un itemSeleccionado anotado, incrementa la cantidad.
 Si el producto ya está en un itemSeleccionado no anotado, no puede modificar ese itemSeleccionado, 
 así que lo agrega al final como un nuevo itemSeleccionado con el mismo producto.
	 * @param evt 
	 */
    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        if (tablaProductos.getSelectedRow() == -1 || tablaPedidos.getSelectedRow() == -1){ // si hay alguna fila seleccionada en ambas tablas
            btnIncluir.setEnabled(false); // deshabilito botón incluir
			return;
        }
		
        int[] arregloFilasProductosSeleccionados = tablaProductos.getSelectedRows();
		for (int numfilaProductos:arregloFilasProductosSeleccionados){
			int idProducto = tablaProductosGetProducto(numfilaProductos).getIdProducto();//obtengo el producto

			//recorro la tabla de items para ver si está ese producto en la tabla (y que sea anotado)
			int numfilaItems = 0;
			while	(numfilaItems < tablaItems.getRowCount() && 
					  !(tablaItemsGetProducto(numfilaItems).getIdProducto() == idProducto && 
					    tablaItemsGetEstado(numfilaItems) == Item.EstadoItem.ANOTADO) 
					)
				numfilaItems++;
			
			//ahora salio porque lo encontro o termino la tabla
			if ( numfilaItems >= tablaItems.getRowCount() )  //no lo encontro... hay que agregarlo
				itemData.altaItem(new Item(idProducto, tablaPedidosGetIdPedidoSeleccionado(), 1, Item.EstadoItem.ANOTADO) ); //agrego el itemSeleccionado en la bd
			else {// lo encontró, hay que aumentar la cantidad
				Item item = itemData.getItem(tablaItemsGetIdItem(numfilaItems));
				item.setCantidad(item.getCantidad()+1);
				itemData.modificarItem(item);
			}
        } //for 
		cargarItems(); //actualizo los items y tabla de items
		mostrarLabelsEncabezamientoItems();
    }//GEN-LAST:event_btnIncluirActionPerformed

	
	
	
	
	/**
	 * Recorre las filas seleccionadas de la tabla de items incrementando las cantidades.
	 * Para ello debe asegurarse que sea un itemSeleccionado anotado: lo incrementa directamente
 Si no es un itemSeleccionado anotado, busca otro itemSeleccionado con el mismo producto (que sea anotado):
    - si lo encuentra incrementa a este itemSeleccionado con el mismo producto
    - si no lo encuentra, agrega un itemSeleccionado nuevo al final con el mismo producto.
	 * @param evt 
	 */
    private void btnAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarActionPerformed
		if (tablaItems.getSelectedRow() == -1){ 
			deshabilitarBotonesItems(); 
			return;
		}
			
		int[] arregloFilasItemsSeleccionados = tablaItems.getSelectedRows();
		for (int numfilaItemSeleccionado:arregloFilasItemsSeleccionados) { //recorro todas las filas seleccionadas de la tablaItems
			int idItemSeleccionado = tablaItemsGetIdItem(numfilaItemSeleccionado);//averiguamos el idItemSeleccionado de esa fila seleccionada
			Item itemSeleccionado = itemData.getItem(idItemSeleccionado); //averiguamos el itemSeleccionado de esa fila seleccionada
			
			if (itemSeleccionado.getEstado() == Item.EstadoItem.ANOTADO) {// si es anotado lo puedo modificar
				itemSeleccionado.setCantidad(itemSeleccionado.getCantidad()+1); //le incremento la cantidad
				itemData.modificarItem(itemSeleccionado);						// modifico el item en la bd
			} else {//si no es anotado, no lo puedo modificar. Busco otro itemSeleccionado con el mismo producto para subir cantidad, sino agrego uno al final
				//recorro la tabla de items para ver si está ese producto en la tabla (y que sea anotado)
				int numfila = 0;
				while	(numfila < tablaItems.getRowCount() && 
						  !(tablaItemsGetProducto(numfila).getIdProducto() == itemSeleccionado.getIdProducto() && 
						    tablaItemsGetEstado(numfila) == Item.EstadoItem.ANOTADO ) 
						)
					numfila++;

				//ahora salio porque lo encontro (en numfila) o termino la tabla
				if ( numfila >= tablaItems.getRowCount() )  //no lo encontro... hay que agregarlo
					itemData.altaItem(new Item(itemSeleccionado.getIdProducto(), tablaPedidosGetIdPedidoSeleccionado(), 1, Item.EstadoItem.ANOTADO) ); //agrego el itemSeleccionado en la bd
				else {// lo encontró, hay que aumentar la cantidad
					Item item2 = itemData.getItem(tablaItemsGetIdItem(numfila)); //averiguo los datos del item encontrado
					item2.setCantidad(item2.getCantidad()+1); // le incremento la cantidad
					itemData.modificarItem(item2);			  // modifico el item en la bd
				}
			}//else
		} //for
		cargarItems();
		//restauro las filas que tenia seleccionadas
		for (int fila:arregloFilasItemsSeleccionados)
			tablaItems.addRowSelectionInterval(fila, fila);
		
		mostrarLabelsEncabezamientoItems();
    }//GEN-LAST:event_btnAumentarActionPerformed

	
	
	/**
	 * Disminuye las cantidades de las filas seleccionadas de la tabla items (si el itemSeleccionado es Anotado)
     - Si es > 0 lo decrementa
     - si es 0 lo elimina
 Si el itemSeleccionado no es anotado, no lo puede modificar... hace un ruido de error.
	 * @param evt 
	 */
    private void btnDisminuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisminuirActionPerformed
		if (tablaItems.getSelectedRow() == -1){ 
			deshabilitarBotonesItems(); 
			return;
		}
		
		int[] arregloFilasItemsSeleccionados = tablaItems.getSelectedRows();
		boolean bajeAlgunItem=false; //si di de baja algun itemSeleccionado
		for (int numfilaItems:arregloFilasItemsSeleccionados) { //recorro todas las filas seleccionadas de la tablaItems
			int idItem = tablaItemsGetIdItem(numfilaItems);//averiguamos el idItemSeleccionado
			Item item = itemData.getItem(idItem);
			if (item.getEstado() == Item.EstadoItem.ANOTADO) { //si es anotado lo puedo modificar
				if (item.getCantidad() > 1) { // si hay varios, disminuyo la cantidad
					item.setCantidad(item.getCantidad()-1);
					itemData.modificarItem(item);
				} else { // solo hay uno, lo elimino
					itemData.bajaItem(item);
					bajeAlgunItem = true;
				}
			} else { //si no es anotado NO lo puedo modificar
				Utils.sonido1("src/sonidos/chord.wav");
			}
		} //for
		
		cargarItems();
		if (bajeAlgunItem) // si di de baja algun itemSeleccionado las filas seleccionadas en los items pueden no ser válidas, no selecciono nada, deshabilito botones
			deshabilitarBotonesItems();
		else { // como no hubo ninguna baja, restauro las filas que tenia seleccionadas
			for (int fila:arregloFilasItemsSeleccionados)
				tablaItems.addRowSelectionInterval(fila, fila);
		}
		
		mostrarLabelsEncabezamientoItems();
    }//GEN-LAST:event_btnDisminuirActionPerformed


	
	
	/**
	 * Solicita los productos seleccionados de la tabla Items, siempre que esten en estado Anotados
	 * @param evt 
	 */
    private void btnSolicitarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarItemActionPerformed
        if (tablaItems.getSelectedRow() == -1){ 
			deshabilitarBotonesItems(); 
			return;
		}
			
		int[] arregloFilasItemsSeleccionados = tablaItems.getSelectedRows();
		for (int numfilaItems:arregloFilasItemsSeleccionados) { //recorro todas las filas seleccionadas de la tablaItems
			int idItem = tablaItemsGetIdItem(numfilaItems);//averiguamos el idItemSeleccionado
			Item item = itemData.getItem(idItem);
			if (item.getEstado() == Item.EstadoItem.ANOTADO) {// si es anotado lo puedo modificar
				item.setEstado(Item.EstadoItem.SOLICITADO);
				itemData.modificarItem(item);
			} else {//si no es anotado, no lo puedo modificar. 
				Utils.sonido1("src/sonidos/chord.wav");
			}//else
		} //for
		cargarItems();
		//restauro las filas que tenia seleccionadas
		for (int fila:arregloFilasItemsSeleccionados)
			tablaItems.addRowSelectionInterval(fila, fila);
    }//GEN-LAST:event_btnSolicitarItemActionPerformed
	
	
	
	
	/**
	 * Entrega los items seleccionados de la tabla items siempre que esten en estado despachado
	 * @param evt 
	 */
    private void btnServirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServirItemActionPerformed
          if (tablaItems.getSelectedRow() == -1){ 
			deshabilitarBotonesItems(); 
			return;
		}
			
		int[] arregloFilasItemsSeleccionados = tablaItems.getSelectedRows();
		for (int numfilaItems:arregloFilasItemsSeleccionados) { //recorro todas las filas seleccionadas de la tablaItems
			int idItem = tablaItemsGetIdItem(numfilaItems);//averiguamos el idItemSeleccionado
			Item item = itemData.getItem(idItem);
			if (item.getEstado() == Item.EstadoItem.DESPACHADO || item.getEstado() == Item.EstadoItem.SOLICITADO) {// si es anotado lo puedo modificar
				item.setEstado(Item.EstadoItem.ENTREGADO);
				itemData.modificarItem(item);
			} else {//si no es despachado, no lo puedo modificar. 
				Utils.sonido1("src/sonidos/chord.wav");
			}//else
		} //for
		cargarItems();
		//restauro las filas que tenia seleccionadas
		for (int fila:arregloFilasItemsSeleccionados)
			tablaItems.addRowSelectionInterval(fila, fila);
    }//GEN-LAST:event_btnServirItemActionPerformed

	
	
	
	
	
	
	/**
	 * Se hizo click sobre un item para seleccionarlo, por lo que ya se pueden
	 * habilitar los botones de items que operan sobre esos items seleccionados.
	 * @param evt 
	 */
    private void tablaItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaItemsMouseClicked
        habilitarBotonesItems();
    }//GEN-LAST:event_tablaItemsMouseClicked

	
	
	
	
	
	
	
	
	
	/**
	 * Dado un numero de fila de la tablaItems, cancela todas las cantidades del item correspondiente:
	 * Si el item era anotado, solo disminuye en 1 su cantidad (o lo borra si solo quedaba 1)
	 * Si el item era cancelado, no lo puede eliminar (si conSonido es true, hace un ruido por el error)
	 * Si el item era de otro tipo, disminuye en 1 su cantidad (o lo borra si
	 *    solo quedaba 1) y lo agrega al final de la tabla como Cancelado ( o si
	 *    ya había un item cancelado con el mismo idProducto, lo incrementa en 1)
	 * @param numfilaItems fila de tablaItems con el item a cancelar
	 * @param conSonido True si hara sonido cuando intente cancelar un item ya cancelado.
	 * @return true si tuvo que borrar un item de la lista (cuando la cantidad era 1)
	 */
	private void cancelarTodosLosItem(int numfilaItems){
		boolean bajeAlgunItem = false;
		int idItem = tablaItemsGetIdItem(numfilaItems);//averiguamos el idItemSeleccionado
		Item item = itemData.getItem(idItem);
		if (item.getEstado() == Item.EstadoItem.ANOTADO) { //si es anotado lo puedo decrementar directamente
			itemData.bajaItem(item);
		} else if (item.getEstado() == Item.EstadoItem.CANCELADOVISTO) { //si ya esta cancelado y visto no se puede volver a cancelar
			// no hago nada Utils.sonido1("src/sonidos/chord.wav");
		} else {// SOLICITADO, DESPACHADO, ENTREGADO, CANCELADO: es decir, en cualquier otro caso, lo cancelo
				item.setEstado(Item.EstadoItem.CANCELADO);
				itemData.modificarItem(item);
		}
	} // cancelarTodosLosItem
	
	
	
	
	/**
	 * Dado un numero de fila de la tablaItems, cancela 1 item desminuyendo su cantidad en 1:
	 * Si el item era anotado, solo disminuye en 1 su cantidad (o lo borra si solo quedaba 1)
	 * Si el item era cancelado, no lo puede eliminar (si conSonido es true, hace un ruido por el error)
	 * Si el item era de otro tipo, disminuye en 1 su cantidad (o lo borra si
	 *    solo quedaba 1) y lo agrega al final de la tabla como Cancelado ( o si
	 *    ya había un item cancelado con el mismo idProducto, lo incrementa en 1)
	 * @param numfilaItems fila de tablaItems con el item a cancelar
	 * @param conSonido True si hara sonido cuando intente cancelar un item ya cancelado.
	 * @return true si tuvo que borrar un item de la lista (cuando la cantidad era 1)
	 */
	private boolean cancelar1Item(int numfilaItems){
		boolean bajeAlgunItem = false;
		int idItem = tablaItemsGetIdItem(numfilaItems);//averiguamos el idItemSeleccionado
		Item item = itemData.getItem(idItem);
		if (item.getEstado() == Item.EstadoItem.ANOTADO) { //si es anotado lo puedo decrementar directamente
			if (item.getCantidad() > 1) { // si hay varios, disminuyo la cantidad
				item.setCantidad(item.getCantidad()-1);
				itemData.modificarItem(item);
			} else { // solo hay uno, lo elimino
				itemData.bajaItem(item);
				bajeAlgunItem = true;
			}
		} else if (item.getEstado() == Item.EstadoItem.CANCELADO) { //si ya esta cancelado no se puede volver a cancelar
			Utils.sonido1("src/sonidos/chord.wav");
		} else {//if (item.getEstado()==Item.EstadoItem.SOLICITADO || 
				// item.getEstado()==Item.EstadoItem.DESPACHADO) || 
				// item.getEstado()==Item.EstadoItem.ENTREGADO      // es decir, en cualquier otro caso, lo cancelo
			if (item.getCantidad() > 1) { // si hay varios, disminuyo la cantidad y lo pongo al final como cancelado
				item.setCantidad(item.getCantidad()-1);
				itemData.modificarItem(item);

				//Busco otro item cancelado con el mismo producto para subir cantidad, sino agrego uno al final uno cancelado
				//recorro la tabla de items para ver si está ese producto en la tabla (y que sea cancelado)
				int numfila = 0;
				while	(numfila < tablaItems.getRowCount() && 
						  !(tablaItemsGetProducto(numfila).getIdProducto() == item.getIdProducto() && 
							tablaItemsGetEstado(numfila) == Item.EstadoItem.CANCELADO ) 
						)
					numfila++;

				//ahora salio porque lo encontro (en numfila) o termino la tabla
				if ( numfila >= tablaItems.getRowCount() )  //no lo encontro... hay que agregarlo al final
					itemData.altaItem(new Item(item.getIdProducto(), tablaPedidosGetIdPedidoSeleccionado(), 1, Item.EstadoItem.CANCELADO) ); //agrego el item en la bd
				else {// encontro otro cancelado con el mismo idProducto, hay que aumentar la cantidad
					Item item2 = itemData.getItem(tablaItemsGetIdItem(numfila)); //averiguo los datos del item encontrado
					item2.setCantidad(item2.getCantidad()+1); // le incremento la cantidad
					itemData.modificarItem(item2);			  // modifico el item en la bd
				}

			} else { // solo hay uno, lo marco como cancelado
				item.setEstado(Item.EstadoItem.CANCELADO);
				itemData.modificarItem(item);
			}
		}
		return bajeAlgunItem;
	} // cancelar1Item
	
	
	
	/**
	 * Cancela un item seleccionado de tablaItems. 
	 * Si el item es anotado, lo disminuye (o lo borra si solo quedaba cantidad 1)
	 * Si el item es cancelado, no lo puede volver a cancelar, hace sonido
	 * Cualquier otro estado, lo disminuye (o borra si solo queda cantidad 1) y lo agrega
	 * al final como item cancelado (o si ya había otro item cancelado con el 
	 * mismo idProducto, incrementa en 1 la cantidad de ese item cancelado).
	 * @param evt 
	 */
    private void btnCancelarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarItemActionPerformed
		if (tablaItems.getSelectedRow() == -1){ 
			deshabilitarBotonesItems(); 
			return;
		}
		
		int[] arregloFilasItemsSeleccionados = tablaItems.getSelectedRows();
		boolean bajeAlgunItem=false; //si di de baja algun itemSeleccionado
		for (int numfilaItems:arregloFilasItemsSeleccionados) { //recorro todas las filas seleccionadas de la tablaItems
			bajeAlgunItem = bajeAlgunItem || cancelar1Item(numfilaItems); //cancelo el item de la fila especificada. Si no puede cancelarse, hace sonido	
		} //for
		
		cargarItems();
		if (bajeAlgunItem) // si di de baja algun itemSeleccionado las filas seleccionadas en los items pueden no ser válidas, no selecciono nada, deshabilito botones
			deshabilitarBotonesItems();
		else { // como no hubo ninguna baja, restauro las filas que tenia seleccionadas
			for (int fila:arregloFilasItemsSeleccionados)
				tablaItems.addRowSelectionInterval(fila, fila);
		}
		
		mostrarLabelsEncabezamientoItems();
    }//GEN-LAST:event_btnCancelarItemActionPerformed

	
	
	
	
	
	
	
	
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
    private javax.swing.JPanel botoneraVertical;
    private javax.swing.JButton btnAumentar;
    private javax.swing.JButton btnCancelarItem;
    private javax.swing.JButton btnDisminuir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnServirItem;
    private javax.swing.JButton btnSolicitarItem;
    private javax.swing.JScrollPane panelItems;
    private javax.swing.JTable tablaItems;
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
	 * mesa (Libre, Ocupada, Atendida). La info la obtiene de mapaMesas.
	 * También permite gestionar los eventos realizados sobre la tabla.
	 * 
	 * Para usarla, en la definicion de la tabla se pone:
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
		Color colorCancelado = Color.LIGHT_GRAY;
		Color colorCanceladoVisto = Color.LIGHT_GRAY;

		public RendererItems() {
			setOpaque(true);
		}

		
		/*
		 * Este metodo controla toda la tabla, podemos obtener el valor que contiene
		 * definir que celda esta seleccionada, la fila y columna al tener el foco en ella.
		 * Cada evento sobre la tabla invocara a este metodo
		 */
		public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean isSelected, boolean hasFocus, int row, int column) {
			//defino el color de fondo según el tipo
			if (isSelected) {
				// setBackground(colorSeleccionado);
				//BevelBorder, SoftBevelBorder, EtchedBorder, LineBorder, TitledBorder, and MatteBorder.
				//this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
				//this.setBorder(javax.swing.BorderFactory.createLineBorder(colorSeleccionado, 2));
				
				//Defino un borde alrededor de la fila seleccionada
				if (column==0) //columna izquierda, con borde izquierdo
					setBorder(javax.swing.BorderFactory.createMatteBorder(6, 4, 6, 0, colorSeleccionado)); //top, left, bottom, right, colorGeneral
				else if (column==3) //columna derecha, con borde derecho
					setBorder(javax.swing.BorderFactory.createMatteBorder(6, 0, 6, 4, colorSeleccionado));
				else //columnas del medio, sin borde izquierdo ni derecho, solo arriba y abajo
					setBorder(javax.swing.BorderFactory.createMatteBorder(6, 0, 6, 0, colorSeleccionado));
			}
			else
				this.setBorder(null);
			
			if ( (Item.EstadoItem) tabla.getValueAt(row, 3) == Item.EstadoItem.ANOTADO ) 
				setBackground(colorAnotado);
			else if ( (Item.EstadoItem) tabla.getValueAt(row, 3) == Item.EstadoItem.SOLICITADO ) 
				setBackground(colorSolicitado);
			else if ( (Item.EstadoItem) tabla.getValueAt(row, 3) == Item.EstadoItem.DESPACHADO ) 
				setBackground(colorDespachado);
			else if ( (Item.EstadoItem) tabla.getValueAt(row, 3) == Item.EstadoItem.ENTREGADO ) 
				setBackground(colorEntregado);
			else if ( (Item.EstadoItem) tabla.getValueAt(row, 3) == Item.EstadoItem.CANCELADO ) 
				setBackground(colorCancelado);
			else if ( (Item.EstadoItem) tabla.getValueAt(row, 3) == Item.EstadoItem.CANCELADOVISTO ) 
				setBackground(colorCanceladoVisto);
			else 
				setBackground(Color.MAGENTA); //esto nunca debería pasar.
			
			//defino la alineación horizontal
			if (column == 0 || column==2)
				setHorizontalAlignment( JLabel.CENTER );
			else
				setHorizontalAlignment( JLabel.LEFT );
			
			//escribo el valor pasado en valor
			try {
				//setFont(f);
				setText(valor.toString());
			} catch (NullPointerException npe) {
				System.out.println("Error al escribir celda " + row + "," + column + ":" + valor.toString() + "." + npe);
				setText("0");
			}
			return this;
		}
	} //class rendererItems
	
} //class Meseros


