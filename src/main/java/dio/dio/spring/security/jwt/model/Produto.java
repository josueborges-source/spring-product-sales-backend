package dio.dio.spring.security.jwt.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O código é obrigatório.")
    private String codigo;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "O preço de compra é obrigatório.")
    private BigDecimal precoCompra;

    @NotBlank(message = "A marca é obrigatória.")
    private String marca;

    @ManyToOne
    private Fabricante fabricante;
}
