package Pacote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Ponto {
	int limiar;
	float x;
	float y;

	public Ponto(double x2, double y2) {
		this.x = (float) x2;
		this.y = (float) y2;
	}

	@Override
	public String toString() {
		return "Ponto [x=" + x + ", y=" + y + "]";
	}
}

public class Principal {

	public static void main(String[] args) {
		String caminhoArquivo = "C:\\Users\\laboratorio\\Desktop\\amostras_saidas_problemaTimes.txt"; // Caminho do
																										// Arquivo de
																										// amostras

		Scanner leitor = new Scanner(System.in);
		List<Ponto> amostras = new ArrayList<>();
		List<Integer> saidas = new ArrayList<>();
		// Adicionar as amostras do arquivo txt em uma lista

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				// System.out.println(linha);
				// amostras.add(linha);
				String vetor_linha[] = linha.split(";");
				amostras.add(new Ponto(Float.parseFloat(vetor_linha[0]), Float.parseFloat(vetor_linha[1])));
				vetor_linha[2].replace('\n', '\0');
				saidas.add(Integer.parseInt(vetor_linha[2]));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Printar na tela as amostras adicionadas a partir do arquivo txt que foi
		// adicionada na lista e a lista de saídas

		for (Ponto ponto : amostras) {
			System.out.println(ponto);
		}

		for (int s : saidas) {
			System.out.println("Saídas: " + s);
		}
		
		System.out.println("\n");

		double taxa_aprendizado = 0.1;
		int geracoes = 1000;
		int limiar = 1;
		Perceptron p = new Perceptron(amostras, saidas, taxa_aprendizado, geracoes, limiar);

		p.treinar();

		String op;
		do {
			System.out.println("\n\nInforme valor para x (-1 a 1): ");
			double x = leitor.nextDouble();
			System.out.println("Informe valor para y (-1 a 1): ");
			double y = leitor.nextDouble();

			p.testar(new Ponto(x, y));
			System.out.println("1 - Sair: ");
			op = leitor.nextLine();
		} while (op != "1");

	}

}
