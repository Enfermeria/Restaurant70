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

import accesoadatos.ProductoData;
import entidades.Producto;
import java.util.List;

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Restaurant70 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ProductoData productoData = new ProductoData();
		
		List<Producto> listaProductos = productoData.getListaProductos();
		
		for (Producto producto: listaProductos)
			System.out.println(producto);
		
	}
	
}
