package dio.dio.spring.security.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.dio.spring.security.jwt.model.RepresentanteComercial;
import dio.dio.spring.security.jwt.repository.RepresentanteComercialRepository;

@Service
public class RepresentanteComercialService {

    private final RepresentanteComercialRepository representanteComercialRepository;

    @Autowired
    public RepresentanteComercialService(RepresentanteComercialRepository representanteComercialRepository) {
        this.representanteComercialRepository = representanteComercialRepository;
    }

    public List<RepresentanteComercial> getAllRepresentantesComerciais() {
        return representanteComercialRepository.findAll();
    }

    public RepresentanteComercial getRepresentanteComercialById(Long id) {
        Optional<RepresentanteComercial> optionalRepresentanteComercial = representanteComercialRepository.findById(id);
        return optionalRepresentanteComercial.orElse(null);
    }

    public RepresentanteComercial createRepresentanteComercial(RepresentanteComercial representanteComercial) {
        return representanteComercialRepository.save(representanteComercial);
    }

    public RepresentanteComercial updateRepresentanteComercial(Long id, RepresentanteComercial representanteComercial) {
        Optional<RepresentanteComercial> optionalRepresentanteComercial = representanteComercialRepository.findById(id);
        if (optionalRepresentanteComercial.isPresent()) {
            RepresentanteComercial existingRepresentanteComercial = optionalRepresentanteComercial.get();
            return representanteComercialRepository.save(existingRepresentanteComercial);
        } else {
            return null;
        }
    }

    public void deleteRepresentanteComercial(Long id) {
        representanteComercialRepository.deleteById(id);
    }
}
