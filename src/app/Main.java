package app;

import java.util.Scanner;
import java.util.Vector;

public class Main {
	private static Parser parser;

	public static void main(String[] args) throws Exception {
		String opcao;
		String opcaoFormatacao;
		String delimitador;
		Vector<Integer> qtdEvolucoes;
		
		parser = new Parser();

		do {
			System.out.println("Escolha o arquivo de entrada: ");
			System.out.println("[ 1 ] - analysisTime.out");
			System.out.println("[ 2 ] - analysisMemory.out");
			opcao = new Scanner(System.in).next();
		} while (!opcao.contentEquals("1") && !opcao.contentEquals("2"));

		switch (opcao) {
		case "1": {
			try {
				parser.leArquivo("analysisTime.out");
			} catch (ArquivoNaoEncontradoException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			break;
		}
		case "2": {
			try {
				parser.leArquivo("analysisMemory.out");
			} catch (ArquivoNaoEncontradoException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			break;
		}
		default:
			System.out.println("Op��o Inv�lida");
		}

		System.out.println("Defina o delimitador de 1 caracter [;] [,] etc) : ");
		try {
			delimitador = new Scanner(System.in).next();
			parser.definirDelimitador(delimitador);
		} catch (DelimitadorInvalidoException e) {
			e.printStackTrace();
			System.out.println(e);
		}

		try {
			parser.criaArquivoSaida();
		} catch (EscritaNaoPermitidaException e) {
			e.printStackTrace();
			System.out.println(e);
		}

		do {
			System.out.println("Escolha o tipo de formata��o: ");
			System.out.println("[ 1 ] - Linha");
			System.out.println("[ 2 ] - Coluna");
			opcaoFormatacao = new Scanner(System.in).next();
		} while (!opcaoFormatacao.contentEquals("1") && !opcaoFormatacao.contentEquals("2"));

		switch (opcaoFormatacao) {
		case "1": {
			parser.converteLinha();
			break;
		}
		case "2": {
			parser.converteColuna();
			break;
		}
		default:
			System.out.println("Op��o Inv�lida");
		}
		
		parser.formataTextoComDelimitador();
		parser.escreveTextoSaida();
		System.out.println("O seu arquivo de sa�da foi escrito! Aguarde um puco e atualize o diret�rio. ");
		qtdEvolucoes = parser.analiseEvolucoes();
		System.out.println("An�lises realizadas: " + qtdEvolucoes.size());
		System.out.println("Quantidade de evolu��es realizadas em cada an�lise");
		System.out.println(qtdEvolucoes);
		
	}

}
