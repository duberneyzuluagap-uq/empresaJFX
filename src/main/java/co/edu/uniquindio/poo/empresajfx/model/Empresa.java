package co.edu.uniquindio.poo.empresajfx.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class Empresa {
    private String nombre;
    private Collection<Cliente> clientes;
    private Collection<Producto> listaProductos;

    public Empresa(String nombre) {
        this.nombre = nombre;
        clientes = new LinkedList<>();
        listaProductos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Collection<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(Collection<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public boolean agregarCliente(Cliente cliente) {
        boolean centinela = false;
        if (!verificarCliente(cliente.getCedula())) {
            clientes.add(cliente);
            centinela = true;
        }
        return centinela;
    }

    public boolean eliminarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                clientes.remove(cliente);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean actualizarCliente(String cedula, Cliente actualizado) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                cliente.setCedula(actualizado.getCedula());
                cliente.setNombre(actualizado.getNombre());
                cliente.setApellido(actualizado.getApellido());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }

    //CRUD PRODUCTO
    public Optional<Producto> buscarProducto(String id){
        return listaProductos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public boolean agregarProducto(Producto producto){
        Optional<Producto> productoOptional = buscarProducto(producto.getId());

        if (productoOptional.isPresent()){
            return false;
        }

        listaProductos.add(producto);
        return true;
    }

    public boolean eliminarProducto(String id){
        Optional<Producto> productoOptional = buscarProducto(id);

        if (productoOptional.isEmpty()){
            return false;
        }

        listaProductos.remove(productoOptional.get());
        return true;
    }

    public boolean actualizarProducto(String id, Producto producto){
        Optional<Producto> productoOptional = buscarProducto(id);

        if (productoOptional.isEmpty()){
            return false;
        }

        Producto producto1 = productoOptional.get();

        producto1.setId(producto.getId());
        producto1.setCantidadDisponible(producto.getCantidadDisponible());
        producto1.setNombre(producto.getNombre());

        return true;

    }
}

