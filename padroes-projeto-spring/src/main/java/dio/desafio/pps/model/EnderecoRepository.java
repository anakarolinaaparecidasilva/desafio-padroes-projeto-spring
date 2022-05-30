package dio.desafio.pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Repository

public interface EnderecoRepository extends CrudRepository<Endereco, String> {

}