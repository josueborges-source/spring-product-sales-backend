package dio.dio.spring.security.jwt.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.dio.spring.security.jwt.model.CarrinhoCompra;
import dio.dio.spring.security.jwt.repository.CarrinhoCompraRepository;

@Service
public class CarrinhoCompraService {

    private final CarrinhoCompraRepository carrinhoCompraRepository;

    @Autowired
    public CarrinhoCompraService(CarrinhoCompraRepository carrinhoCompraRepository) {
        this.carrinhoCompraRepository = carrinhoCompraRepository;
    }

    public List<CarrinhoCompra> getAllCarrinhoCompras() {
        return carrinhoCompraRepository.findAll();
    }

    public CarrinhoCompra getCarrinhoCompraById(Long id) {
        Optional<CarrinhoCompra> optionalCarrinhoCompra = carrinhoCompraRepository.findById(id);
        return optionalCarrinhoCompra.orElse(null);
    }

    public CarrinhoCompra createCarrinhoCompra(CarrinhoCompra carrinhoCompra) {
        return carrinhoCompraRepository.save(carrinhoCompra);
    }

    public CarrinhoCompra updateCarrinhoCompra(Long id, CarrinhoCompra carrinhoCompra) {
        Optional<CarrinhoCompra> optionalCarrinhoCompra = carrinhoCompraRepository.findById(id);
        if (optionalCarrinhoCompra.isPresent()) {
            CarrinhoCompra existingCarrinhoCompra = optionalCarrinhoCompra.get();
            existingCarrinhoCompra.setProdutos(carrinhoCompra.getProdutos());
            existingCarrinhoCompra.setQuantidadeVendida(carrinhoCompra.getQuantidadeVendida());
            existingCarrinhoCompra.setCliente(carrinhoCompra.getCliente());
            existingCarrinhoCompra.setDataHoraVenda(carrinhoCompra.getDataHoraVenda());
            existingCarrinhoCompra.setFuncionarioResponsavel(carrinhoCompra.getFuncionarioResponsavel());
            return carrinhoCompraRepository.save(existingCarrinhoCompra);
        } else {
            return null;
        }
    }

    public void deleteCarrinhoCompra(Long id) {
        carrinhoCompraRepository.deleteById(id);
    }
}
