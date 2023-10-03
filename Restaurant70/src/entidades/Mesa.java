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
public class Mesa {
	private int idMesa, capacidad;
	private Estado estado;
	
	public enum Estado {LIBRE, OCUPADA, ATENDIDA};

	public Mesa() {
	}

	public Mesa(int capacidad, Estado estado) {
		this.capacidad = capacidad;
		this.estado = estado;
	}

	public Mesa(int idMesa, int capacidad, Estado estado) {
		this.idMesa = idMesa;
		this.capacidad = capacidad;
		this.estado = estado;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Mesa{" + "idMesa=" + idMesa + ", capacidad=" + capacidad + ", estado=" + estado + '}';
	}
	
	

}
