package co.edu.uniquindio.poo.empresajfx.controller;

import co.edu.uniquindio.poo.empresajfx.model.Empresa;
import co.edu.uniquindio.poo.empresajfx.model.Producto;

import java.util.Collection;

public class ProductoController {
    private Empresa empresa;

    public ProductoController(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean agregarProducto(Producto producto){
         return empresa.agregarProducto(producto);
    }

    public Collection<Producto> obtenerListaProductos(){
        return empresa.getListaProductos();
    }

    public boolean actualizarProducto(String id, Producto producto){
        return empresa.actualizarProducto(id,producto);
    }

    public boolean eliminarProducto(String id){
        return empresa.eliminarProducto(id);
    }
}
