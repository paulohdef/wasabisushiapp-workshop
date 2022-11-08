package com.wasabisushi.business.controller;

import com.wasabisushi.persistence.dao.ProdutoDAO;
import com.wasabisushi.persistence.entity.Produto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoController {

    private final ProdutoDAO produtoDAO;

    public ProdutoController() {
        produtoDAO = new ProdutoDAO();
    }

    public Map<String, List<Produto>> getAllProdutoByCategoria(){
        List<Produto> produtos = produtoDAO.getAllProduto();
        Map<String, List<Produto>> produtosPorCategoria = new HashMap<>();

        for (Produto produto : produtos) {
            produtosPorCategoria.computeIfAbsent(produto.getCategoria(), f-> new ArrayList<>());
            produtosPorCategoria.get(produto.getCategoria()).add(produto);
        }
        return produtosPorCategoria;
    }

	public void alterar(Produto produto) {
		ProdutoDAO produtodao = new ProdutoDAO();
		
		produtodao.updateProduto(produto);
	}

	public void deletar(Produto produto) {
		ProdutoDAO produtodao = new ProdutoDAO();
		
		produtodao.deleteProduto(produto);		
	}

	public List<Produto> listar() {
		ProdutoDAO produtodao = new ProdutoDAO();
		
		return produtodao.getAllProduto();
	}

	public void salvar(Produto produto) {
		ProdutoDAO produtodao = new ProdutoDAO();
		
		produtodao.createProduto(produto);
		
	}
}