package ThreadProgramming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// https://www.devmedia.com.br/leitura-e-escrita-de-arquivos-de-texto-em-java/25529

public class MyFileWriter {
	public static void writeMatrix(String path, Integer[][] Matrix, int dimension) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String line = dimension + " " + dimension;
		buffWrite.append(line + "\n");
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				buffWrite.append(Matrix[i][j] + " ");
			}
			buffWrite.append("\n");
		}
		buffWrite.close();

	}
	
}
