package gestionDeReservas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")

@Tag(name = "Ejemplo", description = "Controlador de ejemplo")
public class EjSwagger {

    @GetMapping("/hola")
    @Operation(
            summary = "Obtener saludo",
            description = "Devuelve un mensaje de saludo"
    )

    @ApiResponse(responseCode = "200", description = "Saludo exitoso")
    public String hola(){
        return  "hola";
    }

    @GetMapping("/adios")
    @Operation(
            summary = "Obtener despedida",
            description = "Devuelve un mensaje de despedida"
    )

    @ApiResponse(responseCode = "200", description = "Despedida exitosa")
    public String adios(){
        return  "adios";
    }

}
