package com.bps.ejercicio.dao;

import com.bps.ejercicio.models.Producto;
import com.bps.ejercicio.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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


}
