package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.visitor.VisitorRequestDTO;
import gestionDeReservas.model.entity.Visitor;

public interface VisitorService {
    void createVisitor(VisitorRequestDTO visitorRequestDTO);
    Visitor getVisitorByEmail(String email);
}
