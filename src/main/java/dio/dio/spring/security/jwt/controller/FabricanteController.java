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

import dio.dio.spring.security.jwt.model.Fabricante;
import dio.dio.spring.security.jwt.repository.FabricanteRepository;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteController {

	@Autowired
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteController(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Fabricante>> listarFabricantes() {
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> obterFabricantePorId(@PathVariable Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElse(null);

        if (fabricante != null) {
            return ResponseEntity.ok(fabricante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Fabricante> criarFabricante(@RequestBody Fabricante fabricante) {
    	System.out.println("Fabricante: "+fabricante);
        Fabricante novoFabricante = fabricanteRepository.save(fabricante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFabricante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fabricante> atualizarFabricante(@PathVariable Long id, @RequestBody Fabricante fabricanteAtualizado) {
        Fabricante fabricanteExistente = fabricanteRepository.findById(id)
                .orElse(null);

        if (fabricanteExistente != null) {
            fabricanteAtualizado.setId(id);
            Fabricante fabricanteAtualizadoSalvo = fabricanteRepository.save(fabricanteAtualizado);
            return ResponseEntity.ok(fabricanteAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFabricante(@PathVariable Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElse(null);

        if (fabricante != null) {
            fabricanteRepository.delete(fabricante);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
