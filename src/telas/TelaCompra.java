package telas;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Produto;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCompra extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel jtable_model;
	private JButton btnAdicionarCarrinho;
	private JButton btnEmitirNota;
	private JButton btnVoltar;
	private JLabel lbTotal;
	private JTextArea txtDetalhes;


	/**
	 * Create the panel.
	 */
	public TelaCompra() {
		setBackground(new Color(128, 128, 128));
		setLayout(new MigLayout("", "[grow 5][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][][][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Compras");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 25));
		add(lblNewLabel, "cell 0 1 4 1,alignx center,aligny center");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		add(scrollPane, "cell 0 3 4 1,grow");
		
		table = new JTable();
		table.setFont(new Font("Sitka Text", Font.BOLD, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Pre\u00E7o", "Quantidade"
			}
		));
		scrollPane.setViewportView(table);
		
		this.jtable_model = (DefaultTableModel) this.table.getModel();
		
		btnAdicionarCarrinho = new JButton("Adicionar ao carrinho");
		btnAdicionarCarrinho.setBackground(new Color(182, 209, 175));
		btnAdicionarCarrinho.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		add(btnAdicionarCarrinho, "cell 0 5,growx");
		
			btnEmitirNota = new JButton("Emitir Nota");
			btnEmitirNota.setBackground(new Color(182, 209, 175));
			btnEmitirNota.setFont(new Font("Sitka Text", Font.PLAIN, 15));
			add(btnEmitirNota, "cell 1 5,growx");
			
						btnVoltar = new JButton("Voltar");
						btnVoltar.setBackground(new Color(182, 209, 175));
						btnVoltar.setFont(new Font("Sitka Text", Font.PLAIN, 15));
						add(btnVoltar, "cell 2 5,growx");

				JLabel lblDetalhes = new JLabel("Detalhes do Produto:");
				lblDetalhes.setFont(new Font("Sitka Text", Font.BOLD, 14));
				lblDetalhes.setForeground(Color.WHITE);
				
				add(lblDetalhes, "flowx,cell 0 8 4 1");

				txtDetalhes = new JTextArea();
				txtDetalhes.setEditable(false);
				txtDetalhes.setBackground(new Color(182, 209, 175));
				txtDetalhes.setFont(new Font("Sitka Text", Font.PLAIN, 14));
				add(txtDetalhes, "cell 0 9 4 1,grow");
				
					lbTotal = new JLabel("TOTAL: R$0,00");
					lbTotal.setForeground(new Color(255, 255, 255));
					lbTotal.setFont(new Font("Sitka Text", Font.BOLD, 12));
					add(lbTotal, "cell 2 8,alignx left");
	
		}
	
	public void setTabelaProdutos(List<Produto> produtos){
	    jtable_model.setRowCount(0); 

	    for (Produto p : produtos) {
	        jtable_model.addRow(new Object[]{
	            p.getId(),
	            p.getNome(),
	            p.getPreco(),
	            p.getQuantidadeEstoque()
	        });
	    }
	

	
	this.table.setModel(this.jtable_model);
	}
	
	public int getSelectedRow() {
		return this.table.getSelectedRow();
	}
	
	public String getValorNaLinha(int linha, int coluna) {
		if (linha >= 0 && coluna >= 0) {
			return this.table.getValueAt(linha, coluna).toString();
		}
		return null;
	}

	
		public void addTableSelectionListener(ListSelectionListener listener) {
			this.table.getSelectionModel().addListSelectionListener(listener);
		}

		public void setDetalhes(String detalhes) {
			this.txtDetalhes.setText(detalhes);
		}

		public void adicionarCarrinho(ActionListener listener) {
		    btnAdicionarCarrinho.addActionListener(listener);
		}
	
		public void EmitirNota(ActionListener listener) {
			btnEmitirNota.addActionListener(listener);
		}

		public void voltar(ActionListener listener) {
			btnVoltar.addActionListener(listener);
		}
		
		public void atualizarTotal(double total) {
		    lbTotal.setText(String.format("TOTAL: R$ %.2f", total));
		}

		public void limparCarrinho() {
			lbTotal.setText("TOTAL: R$ 0,00");
		}
	
}
