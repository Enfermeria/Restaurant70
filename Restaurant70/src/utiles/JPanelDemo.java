
package utiles;

import entidades.Mesa;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import static vistas.Meseros.mapaMesas;


public class JPanelDemo extends JFrame {
	private String columnas[] = {"Auto", "Color", "Tipo"};
	private Object celdas[][] = {
		{"Kia","Rojo", "A"},
		{"Toyota","Azul","C"},
		{"Lexus","Negro","B"},
		{"BMW","Verde","B"},
		{"Pagani", "Dorado", "C"},
		{"Ferrari", "Rojo", "A"}
	};
	private JTable tabla;

	public JPanelDemo(){
		super("JTable Color");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		tabla = new JTable(celdas,columnas);
		//RowsRenderer2 rr = new RowsRenderer2(2);
		tabla.setDefaultRenderer(Object.class, new RowsRenderer2(2));
		add(new JScrollPane(tabla));
		setVisible(true);
		pack();
	}

	public static void main(String[]args){
		JPanelDemo obj = new JPanelDemo();
	}

	
	
	
	public class RowsRenderer2 extends DefaultTableCellRenderer {
	private int columna ;

	public RowsRenderer2(int Colpatron) {
		this.columna = Colpatron;
	}

	@Override
	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column) {  
		System.out.println("hola");
		//setBackground(Color.yellow);
		table.setForeground(Color.black);
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		if(table.getValueAt(row,columna).equals("A")) {
			this.setBackground(Color.RED);
		}else if(table.getValueAt(row,columna).equals("B")){
			this.setBackground(Color.BLUE);
		}else if(table.getValueAt(row, columna).equals("C")){
			this.setBackground(Color.GREEN);
		}
		return this;
	  }
} //class RowsRenderer
}








/*
	ESTOS NO ME ANDUVIERON
*/
/**
	 * Para cambiar el color u otras caracaterísticas de alguna fila/columna de una
	 * JTable. Para usarla simplemente:
	 *    tabla.setDefaultRenderer(Object.class, new RendererMesas(0));
	 * donde ese 0 es la columna en base a cuyo valor se cambiará el color de la fila
	 * @author John David Molina
	 */
	class RendererMesas extends DefaultTableCellRenderer {

		public RendererMesas() {
			System.out.println("En constructor RedererMesas"); //debug
			setOpaque(true); //Permite que se vea el color de la celda del JLabel
		}

		@Override
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column) {        
			System.out.println("en getTableCellRendererComponent"); //debug
			setBackground(Color.YELLOW);
			table.setForeground(Color.GREEN);
			//elijo alineacion centro para mesas y pedidos
			setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTE //elijo alineacion centro para mesas y pedidos
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			if( mapaMesas.get(table.getValueAt(row,0)).getEstado() == Mesa.EstadoMesa.OCUPADA ) {
				this.setForeground(Color.RED);
			}else if( mapaMesas.get(table.getValueAt(row,0)).getEstado() == Mesa.EstadoMesa.ATENDIDA){
				this.setForeground(Color.GREEN);
			}else if( mapaMesas.get(table.getValueAt(row,0)).getEstado() == Mesa.EstadoMesa.LIBRE){
				this.setForeground(Color.BLUE);
			}
			return this;
		}
	} // class RendererMesas

	
	
	/**
	 *		TableColumn columna = jTable1.getColumnModel().getColumn(1);// selecciono la columna que me interesa de la tabla
	 *		EditorCeldas TableCellRenderer = new EditorCeldas();
	 *		TableCellRenderer.setColumns(column); //se le da por parametro la columna que se quiere modificar
	 *		TableCellRenderer.setRow(Row);//se le da por parametro la fila que se quiere modificar
	 *		columna.setCellRenderer(TableCellRenderer); // le aplico la edicion
	 */
	class EditorCeldas extends JLabel implements TableCellRenderer {
		public EditorCeldas() {
			setOpaque(true); // Permite que se vea el color en la celda del JLabel
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if( (column == 0)){
				setBackground(Color.red); // Una condicion arbitraria solo para pintar el JLabel que esta en la celda.
				//setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
			}
			if((column==0)){
				setBackground(Color.white); // Una condicion arbitraria solo para pintar el JLabel que esta en la celda.
				// setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
			}
			return this;
		}

	} //EditorCeldas

	