package dio.dio.spring.security.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.dio.spring.security.jwt.model.RepresentanteComercial;
import dio.dio.spring.security.jwt.repository.RepresentanteComercialRepository;

@RestController
@RequestMapping("/representantes-comerciais")
public class RepresentanteComercialController {

	@Autowired
    private final RepresentanteComercialRepository representanteComercialRepository;

    @Autowired
    public RepresentanteComercialController(RepresentanteComercialRepository representanteComercialRepository) {
        this.representanteComercialRepository = representanteComercialRepository;
    }

    @GetMapping
    public ResponseEntity<List<RepresentanteComercial>> listarRepresentantesComerciais() {
        List<RepresentanteComercial> representantesComerciais = representanteComercialRepository.findAll();
        return ResponseEntity.ok(representantesComerciais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepresentanteComercial> obterRepresentanteComercialPorId(@PathVariable Long id) {
        RepresentanteComercial representanteComercial = representanteComercialRepository.findById(id)
                .orElse(null);

        if (representanteComercial != null) {
            return ResponseEntity.ok(representanteComercial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RepresentanteComercial> criarRepresentanteComercial(@RequestBody RepresentanteComercial representanteComercial) {
        RepresentanteComercial novoRepresentanteComercial = representanteComercialRepository.save(representanteComercial);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRepresentanteComercial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepresentanteComercial> atualizarRepresentanteComercial(@PathVariable Long id, @RequestBody RepresentanteComercial representanteComercialAtualizado) {
        RepresentanteComercial representanteComercialExistente = representanteComercialRepository.findById(id)
                .orElse(null);

        if (representanteComercialExistente != null) {
            representanteComercialAtualizado.setId(id);
            RepresentanteComercial representanteComercialAtualizadoSalvo = representanteComercialRepository.save(representanteComercialAtualizado);
            return ResponseEntity.ok(representanteComercialAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRepresentanteComercial(@PathVariable Long id) {
        RepresentanteComercial representanteComercial = representanteComercialRepository.findById(id)
                .orElse(null);

        if (representanteComercial != null) {
            representanteComercialRepository.delete(representanteComercial);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
