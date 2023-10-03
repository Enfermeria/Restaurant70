/*
	Trabajo práctico final de la Guía 6 del curso Desarrollo de Apps
	Universidad de La Punta en el marco del proyecto Argentina Programa 4.0

	Integrantes:
		John David Molina Velarde
		Leticia Mores
		Enrique Germán Martínez
		Carlos Eduardo Beltrán

	Controlador de Mesero. Permite almacenar y recuperar meseros de la bd.
 */


package accesoadatos;

import static accesoadatos.Utils.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entidades.Mesero;
import entidades.Pedido;


/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class MeseroData {
	ConexionMySQL conexion; //gestiona la conexión con la bd
	public enum OrdenacionMesero {PORIDMESERO, PORNOMBRECOMPLETO}; //tipo de ordenamiento
	
	
	/**
	 * constructor. Gestiona la conexión con la bd.
	 */
	public MeseroData() {
		conexion = new ConexionMySQL();
		conexion.conectar(); //esto es opcional. Podría ponerse en el main.
	} //MeseroData

	

	/**
	 * agrega el mesero a la BD. 
	 * @param mesero El que se dará de alta. Viene sin idmesero (se genera ahora)
	 * @return devuelve true si pudo darlo de alta
	 */
	public boolean altaMesero(Mesero mesero){// 
		// una alternativa es usar ?,?,? y luego insertarlo con preparedStatement.setInt(1, dato) // o setString, setBoolean, setData
		String sql = "Insert into mesero (idmesero, nombreCompleto, clave) " +
			"VALUES " + "(null,'" + mesero.getNombreCompleto() +  "','" + mesero.getClave() +  "'" + " )";
		if (conexion.sqlUpdate(sql)) {
			mensaje("Alta de mesero exitosa");
			mesero.setIdMesero(conexion.getKeyGenerado()); //asigno el id generado
			conexion.cerrarSentencia(); //cierra PreparedStatement y como consecuencia tambien el reultSet
			return true;
		} else {
			mensajeError("Falló el alta de mesero");
			return false;
		}
	} //altaMesero
	
	
	
	
	/**
	 * Da de baja al mesero de la BD.
	 * @param mesero el mesero que se dará debaja (usando su idMesero)
	 * @return devuelve true si pudo darlo de baja
	 */
	public boolean bajaMesero(Mesero mesero){// 
		return bajaMesero(mesero.getIdMesero()); // llama a la baja usando el idmesero
	} //bajaMesero
	
	
	
	
	/**
	 * Da de baja al mesero de la BD en base al id (si no está inscripto en materias)
	 * @param id es el idmesero del mesero que se dará de baja
	 * @return  true si pudo darlo de baja
	 */
	public boolean bajaMesero(int id){// devuelve true si pudo darlo de baja
		//Doy de baja al mesero
		String sql = "Delete from mesero where idmesero=" + id;
		if (conexion.sqlUpdate(sql)){
			mensaje("Baja de mesero exitosa");
			conexion.cerrarSentencia();
			return true;
		} 
		else {
			mensajeError("Falló la baja del mesero");
			return false;
		}
	} //bajaMesero
	
	
	
	
	
	/**
	 * Da de baja al mesero de la BD en base al id. Si está con inscripciones, 
	 * también las da de baja.
	 * @param idMesero es el idmesero del mesero que se dará de baja
	 * @return  true si pudo darlo de baja
	 */
	public boolean bajaMeseroconPedidosEnCascada(int idMesero){// devuelve true si pudo darlo de baja OJO FALTA PROBAR
		//Borro todas los pedidos de ese mesero
		//PedidoData pedidoData = new PedidoData();
		//List<Pedido> listaPedidos = pedidoData.getListaPedidosDelMesero(idMesero);
		//for (Pedido pedido : listaPedidos)
		//	pedidoData.bajaPedido(pedido);
		
		
		//Doy de baja al mesero
		String sql = "Delete from mesero where idmesero=" + idMesero;
		if (conexion.sqlUpdate(sql)){
			mensaje("Baja de mesero exitosa");
			conexion.cerrarSentencia();
			return true;
		} 
		else {
			mensajeError("Falló la baja del mesero");
			return false;
		}
	} //bajaMeseroInscripcionesEnCascada
	
	
	
	
	
	
	/**
	 * Modifica al mesero en la BD poniendole estos nuevos datos
	 * @param mesero el mesero que se modificará (en base a su idmesero)
	 * @return true si pudo modificarlo
	 */
	public boolean modificarMesero(Mesero mesero){
		String sql = 
				"Update mesero set " + 
				"nombreCompleto='" + mesero.getNombreCompleto() + "'," + 
				"clave='" + mesero.getClave() + "'" +
				" where idMesero='" + mesero.getIdMesero() + "'";
		if (conexion.sqlUpdate(sql)) {
			mensaje("Modificación de mesero exitosa");
			conexion.cerrarSentencia();
			return true;
		} 
		else {
			mensajeError("Falló la modificación de mesero");;
			return false;
		}
	} //modificarMesero
	
	
	
	
	
	/**
	 * Dado un resultSet lo convierte en un Mesero
	 * @param rs es el ResultSet que se pasa para convertirlo en el objeto Mesero
	 * @return el mesero con los datos del resultSet
	 */
	public Mesero resultSet2Mesero(ResultSet rs){
		Mesero mesero = new Mesero();
		try {
			mesero.setIdMesero(rs.getInt("idMesero"));
			mesero.setNombreCompleto(rs.getString("nombreCompleto"));
			mesero.setClave(rs.getString("clave"));
		} catch (SQLException ex) {
			//Logger.getLogger(MeseroData.class.getName()).log(Level.SEVERE, null, ex);
			mensajeError("Error al pasar de ResultSet a Mesero"+ex.getMessage());
		}
		return mesero;
	} // resultSet2Mesero
	
	
	
	
	
	/**
	 * Devuelve una lista con los meseros de la base de datos ordenados por idmesero
	 * @return la lista de meseros
	 */
	public List<Mesero> getListaMeseros(){ 
		return getListaMeseros(OrdenacionMesero.PORIDMESERO);
	} // getListaMeseros
	
	
	
	
	
	/**
	 * Devuelve una lista ordenada con los meseros de la base de datos
	 * @param ordenacion es el orden en el que se devolverán
	 * @return devuelve la lista de meseros
	 */
	public List<Mesero> getListaMeseros(OrdenacionMesero ordenacion){
		ArrayList<Mesero> lista = new ArrayList();
		String sql = "Select * from mesero";
		
		//defino orden
		if (ordenacion == OrdenacionMesero.PORIDMESERO) 
			sql = sql + " Order by idmesero";
		else if (ordenacion == OrdenacionMesero.PORNOMBRECOMPLETO)
			sql = sql + " Order by nombreCompleto";
		else 
			sql = sql + " Order by idmesero";
		
		//ejecuto
		ResultSet rs = conexion.sqlSelect(sql);
		
		//cargo la lista con los resultados
		try {
			while (rs.next()) {
				Mesero mesero = resultSet2Mesero(rs);
				lista.add(mesero);
			}
			conexion.cerrarSentencia(); // cierra el PreparedStatement y tambien cierra automaticamente el ResultSet
		} catch (SQLException ex) {
			mensajeError("Error al obtener lista de meseros" + ex.getMessage());
		}
		return lista;
	} //getListaMeseros
	
	
	
	
	
	/**
	 * devuelve una lista con los meseros de la base de datos en base al criterio de búsqueda que se le pasa.
	 * Si dni no es -1 usa dni. Si apellido no es "" usa apellido. Si nombre no es "" usa nombre
	 * Si hay más de un criterio de búsqueda lo combina con ANDs
	 * Si no hay ningún criterio de búsqueda devuelve toda la tabla
	 * 
	 * @param idMesero si idMesero no es -1 usa idMesero como criterio de búsqueda 
	 * @param nombreCompleto si nombre completo no es '' usa nombre como criterio de búsqueda
	 * @param clave      si clave no es '' usa clave como criterio de búsqueda
	 * @param ordenacion es el orden en el que devolverá la lista
	 * @return lista de meseros que cumplen con el criterio de búsqueda
	 */
	public List<Mesero> getListaMeserosXCriterioDeBusqueda(int idMesero, String nombreCompleto, String clave, OrdenacionMesero ordenacion){ 
		ArrayList<Mesero> lista = new ArrayList();
		String sql = "Select * from mesero";
		if ( idMesero != -1 || ! nombreCompleto.isEmpty() || ! clave.isEmpty() ) {
			sql = sql + " Where";
			
			if ( idMesero != -1 )
				sql = sql + " idmesero=" + idMesero;
			
			if ( ! nombreCompleto.isEmpty() ) {
				if (idMesero != -1) //Si ya puse el idMesero agrego and
					sql = sql+" AND";
				sql = sql+ " nombreCompleto LIKE '" + nombreCompleto + "%'";
			}
			
			if ( ! clave.isEmpty() ) {
				if (idMesero != -1 || ! nombreCompleto.isEmpty()) //Si ya puse el idMesero o nombreCompleto agrego and
					sql = sql+" AND";
				sql = sql+" clave LIKE '" + clave + "%'";
			}
			
		}
		
		//defino orden
		if (ordenacion == OrdenacionMesero.PORIDMESERO) 
			sql = sql + " Order by idmesero";
		else if (ordenacion == OrdenacionMesero.PORNOMBRECOMPLETO)
			sql = sql + " Order by nombreCompleto";
		else 
			sql = sql + " Order by idmesero";		
	
		// ejecuto
		ResultSet rs = conexion.sqlSelect(sql);
		
		// cargo la lista con los resultados
		try {
			while (rs.next()) {
				Mesero mesero = resultSet2Mesero(rs);
				lista.add(mesero);
			}
			conexion.cerrarSentencia(); // cierra el PreparedStatement y tambien cierra automaticamente el ResultSet
		} catch (SQLException ex) {
			mensajeError("Error al obtener lista de meseros" + ex.getMessage());
		}
		return lista;
	} // getListaMeserosXCriterioDeBusqueda
	
	
	
	
	
	/**
	 * Devuelve el mesero con ese idmesero
	 * @param id es el idmesero para identificarlo
	 * @return  el mesero retornado
	 */
	public Mesero getMesero(int id){
		String sql = "Select * from mesero where idmesero=" + id;
		ResultSet rs = conexion.sqlSelect(sql);
		Mesero mesero = null;
		try {
			if (rs.next()) {
				mesero = resultSet2Mesero(rs);
				conexion.cerrarSentencia();
			} else
				mensaje("Error al obtener un mesero");
		} catch (SQLException ex) {
			//Logger.getLogger(MeseroData.class.getName()).log(Level.SEVERE, null, ex);
			mensajeError("Error al obtener un Mesero " + ex.getMessage());
		}
		return mesero;
	} //getMesero
	
	
	
	
	
	/**
	 * Devuelve el mesero con ese apellido y nombre y con ese dni
	 * @param nombre es el parametro para identificarlo
	 * @return  el mesero retornado. Si no lo encuentra devuelve null.
	 */
	public Mesero getMesero(String nombreCompleto){
		String sql = "Select * from mesero where nombreCompleto='" + nombreCompleto + "' ";
		ResultSet rs = conexion.sqlSelect(sql);
		Mesero mesero = null;
		try {
			if (rs.next()) {
				mesero = resultSet2Mesero(rs);
				conexion.cerrarSentencia();
			} else
				mensaje("Error al obtener un mesero");
		} catch (SQLException ex) {
			//Logger.getLogger(MeseroData.class.getName()).log(Level.SEVERE, null, ex);
			mensajeError("Error al obtener un Mesero " + ex.getMessage());
		}
		return mesero;
	} //getMesero
	
} //class MeseroData