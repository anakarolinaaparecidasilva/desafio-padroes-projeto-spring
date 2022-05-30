package dio.desafio.pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Repository

public interface ClienteService {

    Iterable <Cliente> buscarTodos();
    Cliente buscarPorId (Long id);
    void inserir (Cliente cliente);
    void atualizar (Long id, Cliente cliente);
    void deletar (Long id);

}