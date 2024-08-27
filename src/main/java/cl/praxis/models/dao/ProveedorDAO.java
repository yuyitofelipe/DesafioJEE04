package cl.praxis.models.dao;

import java.util.List;

import cl.praxis.models.dto.Proveedor;


public interface ProveedorDAO {
	void create(Proveedor p);
	Proveedor read(int id);
	List<Proveedor> read();
	void update(Proveedor p);
	void delete(int id);
}