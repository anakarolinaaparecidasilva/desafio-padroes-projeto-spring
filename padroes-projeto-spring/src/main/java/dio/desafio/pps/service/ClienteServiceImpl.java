package dio.desafio.pps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.desafio.pps.model.Cliente;
import dio.desafio.pps.model.ClienteRepository;
import dio.desafio.pps.model.Endereco;
import dio.desafio.pps.model.EnderecoRepository;
import dio.desafio.pps.service.ClienteService;
import dio.desafio.pps.service.ViaCepService;

@Service

public class ClienteServiceImpl implements ClienteService {

	//Singleton: Injetar os componentes do Spring com @Autowired
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ViaCepService viaCepService;


	//Strategy: Implementar os métodos definidos na interface
	//Facade: Abstrair integrações com subsistemas, provendo uma interface simples

	@Override
	public Iterable <Cliente> buscarTodos() {
		//Buscar todos os Clientes
		return clienteRepository.findAll();

	}

	@Override
	public Cliente buscarPorId (Long id){
		//Buscar Cliente por ID
		Optional<Cliente> cliente = clienteRepository.findById (id);
		return cliente.get ();

	}

	@Override
public void inserir (Cliente cliente) {
		salvarClienteComCep (cliente);
	}

	@Override
public void atualizar (Long id, Cliente cliente){
		//Buscar Cliente por ID, caso exista
		Optional<Cliente>clienteBd = clienteRepository.findById(id);
		if (clienteBd.isPresent()){
			salvarClienteComCep (cliente);
		}

		@Override
		public void deletar (Long Id){
			//Deletar Cliente por ID
			clienteRepository.deleteById(id);
		}

		private void salvarClienteComCep (Cliente cliente) {
			//Verificar se o Endereço do Cliente já existe
			String cep = cliente.getEndereco().getCep();
			Endereco endereco=enderecoRepository.findById(cep).orElseGet(() -> {
				//Caso não exista, integrar com o ViaCEP e persistir o retorno
				Endereco novoEndereco = viaCepService.consultarCep(cep);
				enderecoRepository.save(novoEndereco);
				return novoEndereco;
			});

			cliente.setEndereco (endereco);
			//Inserir Cliente, vinculando o Endereço (novo ou existente)
			clienteRepository.save(cliente);
		}
	}
	}