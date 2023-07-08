package dio.dio.spring.security.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.dio.spring.security.jwt.model.DadosPessoais;
import dio.dio.spring.security.jwt.repository.DadosPessoaisRepository;

@Service
public class DadosPessoaisService {

    private final DadosPessoaisRepository dadosPessoaisRepository;

    @Autowired
    public DadosPessoaisService(DadosPessoaisRepository dadosPessoaisRepository) {
        this.dadosPessoaisRepository = dadosPessoaisRepository;
    }

    public List<DadosPessoais> getAllDadosPessoais() {
        return dadosPessoaisRepository.findAll();
    }

    public DadosPessoais getDadosPessoaisById(Long id) {
        Optional<DadosPessoais> optionalDadosPessoais = dadosPessoaisRepository.findById(id);
        return optionalDadosPessoais.orElse(null);
    }

    public DadosPessoais createDadosPessoais(DadosPessoais dadosPessoais) {
        return dadosPessoaisRepository.save(dadosPessoais);
    }

    public DadosPessoais updateDadosPessoais(Long id, DadosPessoais dadosPessoais) {
        Optional<DadosPessoais> optionalDadosPessoais = dadosPessoaisRepository.findById(id);
        if (optionalDadosPessoais.isPresent()) {
            DadosPessoais existingDadosPessoais = optionalDadosPessoais.get();
            existingDadosPessoais.setNome(dadosPessoais.getNome());
            existingDadosPessoais.setCpf(dadosPessoais.getCpf());
            existingDadosPessoais.setDataNascimento(dadosPessoais.getDataNascimento());
            existingDadosPessoais.setEndereco(dadosPessoais.getEndereco());
            existingDadosPessoais.setEmail(dadosPessoais.getEmail());
            existingDadosPessoais.setTelefones(dadosPessoais.getTelefones());
            return dadosPessoaisRepository.save(existingDadosPessoais);
        } else {
            return null;
        }
    }

    public void deleteDadosPessoais(Long id) {
        dadosPessoaisRepository.deleteById(id);
    }
}
