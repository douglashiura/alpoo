package br.ies.aula.alpoo.jogo;

public class Aposta {

	private Integer valor;
	private String nome;
	private ResultadosDoJogoParImpar aposta;

	public Aposta(Integer valor) {
		this.valor = valor;
	}

	public Aposta(String nome, ResultadosDoJogoParImpar aposta, Integer valor) {
		this.nome = nome;
		this.aposta = aposta;
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public ResultadosDoJogoParImpar getAposta() {
		return aposta;
	}

	public String getNome() {
		return nome;
	}
}