package br.ies.main.banco.de.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ies.main.entidades.Pessoa;

public class BancoDeDadosPessoa extends GerenciadorBancoDeDados {

	private final String INSERT = "INSERT INTO pessoa(nome, senha) VALUES(?, ?) RETURNING id;";
	private final String INSERT_TIME = "UPDATE pessoa SET tempo = ? WHERE id = ?";
	private final String CHECAR = "SELECT id FROM pessoa WHERE nome = ? AND senha = ?;";
	private final String RETORNAR_NOMES = "SELECT nome FROM pessoa;";
	private final String PEGAR_TEMPO_DO_BANCO = "SELECT tempo FROM pessoa WHERE id = ?;";

	public void inserirPessoa(Pessoa pessoa) throws SQLException {
		Connection connection = obterConexao();
		PreparedStatement statement = connection.prepareStatement(INSERT);
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getSenha());
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		pessoa.setId(resultSet.getInt(1));
		statement.close();
		connection.close();
	}

	public void inserirMelhorTempo(Pessoa pessoa, long tempo) throws SQLException {
		Connection connection = obterConexao();

		PreparedStatement statement = connection.prepareStatement(PEGAR_TEMPO_DO_BANCO);
		statement.setInt(1, pessoa.getId());
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		boolean maior = resultSet.getInt(1) == 0;
		HashMap<Boolean, Runnable> listaDeAcoes = new HashMap<Boolean, Runnable>();

		listaDeAcoes.put(true, () -> {
			try {
				PreparedStatement statementInserirTempo = connection.prepareStatement(INSERT_TIME);
				statementInserirTempo.setLong(1, tempo);
				statementInserirTempo.setInt(2, pessoa.getId());
				statementInserirTempo.execute();
				System.out.println(tempo);
			} catch (SQLException e) {

			}
		});

		try {
			listaDeAcoes.get(maior).run();
		} catch (Exception e) {
			maior = tempo < resultSet.getInt(1);
			listaDeAcoes.get(maior).run();
		} finally {
			statement.close();
			connection.close();
		}

	}

	public void jaExistente(Pessoa pessoa) throws SQLException {
		Connection connection = obterConexao();
		PreparedStatement statement = connection.prepareStatement(CHECAR);
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getSenha());
		statement.execute();

		try {
			ResultSet resultSet = statement.getResultSet();
			resultSet.next();
			pessoa.setId(resultSet.getInt(1));
		} catch (SQLException e) {
			inserirPessoa(pessoa);
		}
	}

	public List<String> retornarTodosOsNomeDePessoas() throws SQLException {
		Connection connection = obterConexao();
		PreparedStatement statement = connection.prepareStatement(RETORNAR_NOMES);
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		List<String> listaDeNomesDePessoas = new ArrayList<String>();
		while (resultSet.next()) {
			listaDeNomesDePessoas.add(resultSet.getString(1));
		}
		return listaDeNomesDePessoas;
	}

}
