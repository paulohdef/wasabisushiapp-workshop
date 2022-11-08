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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wasabisushi.business.controller.ProdutoController;
import com.wasabisushi.persistence.entity.Produto;

public class WasabiSushiProdutoFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelCategoria, labelDescricao, labelPreco;
	private JTextField textoNome, textoDescricao, textoPreco;
	private JButton botaoSalvarProduto, botaoEditarProduto, botaoLimparProduto, botarApagarProduto;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JComboBox comboCategoria;
	
	private ProdutoController produtoController;

	public WasabiSushiProdutoFrame() {
		super("WASABI SUSHI APP CLIENTES");
		Container container = getContentPane();
		setLayout(null);

		this.produtoController = new ProdutoController();

		labelNome = new JLabel("Nome");
		labelCategoria = new JLabel("Categoria");
		labelDescricao = new JLabel("Descrição");
		labelPreco = new JLabel("Preço");

		labelNome.setBounds(10, 10, 240, 15);
		labelCategoria.setBounds(10, 50, 240, 15);
		labelDescricao.setBounds(10, 50, 240, 15);
		labelPreco.setBounds(10, 50, 240, 15);

		labelNome.setForeground(Color.BLACK);
		
		labelCategoria.setForeground(Color.BLACK);
		
		
		labelDescricao.setForeground(Color.BLACK);
		labelPreco.setForeground(Color.BLACK);
		
		comboCategoria = new JComboBox();
	
		
		comboCategoria.addItem("Promoção");
		comboCategoria.addItem("Entradas");
		comboCategoria.addItem("Temaki Especial");
		comboCategoria.addItem("Holl Wasabi");
		
		comboCategoria.setSelectedIndex(-1);

		textoNome = new JTextField(10);
		textoDescricao = new JTextField(10);
		textoPreco = new JTextField(10);
		
		JPanel panelInputs = new JPanel();
		GridLayout inputsLayout = new GridLayout(4,2);
		inputsLayout.setVgap(5);
		inputsLayout.setHgap(1);
		panelInputs.setLayout(inputsLayout);
		panelInputs.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		panelInputs.add(labelNome);
		panelInputs.add(textoNome);
		
		panelInputs.add(labelCategoria);
		panelInputs.add(comboCategoria);
		
		panelInputs.add(labelDescricao);
		panelInputs.add(textoDescricao);
		
		panelInputs.add(labelPreco);
		panelInputs.add(textoPreco);
		
		container.add(panelInputs);

		botaoSalvarProduto = new JButton("Salvar");
		botaoLimparProduto = new JButton("Limpar");

		botaoSalvarProduto.setBounds(10, 145, 120, 20);
		botaoLimparProduto.setBounds(150, 145, 120, 20);
		
		JPanel panelButtons = new JPanel();
		
		panelButtons.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panelButtons.add(botaoSalvarProduto);
		panelButtons.add(botaoLimparProduto);
		
		container.add(panelButtons);

		tabela = new JTable();
		
		tabela.setFillsViewportHeight(true);
		
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador");
		modelo.addColumn("Nome");
		modelo.addColumn("Categoria");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botarApagarProduto = new JButton("Excluir");
		botaoEditarProduto = new JButton("Alterar");

		botarApagarProduto.setBounds(10, 500, 120, 20);
		botaoEditarProduto.setBounds(150, 500, 120, 20);

		container.add(botarApagarProduto);
		container.add(botaoEditarProduto);
		
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


		panel2.add(botarApagarProduto);
		panel2.add(botaoEditarProduto);
		container.add(panel2);

		setSize(800, 700);
		setVisible(true);
		setLayout(new GridLayout(4,1));
		setLocationRelativeTo(null);

		botaoSalvarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarProduto();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimparProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparProduto();
			}
		});

		botarApagarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditarProduto.addActionListener(new ActionListener() {
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
			String categoria = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			String descricao = (String) modelo.getValueAt(tabela.getSelectedRow(), 3);
			String preco = (String) modelo.getValueAt(tabela.getSelectedRow(), 4);

			// VERIFICAR COMO SERÁ REALIZADO O EDITAR
			
			Produto produto = new Produto(id,nome, categoria, descricao,preco);
			
			this.produtoController.alterar(produto);
			
			JOptionPane.showMessageDialog(this, "Produto alterado com sucesso");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		
		if (objetoDaLinha instanceof Integer) {
			
			Integer id = (Integer) objetoDaLinha;
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String categoria = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			String descricao = (String) modelo.getValueAt(tabela.getSelectedRow(), 3);
			String preco = (String) modelo.getValueAt(tabela.getSelectedRow(), 4);

			Produto produto = new Produto(id, nome, categoria, descricao, preco);
			
			this.produtoController.deletar(produto);
			
			modelo.removeRow(tabela.getSelectedRow());
			
			JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void preencherTabela() {
		List<Produto> produtos =  listarProdutos();
		try {
			for (Produto produto : produtos) {
				modelo.addRow(new Object[] { produto.getIdProduto() ,produto.getNome(), produto.getCategoria(), produto.getDescricao(), produto.getPreco()});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void salvarProduto() {
		if (!textoNome.getText().equals("") && !comboCategoria.getSelectedItem().equals("") && !textoDescricao.getText().equals("") && !textoPreco.getText().equals("")) {
			Produto produto = new Produto(textoNome.getText(), (String)comboCategoria.getSelectedItem(), textoDescricao.getText(), textoPreco.getText());
			this.produtoController.salvar(produto);
			JOptionPane.showMessageDialog(this, "Produto Salvo com sucesso!");
			this.limparProduto();
		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos devem ser informados.");
		}
	}

	private List<Produto> listarProdutos() {
		return this.produtoController.listar();
	}

	private void limparProduto() {
		this.textoNome.setText("");
		this.comboCategoria.setSelectedIndex(-1);
		this.textoDescricao.setText("");
		this.textoPreco.setText("");
	}

}
