package com.wasabisushi.business.controller;

import com.wasabisushi.persistence.dao.PedidoDAO;
import com.wasabisushi.persistence.entity.Pedido;

public class PedidoController {


	public void adiciona(Pedido pedido) {
		System.out.println(pedido.getCliente().getNome());
		System.out.println(pedido.getProduto().getNome());
		System.out.println(pedido.getValorTotal());
		PedidoDAO clientedao = new PedidoDAO();
		clientedao.createPedido(pedido);
	}
}
