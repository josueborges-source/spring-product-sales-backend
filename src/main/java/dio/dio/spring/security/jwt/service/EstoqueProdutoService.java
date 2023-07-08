package dio.dio.spring.security.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.dio.spring.security.jwt.model.EstoqueProduto;
import dio.dio.spring.security.jwt.repository.EstoqueProdutoRepository;

@Service
public class EstoqueProdutoService {

    private final EstoqueProdutoRepository estoqueProdutoRepository;

    @Autowired
    public EstoqueProdutoService(EstoqueProdutoRepository estoqueProdutoRepository) {
        this.estoqueProdutoRepository = estoqueProdutoRepository;
    }

    public List<EstoqueProduto> getAllEstoqueProdutos() {
        return estoqueProdutoRepository.findAll();
    }

    public EstoqueProduto getEstoqueProdutoById(Long id) {
        Optional<EstoqueProduto> optionalEstoqueProduto = estoqueProdutoRepository.findById(id);
        return optionalEstoqueProduto.orElse(null);
    }

    public EstoqueProduto createEstoqueProduto(EstoqueProduto estoqueProduto) {
        return estoqueProdutoRepository.save(estoqueProduto);
    }

    public EstoqueProduto updateEstoqueProduto(Long id, EstoqueProduto estoqueProduto) {
        Optional<EstoqueProduto> optionalEstoqueProduto = estoqueProdutoRepository.findById(id);
        if (optionalEstoqueProduto.isPresent()) {
            EstoqueProduto existingEstoqueProduto = optionalEstoqueProduto.get();
            existingEstoqueProduto.setProduto(estoqueProduto.getProduto());
            existingEstoqueProduto.setQuantidade(estoqueProduto.getQuantidade());
            existingEstoqueProduto.setDataChegada(estoqueProduto.getDataChegada());
            existingEstoqueProduto.setDataValidade(estoqueProduto.getDataValidade());
            existingEstoqueProduto.setPrecoCompra(estoqueProduto.getPrecoCompra());
            existingEstoqueProduto.setRepresentanteResponsavel(estoqueProduto.getRepresentanteResponsavel());
            return estoqueProdutoRepository.save(existingEstoqueProduto);
        } else {
            return null;
        }
    }

    public void deleteEstoqueProduto(Long id) {
        estoqueProdutoRepository.deleteById(id);
    }
}
