package controller;

import model.Usuario;
import model.UsuarioDAO;
import telas.TelaLogin;


public class LoginController {
	
	@SuppressWarnings("unused")
	private TelaLogin view;
	@SuppressWarnings("unused")
	private Navegador navegador;
	private UsuarioDAO model;

	public LoginController(TelaLogin view, UsuarioDAO model, Navegador navegador)  {
		this.view = view;
		this.navegador = navegador;
		this.model =model;

	
		view.entrar(e -> {
			String nome = view.getNome();
			String cpf = view.getCpf();
			
			if(!nome.equals("") && !cpf.equals("")) {
				   Usuario usuario = model.buscarPorNomeECpf(nome, cpf);
				   
				   Usuario.TipoUsuario tipo = usuario.getTipo();
			
			 // direciona para a tela de acordo com a escolha admin ou cliente 
				if (tipo == Usuario.TipoUsuario.ADMIN) { 
					this.navegador.navegarPara("CADASTRO_PRODUTO"); } else { this.navegador.navegarPara("COMPRA"); } }
			else { this.view.exibirMensagem("Erro", "Preencha todos os campos!", 0); } 
			});
			
			
	view.irParaCadastro(e -> {
		navegador.navegarPara("CADASTRO");
	});

		
	

	}
}
