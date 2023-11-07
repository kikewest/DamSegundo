package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import vista.Mapa2;
public class Controlador2 implements ActionListener {
	public Mapa2 vista;
	public Map<String, Productos> inventario = new HashMap<>();

	public Controlador2(Mapa2 vis) {
		this.vista = vis;
		Productos p1 = new Productos("Lomo de cerdo", 1.20, 70);
		Productos p2 = new Productos("Pechuga de cerdo", 0.70, 40);
		Productos p3 = new Productos("Costillas de cerdo", 2.20, 50);
		Productos p4 = new Productos("Secreto iberico", 3.50, 60);
		inventario.put("Lomo de cerdo", p1);
		inventario.put("Pechuga de cerdo", p2);
		inventario.put("Costillas de cerdo", p3);
		inventario.put("Secreto iberico", p4);
	}

	public void escuchar() {
		for (JButton btn : vista.botones) {
			btn.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.botones[0]) {
			aniadirProducto();
			mostrarInventario();
		} else if (e.getSource() == vista.botones[1]) {
			mostrarInventario();
		} else if (e.getSource() == vista.botones[2]) {
			actualizarProducto();
			mostrarInventario();
		} else if (e.getSource() == vista.botones[3]) {
			eliminarProducto();
			mostrarInventario();
		} else if (e.getSource() == vista.botones[4]) {
			vender();
			mostrarInventario();
		} else if (e.getSource()== vista.botones[5]) {
			salir();
		}
	}

	public void mostrarInventario() {
		@SuppressWarnings("serial")
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false; // Establece todas las celdas como no editables
			}
		};
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Cantidad");

		for (Productos producto : inventario.values()) {
			modelo.addRow(new Object[]{producto.getNombre(), producto.getPrecio(), producto.getCantidad()});
		}

		vista.tabla.setModel(modelo);
	}

	public void aniadirProducto() {
		String nombre = JOptionPane.showInputDialog(vista, "Por favor, introduzca el nombre del producto:");
		if (nombre != null && !nombre.isEmpty()) {
			String precioStr = JOptionPane.showInputDialog(vista, "Por favor, introduzca el precio del producto:");
			String cantidadStr = JOptionPane.showInputDialog(vista, "Por favor, introduzca la cantidad en stock:");

			try {
				double precio = Double.parseDouble(precioStr);
				int cantidad = Integer.parseInt(cantidadStr);

				if (inventario.containsKey(nombre)) {
					JOptionPane.showMessageDialog(vista, "Error el producto ya existe, Boton modificar");
				} else {
					Productos nuevoProducto = new Productos(nombre, precio, cantidad);
					inventario.put(nombre, nuevoProducto);
				}

				JOptionPane.showMessageDialog(vista, "Producto añadido con éxito.");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(vista, "Error: Ingrese un precio y cantidad válidos (números).");
			}
		}
	}


	public void eliminarProducto() {
		String nombreEliminar = JOptionPane.showInputDialog(vista, "Por favor, introduzca el nombre del producto que desea eliminar:");
		if (nombreEliminar != null && !nombreEliminar.isEmpty()) {
			if (inventario.containsKey(nombreEliminar)) {
				int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					inventario.remove(nombreEliminar);
					JOptionPane.showMessageDialog(vista, "Producto eliminado con éxito.");
				}
			} else {
				JOptionPane.showMessageDialog(vista, "El producto a eliminar no se encuentra en el inventario.");
			}
		}
	}

	public void salir() {
		int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (confirmacion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void actualizarProducto() {
		String nombreProducto = JOptionPane.showInputDialog(vista, "Por favor, introduzca el nombre del producto que desea actualizar:");
		if (nombreProducto != null && !nombreProducto.isEmpty()) {
			if (inventario.containsKey(nombreProducto)) {
				// Eliminar el producto existente
				inventario.remove(nombreProducto);

				String nuevoNombre = JOptionPane.showInputDialog(vista, "Nuevo nombre:");
				String nuevoPrecioStr = JOptionPane.showInputDialog(vista, "Nuevo precio:");
				String nuevaCantidadStr = JOptionPane.showInputDialog(vista, "Nueva cantidad en stock:");

				try {
					double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
					int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);

					// Crear un nuevo producto con los datos proporcionados
					Productos nuevoProducto = new Productos(nuevoNombre, nuevoPrecio, nuevaCantidad);

					// Agregar el nuevo producto al inventario
					inventario.put(nuevoNombre, nuevoProducto);

					JOptionPane.showMessageDialog(vista, "Producto actualizado con éxito.");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista, "Error: Ingrese un precio y cantidad válidos (números).");
				}
			} else {
				JOptionPane.showMessageDialog(vista, "El producto a actualizar no se encuentra en el inventario.");
			}
		}
	}
	
	public void vender() {
	    String nombreProducto = JOptionPane.showInputDialog(vista, "Por favor, introduzca el nombre del producto que desea vender:");
	    if (nombreProducto != null && !nombreProducto.isEmpty()) {
	        if (inventario.containsKey(nombreProducto)) {
	            String cantidadVendidaStr = JOptionPane.showInputDialog(vista, "Cantidad que desea vender:");
	            try {
	                int cantidadVendida = Integer.parseInt(cantidadVendidaStr);

	                // Verificar si hay suficiente stock para la venta
	                Productos productoExistente = inventario.get(nombreProducto);

	                if (cantidadVendida > 0 && cantidadVendida <= productoExistente.getCantidad()) {
	                    // Calcular el precio total de la cantidad vendida
	                    double precioVenta = cantidadVendida * productoExistente.getPrecio();

	                    // Calcular la nueva cantidad en stock
	                    int nuevaCantidad = productoExistente.getCantidad() - cantidadVendida;

	                    // Crear un nuevo objeto Productos con el mismo nombre y precio, pero con la nueva cantidad
	                    Productos nuevoProducto = new Productos(productoExistente.getNombre(), productoExistente.getPrecio(), nuevaCantidad);

	                    // Eliminar el registro existente y agregar el nuevo
	                    inventario.remove(nombreProducto);
	                    inventario.put(nombreProducto, nuevoProducto);

	                    JOptionPane.showMessageDialog(vista, "Venta realizada con éxito.\nPrecio total de la venta: " + precioVenta);
	                } else {
	                    JOptionPane.showMessageDialog(vista, "Error: Cantidad de venta no válida o insuficiente stock.");
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(vista, "Error: Ingrese una cantidad válida (número).");
	            }
	        } else {
	            JOptionPane.showMessageDialog(vista, "El producto a vender no se encuentra en el inventario.");
	        }
	    }
	}

}

