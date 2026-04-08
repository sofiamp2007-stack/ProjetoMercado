package telas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import model.Produto;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.math.BigDecimal;
import java.util.List;

public class TelaCadastro_de_Produtos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabela;
	private JTextField tfNome;
	private JTextField tfQuantidade;
	private JTextField tfPreco;
	private JButton btnCadastro; 
	private JButton btnEditar; 
	private JButton btnExcluir; 

	private DefaultTableModel jtable_model;
	private JButton btnVoltar;

	/**
	 * Create the panel.
	 */
	public TelaCadastro_de_Produtos() {
		setBackground(new Color(128, 128, 128));
		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Produtos");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		add(lblNewLabel, "flowx,cell 1 1 7 1,alignx center");
		
		JLabel lbNomeProduto = new JLabel("Nome do produto:");
		lbNomeProduto.setForeground(new Color(255, 255, 255));
		lbNomeProduto.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		add(lbNomeProduto, "cell 1 3");
		
		tfNome = new JTextField();
		tfNome.setBackground(new Color(182, 209, 175));
		add(tfNome, "cell 3 3,growx");
		tfNome.setColumns(10);
		
		JLabel lbPreco = new JLabel("Preço:");
		lbPreco.setForeground(new Color(255, 255, 255));
		lbPreco.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		add(lbPreco, "cell 5 3");
		
		tfPreco = new JTextField();
		tfPreco.setBackground(new Color(182, 209, 175));
		add(tfPreco, "cell 6 3,growx");
		tfPreco.setColumns(10);
		
		JLabel lbQuantidade = new JLabel("Quantidade:");
		lbQuantidade.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lbQuantidade.setForeground(new Color(255, 255, 255));
		add(lbQuantidade, "cell 1 5");
		
		tfQuantidade = new JTextField();
		tfQuantidade.setBackground(new Color(182, 209, 175));
		add(tfQuantidade, "cell 3 5,growx");
		tfQuantidade.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 7 6 1,grow");
		
		tabela = new JTable();
		tabela.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Id", "Nome Produto: ", "Pre\u00E7o", "Quantidade:"
			}
		));
		
		this.jtable_model = (DefaultTableModel) this.tabela.getModel();
		scrollPane.setViewportView(tabela);
		
		btnExcluir= new JButton("Excluir");
		btnExcluir.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		btnExcluir.setBackground(new Color(182, 209, 175));
		add(btnExcluir, "cell 1 9,growx");
		
		btnEditar= new JButton("Editar");
		btnEditar.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		btnEditar.setBackground(new Color(182, 209, 175));
		btnEditar.setForeground(new Color(0, 0, 0));
		add(btnEditar, "cell 2 9 2 1,growx");
		
		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		btnCadastro.setBackground(new Color(182, 209, 175));
		add(btnCadastro, "cell 4 9 2 1,growx");
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		btnVoltar.setBackground(new Color(182, 209, 175));
		add(btnVoltar, "cell 6 9,alignx right,growy");

	}
	
	public void cadastro(ActionListener acao) {
		btnCadastro.addActionListener(acao);
	}
	
	public void voltar(ActionListener acao) {
		btnVoltar.addActionListener(acao);
	}
	
	
	public void editar(ActionListener acao) {
		btnEditar.addActionListener(acao);
	}
	
	public void excluir(ActionListener acao) {
		btnExcluir.addActionListener(acao);
	}
	
	public String getNome() {
		return tfNome.getText();
	}


	public int getQuantidade() {
		 return Integer.parseInt(tfQuantidade.getText());
	}
	
	

	public BigDecimal getPreco() {
	    return new BigDecimal(tfPreco.getText());
	}
	
	public void setTabelaProdutos(List<Produto> produto){
		int linha = 0;

		for(int i=0; i<produto.size(); i++){
			Produto p= produto.get(i);



		    this.jtable_model.insertRow(linha, new Object[] {
		        p.getId(),
		        p.getNome(),
		        p.getPreco(),
		        p.getQuantidadeEstoque()
		    });

		    linha++;
			}
		

		this.tabela.setModel(this.jtable_model);
	}
	
	public void limpar() {
		this.jtable_model.setRowCount(0);
		this.tfNome.setText("");
		this.tfQuantidade.setText("");
		this.tfPreco.setText("");
	}

	public void preencherFormulario(Produto p) {
	    this.tfNome.setText(p.getNome());
	    this.tfPreco.setText(p.getPreco().toString());
	    this.tfQuantidade.setText(String.valueOf(p.getQuantidadeEstoque()));
	}
	
	public int getSelectedRow() {
		return this.tabela.getSelectedRow();
	}
	
	public String getValorNaLinha(int linha, int coluna) {
		if (linha >= 0 && coluna >= 0) {
			return this.tabela.getValueAt(linha, coluna).toString();
		}
		return null;
	}
	
	public void aoExibir(Runnable acao) {
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				acao.run();
			}
		});
	}

public void addTableSelectionListener(ListSelectionListener listener) {
	this.tabela.getSelectionModel().addListSelectionListener(listener);
}


public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
	JOptionPane.showMessageDialog(
			null,
			mensagem,
			titulo,
			tipoMensagem
			);}

public void limparFormulario() {
	tfNome.setText("");
	tfQuantidade.setText("");
	tfPreco.setText("");
}

}
