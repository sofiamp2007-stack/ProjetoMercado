package telas;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class TelaCadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfSenha;

	/**
	 * Create the panel.
	 */
	
	private JButton btnCadastrar;
	private JRadioButton rdbSim;
	private JRadioButton rdbNao;
	
	public TelaCadastro() {
		setBackground(new Color(128, 128, 128));
		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Cadastro");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 24));
		add(lblNewLabel, "cell 2 1,alignx center,growy");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(182, 209, 175));
		add(panel, "cell 1 3 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][][grow][grow][grow][grow][grow]"));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		panel.add(lblNome, "cell 1 1");
		
		tfNome = new JTextField();
		panel.add(tfNome, "cell 3 1,growx");
		tfNome.setColumns(10);
		
		JLabel lbCPF = new JLabel("CPF");
		lbCPF.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		panel.add(lbCPF, "cell 1 3");
		
		tfCPF = new JTextField();
		panel.add(tfCPF, "cell 3 3,growx");
		tfCPF.setColumns(10);
		
		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		panel.add(lbSenha, "cell 1 5");
		
		tfSenha = new JTextField();
		panel.add(tfSenha, "cell 3 5,growx");
		tfSenha.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("É um administrador?");
		lblNewLabel_4.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		add(lblNewLabel_4, "cell 1 5,alignx center");
		
		rdbSim = new JRadioButton("Sim");
		rdbSim.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		rdbSim.setBackground(new Color(182, 209, 175));
		add(rdbSim, "cell 2 5,alignx center");
		
		rdbNao = new JRadioButton("Nao");
		rdbNao.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		rdbNao.setBackground(new Color(182, 209, 175));
		add(rdbNao, "cell 3 5,alignx center");
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbSim);
		grupo.add(rdbNao);


		rdbNao.setSelected(true);
		
		
		btnCadastrar = new JButton("Cadastrar"); 
		btnCadastrar.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		btnCadastrar.setBackground(new Color(182, 209, 175));
		add(btnCadastrar, "cell 3 7,growx,aligny top");

	}
	
	public void cadastrar(ActionListener acao) {
		btnCadastrar.addActionListener(acao);
	}

	public String getNome() {
		return tfNome.getText();
	}
	


	public String getCPF() {
		return tfCPF.getText();
	}

	public String getSenha() {
		return tfSenha.getText();
	}
	
	public boolean isAdministrador() {
	    return rdbSim.isSelected();
	}
	
	public void irParaLogin(ActionListener al) {
		btnCadastrar.addActionListener(al);
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
		tfCPF.setText("");
		tfSenha.setText("");
	}

}
