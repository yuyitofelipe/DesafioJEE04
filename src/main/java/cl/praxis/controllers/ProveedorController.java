package cl.praxis.controllers;
import cl.praxis.models.dao.ProveedorDAOImpl;
import cl.praxis.models.dto.Proveedor;
import cl.praxis.models.conn.Conexion;
import cl.praxis.models.dao.ProveedorDAO;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("/ProveedorController")
public class ProveedorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProveedorController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Proveedor> proveedor = obtenerProveedoresDesdeBD();
	    request.setAttribute("proveedores", proveedor);
	    request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String rut = request.getParameter("rut");
		String direccion = request.getParameter("direccion");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		String contacto = request.getParameter("contacto");
		String telefono_contacto = request.getParameter("telefono_contacto");
		
		if (nombre == null || rut == null ||direccion == null || correo == null || telefono == null || telefono_contacto == null || nombre.isEmpty() || rut.isEmpty() || direccion.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contacto.isEmpty() || telefono_contacto.isEmpty()) {
			request.setAttribute("errorMessage", "Completar todos los campos");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		try {
			
			Connection connection = Conexion.getCon();
			String sql = "INSERT INTO proveedores (nombre, rut, direccion, correo, telefono, contacto, telefono_contacto) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nombre);
            statement.setString(2, rut);
            statement.setString(3, direccion);
            statement.setString(4, correo);
            statement.setString(5, telefono);
            statement.setString(6, contacto);
            statement.setString(7, telefono_contacto);
            statement.executeUpdate();
            
		} catch (SQLException e) {
			
			throw new ServletException("Error al tratar de insertar al proveedor", e);
			
		}
	
		response.sendRedirect("ProveedorController");
	}
	
	private List<Proveedor> obtenerProveedoresDesdeBD() throws ServletException {
	    List<Proveedor> proveedores = new ArrayList<>();

	    try {
	    	Connection connection = Conexion.getCon();
	        String sql = "SELECT id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto FROM public.proveedores";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Proveedor proveedor = new Proveedor();
	            proveedor.setId(resultSet.getInt("id"));
	            proveedor.setNombre(resultSet.getString("nombre"));
	            proveedor.setRut(resultSet.getString("rut"));
	            proveedor.setDireccion(resultSet.getString("direccion"));
	            proveedor.setCorreo(resultSet.getString("correo"));
	            proveedor.setTelefono(resultSet.getString("telefono"));
	            proveedor.setContacto(resultSet.getString("contacto"));
	            proveedor.setTelefonoContacto(resultSet.getString("telefono_contacto"));
	            proveedores.add(proveedor);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();

	    } catch (SQLException e) {
	        throw new ServletException("Error al tratar de mostar los proveedores", e);
	    }

	    return proveedores;
	}

}