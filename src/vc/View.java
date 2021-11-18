/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vc;

import java.util.Scanner;

/**
 *
 * @author brais.fernandezvazqu
 */
public class View {

    private Scanner teclado = new Scanner(System.in);

    public boolean showMessage(String message) {
        System.out.println(message);
        return true;
    }

    public String showMessageString(String message) {
        System.out.println(message);
        return teclado.nextLine();
    }

    public int menuTablas() {

        System.out.println("Selecciona una opcion:");
        System.out.println("1.- Alumnos");

        System.out.println("0.- Salir");

        int opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    public int menuAlumno() {
        System.out.println("Selecciona una opcion:");
        System.out.println("1.- Ver todos los alumnos");
        System.out.println("2.- Buscar alumno por DNI");
        System.out.println("3.- Ver alumnos buscando por nombre");
        System.out.println("4.- Ver alumnos buscando por apellido");
        System.out.println("5.- Ver alumnos buscando por curso");
        System.out.println("6.- Ver alumnos por año de nacimiento");
        System.out.println("0.- Volver");
        int opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

}
