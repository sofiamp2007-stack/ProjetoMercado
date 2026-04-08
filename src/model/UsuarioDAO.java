package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

	// CREATE - Adiciona um novo usuário
	public void adicionarUsuario(Usuario usuario) {
		

		
		String sql = "INSERT INTO Usuarios(nome, CPF, senha, tipo) VALUES (?, ?, ?, ?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		
		try {
			
			conexao = dataBase.Conexão.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getCpf());
			pstm.setString(3, usuario.getSenha());
			pstm.setString(4, usuario.getTipo().name().toLowerCase()); 
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dataBase.Conexão.desconectar(conexao);}}
		
			
			
		    public Usuario buscarPorNomeECpf(String nome, String cpf) {
		    	String sql = "SELECT * FROM Usuarios WHERE nome = ? AND CPF = ?";
		        Connection conexao = null;
		        PreparedStatement pstm = null;
		        ResultSet rset = null;

		        Usuario usuario = null;

		        try {
		            conexao = dataBase.Conexão.conectar();
		            pstm = conexao.prepareStatement(sql);

		            pstm.setString(1, nome);
		            pstm.setString(2, cpf);

		            rset = pstm.executeQuery();

		            if (rset.next()) {
		                usuario = new Usuario();

		                usuario.setId(rset.getInt("id"));
		                usuario.setNome(rset.getString("nome"));
		                usuario.setCpf(rset.getString("CPF"));
		                usuario.setSenha(rset.getString("senha"));

		                Usuario.TipoUsuario tipo = Usuario.TipoUsuario.valueOf(
		                		rset.getString("tipo").toUpperCase());

		                usuario.setTipo(tipo);
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            dataBase.Conexão.desconectar(conexao);
		        }

		        return usuario;
			
	}
	
	// READ - Lista de todos os Usuários
			public List<Usuario> listarUsuarios(){
				String sql = "SELECT * FROM Usuarios";
				List<Usuario> usuarios = new ArrayList<>();
				Connection conexao = null;
				PreparedStatement pstm = null;
				ResultSet rset = null;
				
				try {
					conexao = dataBase.Conexão.conectar();
					pstm = conexao.prepareStatement(sql);
					rset = pstm.executeQuery();
					
					while(rset.next()) {
						Usuario usuario = new Usuario();
						usuario.setNome(rset.getString("nome"));
						usuario.setSenha(rset.getString("senha"));
						usuario.setCpf(rset.getString("cargo"));
					
						Usuario.TipoUsuario tipo = Usuario.TipoUsuario.valueOf(
							        rset.getString("tipoUsuario")); 

						usuario.setTipo(tipo);

						usuarios.add(usuario);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					dataBase.Conexão.desconectar(conexao);
				}
				return usuarios;
			}
			
			// UPDATE - Atualizar um usuário existente
		    public void atualizarUsuario(Usuario usuario) {
		        String sql = "UPDATE usuarios SET  nome = ?, cpf = ?, senha = ?, tipoUsuario = ?  WHERE id = ?";
		        Connection conexao = null;
		        PreparedStatement pstm = null;

		        try {
		            conexao = dataBase.Conexão.conectar();
		            pstm = conexao.prepareStatement(sql);
		            pstm.setString(1, usuario.getNome());
		            pstm.setString(2, usuario.getCpf());
		            pstm.setString(3, usuario.getSenha());
		            pstm.setString(4, usuario.getTipo().name()); 
		            pstm.setInt(5, usuario.getId()); 
		            
		            pstm.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		        	dataBase.Conexão.desconectar(conexao);
		        }
		    }
	
}
