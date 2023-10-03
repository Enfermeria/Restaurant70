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
public class Pedido {
	private int idPedido, idMesa, idMesero;
	private boolean pagado;

	public Pedido() {
	}

	public Pedido(int idMesa, int idMesero, boolean pagado) {
		this.idMesa = idMesa;
		this.idMesero = idMesero;
		this.pagado = pagado;
	}

	public Pedido(int idPedido, int idMesa, int idMesero, boolean pagado) {
		this.idPedido = idPedido;
		this.idMesa = idMesa;
		this.idMesero = idMesero;
		this.pagado = pagado;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getIdMesero() {
		return idMesero;
	}

	public void setIdMesero(int idMesero) {
		this.idMesero = idMesero;
	}

	public boolean getPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	@Override
	public String toString() {
		return "Pedido{" + "idPedido=" + idPedido + ", idMesa=" + idMesa + ", idMesero=" + idMesero + ", pagado=" + pagado + '}';
	}
	
	
}
