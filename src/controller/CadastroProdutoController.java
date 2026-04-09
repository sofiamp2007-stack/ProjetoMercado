package controller;

import model.Produto;
import model.ProdutoDAO;
import model.Usuario;

import model.UsuarioDAO;

import telas.TelaCadastro_de_Produtos;
import telas.TelaLogin;

import java.awt.event.ComponentEvent;
import java.math.BigDecimal;
import java.util.List;


/**
 * Classe responsável pela comunicação entre a view (TelaCadastro) e o model (candidatoDAO).
 */
public class CadastroProdutoController {
	private final TelaCadastro_de_Produtos view;
	private final ProdutoDAO model;
	@SuppressWarnings("unused")
	private final Navegador navegador;
	private TelaLogin telaLogin;

	/**
	 * Construtor da classe
	 * @param view Referência à tela que controla (TelaCadastro).
	 * @param model Referência ao modelo de dados (CandidatoDAO).
	 * @param navegador Referência ao elemento que faz a transição de telas.
	 */
	public CadastroProdutoController(TelaCadastro_de_Produtos view, ProdutoDAO model, Navegador navegador, TelaLogin telaLogin) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		this.telaLogin = telaLogin;

		  // Seleção da tabela
        this.view.addTableSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleTableSelection();
            }
        });
        
        this.view.cadastro(e -> cadastrarProduto());
        this.view.editar(e -> editarProduto());
        this.view.excluir(e -> excluirProduto());
        
	        this.view.voltar(e -> {
	            this.view.limparFormulario();
	            telaLogin.limparCampos(); 
	            this.navegador.navegarPara("LOGIN");
	        });

	        this.view.aoExibir(() -> inicializarTabela());
	    }
	
	
        
	
    


 
    public void componentShown(ComponentEvent e) {
        this.inicializarTabela();
    }


    private void inicializarTabela() {
        List<Produto> produtos = this.model.listarProdutos();
        this.view.limpar();
        this.view.setTabelaProdutos(produtos);
    }

    
    private void handleTableSelection() {
        int linhaSelecionada = view.getSelectedRow();

        if (linhaSelecionada != -1) {
            String nome = view.getValorNaLinha(linhaSelecionada, 1); // coluna do nome

            Produto selecionado = model.buscarPorNome(nome);

      
            this.view.preencherFormulario(selecionado);
        }
        
     
    }
    
    private void cadastrarProduto() {
        try {
            String nome = view.getNome();
            BigDecimal preco = view.getPreco();
            int quantidade = view.getQuantidade();

            if (nome == null || nome.trim().isEmpty()) {
                view.exibirMensagem("Erro", "Digite o nome!", 0);
                return;
            }

            Produto p = new Produto(nome, preco, quantidade);

            model.adicionarProduto(p);

            inicializarTabela();
            view.limparFormulario();

        } catch (Exception e) {
            view.exibirMensagem("Erro", "Digite um preço válido!", 0);
        }
    }

   
    private void editarProduto() {
        int linha = view.getSelectedRow();

        if (linha != -1) {
            try {
                int id = Integer.parseInt(view.getValorNaLinha(linha, 0));

                Produto p = new Produto(
                        id,
                        view.getNome(),
                        view.getPreco(),
                        view.getQuantidade()
                );

                model.atualizarProduto(p);

                inicializarTabela();
                view.limparFormulario();

            } catch (Exception e) {
                view.exibirMensagem("Erro", "Dados inválidos!", 0);
            }
        } else {
            view.exibirMensagem("Erro", "Selecione um produto!", 0);
        }
    }

    
    private void excluirProduto() {
        int linha = view.getSelectedRow();

        if (linha != -1) {
            int id = Integer.parseInt(view.getValorNaLinha(linha, 0));

            model.deletarProduto(id);

            inicializarTabela();
            view.limparFormulario();

        } else {
            view.exibirMensagem("Erro", "Selecione um produto!", 0);
        }
    }
    



}