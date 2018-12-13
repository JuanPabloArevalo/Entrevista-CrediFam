
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */

/**
 * http://54.186.163.136:8087
 * @type 
 */
var configuracionHost = (function () {
    var equipoBackEnd = "http://localhost";
    var version = "/credifamilia/V1/";
    return {
        getEquipoBackEnd(){
            return equipoBackEnd;
        },
        getVersion(){
            return version;
        }
    };
}());
