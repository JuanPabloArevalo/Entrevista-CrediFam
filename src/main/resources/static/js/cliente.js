
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global apiclientResultados, diagramaGaleria, diagramaCalculemos, diagramaPercepcion, diagramaAtencion, diagramaFormas, appPercepcion, diagramaMusicoterapia */
function inicializarElementosBusqueda() {
    $(".filasB").remove("tr");
}
function adicionarFilaBusqueda(item) {
    console.info(item);
    var markup = "<tr class=\"filasB\"><td>" + item.documento + "</td><td>" + item.nombres + "</td><td>" + item.apellidos + "</td><td>" + item.fechaNacimiento + "</td><td>" + item.ciudad + "</td><td>" + item.correo + "</td><td>" + item.ocupacion + "</td><td>" + item.telefono + "</td><td>" + item.viabilidad + "</td><td><button type=\"button\" class=\"btn btn-success\" onclick=\"cliente.irActualizarCliente("+item.documento+")\">Actualizar</button> <button type=\"button\" class=\"btn btn-danger\" onclick=\"cliente.eliminarCliente("+item.documento+")\">Eliminar</button></td></tr>";
    $("#idTablaBusqueda").append(markup);
}

var cliente = (function () {
    return{
        adicionarCliente() {
            var ocupacion = $("select#ocupacion").val();
            var documento = $("#documento").val();
            var nombres = $("#nombres").val();
            var apellidos = $("#apellidos").val();
            var fecha = $("#fecha").val();
            var ciudad = $("#ciudad").val();
            var email = $("#email").val();
            var telefono = $("#telefono").val();
            if (documento === '') {
                alert("El documento no puede ir vacio")
            } else if (telefono === '') {
                alert("El telefono no puede ir vacio")
            } else if (fecha === '') {
                alert("La fecha no puede ir vacia")
            } else if (ocupacion === '...') {
                alert("Por favor seleccione una ocupación")
            } else if (email === '') {
                alert("El email no puede ir vacio")
            } else {
                var promesaConsulta = apicliente.adicionarCliente(documento, nombres, apellidos, fecha, ciudad, email, ocupacion, telefono);
                promesaConsulta.then(
                        function (dato) {
                            alert(dato);
                            cliente.inicializarFormulario();
                        },
                        function (dato) {
                            alert(dato.responseText);
                        }
                );
            }
        },
        inicializarFormulario() {
            $("#documento").val("");
            $("#nombres").val("");
            $("#apellidos").val("");
            $("#fecha").val("");
            $("#ciudad").val("");
            $("#email").val("");
            $("#telefono").val("");
        },
        consultarClientes() {
            var buscar = $("#idBusqueda").val();
            if (buscar === "") {
                alert("Para realizar la búsqueda debe ingresar un dato");
            } else {
                var promesaConsulta = apicliente.getClientes(buscar);
                promesaConsulta.then(
                        function (datos) {
                            inicializarElementosBusqueda();
                            datos.map(adicionarFilaBusqueda);
                        },
                        function (dato) {
                            alert(dato.responseText);
                        }
                );
            }
        },
        eliminarCliente(documento) {
            var promesaConsulta = apicliente.eliminarCliente(documento);
            promesaConsulta.then(
                    function (dato) {
                        inicializarElementosBusqueda();
                        alert(dato);
                    },
                    function (dato) {
                        alert(dato.responseText);
                    }
            );

        },
        irActualizarCliente(documento) {
            sessionStorage.setItem("documento", documento);
            window.location.href = "actualizar.html";
        },
        cargarDatosCliente(){
            var documento = sessionStorage.getItem("documento");
            if (documento===''){
                alert("Ha ocurrido un error con el documento");
                window.location.href = "index.html";
            }
            var promesaConsulta = apicliente.getCliente(documento);
            promesaConsulta.then(
                    function (datos) {
                        $("#documento").val(datos.documento);
                        $("#nombres").val(datos.nombres);
                        $("#apellidos").val(datos.apellidos);
                        $("#fecha").val(datos.fechaNacimiento);
                        $("#ciudad").val(datos.ciudad);
                        $("#email").val(datos.correo);
                        $("#telefono").val(datos.telefono);
                        $("#ocupacion").val(datos.ocupacion);
                    },
                    function (dato) {
                        alert(dato.responseText);
                    }
            );
        },
        actualizarCliente() {
            var ocupacion = $("select#ocupacion").val();
            var documento = sessionStorage.getItem("documento");
            var nombres = $("#nombres").val();
            var apellidos = $("#apellidos").val();
            var fecha = $("#fecha").val();
            var ciudad = $("#ciudad").val();
            var email = $("#email").val();
            var telefono = $("#telefono").val();
            if (documento === '') {
                alert("Ha ocurrido un error con el documento.")
            } else if (telefono === '') {
                alert("El telefono no puede ir vacio")
            } else if (fecha === '') {
                alert("La fecha no puede ir vacia")
            } else if (ocupacion === '...') {
                alert("Por favor seleccione una ocupación")
            } else if (email === '') {
                alert("El email no puede ir vacio")
            } else {
                var promesaConsulta = apicliente.actualizarCliente(documento, nombres, apellidos, fecha, ciudad, email, ocupacion, telefono);
                promesaConsulta.then(
                        function (dato) {
                            alert(dato);
                            window.location.href = "index.html";
                        },
                        function (dato) {
                            alert(dato.responseText);
                        }
                );
            }
        }
    };
}());



