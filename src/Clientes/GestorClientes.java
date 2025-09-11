/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jprod
 */
public class GestorClientes {
    private final HashMap<String, Cliente> map;

    public GestorClientes() {
        map = new HashMap<>();
    }
    
    public Cliente buscar(String id) { 
        Objects.requireNonNull(id, "Id requerido");
        return map.get(id); 
    }
    
    public void guardar(Cliente cliente) {
        Objects.requireNonNull(cliente, "Cliente requerido");
        if (map.putIfAbsent(cliente.getId(), cliente) != null) {
            throw new IllegalStateException("Ya existe un cliente con id=" + cliente.getId());
        } 
    }
    
    public void actualizar(Cliente cliente) {
        Objects.requireNonNull(cliente, "Cliente requerido");
        String id = cliente.getId();
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException("No existe cliente con id=" + id);
        }
        map.put(id, cliente);
    }

    public void eliminar(String id) {
        Objects.requireNonNull(id, "Id requerido");
        if (map.remove(id) == null) {
            throw new IllegalArgumentException("No existe cliente con id=" + id);
        }
    }

    public boolean existe(String id) {
        Objects.requireNonNull(id, "Id requerido");
        return map.containsKey(id);
    }

    public List<Cliente> listar() {
        return new ArrayList<>(map.values());
    }
}
