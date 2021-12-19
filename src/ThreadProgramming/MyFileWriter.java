package ThreadProgramming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// https://www.devmedia.com.br/leitura-e-escrita-de-arquivos-de-texto-em-java/25529

public class MyFileWriter {
	public static void writeMatrix(String path, Integer[][] Matriz, int dimensao) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = dimensao + " " + dimensao;
		buffWrite.append(linha + "\n");
		for(int i = 0; i < dimensao; i++) {
			for(int j = 0; j < dimensao; j++) {
				buffWrite.append(Matriz[i][j] + " ");
			}
			buffWrite.append("\n");
		}
		buffWrite.close();

	}
	
}
