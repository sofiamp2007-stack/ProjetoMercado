package model;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	public class ProdutoDAO {

		// CREATE - Adiciona um novo usuário
		public void adicionarProduto(Produto produto)  {
			String sql = "INSERT INTO produtos(nome_produto, preco, quantidade) VALUES (?, ?, ?)";
			Connection conexao = null;
			PreparedStatement pstm = null;
			
			try {
				
				conexao = dataBase.Conexão.conectar();
				pstm = conexao.prepareStatement(sql);
				pstm.setString(1, produto.getNome());
				pstm.setBigDecimal(2, produto.getPreco());
			    pstm.setInt(3, produto.getQuantidadeEstoque());
			    
			    pstm.executeUpdate(); 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dataBase.Conexão.desconectar(conexao);
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		// READ - Lista de todos os Usuários
				public List<Produto> listarProdutos(){
					String sql = "SELECT * FROM Produtos";
					List<Produto> produtos = new ArrayList<>();
					Connection conexao = null;
					PreparedStatement pstm = null;
					ResultSet rset = null;
					
					try {
						conexao = dataBase.Conexão.conectar();
						pstm = conexao.prepareStatement(sql);
						rset = pstm.executeQuery();
						
						
						while (rset.next()) {
						    Produto produto = new Produto(
						        rset.getInt("id"),
						        rset.getString("nome_produto"),
						        rset.getBigDecimal("preco"),
						        rset.getInt("quantidade")
						    );

						    produtos.add(produto);
						
			            }
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						dataBase.Conexão.desconectar(conexao);
					}
					   return produtos;
				}
				
				// UPDATE - Atualizar um usuário existente
			    public void atualizarProduto(Produto produto) {
			    	String sql = "UPDATE produtos SET nome_produto = ?, preco = ?, quantidade = ? WHERE id = ?";
			        Connection conexao = null;
			        PreparedStatement pstm = null;
  
			        try {
			            conexao = dataBase.Conexão.conectar();
			            pstm = conexao.prepareStatement(sql);

			            pstm.setString(1, produto.getNome());
			            pstm.setBigDecimal(2, produto.getPreco());
			            pstm.setInt(3, produto.getQuantidadeEstoque());
			            pstm.setInt(4, produto.getId());

			            pstm.executeUpdate();
			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			        	dataBase.Conexão.desconectar(conexao);
			        }
			    }
			    
			    public Produto buscarPorNome(String nome) {
			        String sql = "SELECT * FROM produtos WHERE nome_produto = ? LIMIT 1";

			        Connection conexao = null;
			        PreparedStatement pstm = null;
			        ResultSet rset = null;

			        try {
			            conexao = dataBase.Conexão.conectar();
			            pstm = conexao.prepareStatement(sql);

			            pstm.setString(1, nome); 
			            rset = pstm.executeQuery();

			            if (rset.next()) {
			                return new Produto(
			                    rset.getInt("id"),
			                    rset.getString("nome_produto"),
			                    rset.getBigDecimal("preco"),
			                    rset.getInt("quantidade")
			                );
			            }

			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			            dataBase.Conexão.desconectar(conexao);
			        }

			        return null;
			    }
			    
			    public Produto buscarPorId(int id) {
			        String sql = "SELECT * FROM produtos WHERE id = ?";
			        Connection conexao = null;
			        PreparedStatement pstm = null;
			        ResultSet rset = null;
			        try {
			            conexao = dataBase.Conexão.conectar();
			            pstm = conexao.prepareStatement(sql);
			            pstm.setInt(1, id);
			            rset = pstm.executeQuery();
			            if (rset.next()) {
			                return new Produto(
			                    rset.getInt("id"),
			                    rset.getString("nome_produto"),
			                    rset.getBigDecimal("preco"),
			                    rset.getInt("quantidade")
			                );
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			            dataBase.Conexão.desconectar(conexao);
			        }
			        return null;
			    }
			    
			    public void deletarProduto(int id) {
			        String sql = "DELETE FROM produtos WHERE id = ?";

			        Connection conexao = null;
			        PreparedStatement pstm = null;

			        try {
			            conexao = dataBase.Conexão.conectar();
			            pstm = conexao.prepareStatement(sql);

			            pstm.setInt(1, id);
			            pstm.executeUpdate();

			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			            dataBase.Conexão.desconectar(conexao);
			        }
			    }
		
	

    
}