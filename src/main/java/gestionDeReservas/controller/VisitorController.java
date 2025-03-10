package gestionDeReservas.controller;

import gestionDeReservas.model.dto.visitor.VisitorRequestDTO;
import gestionDeReservas.services.Interface.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visitor")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class VisitorController {
    VisitorService visitorService;

    @PostMapping
    @Operation(summary = "guardar datos del visitante",description = "se guardan datos del visitante" +
            "para luego obtener su historial completo de reservas si es que se registra en la pagina")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully visitor created"),
    })
    public ResponseEntity<?> createVisitor(@RequestBody VisitorRequestDTO visitorRequestDTO){
        visitorService.createVisitor(visitorRequestDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}
