package com.vastag.sb;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vastag.sb.domain.Categoria;
import com.vastag.sb.domain.Cidade;
import com.vastag.sb.domain.Cliente;
import com.vastag.sb.domain.Endereco;
import com.vastag.sb.domain.Estado;
import com.vastag.sb.domain.Produto;
import com.vastag.sb.domain.enums.TipoCliente;
import com.vastag.sb.repositories.CategoriaRepository;
import com.vastag.sb.repositories.CidadeRepository;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.repositories.EnderecoRepository;
import com.vastag.sb.repositories.EstadoRepository;
import com.vastag.sb.repositories.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class RwsbootApplication implements CommandLineRunner {

	private final CategoriaRepository categoriaRepository;
	private final ProdutoRepository produtoRepository;
	private final CidadeRepository cidadeRepository;
	private final EstadoRepository estadoRepository;
	private final EnderecoRepository enderecoRepository;
	private final ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(RwsbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria c1 = new Categoria("Informática");
		Categoria c2 = new Categoria("Escritório");

		Produto p1 = new Produto("Computador", 2000.0);
		Produto p2 = new Produto("Impressora", 800.0);
		Produto p3 = new Produto("Mouse", 80.0);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");

		Cidade cd1 = new Cidade("Uberlândia", est1);
		Cidade cd2 = new Cidade("São Paulo", est2);
		Cidade cd3 = new Cidade("Campinas", est2);

		Cliente cli1 = new Cliente("Rafael Vastag", "rafaelvastag@email.com", "44422233312", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("944442222", "988884444"));

		Endereco end1 = new Endereco("Rua 01", "300", "Apto 100", "Centro", "030303-000", cli1, cd1);
		Endereco end2 = new Endereco("Rua Esquerda", "450", "Apto 30", "Vila Rosilda", "456783-000", cli1, cd2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cd1, cd2, cd3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}
}
