package br.com.diego.controller;

import br.com.diego.response.EnderecoResponse;
import br.com.diego.service.CepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BuscaCEP endpoint")
@RestController
@RequestMapping("buscacep-service")
@Slf4j
public class BuscaController {


    @Operation(summary = "find a specific endereco by your CEP")
    @GetMapping(value = "/{cep}/")
    public ResponseEntity<EnderecoResponse> findBook(
            @PathVariable("cep") Long cep
    ) throws Exception {

        CepService cepService = new CepService();

        EnderecoResponse enderecoResponse = cepService.buscaEnderecoPeloCEP(cep+"");

        if (enderecoResponse == null) throw new RuntimeException("Endereco not found.");

        return ResponseEntity.status(HttpStatus.OK).body(enderecoResponse);
    }

}
