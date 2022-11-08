package com.wasabisushi.persistence.dao;

import com.wasabisushi.persistence.entity.Pedido;

public class PedidoDAO extends AbstractDAO<Pedido>{

	public PedidoDAO() {
        super();
        super.setType(Pedido.class);
        super.setTable("Pedido");
    }

//    public List<Pedido> getAllPedido() {
//        return super.getAll();
//    }
//
//    public Pedido getPedidoById(Integer id) {
//        return super.getById(id);
//    }
//    
//    public void deletePedido(Pedido pedido) {
//		
//		super.delete(pedido);	
//	}
//
//	public void updatePedido(Pedido pedido) {
//		
//		super.update(pedido);		
//	}
//	
	public void createPedido(Pedido pedido) {
		super.create(pedido);		
	}
}
