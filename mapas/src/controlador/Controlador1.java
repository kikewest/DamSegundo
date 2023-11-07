package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import vista.Mapa1;

public class Controlador1 implements ActionListener {
	public Mapa1 vista;
	public Map<String, Double> notas = new HashMap<>();

	public Controlador1(Mapa1 vis) {
		this.vista = vis;
		
		notas.put("Kike", 9.5);
        notas.put("Jesus", 9.0);
        notas.put("Yerai", 8.8);
        notas.put("Pablo", 6.0);
        notas.put("Mario", 7.3);
        notas.put("Celuix", 7.8);
        notas.put("Rafa", 1.5);
        notas.put("Fran", 1.0);
	}

	public void escuchar() {
		for (JButton btn : vista.botones) {
			btn.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vista.botones[0]) {
			aniadir();
		} else if (e.getSource() == vista.botones[1]) {
			mostrar();
		} else if (e.getSource() == vista.botones[2]) {
			actualizarRegistro();
		} else if (e.getSource() == vista.botones[3]) {
			eliminarRegistro();
		} else if (e.getSource() == vista.botones[4]) {
			salir();
		}
	}

	public void mostrar() {
		@SuppressWarnings("serial")
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false; // Establece todas las celdas como no editables
			}
		};
		modelo.addColumn("Nombre");
		modelo.addColumn("Nota");

		for (Map.Entry<String, Double> entry : notas.entrySet()) {
			modelo.addRow(new Object[]{entry.getKey(), entry.getValue()});
		}

		vista.tabla.setModel(modelo);
	}


	public void aniadir() {
		String nombre = JOptionPane.showInputDialog(vista, "Por favor, introduzca el nombre del alumno:");
		if (nombre != null && !nombre.isEmpty()) {
			// Solicitar nota del alumno al usuario
			String notaStr = JOptionPane.showInputDialog(vista, "Por favor, introduzca la nota del alumno:");
			try {
				double nota = Double.parseDouble(notaStr);
				notas.put(nombre, nota); // Agregar el nombre y la nota al mapa
				JOptionPane.showMessageDialog(vista, "Alumno añadido con éxito.");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(vista, "Error: Ingrese una nota válida (número).");
			}
		}
	}

	public void actualizarRegistro() {
		String nombreActualizar = JOptionPane.showInputDialog(vista, "Por favor, introduzca el nombre que desea actualizar:");
		if (nombreActualizar != null && !nombreActualizar.isEmpty()) {
			if (notas.containsKey(nombreActualizar)) {
				String nuevoNombre = JOptionPane.showInputDialog(vista, "Nuevo nombre:");
				String nuevaNotaStr = JOptionPane.showInputDialog(vista, "Nueva nota:");
				try {
					double nuevaNota = Double.parseDouble(nuevaNotaStr);
					int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de actualizar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION);
					if (confirmacion == JOptionPane.YES_OPTION) {
						notas.remove(nombreActualizar);
						notas.put(nuevoNombre, nuevaNota);
						JOptionPane.showMessageDialog(vista, "Registro actualizado con éxito.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista, "Error: Ingrese una nota válida (número).");
				}
			} else {
				JOptionPane.showMessageDialog(vista, "El nombre a actualizar no se encuentra en el mapa.");
			}
		}
	}
	
	public void eliminarRegistro() {
	    String nombreEliminar = JOptionPane.showInputDialog(vista, "Por favor, introduzca el nombre que desea eliminar:");
	    if (nombreEliminar != null && !nombreEliminar.isEmpty()) {
	        if (notas.containsKey(nombreEliminar)) {
	            int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION);
	            if (confirmacion == JOptionPane.YES_OPTION) {
	                notas.remove(nombreEliminar);
	                JOptionPane.showMessageDialog(vista, "Registro eliminado con éxito.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(vista, "El nombre a eliminar no se encuentra en el mapa.");
	        }
	    }
	}
	public void salir() {
	    int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
	    if (confirmacion == JOptionPane.YES_OPTION) {
	        System.exit(0);
	    }
	}
}