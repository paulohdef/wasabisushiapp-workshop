package com.wasabisushi.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wasabisushi.business.controller.ProdutoController;
import com.wasabisushi.business.controller.ClienteController;
import com.wasabisushi.persistence.entity.Cliente;


public class WasabiSushiClienteFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JLabel labelCpf, labelNome, labelEndereco, labelTelefone, labelNCartao;
	private JTextField textoCpf, textoNome, textoEndereco, textoTelefone, textoNCartao;
	private JButton botaoSalvarCliente, botaoEditarCliente, botaoLimparCliente, botarApagarCliente;
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private ClienteController clienteController;

	public WasabiSushiClienteFrame() {
		super("WASABI SUSHI APP CLIENTES");
		Container container = getContentPane();
		setLayout(null);

		this.clienteController = new ClienteController();

		labelNome = new JLabel("Nome");
		labelCpf = new JLabel("CPF");
		labelEndereco = new JLabel("Endereço");
		labelTelefone = new JLabel("Telefone");
		labelNCartao = new JLabel("Número do Cartão");;

		labelNome.setBounds(10, 10, 240, 15);
		labelCpf.setBounds(10, 50, 240, 15);
		labelEndereco.setBounds(10, 50, 240, 15);
		labelTelefone.setBounds(10, 50, 240, 15);
		labelNCartao.setBounds(10, 50, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelCpf.setForeground(Color.BLACK);
		labelEndereco.setForeground(Color.BLACK);
		labelTelefone.setForeground(Color.BLACK);
		labelNCartao.setForeground(Color.BLACK);

		textoCpf = new JTextField(10);
		textoNome = new JTextField(10);
		textoEndereco = new JTextField(10);
		textoTelefone = new JTextField(10);
		textoNCartao = new JTextField(10);
		
		JPanel panelInputs = new JPanel();
		GridLayout inputsLayout = new GridLayout(5,2);
		inputsLayout.setVgap(5);
		inputsLayout.setHgap(1);
		panelInputs.setLayout(inputsLayout);
		panelInputs.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


		panelInputs.add(labelNome);
		panelInputs.add(textoNome);
		
		panelInputs.add(labelCpf);
		panelInputs.add(textoCpf);
		
		panelInputs.add(labelEndereco);
		panelInputs.add(textoEndereco);
		
		panelInputs.add(labelTelefone);
		panelInputs.add(textoTelefone);
		
		panelInputs.add(labelNCartao);
		panelInputs.add(textoNCartao);
		
		container.add(panelInputs);

		botaoSalvarCliente = new JButton("Salvar");
		botaoLimparCliente = new JButton("Limpar");

		botaoSalvarCliente.setBounds(10, 145, 120, 20);
		botaoLimparCliente.setBounds(150, 145, 120, 20);
		
		JPanel panelButtons = new JPanel();
		
		panelButtons.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		
		panelButtons.add(botaoSalvarCliente);
		panelButtons.add(botaoLimparCliente);
		
		container.add(panelButtons);

		tabela = new JTable();
		
		tabela.setFillsViewportHeight(true);
		
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Endereco");
		modelo.addColumn("Telefone");
		modelo.addColumn("NCartao");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botarApagarCliente = new JButton("Excluir");
		botaoEditarCliente = new JButton("Alterar");

		botarApagarCliente.setBounds(10, 500, 120, 20);
		botaoEditarCliente.setBounds(150, 500, 120, 20);

		container.add(botarApagarCliente);
		container.add(botaoEditarCliente);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setVisible(true);
		scrollPane.getViewport().add(tabela);

		JPanel panel = new JPanel();
		panel.add(scrollPane);

		GridLayout gridLayout = new GridLayout(1, 1);

		panel.setLayout(gridLayout);
		gridLayout.setVgap(5);


		container.add(panel);

		JPanel panel2 = new JPanel();
		
		panel2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


		panel2.add(botarApagarCliente);
		panel2.add(botaoEditarCliente);
		container.add(panel2);

		setSize(800, 700);
		setVisible(true);
		setLayout(new GridLayout(4,1));
		setLocationRelativeTo(null);

		botaoSalvarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarCliente();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimparCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCliente();
			}
		});

		botarApagarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				preencherTabela();
			}
		});
	}

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	private void alterar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		
		if (objetoDaLinha instanceof Integer) {
			
			Integer id = (Integer) objetoDaLinha;
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String cpf = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			String endereco = (String) modelo.getValueAt(tabela.getSelectedRow(), 3);
			String telefone = (String) modelo.getValueAt(tabela.getSelectedRow(), 4);
			String NCartao = (String) modelo.getValueAt(tabela.getSelectedRow(), 5);

			// VERIFICAR COMO SERÁ REALIZADO O EDITAR
			
			Cliente cliente = new Cliente(id, cpf,nome, endereco, telefone, NCartao);
			
			this.clienteController.alterar(cliente);
			
			JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	// alterar de acordo com fernando
	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		
		if (objetoDaLinha instanceof Integer) {
			
			Integer id = (Integer) objetoDaLinha;
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String cpf = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			String endereco = (String) modelo.getValueAt(tabela.getSelectedRow(), 3);
			String telefone = (String) modelo.getValueAt(tabela.getSelectedRow(), 4);
			String NCartao = (String) modelo.getValueAt(tabela.getSelectedRow(), 5);

			Cliente cliente = new Cliente(id, cpf, nome, endereco, telefone, NCartao);
			
			this.clienteController.deletar(cliente);
			
			modelo.removeRow(tabela.getSelectedRow());
			
			JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void preencherTabela() {
		List<Cliente> clientes =  listarClientes();
//		clientes.forEach(cliente -> System.out.println(cliente.getNome()));
		try {
			for (Cliente cliente : clientes) {
				modelo.addRow(new Object[] { cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getTelefone(), cliente.getNCartao() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void salvarCliente() {
		if (!textoCpf.getText().equals("") && !textoNome.getText().equals("") && !textoEndereco.getText().equals("") && !textoTelefone.getText().equals("") && !textoNCartao.getText().equals("")) {
			Cliente cliente = new Cliente(textoCpf.getText(), textoNome.getText(), textoEndereco.getText(), textoTelefone.getText(), textoNCartao.getText());
			this.clienteController.salvarCliente(cliente);
			JOptionPane.showMessageDialog(this, "Cliente Salvo com sucesso!");
			this.limparCliente();
		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos devem ser informados.");
		}
	}

	private List<Cliente> listarClientes() {
		return this.clienteController.getAllClientes();
	}

	private void limparCliente() {
		this.textoCpf.setText("");
		this.textoNome.setText("");
		this.textoEndereco.setText("");
		this.textoTelefone.setText("");
		this.textoNCartao.setText("");
	}

}
