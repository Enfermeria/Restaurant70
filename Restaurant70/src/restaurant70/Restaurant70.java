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

import accesoadatos.MeseroData;
import accesoadatos.ProductoData;
import entidades.Mesero;
import entidades.Producto;
import java.util.List;

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Restaurant70 {

	public static void pruebaProductoData(){
		ProductoData productoData = new ProductoData();
		
		// prueba de alta producto
		Producto p = new Producto("Coca Cola Light", "Gaseosa Coca Cola Light Sin azucar 1L", 12, 800.5, true);
		//productoData.altaProducto(p);
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
	

	
	public static void main(String[] args) {
		pruebaMeseroData();
	}
	
}
