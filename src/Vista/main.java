/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Vista;

import Controlador.CuentaController;
import Modelo.Cliente;
import Modelo.Cuenta;

/**
 *
 * @author llean
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear un controlador (usa Singleton internamente)
        CuentaController controller = new CuentaController();

        // Crear un cliente (ya debería existir del módulo de clientes, aquí lo simulamos)
        Cliente cliente1 = new Cliente(
            "CLI001", 
            "Juan Pérez", 
            "juan.perez@email.com", 
            "555-1234", 
            true
        );

        Cliente cliente2 = new Cliente(
            "CLI002", 
            "María García", 
            "maria.garcia@email.com", 
            "555-5678", 
            false
        );
        
        // Crear cuentas
        Cuenta cuentaCRC = controller.crearCuenta(cliente1, "CRC");
        Cuenta cuentaUSD = controller.crearCuenta(cliente2, "USD");

        System.out.println("✅ Cuentas creadas:");
        controller.listarCuentas();

        // Depositar dinero
        controller.depositar(cuentaCRC.getNumeroCuenta(), 50000);
        controller.depositar(cuentaUSD.getNumeroCuenta(), 200);

        System.out.println("\n✅ Después de depósitos:");
        controller.listarCuentas();

        // Retiro
        controller.retirar(cuentaCRC.getNumeroCuenta(), 10000);
        System.out.println("\n✅ Después de retiro de 10,000 CRC:");
        controller.listarCuentas();

        // Transferencia
        controller.transferir(cuentaCRC.getNumeroCuenta(), cuentaUSD.getNumeroCuenta(), 5000);
        System.out.println("\n✅ Después de transferencia 5,000 CRC → USD:");
        controller.listarCuentas();

        // Eliminar cuenta con saldo != 0 (fallará)
        boolean eliminado = controller.eliminarCuenta(cuentaCRC.getNumeroCuenta());
        System.out.println("\n❌ Intento de eliminar cuenta CRC con saldo distinto de 0: " + eliminado);

        // Retirar todo para vaciar cuenta
        double saldoRestante = cuentaCRC.getSaldo();
        controller.retirar(cuentaCRC.getNumeroCuenta(), saldoRestante);

        // Ahora sí eliminar
        eliminado = controller.eliminarCuenta(cuentaCRC.getNumeroCuenta());
        System.out.println("\n✅ Intento de eliminar cuenta CRC con saldo = 0: " + eliminado);

        System.out.println("\n📋 Estado final de las cuentas:");
        controller.listarCuentas();
    }
}
