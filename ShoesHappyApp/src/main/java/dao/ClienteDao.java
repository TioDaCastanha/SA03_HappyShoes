package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

/**
*
* Esta classe DAO permite operações de inserção, leitura, atualização e
* exclusão na tabela de 'usuario' do banco de dados.
*
*/

public class ClienteDao {
	// URL de conexão do banco de dados 'jdbc_servlet' do SGBD MySQL
	private String jdbcURL = "jdbc:mysql://localhost/happyshoes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	// Nome de usuário 'root' para acesso ao banco de dados do SGBD MySQL
	private String jdbcNomeUsuario = "root";
	// Senha do usuário 'root' para acesso ao banco de dados do SGBD MySQL
	private String jdbcSenha = "Ambic@tus2506";

	private static final String INSERIR_CLIENTE = "INSERT INTO cliente" + " (nome, endereco, modalidade) VALUES "
			+ " (?, ?, ?);";
	private static final String SELECIONAR_CLIENTE = "SELECT matricula, nome, endereco, modalidade FROM cliente WHERE matricula = ?";
	private static final String SELECIONAR_CLIENTES = "SELECT * FROM cliente";
	private static final String DELETAR_CLIENTE = "DELETE FROM cliente WHERE matricula = ?;";
	private static final String ATUALIZAR_CLIENTE = "UPDATE cliente SET nome = ?, endereco = ? , modalidade = ? WHERE matricula = ?;";

	public ClienteDao() {	
	}
	
	protected Connection getConnection() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(jdbcURL, jdbcNomeUsuario, jdbcSenha);
		} catch (SQLException erro) {
			erro.printStackTrace();
		} catch (ClassNotFoundException erro) {
			erro.printStackTrace();
		}
		return conexao;
	}
	
	public void inserirCliente(Cliente cliente) throws SQLException {
		// Fecha automaticamente a conexão após o uso
		try (Connection conexao = getConnection();
				PreparedStatement executarComando = conexao.prepareStatement(INSERIR_CLIENTE)) {
			// A matricula do cliente é omitido do comando, pois foi definido na tabela como autoincremento
			executarComando.setString(1, cliente.getNome());
			executarComando.setString(2, cliente.getEndereco());
			executarComando.setString(3, cliente.getModalidade());
			System.out.println(executarComando);
			executarComando.executeUpdate();
		} catch (SQLException erro) {
			printSQLException(erro);
		}
	}
	
	public Cliente selecionarCliente(int matricula) {
		Cliente cliente = null;
		// Etapa 1: estabelece a conexão
		try (Connection conexao = getConnection();
				// Etapa 2: cria o comando da instrução usando o objeto da conexão
				PreparedStatement executarComando = conexao.prepareStatement(SELECIONAR_CLIENTE);) {
			executarComando.setInt(1, matricula);
			System.out.println(executarComando);
			// Etapa 3: executa ou atualiza a query
			ResultSet resultado = executarComando.executeQuery();
			// Etapa 4: processa o objeto ResultSet
			while (resultado.next()) {
				String nome = resultado.getString("nome");
				String endereco = resultado.getString("endereco");
				String modalidade = resultado.getString("modalidade");
				cliente = new Cliente(matricula, nome, endereco, modalidade);
			}
		} catch (SQLException erro) {
			printSQLException(erro);
		}
		return cliente;
	}
	
	public List selecionarClientes() {
		List clientes = new ArrayList<>();
		// Código boilerplate
		// Etapa 1: estabelece a conexão
		try (Connection conexao = getConnection();
				// Etapa 2: cria o comando da instrução usando o objeto da conexão
				PreparedStatement executarComando = conexao.prepareStatement(SELECIONAR_CLIENTES);) {
			System.out.println(executarComando);
			// Etapa 3: executa ou atualiza a query
			ResultSet resultado = executarComando.executeQuery();
			// Etapa 4: processa o objeto ResultSet
			while (resultado.next()) {
				int matricula = resultado.getInt("matricula");
				String nome = resultado.getString("nome");
				String endereco = resultado.getString("endereco");
				String modalidade = resultado.getString("modalidade");
				clientes.add(new Cliente(matricula, nome, endereco, modalidade));
			}
		} catch (SQLException erro) {
			printSQLException(erro);
		}
		return clientes;
	}
	
	public boolean deletarCliente(int matricula) throws SQLException {
		boolean registroDeletado;
		try (Connection conexao = getConnection();
				PreparedStatement executarComando = conexao.prepareStatement(DELETAR_CLIENTE);) {
			executarComando.setInt(1, matricula);
			System.out.println(executarComando);
			registroDeletado = executarComando.executeUpdate() > 0;
		}
		return registroDeletado;
	}
	
	public boolean atualizarCliente(Cliente cliente) throws SQLException {
		boolean registroAtualizado;
		try (Connection connection = getConnection();
				PreparedStatement executarComando = connection.prepareStatement(ATUALIZAR_CLIENTE);) {
			executarComando.setString(1, cliente.getNome());
			executarComando.setString(2, cliente.getEndereco());
			executarComando.setString(3, cliente.getModalidade());
			executarComando.setInt(4, cliente.getMatricula());
			registroAtualizado = executarComando.executeUpdate() > 0;
		}
		return registroAtualizado;
	}
	
	
	private void printSQLException(SQLException erro) {
		for (Throwable e : erro) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("Estado do SQL: " + ((SQLException) e).getSQLState());
				System.err.println("Código de erro: " + ((SQLException) e).getErrorCode());
				System.err.println("Mensagem: " + e.getMessage());
				Throwable causa = erro.getCause();
				while (causa != null) {
					System.out.println("Causa: " + causa);
					causa = causa.getCause();
				}
			}
		}
	}
	
	
	
}
