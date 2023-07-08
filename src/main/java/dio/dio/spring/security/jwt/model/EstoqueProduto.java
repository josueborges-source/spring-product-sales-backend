package dio.dio.spring.security.jwt.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EstoqueProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "O produto é obrigatório.")
    private Produto produto;

    @Min(value = 0, message = "A quantidade do produto deve ser maior ou igual a zero.")
    private int quantidade;

    @NotNull(message = "A data de chegada é obrigatória.")
    private LocalDate dataChegada;

    @NotNull(message = "A data de validade é obrigatória.")
    private LocalDate dataValidade;

    @NotNull(message = "O preço de compra é obrigatório.")
    private Double precoCompra;

    @ManyToOne
    @NotNull(message = "O representante comercial responsável é obrigatório.")
    private RepresentanteComercial representanteResponsavel;
}
