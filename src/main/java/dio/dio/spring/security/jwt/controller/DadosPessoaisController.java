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

import dio.dio.spring.security.jwt.model.DadosPessoais;
import dio.dio.spring.security.jwt.repository.DadosPessoaisRepository;

@RestController
@RequestMapping("/dados-pessoais")
public class DadosPessoaisController {

	@Autowired
    private final DadosPessoaisRepository dadosPessoaisRepository;

    @Autowired
    public DadosPessoaisController(DadosPessoaisRepository dadosPessoaisRepository) {
        this.dadosPessoaisRepository = dadosPessoaisRepository;
    }

    @GetMapping
    public ResponseEntity<List<DadosPessoais>> listarDadosPessoais() {
        List<DadosPessoais> dadosPessoaisList = dadosPessoaisRepository.findAll();
        return ResponseEntity.ok(dadosPessoaisList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosPessoais> obterDadosPessoaisPorId(@PathVariable Long id) {
        DadosPessoais dadosPessoais = dadosPessoaisRepository.findById(id)
                .orElse(null);

        if (dadosPessoais != null) {
            return ResponseEntity.ok(dadosPessoais);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DadosPessoais> criarDadosPessoais(@RequestBody DadosPessoais dadosPessoais) {
        DadosPessoais novosDadosPessoais = dadosPessoaisRepository.save(dadosPessoais);
        return ResponseEntity.status(HttpStatus.CREATED).body(novosDadosPessoais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosPessoais> atualizarDadosPessoais(@PathVariable Long id, @RequestBody DadosPessoais dadosPessoaisAtualizados) {
        DadosPessoais dadosPessoaisExistente = dadosPessoaisRepository.findById(id)
                .orElse(null);

        if (dadosPessoaisExistente != null) {
            dadosPessoaisAtualizados.setId(id);
            dadosPessoaisAtualizados.setId(id);
            DadosPessoais dadosPessoaisAtualizadosSalvo = dadosPessoaisRepository.save(dadosPessoaisAtualizados);
            return ResponseEntity.ok(dadosPessoaisAtualizadosSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDadosPessoais(@PathVariable Long id) {
        DadosPessoais dadosPessoais = dadosPessoaisRepository.findById(id)
                .orElse(null);

        if (dadosPessoais != null) {
            dadosPessoaisRepository.delete(dadosPessoais);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
