package com.wasabisushi.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.wasabisushi.business.controller.ClienteController;
import com.wasabisushi.persistence.entity.Cliente;



public class WasabiSushiMainFrame extends JFrame {
	
private static final long serialVersionUID = 1L;
	
	private JButton botaoCliente, botaoProduto, botaoPedido;

	public WasabiSushiMainFrame() {
		super("WASABI SUSHI TESTE APPX");
		
		Container container = getContentPane();
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

		
		JLabel labelApp = new JLabel("Seja bem-vindo ao WasabiSushi App");
		
		labelApp.setBounds(35, 20, 300, 15);
		
		labelApp.setForeground(Color.BLACK);
		
		labelPanel.add(labelApp);
		
		JPanel buttonPanel = new JPanel();
				
		botaoCliente = new JButton("Clientes");
		botaoCliente.setBounds(120, 50,100, 20);
		buttonPanel.add(botaoCliente);
		
		botaoProduto = new JButton("Produtos");
		botaoProduto.setBounds(120, 100,100, 20);
		buttonPanel.add(botaoProduto);
		
		botaoPedido = new JButton("Pedidos");
		botaoPedido.setBounds(120, 150,100, 20);
		buttonPanel.add(botaoPedido);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

		container.add(labelPanel);

		container.add(buttonPanel);
		
		
		setSize(350, 250);
		setLayout(new GridLayout(2,1));
		setLocationRelativeTo(null);
		setVisible(true);
		
		botaoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WasabiSushiClienteFrame clienteFrame = new WasabiSushiClienteFrame();
			}
		});
		
		botaoProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WasabiSushiProdutoFrame produtoFrame = new WasabiSushiProdutoFrame();
			}
		});
		
		botaoPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ClienteController clienteNegocio = new ClienteController();
				List<Cliente> clientes = clienteNegocio.getAllClientes();
				
				List<String> labels = clientes.stream().map(Cliente::getNome).toList();
				JComboBox comboBox = new JComboBox(labels.toArray());
				comboBox.setSelectedIndex(-1);

				JOptionPane.showMessageDialog(null, comboBox, "Selecione o Cliente",
						JOptionPane.QUESTION_MESSAGE);
				System.out.println("Cliente Selecionado: " + clientes.get(comboBox.getSelectedIndex()));
				WasabiSushiPedidoFrame frameWasabi = new WasabiSushiPedidoFrame(clientes.get(comboBox.getSelectedIndex()));
				
			}
		});

	}

}
