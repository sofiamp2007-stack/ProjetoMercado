package main;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;


import controller.CadastroProdutoController;
import controller.CadastroUsuarioController;

import controller.CompraController;

import controller.LoginController;
import controller.Navegador;

import model.ProdutoDAO;
import model.UsuarioDAO;
import telas.Janela;
import telas.TelaCadastro;
import telas.TelaCadastro_de_Produtos;
import telas.TelaCompra;
import telas.TelaLogin;


/**
 * Classe responsável por inicializar os elementos das 3 camadas: model, view e controller.
 * A execução do programa começa por aqui.
 */
public class Main {
	public static void main(String[] args) {
		
		UIManager.put("OptionPane.messageFont", new FontUIResource(
				new Font("Arial", Font.PLAIN, 18)
				));

		try {
		
			Janela janela = new Janela();
			Navegador navegador = new Navegador(janela);

		
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();

		
			TelaLogin telaLogin = new TelaLogin();
			TelaCadastro telaCadastro = new TelaCadastro();
			TelaCompra telaCompra = new TelaCompra();
			TelaCadastro_de_Produtos telaCadastroProduto = new TelaCadastro_de_Produtos();

		
			navegador.adicionarPainel("LOGIN", telaLogin);
			navegador.adicionarPainel("CADASTRO", telaCadastro);
			navegador.adicionarPainel("COMPRA", telaCompra);
			navegador.adicionarPainel("CADASTRO_PRODUTO", telaCadastroProduto);

			CompraController compraController = new CompraController(telaCompra, produtoDAO, navegador,telaLogin);
			LoginController loginController = new LoginController(telaLogin, usuarioDAO, navegador,compraController);
			CadastroUsuarioController cadastroUsuarioController = new CadastroUsuarioController(telaCadastro, usuarioDAO, navegador);
			CadastroProdutoController cadastroProdutoController = new CadastroProdutoController(telaCadastroProduto, produtoDAO, navegador,telaLogin);
			
		
			telaCompra.adicionarOuvinte(compraController);
			
		
			janela.setLocationRelativeTo(null);
			janela.setVisible(true);

	
			navegador.navegarPara("LOGIN");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

