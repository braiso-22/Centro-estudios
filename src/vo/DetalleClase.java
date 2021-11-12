/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author brais.fernandezvazqu
 */
public class DetalleClase {

    private String Alumno;

    private String Profesor;

    private String Asignatura;

    public DetalleClase(String Alumno, String Profesor, String Asignatura) {
        this.Alumno = Alumno;
        this.Profesor = Profesor;
        this.Asignatura = Asignatura;
    }

    /**
     * Get the value of Asignatura
     *
     * @return the value of Asignatura
     */
    public String getAsignatura() {
        return Asignatura;
    }

    /**
     * Set the value of Asignatura
     *
     * @param Asignatura new value of Asignatura
     */
    public void setAsignatura(String Asignatura) {
        this.Asignatura = Asignatura;
    }

    /**
     * Get the value of Profesor
     *
     * @return the value of Profesor
     */
    public String getProfesor() {
        return Profesor;
    }

    /**
     * Set the value of Profesor
     *
     * @param Profesor new value of Profesor
     */
    public void setProfesor(String Profesor) {
        this.Profesor = Profesor;
    }

    /**
     * Get the value of Alumno
     *
     * @return the value of Alumno
     */
    public String getAlumno() {
        return Alumno;
    }

    /**
     * Set the value of Alumno
     *
     * @param Alumno new value of Alumno
     */
    public void setAlumno(String Alumno) {
        this.Alumno = Alumno;
    }

}
