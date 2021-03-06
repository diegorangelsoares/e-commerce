package br.com.diego.controller;

import br.com.diego.model.Cambio;
import br.com.diego.service.CambioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Service API")
@RestController
@RequestMapping("cambio-service")
public class CambioController {

    private Logger logger = LoggerFactory.getLogger(CambioController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CambioService cambioService;

    @Operation(summary = "Get cambio from currency!")
    @GetMapping(value = "/{amount}/{from}/{to}")
    public ResponseEntity<Cambio> getCambio(
            @PathVariable ("amount")BigDecimal amount,
            @PathVariable ("from") String from,
            @PathVariable ("to") String to
            ){
        logger.info("getCambio is called with -> {}, {} and {}", amount, from, to);
        var port = environment.getProperty("local.server.port");
        var cambio = cambioService.findByFromAndTo(from, to);
        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        cambio.setEnvironment(port);
        cambio.setConversionValue(convertedValue.setScale(2, RoundingMode.CEILING));
        return ResponseEntity.status(HttpStatus.OK).body(cambio);
    }



}
