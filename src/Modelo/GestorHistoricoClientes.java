/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brwni
 */
public class GestorHistoricoClientes implements IGestorHistoricoClientes {
    private static GestorHistoricoClientes instancia;
    private final List<Cliente> historico;

    private  GestorHistoricoClientes() {
        this.historico = new ArrayList();
    }
    
    public static GestorHistoricoClientes getInstancia() {
        if (instancia == null) {
            instancia = new GestorHistoricoClientes();
        }
        return instancia;
    }
    

    @Override
    public void guardar(Cliente cliente) {
        historico.add(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(historico); 
    }
}
