/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credifamiliaCF.prueba.controllers;

import com.credifamiliaCF.prueba.model.Cliente;
import com.credifamiliaCF.prueba.persistence.PersistenceException;
import com.credifamiliaCF.prueba.persistence.PersistenceNotFoundException;
import com.credifamiliaCF.prueba.services.Services;
import com.credifamiliaCF.prueba.services.ServicesException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JuanArevaloMerchan
 */
@RestController
@RequestMapping(value = "/credifamilia/V1/")
public class Controller {
    
    @Autowired
    Services servicio = null;

    @RequestMapping(path = "clientes/{busqueda}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarClientes(@PathVariable("busqueda") String busqueda) throws ServicesException {
        try {
            return new ResponseEntity<>(servicio.consultarClientes(busqueda), HttpStatus.ACCEPTED);
        } catch (PersistenceNotFoundException | PersistenceException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "clientes/unico/{documento}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarCliente(@PathVariable("documento") int documento) throws ServicesException {
        try {
            return new ResponseEntity<>(servicio.consultarCliente(documento), HttpStatus.ACCEPTED);
        } catch (PersistenceNotFoundException | PersistenceException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "clientes", method = RequestMethod.POST)
    public ResponseEntity<?> adicionarCliente(@RequestBody Cliente cliente) {
        try {
            servicio.adicionarCliente(cliente);
            return new ResponseEntity<>("Se ha creado con éxito el cliente!",HttpStatus.CREATED);
        } catch (PersistenceNotFoundException | PersistenceException | ServicesException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } 
    }
    
    @RequestMapping(path = "clientes/{documento}", method = RequestMethod.DELETE)
    public ResponseEntity<?> eliminarCliente(@PathVariable("documento") int documento) throws ServicesException {
        try {
            servicio.eliminarCliente(documento);
            return new ResponseEntity<>("Se ha eliminado con éxito el cliente", HttpStatus.ACCEPTED);
        } catch (PersistenceNotFoundException | PersistenceException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "clientes", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente) {
        try {
            servicio.actualizarCliente(cliente);
            return new ResponseEntity<>("Se ha actualizado con éxito el cliente!",HttpStatus.CREATED);
        } catch (PersistenceNotFoundException | PersistenceException | ServicesException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } 
    }
    
    
    
    
    
}
