package dio.desafio.pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@FeignClient (name="viacep", url= "https://viacep.com.br/ws")

public interface ViaCepService {

    @GetMapping ("/{cep}/json/")
    Endereco consultarCep (@PathVariable ("cep") String cep);

}