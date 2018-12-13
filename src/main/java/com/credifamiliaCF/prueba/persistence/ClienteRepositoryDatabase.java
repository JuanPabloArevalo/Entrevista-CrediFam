/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credifamiliaCF.prueba.persistence;

import com.credifamiliaCF.prueba.controllers.Controller;
import com.credifamiliaCF.prueba.model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Pablo Arévalo
 */
@Service
public class ClienteRepositoryDatabase implements ClienteRepository{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String NOMBRE_TABLA = "Cliente";

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver}")
    private String driver;
       
    /**
     * Metodo encargado de cerrar la conexión
     */
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    

    @Override
    public void adicionar(Cliente cliente) throws PersistenceNotFoundException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("INSERT INTO  "+NOMBRE_TABLA+" (NumeroDocumento, Nomnbres, Apellidos, FechaNacimiento, Ciudad, Correo, Ocupacion, Telefono) values (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, cliente.getDocumento());
            preparedStatement.setString(2, cliente.getNombres());
            preparedStatement.setString(3, cliente.getApellidos());
            preparedStatement.setDate(4, cliente.getFechaNacimiento());
            preparedStatement.setString(5, cliente.getCiudad());
            preparedStatement.setString(6, cliente.getCorreo());
            preparedStatement.setString(7, cliente.getOcupacion());
            preparedStatement.setLong(8, cliente.getTelefono());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenceNotFoundException("Ha ocurrido un error con la base de datos. Por favor contacte al administrador del sistema");
        } finally {
            close();
        } 
    }

    @Override
    public boolean existe(Cliente cliente) throws PersistenceNotFoundException, PersistenceException {
        boolean existeFamiliar = false;
       
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url);
            
            preparedStatement = connect.prepareStatement("SELECT Nomnbres FROM "+NOMBRE_TABLA +" WHERE NumeroDocumento='"+cliente.getDocumento()+"'");
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                existeFamiliar = true;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenceNotFoundException("Ha ocurrido un error con la base de datos. Por favor contacte al administrador del sistema");
        } finally {
            close();
        }
    
        if(existeFamiliar){
            throw new PersistenceException("El cliente ya esta registrado!");
        }
        else{
            return false;
        }
    }

    @Override
    public List<Cliente> consultarClientes(String dato) throws PersistenceNotFoundException, PersistenceException {
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url);
            preparedStatement = connect.prepareStatement("SELECT * FROM "+NOMBRE_TABLA +" WHERE NumeroDocumento LIKE '%"+dato+"%' OR Nomnbres LIKE '%"+dato+"%' OR Apellidos LIKE '%"+dato+"%'  ORDER BY NumeroDocumento, Apellidos");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setDocumento(resultSet.getInt("NumeroDocumento"));
                cliente.setNombres(resultSet.getString("Nomnbres"));
                cliente.setApellidos(resultSet.getString("Apellidos"));
                cliente.setCorreo(resultSet.getString("correo"));
                cliente.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                cliente.setCiudad(resultSet.getString("Ciudad"));
                cliente.setCorreo(resultSet.getString("Correo"));
                cliente.setOcupacion(resultSet.getString("Ocupacion"));
                cliente.setTelefono(resultSet.getLong("Telefono"));
                cliente.setViabilidad(resultSet.getBoolean("viable"));
                clientes.add(cliente);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceNotFoundException(e.getMessage());
        } finally {
            close();
        }
    
        if(clientes.isEmpty()){
            throw new PersistenceException("No existen clientes con ese dato! ("+dato+")");
        }
        else{
            return clientes;
        }
    }

    @Override
    public void eliminar(int documento) throws PersistenceNotFoundException, PersistenceException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("DELETE FROM "+NOMBRE_TABLA+" WHERE NumeroDocumento = ?");
            preparedStatement.setInt(1, documento);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenceNotFoundException("Ha ocurrido un error con la base de datos. Por favor contacte al administrador del sistema");
        } finally {
            close();
        }
    }

    @Override
    public Cliente consultarCliente(int documento) throws PersistenceNotFoundException, PersistenceException {
        Cliente cliente = null;
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url);
            preparedStatement = connect.prepareStatement("SELECT * FROM "+NOMBRE_TABLA +" WHERE NumeroDocumento = '"+documento+"'");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                cliente = new Cliente();
                cliente.setDocumento(resultSet.getInt("NumeroDocumento"));
                cliente.setNombres(resultSet.getString("Nomnbres"));
                cliente.setApellidos(resultSet.getString("Apellidos"));
                cliente.setCorreo(resultSet.getString("correo"));
                cliente.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                cliente.setCiudad(resultSet.getString("Ciudad"));
                cliente.setCorreo(resultSet.getString("Correo"));
                cliente.setOcupacion(resultSet.getString("Ocupacion"));
                cliente.setTelefono(resultSet.getLong("Telefono"));
                cliente.setViabilidad(resultSet.getBoolean("viable"));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceNotFoundException(e.getMessage());
        } finally {
            close();
        }
    
        if(cliente==null){
            throw new PersistenceException("No existen cliente con el documento "+documento);
        }
        else{
            return cliente;
        }
    }

    @Override
    public void actualizar(Cliente cliente) throws PersistenceNotFoundException, PersistenceException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("UPDATE "+NOMBRE_TABLA+" SET Nomnbres = ?, Apellidos = ?, Ciudad = ?, Correo = ?, Ocupacion = ?, Telefono = ? WHERE NumeroDocumento = ?");
            
            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidos());
            preparedStatement.setString(3, cliente.getCiudad());
            preparedStatement.setString(4, cliente.getCorreo());
            preparedStatement.setString(5, cliente.getOcupacion());
            preparedStatement.setLong(6, cliente.getTelefono());
            preparedStatement.setInt(7, cliente.getDocumento());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenceNotFoundException("Ha ocurrido un error con la base de datos. Por favor contacte al administrador del sistema");
        } finally {
            close();
        } 
    }

}
