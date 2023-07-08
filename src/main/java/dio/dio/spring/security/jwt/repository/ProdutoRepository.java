package dio.dio.spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.dio.spring.security.jwt.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
