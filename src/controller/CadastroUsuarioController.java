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
		String nome = view.getNome();
		String cpf= view.getCPF();
		String senha = view.getSenha();
		
		if(!nome.equals("") &&!cpf.equals("") && !senha.equals("")) {
			
			
			Usuario.TipoUsuario tipo = view.isAdministrador()
                    ? Usuario.TipoUsuario.ADMIN
                    : Usuario.TipoUsuario.CLIENTE;

			
			
            Usuario usuario = new Usuario(nome, cpf, senha, tipo);

             this.model.adicionarUsuario(usuario);

        	this.view.limparFormulario();
			this.view.exibirMensagem("Sucesso", " Usuario Salvo", 1);
		}
		
		else {
			this.view.exibirMensagem("Erro", "Preencha todos os campos!", 0);
		}
		
	});
	
	view.irParaLogin(e -> {
		navegador.navegarPara("LOGIN");
	});



            this.view.limparFormulario();



}
}
