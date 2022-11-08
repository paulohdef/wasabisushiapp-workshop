package com.wasabisushi.persistence.dao;

import com.wasabisushi.persistence.entity.Produto;
import java.util.List;

public class ProdutoDAO extends AbstractDAO<Produto> {
	
	public ProdutoDAO() {
        super();
        super.setType(Produto.class);
        super.setTable("Produto");
    }

    public List<Produto> getAllProduto() {
        return super.getAll();
    }

    public Produto getProdutoById(Integer id) {
        return super.getById(id);
    }
    
    public void deleteProduto(Produto produto) {
		
		super.delete(produto);	
	}

	public void updateProduto(Produto produto) {
		
		super.update(produto);		
	}
	
	public void createProduto(Produto produto) {
		
		super.create(produto);		
	}

}
