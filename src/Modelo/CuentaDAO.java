/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author llean
 */
public class CuentaDAO {
    private static CuentaDAO instancia;

    private ArrayList<Cuenta> cuentas;
    private static int consecutivo = 1;
    private static final String PREFIJO = "101";

    private CuentaDAO() {
    this.cuentas = new ArrayList();
    }

    public static CuentaDAO getInstancia() {
        if (instancia == null) {
            instancia = new CuentaDAO();
        }
        return instancia;
    }

    public Cuenta crearCuenta(Cliente titular, String tipoMoneda) {
        String numero = PREFIJO + String.format("%014d", consecutivo++);
        Cuenta cuenta = tipoMoneda.equals("CRC") ?
                new CuentaColones(numero, titular) :
                new CuentaDolares(numero, titular);
        cuentas.add(cuenta);
        
        System.out.println("[CuentaDAO] crearCuenta -> " + numero + " | titular=" + titular.getId());
        return cuenta;
    }

    public Cuenta buscarCuentaPorCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            return null;
        }
        for (Cuenta c : cuentas) {
            if (c.getTitular().getId().equals(cedula)) {
                System.out.println("[CuentaDAO] buscarCuenta -> ENCONTRADA: " + c.getNumeroCuenta());
                return c;
            }
        }
         System.out.println("[CuentaDAO] buscarCuenta -> NO encontrada");
        return null;
    }
    
    public Cuenta buscarCuentaPorNumero(String numero) {
        if (numero == null || numero.isBlank()) {
            return null;
        }
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equals(numero)) {
              return c;
            }
        }
        return null;
    }


    public boolean eliminarCuenta(String cedula) {
        Cuenta c = buscarCuentaPorCedula(cedula);
        if (c != null && c.getSaldo() == 0.0) {
            cuentas.remove(c);
            return true;
        }
        return false;
    }

    public ArrayList<Cuenta> listarCuentas() {
        return cuentas;
    }
}