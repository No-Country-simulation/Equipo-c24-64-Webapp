package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.VisitorEmailException;
import gestionDeReservas.model.dto.visitor.VisitorRequestDTO;
import gestionDeReservas.model.entity.Visitor;
import gestionDeReservas.repository.IVisitorRepository;
import gestionDeReservas.services.Interface.VisitorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class VisitorImplService implements VisitorService {
    IVisitorRepository visitorRepository;

    @Override
    public void createVisitor(VisitorRequestDTO visitorRequestDTO) {
        if (visitorRepository.existsByEmail(visitorRequestDTO.email())) {
            throw new VisitorEmailException("Email Already Registered");
        }
        Visitor visitor = Visitor.builder()
                .name(visitorRequestDTO.name())
                .email(visitorRequestDTO.email())
                .dni(visitorRequestDTO.dni())
                .phoneNumber(visitorRequestDTO.phoneNumber())
                .lastname(visitorRequestDTO.lastname())
                .build();

        visitorRepository.save(visitor);
    }
}