package br.unisul.aula.mscliente.dto;

import java.util.List;

import br.unisul.aula.mscliente.modelo.Cliente;

public class ClientePorCidadeDTO {
	private String cidade;
	private String uf;
	private List<ModeloDeClienteDTO> clientes;

	public ClientePorCidadeDTO(List<ModeloDeClienteDTO> clientes, EnderecoDTO enderecoDTO) {
		this.cidade = enderecoDTO.getCidade();
		this.uf = enderecoDTO.getUf();
		this.clientes = clientes;
	}

	public ClientePorCidadeDTO(List<ModeloDeClienteDTO> clienteModelo) {
		this.clientes = clienteModelo;
	}

	public List<ModeloDeClienteDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ModeloDeClienteDTO> clientes) {
		this.clientes = clientes;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
