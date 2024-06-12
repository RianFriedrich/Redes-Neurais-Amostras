package Pacote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Ponto {
	int limiar;
	float x;
	float y;
	
	public Ponto(float x, float y) {
		this.x = x;
		this.y = y;
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

		List<Ponto> amostras = new ArrayList<>();
		List<Integer> saidas = new ArrayList<>();
		// Adicionar as amostras do arquivo txt em uma lista
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				//System.out.println(linha);
				//amostras.add(linha);
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

	}

}
