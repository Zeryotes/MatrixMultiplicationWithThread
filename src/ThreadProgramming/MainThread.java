package ThreadProgramming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainThread {

	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		String directory = "C:\\Users\\Maximus\\Desktop\\PROGRAMAÇÃO CONCORRENTE\\MatrixMultiplicationWithThread\\Matrizes";
		String pathA = directory + "A" + args[0] + "x" + args[0] + ".txt";
		String pathB = directory + "B" + args[0] + "x" + args[0] + ".txt";
		String pathC = directory + "C" + args[0] + "x" + args[0] + ".txt";

		int dimensao = Integer.parseInt(args[0]);
	
		List<Integer> MatrixA = new ArrayList<>();
		List<Integer> MatrixB = new ArrayList<>();
		List<Integer> MatrixC = new ArrayList<>();

		System.out.print("Matriz A:");
		MatrixA = MyFileReader.read(pathA, dimensao);

		System.out.print("\nMatriz B:");
		MatrixB = MyFileReader.read(pathB, dimensao);
			
		System.out.print("\nMultiplicação de AxB:");
		MatrixC = OperateMatrix.multiply(MatrixA, MatrixB, dimensao);
		
		OperateMatrix.print(MatrixC, dimensao);
		
		MyFileWriter.writeMatrix(pathC, MatrixC, dimensao);
		
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("\nTempo de execução em Milisegundos: " + (elapsedTime/1000000));
		if((elapsedTime/1000000) >= 1000) {
			System.out.println("Tempo de execução em Segundos: " + (elapsedTime/1000000000));
		}
	}
	
}