package aula.par.impar;

import aula.par.impar.entidade.Aposta;

public class PrimeiraJogada implements EstadoDeJogada {
	private LojaDoJogoParImpar jogo;

	public PrimeiraJogada(LojaDoJogoParImpar jogo) {
		this.jogo = jogo;
	}

	@Override
	public void jogar(Aposta primeiraAposta) {
		jogo.fixarMaquinaDeEstadoDasJogadas(new SegundaJogada(primeiraAposta, jogo));
	}
}
   