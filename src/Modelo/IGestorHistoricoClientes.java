/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Brwni
 */
public interface IGestorHistoricoClientes {
    void guardar(Cliente cliente);
    List<Cliente> listar();
}
