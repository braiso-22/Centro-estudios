/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.time.LocalDate;

/**
 *
 * @author brais.fernandezvazqu
 */
public class Alumno {

    private String dni;
    private String nombre;
    private String apellidos;
    private String curso;
    private LocalDate fechaNacimiento;

    public Alumno(String dni, String nombre, String apellidos, String curso, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Get the value of fechaNacimiento
     *
     * @return the value of fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Set the value of fechaNacimiento
     *
     * @param fechaNacimiento new value of fechaNacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Get the value of curso
     *
     * @return the value of curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Set the value of curso
     *
     * @param curso new value of curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Get the value of apellidos
     *
     * @return the value of apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Set the value of apellidos
     *
     * @param apellidos new value of apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "dni:" + dni + ", nombre:" + nombre + ", apellidos:" + apellidos + ", curso:" + curso + ", fechaNacimiento:" + fechaNacimiento + "\n";
    }

}
