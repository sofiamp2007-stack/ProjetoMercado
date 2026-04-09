package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produto;
import model.ProdutoDAO;
import model.Usuario;
import telas.TelaCompra;
import telas.TelaLogin;

public class CompraController extends ComponentAdapter {
    private final ProdutoDAO model;
    private final Navegador navegador;
    private TelaCompra view;
    private Usuario usuarioLogado;
    private List<Produto> carrinho = new ArrayList<>();
    private TelaLogin telaLogin;
    
    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;

    }


    public CompraController(TelaCompra view, ProdutoDAO model, Navegador navegador,TelaLogin telaLogin) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;
        this.telaLogin = telaLogin;

        this.carregarProdutos();

        this.view.adicionarCarrinho(e -> adicionarAoCarrinho());
        this.view.EmitirNota(e -> emitirNota());
        this.view.voltar(e -> {
        	   carrinho.clear();
               view.limparTela();
               view.setDetalhes("");
               telaLogin.limparCampos(); 
               navegador.navegarPara("LOGIN");
        });

        this.view.addTableSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                exibirDetalhes();
            }
        });
    }

	@Override
	public void componentShown(ComponentEvent e) {
		this.carregarProdutos();
	}

    private void exibirDetalhes() {
        int linha = view.getSelectedRow();
        if (linha != -1) {
            String nome = view.getValorNaLinha(linha, 1);
            String preco = view.getValorNaLinha(linha, 2);
            String estoque = view.getValorNaLinha(linha, 3);

            String detalhes = String.format(
                "Produto: %s\nPreço: R$ %s\nEstoque Disponível: %s unidades",
                nome, preco, estoque
            );
            view.setDetalhes(detalhes);
        }
    }

    private void carregarProdutos() {
        List<Produto> produtos = model.listarProdutos();
        for (Produto p : produtos) {
        	p.exibir();
        }
        	
        view.setTabelaProdutos(produtos);
    }

    private void adicionarAoCarrinho() {
        int linha = view.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto!");
            return;
        }

        try {
            int id = Integer.parseInt(view.getValorNaLinha(linha, 0));
            String nome = view.getValorNaLinha(linha, 1);
            BigDecimal preco = new BigDecimal(view.getValorNaLinha(linha, 2));
            int estoqueAtual = Integer.parseInt(view.getValorNaLinha(linha, 3));

            // Contar quantas unidades deste produto já estão no carrinho
            int noCarrinho = 0;
            for (Produto p : carrinho) {
                if (p.getId() == id) {
                    noCarrinho++;
                }
            }
            
            //ve se o que ta no carrinho é maior que do estoque
            if (noCarrinho >= estoqueAtual) {
                JOptionPane.showMessageDialog(null, "Quantidade solicitada excede o estoque disponível!");
                return;
            }

          
            Produto p = new Produto(id, nome, preco, 1);
            carrinho.add(p);
            
            calcularTotal();
            JOptionPane.showMessageDialog(null, nome + " adicionado ao carrinho!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto.");
        }
    }

    private void emitirNota() {
        if (carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O carrinho está vazio!");
            return;
        }

        StringBuilder nota = new StringBuilder();
        nota.append(" NOTA FISCAL\n");
        if (usuarioLogado != null) {
            nota.append("Cliente: " + usuarioLogado.getNome() +"\n");
            nota.append("CPF:  " +usuarioLogado.getCpf() +"\n");
            nota.append("-----------\n");
        }
        double total = 0;
        
        try {
            for (Produto p : carrinho) {
                nota.append(String.format("%s - R$ %.2f\n", p.getNome(), p.getPreco()));
                total += p.getPreco().doubleValue();
                
              
                Produto produtoNoBanco = model.buscarPorId(p.getId());
                if (produtoNoBanco != null) {
                    int novoEstoque = produtoNoBanco.getQuantidadeEstoque() - 1;
                    produtoNoBanco.setQuantidadeEstoque(novoEstoque);
                    model.atualizarProduto(produtoNoBanco);
                }
            }
            
            nota.append("-----------\n");
            nota.append(String.format("TOTAL: R$ %.2f", total));

            JOptionPane.showMessageDialog(null, nota.toString(), "Nota Fiscal", JOptionPane.INFORMATION_MESSAGE);
            
            carrinho.clear();
            view.limparCarrinho();
            view.setDetalhes("");
            carregarProdutos(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar a baixa de estoque: " + e.getMessage());
        }
    }

    private void calcularTotal() {
        double total = 0;
        for (Produto p : carrinho) {
            total += p.getPreco().doubleValue();
        }
        view.atualizarTotal(total);
    }
}
