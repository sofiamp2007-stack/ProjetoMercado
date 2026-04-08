package telas;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JPasswordField tfSenha;
	private JButton btnEntrar;
	private JButton btnCadastro; 

	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setBackground(new Color(128, 128, 128));
		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Seja Bem-vinda! ");
		lblNewLabel.setBackground(new Color(182, 209, 175));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 25));
		add(lblNewLabel, "cell 1 1,alignx left,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaLogin.class.getResource("/Imagens/Pronta4.png")));
		add(lblNewLabel_4, "cell 3 1");
		
		JLabel lblNewLabel_3 = new JLabel("Faça o login a baixo:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		add(lblNewLabel_3, "cell 1 2,alignx left,aligny center");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(182, 209, 175));
		add(panel, "cell 1 4 3 4,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		panel.add(lbNome, "cell 0 0,alignx center,aligny center");
		
		tfNome = new JTextField();
		panel.add(tfNome, "cell 1 0,growx");
		tfNome.setColumns(10);
		
		JLabel lbCpf = new JLabel("CPF:");
		lbCpf.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		panel.add(lbCpf, "cell 0 1,alignx center,aligny center");
		
			tfCpf = new JTextField();
			panel.add(tfCpf, "cell 1 1,growx");
			tfCpf.setColumns(10);

			JLabel lbSenha = new JLabel("Senha:");
			lbSenha.setFont(new Font("Sitka Text", Font.PLAIN, 11));
			panel.add(lbSenha, "cell 0 2,alignx center,aligny center");

			tfSenha = new JPasswordField();
			panel.add(tfSenha, "cell 1 2,growx");

			btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		btnEntrar.setBackground(new Color(182, 209, 175));
		add(btnEntrar, "cell 1 9,growx");
		
		btnCadastro = new JButton("Solicitar Cadastro");
		btnCadastro.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		btnCadastro.setBackground(new Color(182, 209, 175));
		add(btnCadastro, "cell 3 9,growx");
	}
		
		
		public void entrar(ActionListener al) {
			btnEntrar.addActionListener(al);
		}

		public void irParaCadastro(ActionListener al) {
			btnCadastro.addActionListener(al);
		}

		public String getNome() {
			return tfNome.getText();
		}

			public String getCpf() {
				return tfCpf.getText();
			}

			public String getSenha() {
				return new String(tfSenha.getPassword());
			}

			public void limparCampos() {
				tfNome.setText("");
				tfCpf.setText("");
				tfSenha.setText("");
			}
			
			public void exibirMensagem(String titulo, String mensagem, int tipo) {
			JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
		}
	
	
	}


