package com.bps.ejercicio.controllers;


import com.bps.ejercicio.dao.ProductoDao;
import com.bps.ejercicio.dao.UsuarioDao;
import com.bps.ejercicio.models.Kardex;
import com.bps.ejercicio.models.Producto;
import com.bps.ejercicio.models.Usuario;
import com.bps.ejercicio.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private JWTUtil jwtUtil;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/productos", method = RequestMethod.GET)
    public List<Producto> getUProductos() {

        return productoDao.getProductos();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/registroProducto", method = RequestMethod.POST)
    public void registrarProducto(@RequestBody Producto producto) {

        productoDao.registrar(producto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/listarProductoId", method = RequestMethod.POST)
    public Producto listarProductoId(@RequestBody int id){
        return productoDao.obtenerProductoPorID(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/editarProducto", method = RequestMethod.POST)
    public void editarProducto(@RequestBody Producto producto) {

        productoDao.editar(producto);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/venderProducto", method = RequestMethod.POST)
    public Producto venderProducto(@RequestBody Producto producto) {

    	return productoDao.vender(producto);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/ingresarProducto", method = RequestMethod.POST)
    public Producto ingresarProducto(@RequestBody Producto producto) {

       return productoDao.ingresar(producto);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/registroKardex", method = RequestMethod.POST)
    public void registrarKardex(@RequestBody Kardex kardex) {

        productoDao.registrarKardex(kardex);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "api/kardex", method = RequestMethod.GET)
    public List<Kardex> getKardex() {

        return productoDao.getKardex();
    }


}
