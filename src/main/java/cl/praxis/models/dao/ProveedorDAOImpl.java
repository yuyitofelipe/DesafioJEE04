package cl.praxis.models.dao;

import cl.praxis.models.conn.Conexion;
import cl.praxis.models.dto.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ProveedorDAOImpl implements ProveedorDAO {

	@Override
	public void create(Proveedor p) {
		String sql = "insert into proveedores (nombre, rut, direccion, correo, telefono, contacto, telefono_contacto) values ('"
				+ p.getNombre() + "', '" + p.getRut() + "', '" + p.getDireccion() + "', '" + p.getCorreo() + "', '"
				+ p.getTelefono() + "', '" + p.getContacto() + "', '" + p.getTelefonoContacto() + "')";
		try {
			Connection c = Conexion.getCon();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error con el metodo create ");
			e.printStackTrace();
		}
	}

	@Override
	public Proveedor read(int id) {
		Proveedor p = null;

		try {
			Connection c = Conexion.getCon();

			Statement s = c.createStatement();
			String sql = "select id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto from proveedores where id = "
					+ id;

			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				p = new Proveedor(rs.getInt("id"), rs.getString("nombre"), rs.getString("rut"),
						rs.getString("direccion"), rs.getString("correo"), rs.getString("telefono"),
						rs.getString("contacto"), rs.getString("telefono_contacto"));
			}
		} catch (SQLException e) {
			System.out.println("Error con el metodo read id ");
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public List<Proveedor> read() {

		List<Proveedor> proveedor = new ArrayList<Proveedor>();

		try {
			Connection c = Conexion.getCon();

			Statement s = c.createStatement();
			String sql = "select id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto from proveedores";

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				proveedor.add(new Proveedor(rs.getInt("id"), rs.getString("nombre"), rs.getString("rut"),
						rs.getString("direccion"), rs.getString("correo"), rs.getString("telefono"),
						rs.getString("contacto"), rs.getString("telefono_contacto")));

			}

		} catch (SQLException e) {
			System.out.println("Error con el metodo read ");
			e.printStackTrace();

		}
		return proveedor;
	}

	@Override
	public void update(Proveedor p) {
		String sql = "update proveedores set nombre = '" + p.getNombre() + "', rut = '" + p.getRut()
				+ "', direccion = '" + p.getDireccion() + "', correo = '" + p.getCorreo() + "', telefono = '"
				+ p.getTelefono() + "', contacto = '" + p.getContacto() + "', telefono_contacto = '"
				+ p.getTelefonoContacto() + "' where id = " + p.getId();
		try {
			Connection c = Conexion.getCon();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error con el metodo update ");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from proveedores where id = " + id;
		try {
			Connection c = Conexion.getCon();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error en el método update()");
			e.printStackTrace();
		}
	}

}