package com.vastag.sb.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Categoria;
import com.vastag.sb.domain.Cidade;
import com.vastag.sb.domain.Cliente;
import com.vastag.sb.domain.Endereco;
import com.vastag.sb.domain.Estado;
import com.vastag.sb.domain.ItemPedido;
import com.vastag.sb.domain.Pagamento;
import com.vastag.sb.domain.PagamentoComBoleto;
import com.vastag.sb.domain.PagamentoComCartao;
import com.vastag.sb.domain.Pedido;
import com.vastag.sb.domain.Produto;
import com.vastag.sb.domain.enums.PagamentoStatus;
import com.vastag.sb.domain.enums.Perfil;
import com.vastag.sb.domain.enums.TipoCliente;
import com.vastag.sb.repositories.CategoriaRepository;
import com.vastag.sb.repositories.CidadeRepository;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.repositories.EnderecoRepository;
import com.vastag.sb.repositories.EstadoRepository;
import com.vastag.sb.repositories.ItemPedidoRepository;
import com.vastag.sb.repositories.PagamentoRepository;
import com.vastag.sb.repositories.PedidoRepository;
import com.vastag.sb.repositories.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DBService {

	private final CategoriaRepository categoriaRepository;
	private final ProdutoRepository produtoRepository;
	private final CidadeRepository cidadeRepository;
	private final EstadoRepository estadoRepository;
	private final EnderecoRepository enderecoRepository;
	private final ClienteRepository clienteRepository;
	private final PedidoRepository pedidoRepository;
	private final PagamentoRepository pagamentoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	private final BCryptPasswordEncoder encoder;

	public void seedTestDatabase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");
		Categoria cat3 = new Categoria("Cama");
		Categoria cat4 = new Categoria("Mesa");
		Categoria cat5 = new Categoria("Banho");
		Categoria cat6 = new Categoria("Cozinha");
		Categoria cat7 = new Categoria("Jardim");
		Categoria cat8 = new Categoria("Marcenaria");

		Produto p1 = new Produto("Computador", 2000.0);
		Produto p2 = new Produto("Impressora", 800.0);
		Produto p3 = new Produto("Mouse", 80.0);
		Produto p4 = new Produto("Mesa de escritório", 300.00);
		Produto p5 = new Produto("Toalha", 50.00);
		Produto p6 = new Produto("Colcha", 200.00);
		Produto p7 = new Produto("TV true color", 1200.00);
		Produto p8 = new Produto("Roçadeira", 800.00);
		Produto p9 = new Produto("Abajour", 100.00);
		Produto p10 = new Produto("Pendente", 180.00);
		Produto p11 = new Produto("Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat7));
		p11.getCategorias().addAll(Arrays.asList(cat8));

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");

		Cidade cd1 = new Cidade("Uberlândia", est1);
		Cidade cd2 = new Cidade("São Paulo", est2);
		Cidade cd3 = new Cidade("Campinas", est2);

		Cliente cli1 = new Cliente("Rafael Vastag", "email@gmail.com", "66742069031", TipoCliente.PESSOA_FISICA,
				encoder.encode("password"));
		cli1.getTelefones().addAll(Arrays.asList("944442222", "988884444"));

		Cliente cli2 = new Cliente("Ana Vastag", "ana@gmail.com", "93418206000", TipoCliente.PESSOA_FISICA,
				encoder.encode("password123"));
		cli2.getTelefones().addAll(Arrays.asList("944443322", "9844484444"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco end1 = new Endereco("Rua 01", "300", "Apto 100", "Centro", "030303-000", cli1, cd1);
		Endereco end2 = new Endereco("Rua Esquerda", "450", "Apto 30", "Vila Rosilda", "456783-000", cli1, cd2);
		Endereco end3 = new Endereco("Rua 3", "450", null, "Vila Jabuti", "456783-000", cli2, cd2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3));

		Pedido pd1 = new Pedido(sdf.parse("30/09/2022 10:32"), cli1, end1);
		Pedido pd2 = new Pedido(sdf.parse("10/11/2022 20:14"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(PagamentoStatus.QUITADO, pd1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(PagamentoStatus.PENDENTE, pd2, sdf.parse("25/11/2022 00:14"), null);

		pd1.setPagamento(pagto1);
		pd2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(pd1, pd2));

		ItemPedido item1 = new ItemPedido(pd1, p1, 0.00, 1, 2000.0);
		ItemPedido item2 = new ItemPedido(pd1, p3, 0.00, 2, 80.0);
		ItemPedido item3 = new ItemPedido(pd2, p2, 100.00, 1, 2800.0);

		pd1.getItens().addAll(Arrays.asList(item1, item2));
		pd2.getItens().addAll(Arrays.asList(item3));

		p1.getItens().addAll(Arrays.asList(item1));
		p2.getItens().addAll(Arrays.asList(item3));
		p3.getItens().addAll(Arrays.asList(item2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cd1, cd2, cd3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		pedidoRepository.saveAll(Arrays.asList(pd1, pd2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
	}
}
