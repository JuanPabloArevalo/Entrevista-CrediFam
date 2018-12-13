/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var apicliente = (function(){
    var equipoBackEnd = configuracionHost.getEquipoBackEnd();
    var version = configuracionHost.getVersion();
    return{
        adicionarCliente(documento,nombres,apellidos, fechaNacimiento, ciudad, correo, ocupacion, telefono){
            return $.ajax({
                url:  equipoBackEnd+version+"clientes",
                type: "POST",                
                data: '{"documento":'+documento+' ,"nombres":"'+nombres+'", "apellidos":"'+apellidos+'", "fechaNacimiento":"'+fechaNacimiento+'", "ciudad":"'+ciudad+'", "correo":"'+correo+'", "ocupacion":"'+ocupacion+'", "telefono":'+telefono+'}',
                contentType: "application/json"
            });
        },
        getClientes(dato){
            return $.ajax({
                url:  equipoBackEnd+version+"clientes/"+dato,
                type: "GET"
            });
        },
        eliminarCliente(documento){
            return $.ajax({
                url:  equipoBackEnd+version+"clientes/"+documento,
                type: "DELETE"
            });
        },
        getCliente(documento){
            return $.ajax({
                url:  equipoBackEnd+version+"clientes/unico/"+documento,
                type: "GET"
            });
        },
        actualizarCliente(documento,nombres,apellidos, fechaNacimiento, ciudad, correo, ocupacion, telefono){
            return $.ajax({
                url:  equipoBackEnd+version+"clientes",
                type: "PUT",                
                data: '{"documento":'+documento+' ,"nombres":"'+nombres+'", "apellidos":"'+apellidos+'", "fechaNacimiento":"'+fechaNacimiento+'", "ciudad":"'+ciudad+'", "correo":"'+correo+'", "ocupacion":"'+ocupacion+'", "telefono":'+telefono+'}',
                contentType: "application/json"
            });
        }
    };
    
}());
