package controller;

import javax.swing.JPanel;
import telas.Janela;

/**
 * Classe responsável por gerenciar a troca de telas.
 */
public class Navegador {

	private Janela janela;

	/**
	 * Construtor da classe.
	 * @param janela Referência ao JFrame.
	 */
	public Navegador(Janela janela) {
		this.janela = janela;
	}

	/**
	 * Método responsável por chamar o método da view que adiciona as telas ao painel principal.
	 * @param nome Nome do painel.
	 * @param tela Painel que será adicionado.
	 */
	public void adicionarPainel(String nome, JPanel tela) {
		this.janela.adicionarTela(nome, tela);
	}

	/**
	 * Método responsável por chamar o método da view que navega para outra tela.
	 * @param nome Nome da tela.
	 */
	public void navegarPara(String nome) {
		this.janela.mostrarTela(nome);
	}

	/**
	 * Método responsável por chamar o método da view que fecha a aplicação.
	 */
	public void sair() {
		this.janela.dispose();
	}
}
