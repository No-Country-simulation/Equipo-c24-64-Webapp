package gestionDeReservas.controller;

import gestionDeReservas.model.dto.visitor.VisitorRequestDTO;
import gestionDeReservas.services.Interface.VisitorService;
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
    public ResponseEntity<?> createVisitor(@RequestBody VisitorRequestDTO visitorRequestDTO){
        visitorService.createVisitor(visitorRequestDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}
