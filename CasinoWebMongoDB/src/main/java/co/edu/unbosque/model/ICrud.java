package co.edu.unbosque.model;

public interface ICrud {
	
	public abstract String agregar(Object registro);
	public abstract Object consultar();
	public abstract String actualizar(Object id, Object registro);
	public abstract String eliminar(Object id);
	public abstract Object listar();
}
