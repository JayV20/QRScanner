package com.example.webservice.models;

import java.sql.Date;
import java.sql.Time;

/**
 * Clase para registrar los datos obtenidos de la aplicación ó de la base de datos.
 * @author Jay Vega
 */
public class Control {
    private String nombre;
    private String app;
    private String apm;
    private String matricula;
    private String grupo;
    private String turno;
    private String estudio;
    private int id_asistencia;
    private Date fecha;//Este dato se obtendrá de la base de datos cuando se realice el registro
    private Time horaE;//Este dato se obtendrá de la base de datos cuando se realice el registro
    private Time horaS;//Este dato se obtendrá de la base de datos cuando se realice el registro

    public Control(String nombre, String app, String apm, String matricula, String grupo, String turno, String estudio, int id_asistencia, Date fecha, Time horaE, Time horaS) {
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.matricula = matricula;
        this.grupo = grupo;
        this.turno = turno;
        this.estudio = estudio;
        this.id_asistencia = id_asistencia;
        this.fecha = fecha;
        this.horaE = horaE;
        this.horaS = horaS;
    }

    /**
     * Agrega la base para el control de usuario
     * @param nombre
     * @param app
     * @param apm
     * @param matricula
     * @param grupo
     * @param turno
     * @param estudio
     * @param id_asistencia
     */
    public Control(String nombre, String app, String apm, String matricula, String grupo, String turno, String estudio, int id_asistencia) {
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.matricula = matricula;
        this.grupo = grupo;
        this.turno = turno;
        this.estudio = estudio;
        this.id_asistencia = id_asistencia;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public int getId_asistencia() {
        return id_asistencia;
    }

    public void setId_asistencia(int id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraE() {
        return horaE;
    }
    public void setHoraE(Time horaE) {
        this.horaE = horaE;
    }

    public Time getHoraS() {
        return horaS;
    }

    public void setHoraS(Time horaS) {
        this.horaS = horaS;
    }



}
