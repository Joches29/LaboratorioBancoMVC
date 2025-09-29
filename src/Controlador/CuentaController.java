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
        Cuenta c = cuentaDAO.buscarCuenta(numero);
        if (c == null) throw new IllegalArgumentException("Cuenta no encontrada.");
        c.depositar(monto);
    }

    public void retirar(String numero, double monto) {
        Cuenta c = cuentaDAO.buscarCuenta(numero);
        if (c == null) throw new IllegalArgumentException("Cuenta no encontrada.");
        c.retirar(monto);
    }

    public void transferir(String origen, String destino, double monto) {
        Cuenta c1 = cuentaDAO.buscarCuenta(origen);
        Cuenta c2 = cuentaDAO.buscarCuenta(destino);
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("Cuenta inválida.");
        } else if (!c1.getMoneda().equals(c2.getMoneda())) {
            throw new IllegalArgumentException(" No se pueden transferir entre cuentas de diferente moneda.");
        }
                c1.transferir (c2, monto);
        }

    public boolean eliminarCuenta(String numero) {
        return cuentaDAO.eliminarCuenta(numero);
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
