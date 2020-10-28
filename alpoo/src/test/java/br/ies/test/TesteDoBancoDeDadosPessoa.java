package br.ies.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import br.ies.main.banco.de.dados.BancoDeDadosPessoa;
import br.ies.main.banco.de.dados.GerenciadorBancoDeDados;
import br.ies.main.entidades.Pessoa;

public class TesteDoBancoDeDadosPessoa {

	@Before
	public void setup() throws SQLException {
		new GerenciadorBancoDeDados().limparBanco();
	}

	@Test
	public void isnerindoPessoaNoBanco() throws SQLException {
		BancoDeDadosPessoa banco = new BancoDeDadosPessoa();
		Pessoa pessoa = new Pessoa("Vinicius", "123");
		banco.inserirPessoa(pessoa);
		assertEquals("Vinicius", pessoa.getNome());
		assertNotNull(pessoa.getId());
	}
}
