/*
	Trabajo práctico final de la Guía 6 del curso Desarrollo de Apps
	Universidad de La Punta en el marco del proyecto Argentina Programa 4.0

	Integrantes:
		John David Molina Velarde
		Leticia Mores
		Enrique Germán Martínez
		Carlos Eduardo Beltrán
 */
package entidades;

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Mesero {
	private int idMesero;
	private String nombreCompleto, clave;

	public Mesero() {
	}

	public Mesero(String nombreCompleto, String clave) {
		this.nombreCompleto = nombreCompleto;
		this.clave = clave;
	}

	public Mesero(int idMesero, String nombreCompleto, String clave) {
		this.idMesero = idMesero;
		this.nombreCompleto = nombreCompleto;
		this.clave = clave;
	}

	public int getIdMesero() {
		return idMesero;
	}

	public void setIdMesero(int idMesero) {
		this.idMesero = idMesero;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Mesero{" + "idMesero=" + idMesero + ", nombreCompleto=" + nombreCompleto + ", clave=" + clave + '}';
	}
	
	
}
