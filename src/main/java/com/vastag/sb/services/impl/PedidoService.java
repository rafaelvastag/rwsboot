package com.vastag.sb.services.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.vastag.sb.domain.ItemPedido;
import com.vastag.sb.domain.PagamentoComBoleto;
import com.vastag.sb.domain.Pedido;
import com.vastag.sb.domain.enums.PagamentoStatus;
import com.vastag.sb.repositories.EnderecoRepository;
import com.vastag.sb.repositories.ItemPedidoRepository;
import com.vastag.sb.repositories.PagamentoRepository;
import com.vastag.sb.repositories.PedidoRepository;
import com.vastag.sb.services.IClienteService;
import com.vastag.sb.services.IEmailService;
import com.vastag.sb.services.IPedidoService;
import com.vastag.sb.services.IProdutoService;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;
import com.vastag.sb.services.utils.BoletoServiceUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("pedidoService")
public class PedidoService implements IPedidoService {

	private final PedidoRepository repo;
	public final BoletoServiceUtil boletoService;
	public final PagamentoRepository pagamentoRepo;
	public final IProdutoService produtoService;
	private final IClienteService clienteService;
	private final EnderecoRepository enderecoRepo;
	private final ItemPedidoRepository itemPedidoRepo;
	private final IEmailService emailService;

	@Override
	public Pedido findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Override
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.setEnderecoDeEntrega(enderecoRepo.findById(obj.getEnderecoDeEntrega().getId()).get());
		obj.getPagamento().setStatus(PagamentoStatus.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepo.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepo.saveAll(obj.getItens());
		emailService.sendOrderConfirmation(obj);
		return obj;
	}
}
