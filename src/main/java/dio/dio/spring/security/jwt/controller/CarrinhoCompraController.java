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

import dio.dio.spring.security.jwt.model.CarrinhoCompra;
import dio.dio.spring.security.jwt.repository.CarrinhoCompraRepository;

@RestController
@RequestMapping("/carrinho-compras")
public class CarrinhoCompraController {

	@Autowired
    private final CarrinhoCompraRepository carrinhoCompraRepository;

    @Autowired
    public CarrinhoCompraController(CarrinhoCompraRepository carrinhoCompraRepository) {
        this.carrinhoCompraRepository = carrinhoCompraRepository;
    }

    @GetMapping
    public ResponseEntity<List<CarrinhoCompra>> listarCarrinhoCompras() {
        List<CarrinhoCompra> carrinhoCompras = carrinhoCompraRepository.findAll();
        return ResponseEntity.ok(carrinhoCompras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoCompra> obterCarrinhoCompraPorId(@PathVariable Long id) {
        CarrinhoCompra carrinhoCompra = carrinhoCompraRepository.findById(id)
                .orElse(null);

        if (carrinhoCompra != null) {
            return ResponseEntity.ok(carrinhoCompra);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CarrinhoCompra> criarCarrinhoCompra(@RequestBody CarrinhoCompra carrinhoCompra) {
        CarrinhoCompra novoCarrinhoCompra = carrinhoCompraRepository.save(carrinhoCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarrinhoCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoCompra> atualizarCarrinhoCompra(@PathVariable Long id, @RequestBody CarrinhoCompra carrinhoCompraAtualizado) {
        CarrinhoCompra carrinhoCompraExistente = carrinhoCompraRepository.findById(id)
                .orElse(null);

        if (carrinhoCompraExistente != null) {
            carrinhoCompraAtualizado.setId(id);
            CarrinhoCompra carrinhoCompraAtualizadoSalvo = carrinhoCompraRepository.save(carrinhoCompraAtualizado);
            return ResponseEntity.ok(carrinhoCompraAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCarrinhoCompra(@PathVariable Long id) {
        CarrinhoCompra carrinhoCompra = carrinhoCompraRepository.findById(id)
                .orElse(null);

        if (carrinhoCompra != null) {
            carrinhoCompraRepository.delete(carrinhoCompra);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
