package telas;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CadastroProdutoController;
import controller.CadastroUsuarioController;
import controller.CompraController;
import controller.LoginController;
import controller.Navegador;
import model.ProdutoDAO;
import model.UsuarioDAO;

public class Janela extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Janela janela  = new Janela();
				janela.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Janela() throws FontFormatException, IOException {
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 675);

		
		this.cardLayout = new CardLayout();

		this.contentPane = new JPanel(this.cardLayout);
		this.contentPane.setPreferredSize(new Dimension(600, 675));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);

		
		Navegador navegador = new Navegador(this);

		
		TelaLogin telaLogin = new TelaLogin();
		adicionarTela("LOGIN", telaLogin);

		
		TelaCadastro telaCadastro = new TelaCadastro();
		adicionarTela("CADASTRO", telaCadastro);
		
		
		TelaCompra telaCompra= new TelaCompra();
		adicionarTela("COMPRA", telaCompra);
		
		TelaCadastro_de_Produtos telaCadastroProduto=  new 	TelaCadastro_de_Produtos ();
		adicionarTela("CADASTRO_PRODUTO", telaCadastroProduto);

		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
       
		new LoginController(telaLogin,usuarioDAO, navegador);
	
		new CadastroUsuarioController(telaCadastro, usuarioDAO, navegador);
		
			new CompraController(telaCompra, produtoDAO, navegador);
	
			new CadastroProdutoController(telaCadastroProduto, produtoDAO, navegador);
		
		mostrarTela("LOGIN");
	}

	public void adicionarTela(String nome, JPanel tela) {
		this.contentPane.add(tela, nome);
	}

	public void mostrarTela(String nome) {
		this.cardLayout.show(this.contentPane, nome);
		this.pack();
	}
}