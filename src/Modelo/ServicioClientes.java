/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author jprod
 */
public class ServicioClientes {
    private static ServicioClientes instancia;
    private final IGestorClientes gestor;
    private final IGestorHistoricoClientes gestorHistorico;
    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");


    private ServicioClientes() {
        this.gestor = GestorClientesMem.getInstancia();
        this.gestorHistorico = GestorHistoricoClientes.getInstancia();
    }
    
    public static ServicioClientes getInstancia() {
        if (instancia == null) {
            instancia = new ServicioClientes();
        }
        return instancia;
    }
    
    public void guardar(String id, String nombre, String correo, String telefono, boolean preferencial) {
        validarRequeridos(id, nombre, correo, telefono);
        if (gestor.existe(id)) throw new IllegalArgumentException("Ya existe un registro con id=" + id);
        if (!EMAIL.matcher(correo).matches()) throw new IllegalArgumentException("Formato de correo inválido");
        
        nombre = capitalizarNombre(nombre);
        
        gestor.guardar(new Cliente(id, nombre, correo, telefono, preferencial));
    }
    
    public void actualizar(String id, String correo, String telefono, boolean preferencial) {
        Objects.requireNonNull(ultimoRegistro(), "No se ha cargado ningun registro");
        validarRequeridos(id, correo, telefono);
        if(!hayCambios(id, correo, telefono)) return;
        if (!gestor.existe(id)) throw new IllegalArgumentException("No existe un registro con id=" + id);
        if (!EMAIL.matcher(correo).matches()) throw new IllegalArgumentException("Formato de correo inválido");
        Cliente cliente=gestor.buscar(id);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);
        cliente.setPreferencial(preferencial);
        gestor.actualizar(cliente);
    }
    
    public void eliminar(String id) {
        if (!gestor.existe(id)) throw new IllegalArgumentException("No existe ningun registro con id=" + id);
        gestor.eliminar(id);
    }
    
    public Cliente buscar(String id) {
        validarRequeridos(id);
        if(!gestor.existe(id)) throw new IllegalArgumentException("No existe ningun registro con id=" + id);
        return gestor.buscar(id);
    }
    
    public Cliente ultimoRegistro() {
        Cliente cliente = gestor.ultimoRegistro();
        Objects.requireNonNull(cliente, "No se ha cargado ningun registro");
        return cliente;
    }
    
    public boolean validarIdDisponible(String id){
        if(id==null||id.isBlank()) throw new IllegalArgumentException("Faltan datos requeridos");
        return !gestor.existe(id);
    }
    
    public List<Cliente> listar() {
        return gestor.listar();
    }
    
    private void validarRequeridos(String... datos){
        for(String dato:datos){
            if (dato==null || dato.isBlank())
                throw new IllegalArgumentException("Faltan datos requeridos");
        }
    }
    
    private boolean hayCambios(String id,String correo,String telefono){
        Cliente cliente = gestor.buscar(id);
        Objects.requireNonNull(cliente, "No se ha cargado ningun registro");
        validarRequeridos(correo,telefono);
        return !(cliente.getCorreo().equals(correo) && cliente.getTelefono().equals(telefono));
    }
    
    private String capitalizarNombre(String nombre) {
        String[] palabras = nombre.trim().toLowerCase().split("\\s+");
        for (int i = 0; i < palabras.length; i++) {
            if (!palabras[i].isEmpty()) {
                palabras[i] = Character.toUpperCase(palabras[i].charAt(0)) + palabras[i].substring(1);
            }
        }
        return String.join(" ", palabras);
    }
    
}
