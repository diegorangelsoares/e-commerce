package br.com.diego.service;

import br.com.diego.exception.CambioNotFoundException;
import br.com.diego.model.Cambio;
import br.com.diego.repository.CambioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CambioService {

    @Autowired
    private CambioRepository cambioRepository;

    @Autowired
    public CambioService(CambioRepository cambioRepository) {
        this.cambioRepository = cambioRepository;
    }

    public Cambio findById(Long id){
        return cambioRepository.findById(id)
                .orElseThrow(() -> new CambioNotFoundException(String.format(
                        "Cambio not found!", id)));
    }

    public Cambio findByFromAndTo(String from, String to){
        Cambio cambio = cambioRepository.findByFromAndTo(from, to);
        if (cambio == null) new CambioNotFoundException(String.format(
                "Cambio not found!", from, to));
        return cambio;
    }

}
