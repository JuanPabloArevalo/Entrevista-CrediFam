/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credifamiliaCF.prueba.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author JuanArevaloMerchan
 */
public class Cliente {
    private int documento;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String ciudad;
    private String correo;
    private String ocupacion;
    private long telefono;
    private boolean viabilidad;
    public static final int EDAD_MINIMA_PRODUCTIVA = 18;
    public static final int EDAD_MAXIMA_PRODUCTIVA = 65;
    public static final String PATRON_FECHA_ddMMyyyy = "dd/MM/yyyy";
    
    /**
     * @return the documento
     */
    public int getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(int documento) {
        this.documento = documento;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the ocupacion
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * @param ocupacion the ocupacion to set
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * @return the telefono
     */
    public long getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the viabilidad
     */
    public boolean isViabilidad() {
        return viabilidad;
    }

    /**
     * @param viabilidad the viabilidad to set
     */
    public void setViabilidad(boolean viabilidad) {
        this.viabilidad = viabilidad;
    }
    
    
    public boolean estaEnEdadProductiva(){
        try{
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(PATRON_FECHA_ddMMyyyy);
            LocalDate fechaNacimiento = this.fechaNacimiento.toLocalDate();
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNacimiento, ahora);
            int edad = periodo.getYears();
            System.err.println("EDAD: "+edad);
            if(edad>=EDAD_MINIMA_PRODUCTIVA && edad<=EDAD_MAXIMA_PRODUCTIVA){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
	
    }
}
