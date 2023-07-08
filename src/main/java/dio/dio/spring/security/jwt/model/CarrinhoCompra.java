package dio.dio.spring.security.jwt.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarrinhoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Produto> produtos;

    @ElementCollection
    private List<Integer> quantidadeVendida;

    @ManyToOne
    @NotNull(message = "O cliente é obrigatório.")
    private Cliente cliente;

    @NotNull(message = "A data e hora da venda são obrigatórias.")
    private LocalDateTime dataHoraVenda;

    @ManyToOne
    @NotNull(message = "O funcionário responsável é obrigatório.")
    private Funcionario funcionarioResponsavel;
}
