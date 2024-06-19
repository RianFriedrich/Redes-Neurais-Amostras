package Pacote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Perceptron {
	public List<Ponto> amostras = new ArrayList<Ponto>();
	ArrayList<Integer> saidas = new ArrayList();
	public double taxa_aprendizado;
	public int geracoes;
	public int limiar;
	public int numeroAmostras;
	public int numeroAtributos;

	public double[] pesos;
	private double taxaAprendizado;

	public Perceptron(List<Ponto> amostras, List<Integer> saidas, double taxa_aprendizado, int geracoes, int limiar) {
		this.amostras = amostras;
		this.saidas = (ArrayList) saidas;
		this.taxa_aprendizado = taxa_aprendizado;
		this.geracoes = geracoes;
		this.limiar = limiar;
		this.numeroAmostras = amostras.size();
		this.pesos = new double[3]; // tamanho deste vetor peso vai ser igual a quantidade de atributos + um valor
									// par limiar
	}

	private int funcao_ativacao_signal(double soma) {
		if (soma >= 0)
			return 1;
		return -1;
	}

	public void treinar() {
	    // Inserir o valor do limiar na posi��o limiar de cada ponto de cada amostra da
	    // lista "amostras"
	    // Ex.: [[0.72, 0.82], ...] vira [[1, 0.72, 0.82], ...]
	    for (int i = 0; i < this.amostras.size(); i++) {
	        amostras.get(i).limiar = this.limiar;
	    }

	    // Gerar valores rand�micos entre 0 e 1 (pesos) conforme o n�mero de atributos
	    // a lista de pesos tem tamanho da quantidade de atributos de uma amostra
	    // pesos = [1.0,0.4,0.6], por exemplo
	    Random gerador = new Random();
	    pesos[0] = limiar;
	    pesos[1] = gerador.nextDouble(); // para o peso da entrada x
	    pesos[2] = gerador.nextDouble(); // para o peso da entrada y

	    int conta = 0;
	    boolean aprendeu;
	    double soma;
	    int saida_gerada;
	    while (true) {
	        aprendeu = true;

	        // Para cada amostra ou registrou ou ponto ...
	        soma = 0;
	        for (int i = 0; i < amostras.size(); i++) {
	            // Inicializar potencial de ativa��o
	            soma = soma + (amostras.get(i).limiar * pesos[0]) + (amostras.get(i).x * pesos[1])
	                    + (amostras.get(i).y * pesos[2]);

	            // Obter a sa�da da rede considerando a fun��o sinal
	            saida_gerada = funcao_ativacao_signal(soma);

	            // Verificar se a sa�da da rede � diferente da sa�da desejada
	            // se sim, calibrar ou treinar ou ajustar ou fazer aprender
	            if (saida_gerada != this.saidas.get(i)) {
	                aprendeu = false;
	                double erro = this.saidas.get(i) - saida_gerada;
	                this.pesos[0] = this.pesos[0] + this.taxaAprendizado * erro * this.amostras.get(i).limiar;
	                this.pesos[1] = this.pesos[1] + this.taxaAprendizado * erro * this.amostras.get(i).x;
	                this.pesos[2] = this.pesos[2] + this.taxaAprendizado * erro * this.amostras.get(i).y;
	            }

	            // Atualizar contador de gera��es
	            conta++;

	            if (aprendeu || conta > this.geracoes) {
	                System.out.println("Geracoes de treinamento: " + conta);
	                break;
	            }
	        }

	        if (aprendeu || conta > this.geracoes) {
	            break; // Sair do loop while
	        }
	    }
	}

	// testes para "novas" amostras
	public void testar(Ponto amostra) {
		// Inserir o valor do limiar na posi��o "0" para cada amostra da lista
		// "amostras"
		amostra.limiar = this.limiar;

		// Inicializar potencial de ativa��o
		double soma = 0;
		// # Para cada atributo...
		soma = soma + (amostra.limiar * pesos[0]) + (amostra.x * pesos[1]) + (amostra.y * pesos[2]);

		// Obter a sa�da da rede considerando g a fun��o funcao_ativacao_signal
		double saida_gerada = this.funcao_ativacao_signal(soma);

		if (saida_gerada == 1) {
			System.out.println("Classe: " + saida_gerada + " ou Time Azul");
		} else {
			System.out.println("Classe: " + saida_gerada + " ou Time Vermelho");
		}

	}
}
