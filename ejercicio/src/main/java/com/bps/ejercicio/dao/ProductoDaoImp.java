package com.bps.ejercicio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bps.ejercicio.models.Kardex;
import com.bps.ejercicio.models.Producto;

@Repository
@Transactional
public class ProductoDaoImp implements ProductoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Producto> getProductos() {
        String query = "FROM Producto";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void registrar(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void editar(Producto producto) {

        Query query2 = entityManager.createQuery("UPDATE Producto p SET p.nombre = :nombre, p.valor =:valor, p.cantidad = :cantidad " +
        "  WHERE ID = :id")
                .setParameter("nombre", producto.getNombre())
                .setParameter("valor", producto.getValor())
                .setParameter("cantidad", producto.getCantidad())
                .setParameter("id", producto.getId());

        int rowsUpdated = query2.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);

    }

    @Override
    public Producto obtenerProductoPorID(Integer id) {
        String query = "FROM Producto WHERE ID = :id";
        List<Producto> lista = entityManager.createQuery(query)
                .setParameter("id", id)
                .getResultList();
        if (lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }

	@Override
	public Producto vender(Producto producto) {
		
		//Valido producto y existencia		
		if(producto!= null && existenciaProducto(producto)) {
			Integer cantidad = producto.getCantidad()-1;
			Query query2 = entityManager.createQuery("UPDATE Producto p SET p.cantidad = :cantidad " +
					"  WHERE ID = :id")
			        .setParameter("id", producto.getId())
			        .setParameter("cantidad", cantidad);
			int rowsUpdated = query2.executeUpdate();
		    System.out.println("entities Updated: " + rowsUpdated);
		    return obtenerProductoPorID(producto.getId());
		}
		
		return null;
		      
	}
	
	/**
	 * Valida sila cantidad de productor es mayor a cero para vender
	 * @param id
	 * @return
	 */
	public Boolean existenciaProducto(Producto producto) {
		
		if(producto.getCantidad()>0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Producto ingresar(Producto producto) {
		// TODO Auto-generated method stub
		Integer cantidad = producto.getCantidad()+1;
		Query query2 = entityManager.createQuery("UPDATE Producto p SET p.cantidad = :cantidad " +
				"  WHERE ID = :id")
		        .setParameter("id", producto.getId())
		        .setParameter("cantidad", cantidad);
		int rowsUpdated = query2.executeUpdate();
	    System.out.println("entities Updated: " + rowsUpdated);
	    return obtenerProductoPorID(producto.getId());
		
	}

	@Override
	public void registrarKardex(Kardex kardex) {
		// TODO Auto-generated method stub
		System.out.println("se registro kardex");
		 entityManager.merge(kardex);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Kardex> getKardex() {
		 String query = "FROM Kardex";
	     return entityManager.createQuery(query).getResultList();
	}


}
