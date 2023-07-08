package dio.dio.spring.security.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.dio.spring.security.jwt.model.Marca;
import dio.dio.spring.security.jwt.repository.MarcaRepository;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Marca getMarcaById(Long id) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        return optionalMarca.orElse(null);
    }

    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca updateMarca(Long id, Marca marca) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        if (optionalMarca.isPresent()) {
            Marca existingMarca = optionalMarca.get();
            existingMarca.setNome(marca.getNome());
            return marcaRepository.save(existingMarca);
        } else {
            return null;
        }
    }

    public void deleteMarca(Long id) {
        marcaRepository.deleteById(id);
    }
}
