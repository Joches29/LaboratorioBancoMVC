/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author llean
 */
public class Cuenta {
    protected String numeroCuenta;
    protected Cliente titular;
    protected double saldo;
    protected boolean activa;
    protected String moneda;

    public Cuenta(String numeroCuenta, Cliente titular, String moneda) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.activa = true;
        this.moneda = moneda;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
        public void depositar(double monto) {
        if (!activa) throw new IllegalStateException("La cuenta está inactiva.");
        saldo += monto;
    }

    public void retirar(double monto) {
        if (!activa) throw new IllegalStateException("La cuenta está inactiva.");
        if (monto > saldo) throw new IllegalArgumentException("Saldo insuficiente.");
        saldo -= monto;
    }

    public void transferir(Cuenta destino, double monto) {
        this.retirar(monto);
        destino.depositar(monto);
    }
    
}
