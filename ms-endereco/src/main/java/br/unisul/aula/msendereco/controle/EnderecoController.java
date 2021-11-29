package br.unisul.aula.msendereco.controle;

import br.unisul.aula.msendereco.dtos.EnderecoDTO;
import br.unisul.aula.msendereco.dtos.EnderecoViaCEPDTO;
import br.unisul.aula.msendereco.modelo.Endereco;
import br.unisul.aula.msendereco.repositorio.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/end")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/")
    public List<EnderecoDTO> listarTodosEnderecos(){
        List<EnderecoDTO> dtos = new ArrayList<>();
        for (Endereco endereco: enderecoRepository.findAll()){
            EnderecoDTO dto = new EnderecoDTO(endereco);
            dtos.add(dto);
        }
        return dtos;
    }
    
    @GetMapping("/lambda")
    public List<EnderecoDTO> listarTodosEnderecosUsandoLambda(){
        List<Endereco> enderecoList = enderecoRepository.findAll();
        return enderecoList.stream().map(EnderecoDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    @GetMapping("/{cep}")
    public EnderecoDTO buscarPorCEP(@PathVariable(name = "cep")Integer cep){
        return new EnderecoDTO(enderecoRepository.findFirstByCep(cep));
    }
    
    @GetMapping("/id/{id}")
    public EnderecoDTO buscarPorCEP(@PathVariable(name = "id")Long id){
        return new EnderecoDTO(enderecoRepository.getById(id));
    }
    
    @GetMapping("/cidade/{cidade}")
    public EnderecoDTO buscarPorCidade(@PathVariable(name = "cidade")String cidade){
        return new EnderecoDTO(enderecoRepository.findByCidade(cidade));
    }

    @PostMapping("/")
    public void inserirEndereco(@RequestBody EnderecoDTO dto){
        enderecoRepository.save(dto.converterParaEndereco());
    }
    @PostMapping("/viacep")
    public void inserirEndereco(@RequestBody EnderecoViaCEPDTO dto){
        enderecoRepository.save(dto.converterParaEndereco());
    }


}
