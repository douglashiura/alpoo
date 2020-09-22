package br.ies.aula.alpoo.jogo.parimpar.entidade;

public class Aposta {

	private Integer valor;
	private String nome;
	private ResultadosDoJogoParImpar aposta;
	private Integer id;

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

	@Override
	public String toString() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;

	}
}
