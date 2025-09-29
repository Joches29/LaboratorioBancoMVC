/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;

/**
 *
 * @author llean
 */
public class CuentaController {
    private CuentaDAO cuentaDAO;

    public CuentaController() {
        cuentaDAO = CuentaDAO.getInstancia();
    }
    
    public Cuenta crearCuenta(Cliente titular, String tipoMoneda) {
        return cuentaDAO.crearCuenta(titular, tipoMoneda);
    }

    public void depositar(String numero, double monto) {
        System.out.println("[CuentaController] depositar -> numero:'" + numero + "' monto:" + monto);
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }
        Cuenta c = cuentaDAO.buscarCuentaPorNumero(numero);
        if (c == null) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }
        c.depositar(monto);
        System.out.println("[CuentaController] depositar OK, saldo ahora: " + c.getSaldo());
    }

    public void retirar(String numero, double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }
        Cuenta c = cuentaDAO.buscarCuentaPorNumero(numero);
        if (c == null) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }
        c.retirar(monto); 
}

    public void transferir(String origen, String destino, double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }
        Cuenta c1 = cuentaDAO.buscarCuentaPorNumero(origen);
        Cuenta c2 = cuentaDAO.buscarCuentaPorNumero(destino);
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("Cuenta inválida.");
        }
        if (!c1.getMoneda().equals(c2.getMoneda())) {
            throw new IllegalArgumentException("No se pueden transferir entre cuentas de diferente moneda.");
        }
        c1.transferir(c2, monto);
    }

    public boolean eliminarCuenta(String cedula) {
        return cuentaDAO.eliminarCuenta(cedula);
    }
    
    public Cuenta buscarCedula(String cedula) {
        Cuenta c = cuentaDAO.buscarCuentaPorCedula(cedula);
        if (c == null) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }
        return c;
    }
    
    
    public Cuenta buscarNumeroCuenta(String numero) {
        System.out.println("[CuentaController] buscarNumeroCuenta input: '" + numero + "'");
        Cuenta c = cuentaDAO.buscarCuentaPorNumero(numero);
         System.out.println("[CuentaController] buscarNumeroCuenta result: " + (c == null ? "null" : c.getNumeroCuenta()));
        if (c == null) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }
        return c;
    }


    public void listarCuentas() {
        for (Cuenta c : cuentaDAO.listarCuentas()) {
            System.out.println("Cuenta: " + c.getNumeroCuenta() +
                    " | Titular: " + c.getTitular().getNombre() +
                    " | Saldo: " + c.getSaldo() +
                    " | Estado: " + (c.isActiva() ? "Activa" : "Inactiva") +
                    " | Moneda: " + c.getMoneda());
        }
    }
}
