/*
 	Trabajo práctico final de la Guía 6 del curso Desarrollo de Apps
	Universidad de La Punta en el marco del proyecto Argentina Programa 4.0

	Integrantes:
		John David Molina Velarde
		Leticia Mores
		Enrique Germán Martínez
		Carlos Eduardo Beltrán
 */
package restaurant70;

import accesoadatos.ItemData;
import accesoadatos.MesaData;
import accesoadatos.MeseroData;
import accesoadatos.PedidoData;
import accesoadatos.ProductoData;
import accesoadatos.Utils;
import static accesoadatos.Utils.dateTimeBD2LocalDateTime;
import static accesoadatos.Utils.localDateTime2DateTimeBD;
import entidades.Item;
import entidades.Mesa;
import entidades.Mesero;
import entidades.Pedido;
import entidades.Producto;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Restaurant70 {

	public static void pruebaProductoData(){
		ProductoData productoData = new ProductoData();
		
		// prueba de alta producto
		Producto p = new Producto("Coca Cola Light", "Gaseosa Coca Cola Light Sin azucar 1L", 12, 1200.5, true);
//		productoData.altaProducto(p);
		//Producto p2 = productoData.getProducto(p.getIdProducto());
		//System.out.println("El producto agregado y recuperado de la tabla es " + p2);
		
		//prueba de baja producto
		//productoData.bajaProducto(84);
		//p = productoData.getProducto(81);
		//p.setNombre("Cerveza Quilmes 1L");
		//productoData.modificarProducto(p);
		
		
		
		List<Producto> listaProductos = productoData.getListaProductos();
		
		for (Producto producto: listaProductos)
			System.out.println(producto);
		
//		System.out.println("");
//		System.out.println("POR CRITERIO DE BUSQUEDA");
//		System.out.println("========================");
//		listaProductos = productoData.getListaProductosXCriterioDeBusqueda(-1, "", -1, -1, ProductoData.OrdenacionProducto.PORNOMBRE);
//		
//		for (Producto producto: listaProductos)
//			System.out.println("**** " + producto);
//		
//		System.out.println("");
//		System.out.println("POR OTRO ORDEN");
//		System.out.println("========================");
//		listaProductos = productoData.getListaProductos(ProductoData.OrdenacionProducto.PORIDPRODUCTO);
//		
//		for (Producto producto: listaProductos)
//			System.out.println("**** " + producto);
//		
		
		p = productoData.getProducto("Taco de cerdo");
		System.out.println(p);
	}
	

	public static void pruebaMeseroData(){
		MeseroData meseroData = new MeseroData();
		
		// prueba de alta producto
		Mesero m = new Mesero("Juan Perez", "12345");
		//meseroData.altaMesero(m);
		//Producto p2 = productoData.getProducto(p.getIdProducto());
		//System.out.println("El producto agregado y recuperado de la tabla es " + p2);
		
		//prueba de baja producto
		//productoData.bajaProducto(84);
		//p = productoData.getProducto(81);
		//p.setNombre("Cerveza Quilmes 1L");
		//productoData.modificarProducto(p);
		
		
		
		List<Mesero> listaMeseros = meseroData.getListaMeseros();
		
		//for (Mesero mesero: listaMeseros)
		//	System.out.println(mesero);
		
		System.out.println("");
		System.out.println("POR CRITERIO DE BUSQUEDA");
		System.out.println("========================");
		listaMeseros = meseroData.getListaMeserosXCriterioDeBusqueda(-1, "", "", MeseroData.OrdenacionMesero.PORIDMESERO);
		
		for (Mesero mesero: listaMeseros)
			System.out.println("**** " + mesero);
		
//		System.out.println("");
//		System.out.println("POR OTRO ORDEN");
//		System.out.println("========================");
//		listaProductos = productoData.getListaProductos(ProductoData.OrdenacionProducto.PORIDPRODUCTO);
//		
//		for (Producto producto: listaProductos)
//			System.out.println("**** " + producto);
//		
		
		m = meseroData.getMesero(3);
		m.setNombreCompleto("John Molina");
		//meseroData.modificarMesero(m);
		//System.out.println(m);
		
		//meseroData.bajaMesero(5);
	}
	

	
	
	
	
	public static void pruebaMesaData(){
		MesaData mesaData = new MesaData();
		
		// prueba de alta producto
		Mesa m = new Mesa(4, Mesa.EstadoMesa.ATENDIDA);
		//mesaData.altaMesa(m);
		//Producto p2 = productoData.getProducto(p.getIdProducto());
		//System.out.println("El producto agregado y recuperado de la tabla es " + p2);
		
		//prueba de baja producto
		//productoData.bajaProducto(84);
		//p = productoData.getProducto(81);
		//p.setNombre("Cerveza Quilmes 1L");
		//productoData.modificarProducto(p);
		
		
		
		List<Mesa> listaMesas = mesaData.getListaMesas();
		
		for (Mesa mesa: listaMesas)
			System.out.println(mesa);
		
		System.out.println("");
		System.out.println("POR CRITERIO DE BUSQUEDA");
		System.out.println("========================");
		listaMesas = mesaData.getListaMesasXCriterioDeBusqueda(-1, -1, Mesa.EstadoMesa.ATENDIDA, MesaData.OrdenacionMesa.PORCAPACIDAD);
		
		for (Mesa mesa: listaMesas)
			System.out.println("**** " + mesa);
		
//		System.out.println("");
//		System.out.println("POR OTRO ORDEN");
//		System.out.println("========================");
//		listaProductos = productoData.getListaProductos(ProductoData.OrdenacionProducto.PORIDPRODUCTO);
//		
//		for (Producto producto: listaProductos)
//			System.out.println("**** " + producto);
//		
		
		m = mesaData.getMesa(3);
		m.setCapacidad(1);
		m.setEstado(Mesa.EstadoMesa.OCUPADA);
		mesaData.modificarMesa(m);
		System.out.println(m);
		
		//mesaData.bajaMesa(25);
	}

	public static void pruebaPedidoData(){
		PedidoData pedidoData = new PedidoData();

		// prueba de alta producto
		//						int idMesa, int idMesero, LocalDateTime fechaHora, boolean pagado
		Pedido p = new Pedido(1, 2, LocalDateTime.now(), true);
		//System.out.println("Pedido: " + p);
		//pedidoData.altaPedido(p);
		//Pedido p2 = pedidoData.getPedido(1);
		//System.out.println("El pedido agregado y recuperado de la tabla es " + p2);

		//prueba de baja pedido
		//pedidoData.bajaPedido(2);
		//p = productoData.getProducto(81);
		//p.setNombre("Cerveza Quilmes 1L");
		//productoData.modificarProducto(p);

		//prueba de modificacion
		Pedido p2= pedidoData.getPedido(3);
		p2.setIdMesa(3);
		p2.setIdMesero(2);
		//pedidoData.modificarPedido(p2);

		// List<Pedido> listaPedidos = pedidoData.getListaPedidos(PedidoData.OrdenacionPedido.PORFECHAHORA);
		//					idPedido, idMesa, idMesero, fechaDesde, fechaHasta, ordenacion
		System.out.println( LocalDateTime.of(2023, 9, 1, 0, 0, 0) );
		List<Pedido> listaPedidos = pedidoData.getListaPedidosXCriterioDeBusqueda(
				-1, -1, -1,
				null, //LocalDateTime.of(2023, 9, 1, 0, 0, 0), 
				LocalDateTime.of(2023, 9, 5, 0, 0, 0), 
				PedidoData.OrdenacionPedido.PORFECHAHORA);

		for (Pedido pedido: listaPedidos)
			System.out.println(pedido);
	}
        
	public static void pruebaItemData(){
		ItemData itemData = new ItemData();
               
		// prueba de alta item
		Item i = new Item(49, 4, 1, Item.EstadoItem.ANOTADO);
		//itemData.altaItem(i);
		
		List<Item> listaItems = itemData.getListaItems();
		
		for (Item item: listaItems)
			System.out.println(item);
		
		i = itemData.getItem(4);
		i.setIdPedido(1);
		itemData.modificarItem(i);
		
		System.out.println("+++++++++++++++++++++++");
		listaItems = itemData.getListaItemsXCriterioDeBusqueda(
				//idItem, idProducto, idPedido, ItemData.Ordenacion
				-1,			52,			-1,		ItemData.OrdenacionItem.PORIDPEDIDO);
		
		for (Item item: listaItems)
			System.out.println(item);
		
		i = new Item(58, 4, 15, Item.EstadoItem.ANOTADO);
		itemData.altaItem(i);
		
		System.out.println("======================");
		listaItems = itemData.getListaItems();
		
		for (Item item: listaItems)
			System.out.println(item);
		
		
		//itemData.bajaItem(i);
		
		System.out.println("======================");
		listaItems = itemData.getListaItems();
		
		for (Item item: listaItems)
			System.out.println(item);
		
	}
	
	
	public static void pruebaDeFecha(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		System.out.println(localDateTime2DateTimeBD(ldt));
		
		String s = "2020-05-15 18:05:03";
		System.out.println(s);
		System.out.println(dateTimeBD2LocalDateTime(s));
		System.out.println("");
		System.out.println("");
		Date date = new Date();
		System.out.println("date " + date);
		System.out.println("localDateTime " + Utils.date2LocalDateTime(date));
	}
	
	
	public static void main(String[] args) {
		//pruebaMesaData();
		//pruebaPedidoData();
		pruebaItemData();
	}
}
	


