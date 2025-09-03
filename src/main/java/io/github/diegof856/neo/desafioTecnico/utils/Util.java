package io.github.diegof856.neo.desafioTecnico.utils;


import io.github.diegof856.neo.desafioTecnico.controller.dto.ConsultaCepDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class Util {

    public ConsultaCepDTO consultaCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ConsultaCepDTO> resp = restTemplate.getForEntity("https://viacep.com.br/ws/" + cep + "/json/", ConsultaCepDTO.class);


        return resp.getBody();
    }
}
