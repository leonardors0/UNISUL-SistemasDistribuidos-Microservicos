
package br.unisul.aula.mscliente.servico;

import br.unisul.aula.mscliente.dto.ClienteDTO;
import br.unisul.aula.mscliente.dto.ClientePorCidadeDTO;
import br.unisul.aula.mscliente.dto.EnderecoDTO;
import br.unisul.aula.mscliente.dto.ModeloDeClienteDTO;
import br.unisul.aula.mscliente.modelo.Cliente;
import br.unisul.aula.mscliente.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoClient enderecoClient;

	public List<ClienteDTO> listarTodos() {
		List<ClienteDTO> dtos = new ArrayList<>();
		for (Cliente cliente : clienteRepository.findAll()) {
			EnderecoDTO enderecoDTO = buscarEnderecoPorID(cliente.getEndereco_id());
			ClienteDTO clienteDTO = new ClienteDTO(cliente, enderecoDTO);
			dtos.add(clienteDTO);
		}
		return dtos;
	}

	public ClientePorCidadeDTO listarClientePorCidades(String cidade) {

		List<ClientePorCidadeDTO> clientes = new ArrayList<>();
		List<ModeloDeClienteDTO> clienteModelo = new ArrayList<>();

		EnderecoDTO enderecoDTO = buscarEnderecoPorCidade(cidade);

		for (Cliente cliente : clienteRepository.findAll()) {

			if (cliente.getEndereco_id() == enderecoDTO.getId()) {
				String nome = cliente.getNome();
				Long idCliente = cliente.getId();

				ModeloDeClienteDTO modeloDeClienteDTO = new ModeloDeClienteDTO(idCliente, nome);
				clienteModelo.add(modeloDeClienteDTO);

				ClientePorCidadeDTO clientePorCidadeDTO = new ClientePorCidadeDTO(clienteModelo);
				clientes.add(clientePorCidadeDTO);
			}
		}

		return new ClientePorCidadeDTO(clienteModelo, enderecoDTO);
	}

//	

	private EnderecoDTO buscarEnderecoPorID(Long id) {
		return enderecoClient.buscarPorId(id);
	}

	private EnderecoDTO buscarEnderecoPorCep(Integer cep) {
		return enderecoClient.buscarPorCep(cep);
	}

	private EnderecoDTO buscarEnderecoPorCidade(String cidade) {
		return enderecoClient.buscaClientePorCidade(cidade);
	}

	public void registrarCliente(ClienteDTO dto) {
		dto.setEndereco(buscarEnderecoPorCep(dto.getCep()));
		Cliente cliente = dto.converterParaCliente();
		clienteRepository.save(cliente);
	}

}
