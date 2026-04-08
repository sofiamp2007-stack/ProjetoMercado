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

		    if (nome.trim().isEmpty() || cpf.trim().isEmpty()) {
		        view.exibirMensagem("Erro", "Preencha todos os campos!", 0);
		        return;
		    }

		    Usuario usuario = model.buscarPorNomeECpf(nome, cpf);

		  
		    if (usuario == null) {
		        view.exibirMensagem("Erro", "Usuário não encontrado!", 0);
		        return;
		    }

		    Usuario.TipoUsuario tipo = usuario.getTipo();

		    if (tipo == Usuario.TipoUsuario.ADMIN) {
		        navegador.navegarPara("CADASTRO PRODUTOS");
		    } else {
		        navegador.navegarPara("COMPRA");
		    }
		});
		
		view.irParaCadastro(e -> {
			navegador.navegarPara("CADASTRO");
		});

	}}
