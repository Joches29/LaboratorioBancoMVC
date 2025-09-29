/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author llean
 */
public class CuentaDolares extends Cuenta {
    
    public CuentaDolares(String numeroCuenta, Cliente titular) {
        super(numeroCuenta, titular, "USD");
    }
    
}
