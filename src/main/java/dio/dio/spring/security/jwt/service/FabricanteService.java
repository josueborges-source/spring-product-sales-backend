package dio.dio.spring.security.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.dio.spring.security.jwt.model.Fabricante;
import dio.dio.spring.security.jwt.repository.FabricanteRepository;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public List<Fabricante> getAllFabricantes() {
        return fabricanteRepository.findAll();
    }

    public Fabricante getFabricanteById(Long id) {
        Optional<Fabricante> optionalFabricante = fabricanteRepository.findById(id);
        return optionalFabricante.orElse(null);
    }

    public Fabricante createFabricante(Fabricante fabricante) {
        return fabricanteRepository.save(fabricante);
    }

    public Fabricante updateFabricante(Long id, Fabricante fabricante) {
        Optional<Fabricante> optionalFabricante = fabricanteRepository.findById(id);
        if (optionalFabricante.isPresent()) {
            Fabricante existingFabricante = optionalFabricante.get();
            existingFabricante.setNome(fabricante.getNome());
            existingFabricante.setEndereco(fabricante.getEndereco());
            return fabricanteRepository.save(existingFabricante);
        } else {
            return null;
        }
    }

    public void deleteFabricante(Long id) {
        fabricanteRepository.deleteById(id);
    }
}
