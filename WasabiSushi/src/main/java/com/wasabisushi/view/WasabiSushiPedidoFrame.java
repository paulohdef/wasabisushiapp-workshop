package com.wasabisushi.view;

import com.wasabisushi.persistence.entity.Cliente;
import com.wasabisushi.persistence.entity.Pedido;
import com.wasabisushi.business.controller.PedidoController;
import com.wasabisushi.business.controller.ProdutoController;
import com.wasabisushi.persistence.entity.Produto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WasabiSushiPedidoFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JButton botaoComprar;
	
	private Container container;
	
	private JMenuBar barra;
	
	private JMenu m1, m2, m3, m4;
	
	private JMenuItem m1x, m2x, m3x, m4x;
	
	private JTable tabela;
	
	private DefaultTableModel modelo;
	
	private List<Produto> listaTabela = new ArrayList<>();

	private ProdutoController produtoNegocio;
	
	private PedidoController pedidoController;

	public WasabiSushiPedidoFrame(Cliente clienteSelecionado) {
		
		super("Cliente: " + clienteSelecionado.getNome());
		produtoNegocio = new ProdutoController();
		pedidoController = new PedidoController();
		container = getContentPane();
		barra = new JMenuBar();

		Map<String, List<Produto>> produtosMap = obterProdutosPorCategoria();
		for (Map.Entry<String, List<Produto>> entry : produtosMap.entrySet()) {
			String categoria = entry.getKey();
			List<Produto> produtos = entry.getValue();
			JMenu menuCategoria = new JMenu(categoria);
			barra.add(menuCategoria);
			for (Produto produto : produtos) {
				JMenuItem item = new JMenuItem(produto.getNome());
				menuCategoria.add(item);
				item.addActionListener( event -> {
					adicionaNaTabela(produto);
					limparTabela();
					preencherTabela();
				});
			}
		}

		tabela = new JTable();

		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Nome do Produto");
		modelo.addColumn("Categoria");
		modelo.addColumn("Descrição");
		modelo.addColumn("Valor");

//		tabela.setBounds(5, 10, 490, 340);
//		tabela.setBounds(5, 10, 490, 340);
		botaoComprar = new JButton("Comprar");
		botaoComprar.addActionListener(event ->{
			confirmarPedido(clienteSelecionado);
		});
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.addActionListener(event -> {
			listaTabela.clear();
			limparTabela();
		});


		tabela.setFillsViewportHeight(true);

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

		panel2.add(botaoComprar);
		panel2.add(botaoLimpar);
		container.add(panel2);
//		container.add(tabela);

		setJMenuBar(barra);
		
		
		setSize(500, 400);
		setLayout(new GridLayout(2,1));
		setVisible(true);
		setLocationRelativeTo(null);
	}

	
	private void limparTabela() {
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();
	}
	
	private void confirmarPedido(Cliente clienteSelecionado) {


		for (Produto produto : listaTabela) {
		
		if (JOptionPane.showConfirmDialog(null,"Pedido:"+ produto.getNome()+ "\n"+ "Valor Total: "+ produto.getPreco()+ "\n"+ "Entrega:"+ clienteSelecionado.getEndereco()+"\n"+
				"CONFIRMA A SUA COMPRA? \n" , 
				"Senhor "+ clienteSelecionado.getNome(),
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			
			//INSERIR PEDIDO
//			Cliente clienteDoPedido = new Cliente();
			
//			Produto produtoDoPedido = new Produto();
			
			Pedido pedido = new Pedido(clienteSelecionado, produto, produto.getPreco());
			
			this.pedidoController.adiciona(pedido);
			
		    JOptionPane.showMessageDialog(null, "Pedido confirmado!");

		}else {
		    // no option
	}	
		}
}

	private void preencherTabela() {
		try {
			for (Produto produto : listaTabela) {
				modelo.addRow(new Object[] { produto.getNome(), produto.getCategoria(), produto.getDescricao(), "R$ " + produto.getPreco() });
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void adicionaNaTabela(Produto produto) {
		listaTabela.add(produto);
	}
	
	private void removeDaTabela(Produto produto) {
		listaTabela.remove(produto);
	}


	private Map<String, List<Produto>> obterProdutosPorCategoria() {
			return produtoNegocio.getAllProdutoByCategoria();
	}
}
