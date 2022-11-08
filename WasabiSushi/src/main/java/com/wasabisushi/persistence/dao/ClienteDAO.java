package com.wasabisushi.persistence.dao;

import com.wasabisushi.persistence.entity.Cliente;
import com.wasabisushi.persistence.entity.Produto;

import java.util.List;

public class ClienteDAO extends AbstractDAO<Cliente>{

    public ClienteDAO() {
        super();
        super.setType(Cliente.class);
        super.setTable("Cliente");
    }

    public List<Cliente> getAllCliente() {
        return super.getAll();
    }

    public Cliente getClienteById(Integer id) {
        return super.getById(id);
    }
    
    public void deleteCliente(Cliente cliente) {
		
		super.delete(cliente);	
	}

	public void updateCliente(Cliente cliente) {
		
		super.update(cliente);		
	}
	
	public void createCliente(Cliente cliente) {
		
		super.create(cliente);		
	}


}
