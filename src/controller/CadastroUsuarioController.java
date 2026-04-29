package controller;

import java.awt.event.ComponentAdapter;

import model.Usuario;
import model.UsuarioDAO;
import telas.TelaCadastro;

public class CadastroUsuarioController  { 
private final TelaCadastro view;
private final UsuarioDAO model;
private final Navegador navegador;

/**
 * Construtor da classe
 * @param view Referência à tela que controla (TelaCadastro).
 * @param model Referência ao modelo de dados (CandidatoDAO).
 * @param navegador Referência ao elemento que faz a transição de telas.
 */
public CadastroUsuarioController(TelaCadastro view, UsuarioDAO model, Navegador navegador) {
	this.view = view;
	this.model = model;
	this.navegador = navegador;


	this.view.cadastrar(e -> {
		String nome = view.getNome().trim();
		String cpf= view.getCPF().trim();
		String senha = view.getSenha().trim();
		
		if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
			this.view.exibirMensagem("Erro", "Preencha todos os campos!", 0);
		}
		else if (nome.length() < 3) {
			this.view.exibirMensagem("Erro", "Nome muito curto!", 0);
		}

		else if (senha.length() < 4) {
			this.view.exibirMensagem("Erro", "Senha muito curta!", 0);
		}
		else if (!cpf.matches("\\d{11}")) {
			this.view.exibirMensagem("Erro", "CPF inválido!", 0);
		}
		else {
			
			Usuario.TipoUsuario tipo = view.isAdministrador()
                    ? Usuario.TipoUsuario.ADMIN
                    : Usuario.TipoUsuario.CLIENTE;

            Usuario usuario = new Usuario(nome, cpf, senha, tipo);
            this.model.adicionarUsuario(usuario);
        	this.view.limparFormulario();
			this.view.exibirMensagem("Sucesso", " Usuario Salvo", 1);
		}
		
		
	});
	
	view.irParaLogin(e -> {
		navegador.navegarPara("LOGIN");
	});

}
}
