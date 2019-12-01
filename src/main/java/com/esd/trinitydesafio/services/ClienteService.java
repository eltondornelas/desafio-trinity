package com.esd.trinitydesafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esd.trinitydesafio.domain.Cliente;
import com.esd.trinitydesafio.repositories.ClienteRepository;
import com.esd.trinitydesafio.repositories.EnderecoRepository;
import com.esd.trinitydesafio.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente de Id: " + id + ", não encontrado"));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null); // apenas por garantia
	
		obj.getEndereco().setCliente(obj); 
		// apontando o cliente em endereco antes de salvar para evitar NullPointException
		
		obj = clienteRepository.save(obj);		
		enderecoRepository.save(obj.getEndereco());
		
		return obj;
	}
	
	public void update(Cliente obj) {
		
		find(obj.getId());
		// se não encontrar, já da exceção
		
		obj.getEndereco().setId(obj.getId()); 
		// para evitar um IdentifierGenerationException
		
		clienteRepository.save(obj);
		
	}
	
	
	public void delete(Integer id) {
		find(id); // caso não ache já executa exceção
		
		clienteRepository.deleteById(id);
	}
	
	public List<Cliente> findaAll() {
		return clienteRepository.findAll();
	}
	

}
