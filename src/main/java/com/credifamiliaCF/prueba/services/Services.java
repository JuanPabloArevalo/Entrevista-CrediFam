/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credifamiliaCF.prueba.services;

import com.credifamiliaCF.prueba.model.Cliente;
import com.credifamiliaCF.prueba.persistence.ClienteRepository;
import com.credifamiliaCF.prueba.persistence.PersistenceException;
import com.credifamiliaCF.prueba.persistence.PersistenceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Pablo Arevalo
 */
@Service
public class Services {
    
    
    @Autowired
    private ClienteRepository clienteRep;
    
    /**
     * Metodo encargado de adicionar nuevos cliente
     * @param cliente 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     * @throws com.credifamiliaCF.prueba.services.ServicesException 
     */
    public void adicionarCliente(Cliente cliente) throws PersistenceNotFoundException, PersistenceException, ServicesException{
        if(cliente.getApellidos().isEmpty()){
            throw new ServicesException("Error! El apellido no puede ir vacio");
        }
        else if(cliente.getNombres().isEmpty()){
            throw new ServicesException("Error! El nombre no puede ir vacio");
        }
        else if(cliente.getDocumento()<=0){
            throw new ServicesException("Error! Documento inválido");
        }
        else if(cliente.getOcupacion().equalsIgnoreCase("...")){
            throw new ServicesException("Por favor seleccione una ocupación");
        }
        else if(!cliente.getCorreo().contains("@")){
            throw new ServicesException("Por favor ingrese un correo válido");
        }
        if(!clienteRep.existe(cliente)){
            clienteRep.adicionar(cliente);
        }
    }
    /**
     * Metodo encargado de consultar clientes
     * @param dato 
     * @return  
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     * @throws com.credifamiliaCF.prueba.services.ServicesException 
     */
    public List<Cliente> consultarClientes(String dato) throws PersistenceNotFoundException, PersistenceException, ServicesException{
        if(dato.isEmpty()){
            throw new ServicesException("Ingrese algún dato para realizar la búsqueda");
        }
        return clienteRep.consultarClientes(dato);
    }
    
    /**
     * Metodo encargado de eliminar un cliente
     * @param documento 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     * @throws com.credifamiliaCF.prueba.services.ServicesException 
     */
    public void eliminarCliente(int documento) throws PersistenceNotFoundException, PersistenceException, ServicesException{
        clienteRep.eliminar(documento);
    }
    
    /**
     * Metodo encargado de consultar un cliente
     * @param documento 
     * @return  
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     * @throws com.credifamiliaCF.prueba.services.ServicesException 
     */
    public Cliente consultarCliente(int documento) throws PersistenceNotFoundException, PersistenceException, ServicesException{
        return clienteRep.consultarCliente(documento);
    }
    
    /**
     * Metodo encargado de actualizar cliente
     * @param cliente 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     * @throws com.credifamiliaCF.prueba.services.ServicesException 
     */
    public void actualizarCliente(Cliente cliente) throws PersistenceNotFoundException, PersistenceException, ServicesException{
        clienteRep.actualizar(cliente);
    }
}
