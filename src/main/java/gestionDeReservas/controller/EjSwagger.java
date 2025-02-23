package gestionDeReservas.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")

public class EjSwagger {

    @GetMapping("/hola-todos")

    public String hola(){
        return  "hola todos";
    }

    @GetMapping("/hola-cliente")
    public String holaCliente(){
        return  "hola cliente";
    }

    @GetMapping("/hola-fede")
    public String holaFede(){
        return  "hola fede";
    }

    @GetMapping("/funciona")
    public String funciona(){
        return  "funciona automatizacion de cambios";
    }
}
