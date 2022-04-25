package com.bps.ejercicio.dao;

import com.bps.ejercicio.models.Kardex;
import com.bps.ejercicio.models.Producto;
import com.bps.ejercicio.models.Usuario;

import java.util.List;

public interface ProductoDao {

    List<Producto> getProductos();

   void registrar(Producto producto);

   void editar(Producto producto);
   
   Producto vender(Producto producto);
   
   Producto ingresar(Producto producto);

   public Producto obtenerProductoPorID(Integer id);
   
   void registrarKardex(Kardex kardex);
   
   List<Kardex> getKardex();


}
