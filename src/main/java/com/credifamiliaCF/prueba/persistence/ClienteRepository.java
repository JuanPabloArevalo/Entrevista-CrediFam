/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credifamiliaCF.prueba.persistence;

import com.credifamiliaCF.prueba.model.Cliente;
import java.util.List;
import java.util.Set;

/**
 *
 * @author JuanArevaloMerchan
 */
public interface ClienteRepository {
    /**
     * Metodo encargado de adicionar un nuevo cliente
     * @param cliente
     * @throws PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     */
    public void adicionar(Cliente cliente) throws PersistenceNotFoundException, PersistenceException;
    
    /**
     * Metodo encargado de validar si existe un cliente
     * @param cliente
     * @return 
     * @throws PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     */
    public boolean existe(Cliente cliente) throws PersistenceNotFoundException, PersistenceException;
    
    /**
     * Metodo encargado de consultar todos los clientes que existan con ese dato
     * @param dato
     * @return
     * @throws PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     */
    public List<Cliente> consultarClientes(String dato) throws PersistenceNotFoundException, PersistenceException;
    
    /**
     * Metodo encargado de eliminar un cliente
     * @param documento
     * @throws PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     */
    public void eliminar(int documento) throws PersistenceNotFoundException, PersistenceException;
    
    /**
     * Metodo encargado de consultar el cliente que existan con ese documento
     * @param documento
     * @return
     * @throws PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     */
    public Cliente consultarCliente(int documento) throws PersistenceNotFoundException, PersistenceException;
    
    /**
     * Metodo encargado de actualizar un cliente
     * @param cliente
     * @throws PersistenceNotFoundException 
     * @throws com.credifamiliaCF.prueba.persistence.PersistenceException 
     */
    public void actualizar(Cliente cliente) throws PersistenceNotFoundException, PersistenceException;
    
}
