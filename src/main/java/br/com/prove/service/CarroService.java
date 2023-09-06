package br.com.prove.service;

import br.com.prove.api.dto.CarroMarcaDTO;
import br.com.prove.api.exception.CargoNaoEncontradoException;
import br.com.prove.api.mapper.CarroMapper;
import br.com.prove.api.model.Carro;
import br.com.prove.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CarroMapper carroMapper;

    public CarroMarcaDTO findCarroMarca(Long idCarro) {
        Carro carro = carroRepository.findById(idCarro)
                .orElseThrow(() -> new CargoNaoEncontradoException(idCarro));

        CarroMarcaDTO carroDTO = carroMapper.modelToDto(carro);

        return carroDTO;
    }
}
