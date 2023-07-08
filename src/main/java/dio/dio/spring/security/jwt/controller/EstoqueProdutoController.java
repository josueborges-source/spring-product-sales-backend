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

import dio.dio.spring.security.jwt.model.EstoqueProduto;
import dio.dio.spring.security.jwt.repository.EstoqueProdutoRepository;

@RestController
@RequestMapping("/estoque-produtos")
public class EstoqueProdutoController {

	@Autowired
    private final EstoqueProdutoRepository estoqueProdutoRepository;

    @Autowired
    public EstoqueProdutoController(EstoqueProdutoRepository estoqueProdutoRepository) {
        this.estoqueProdutoRepository = estoqueProdutoRepository;
    }

    @GetMapping
    public ResponseEntity<List<EstoqueProduto>> listarEstoqueProdutos() {
        List<EstoqueProduto> estoqueProdutos = estoqueProdutoRepository.findAll();
        return ResponseEntity.ok(estoqueProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueProduto> obterEstoqueProdutoPorId(@PathVariable Long id) {
        EstoqueProduto estoqueProduto = estoqueProdutoRepository.findById(id)
                .orElse(null);

        if (estoqueProduto != null) {
            return ResponseEntity.ok(estoqueProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstoqueProduto> criarEstoqueProduto(@RequestBody EstoqueProduto estoqueProduto) {
        EstoqueProduto novoEstoqueProduto = estoqueProdutoRepository.save(estoqueProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstoqueProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueProduto> atualizarEstoqueProduto(@PathVariable Long id, @RequestBody EstoqueProduto estoqueProdutoAtualizado) {
        EstoqueProduto estoqueProdutoExistente = estoqueProdutoRepository.findById(id)
                .orElse(null);

        if (estoqueProdutoExistente != null) {
            estoqueProdutoAtualizado.setId(id);
            EstoqueProduto estoqueProdutoAtualizadoSalvo = estoqueProdutoRepository.save(estoqueProdutoAtualizado);
            return ResponseEntity.ok(estoqueProdutoAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEstoqueProduto(@PathVariable Long id) {
        EstoqueProduto estoqueProduto = estoqueProdutoRepository.findById(id)
                .orElse(null);

        if (estoqueProduto != null) {
            estoqueProdutoRepository.delete(estoqueProduto);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
