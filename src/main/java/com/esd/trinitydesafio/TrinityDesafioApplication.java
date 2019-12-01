package com.esd.trinitydesafio;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.esd.trinitydesafio.domain.Cliente;
import com.esd.trinitydesafio.domain.Endereco;
import com.esd.trinitydesafio.repositories.ClienteRepository;
import com.esd.trinitydesafio.repositories.EnderecoRepository;

@SpringBootApplication
public class TrinityDesafioApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TrinityDesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Endereco end1 = new Endereco(null, "Rua João Francisco Lisboa", 121, "BL 12", "Várzea", "Recife", "PE", "51741-100", null);
		Endereco end2 = new Endereco(null, "Av. República do Líbano", 251, "Rio Mar Trade Center Torre A, Sala 1309", "Pina", "Recife", "PE", "51110-160", null);
		
		Cliente cli1 = new Cliente(null, "Elton Dornelas", "111222333-44", end1, sdf.parse("01/11/1990"));
		Cliente cli2 = new Cliente(null, "Trinity Soluções", "444555666-77", end2, sdf.parse("10/05/2000"));
		
		end1.setCliente(cli1);
		end2.setCliente(cli2);
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
				
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
