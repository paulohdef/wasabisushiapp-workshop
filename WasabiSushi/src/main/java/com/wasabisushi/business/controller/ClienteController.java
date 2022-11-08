package com.wasabisushi.business.controller;

import com.wasabisushi.persistence.dao.ClienteDAO;
import com.wasabisushi.persistence.entity.Cliente;


import java.util.List;

public class ClienteController {

	public void alterar(Cliente cliente) {
		
		ClienteDAO clientedao = new ClienteDAO();
		clientedao.updateCliente(cliente);		
	}

	public void deletar(Cliente cliente) {
		
		ClienteDAO clientedao = new ClienteDAO();
		clientedao.deleteCliente(cliente);
		
	}

    public List<Cliente> getAllClientes() {
    	ClienteDAO clientedao = new ClienteDAO();
        return clientedao.getAll();
    }

    public void salvarCliente(Cliente cLiente) {
    	ClienteDAO clientedao = new ClienteDAO();
    	clientedao.create(cLiente);
    }
    

}
