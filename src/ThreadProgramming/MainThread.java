package ThreadProgramming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainThread {
	
	static Map<Integer, Integer> MatrizC = new HashMap<Integer, Integer>();
	
	static class TestThread extends Thread {
		private Integer index;
		private List<Integer> MatrizA;
		private List<Integer> MatrizB;
		private int dimensao;

		public TestThread(Integer index, List<Integer> MatrizA, List<Integer> MatrizB, int dimensao) {
			this.index = index;
			this.MatrizA = MatrizA;
			this.MatrizB = MatrizB;
			this.dimensao = dimensao;
		}
		
		public void run() {
			multiply(this.index, this.MatrizA, this.MatrizB, this.dimensao);
		}
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		String directory = "C:\\Users\\Maximus\\Desktop\\PROGRAMA��O CONCORRENTE\\MatrixMultiplicationWithThread\\Matrizes";
		String pathA = directory + "A" + args[0] + "x" + args[0] + ".txt";
		String pathB = directory + "B" + args[0] + "x" + args[0] + ".txt";
		String pathC = directory + "C" + args[0] + "x" + args[0] + "test.txt";

		int dimensao = Integer.parseInt(args[0]);
	
		List<Integer> MatrixA = new ArrayList<>();
		List<Integer> MatrixB = new ArrayList<>();
		

		System.out.print("Matriz A:");
		MatrixA = MyFileReader.read(pathA, dimensao);

		System.out.print("\nMatriz B:");
		MatrixB = MyFileReader.read(pathB, dimensao);
		
		TestThread t1 = new TestThread(0, MatrixA, MatrixB, dimensao);
		TestThread t2 = new TestThread(1, MatrixA, MatrixB, dimensao);
		TestThread t3 = new TestThread(2, MatrixA, MatrixB, dimensao);
		TestThread t4 = new TestThread(3, MatrixA, MatrixB, dimensao);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
//		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		System.out.println(MatrizC);

//		System.out.print("\nMultiplica��o de AxB:");
//		MatrixC = OperateMatrix.multiply(MatrixA, MatrixB, dimensao);
		
//		print(dimensao);
		
		writeMatrix(pathC, dimensao);
		
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("\nTempo de execu��o em Milisegundos: " + (elapsedTime/1000000));
		if((elapsedTime/1000000) >= 1000) {
			System.out.println("Tempo de execu��o em Segundos: " + (elapsedTime/1000000000));
		}
	}
	
	public static void multiply(Integer index, List<Integer> MatrizA, List<Integer> MatrizB, int dimensao){
		int i, j, k, soma;
		
		for(i = index; i < dimensao; i+=4) {
//			System.out.print("Thread: " + index +  " [" + i + "] ");
			for(j = 0; j < dimensao; j++) {
				soma = 0;
				for(k = 0; k < dimensao; k++) {
					soma = soma +
						   MatrizA.get(k+i*dimensao) * 
						   MatrizB.get(j+k*dimensao);
				}
//				System.out.print(soma + " ");
				MatrizC.put(j+(i*dimensao), soma);
			}
			System.out.println("");
		}
	}
	
	public static void print(int dimensao) {
		System.out.println("");
		System.out.println(dimensao + " " + dimensao);
		for(int i = 0; i < dimensao; i++) {
			for(int j = 0; j < dimensao; j++) {
				System.out.print(MatrizC.get(j+(i*dimensao)) + " ");
			}
			System.out.println("");			
		}
	}
	
	public static void writeMatrix(String path, int dimensao) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = dimensao + " " + dimensao;
		buffWrite.append(linha + "\n");
		for(int i = 0; i < dimensao; i++) {
			for(int j = 0; j < dimensao; j++) {
				buffWrite.append(MatrizC.get(j+(i*dimensao)) + " ");
			}
			buffWrite.append("\n");
			System.out.print(i);
		}
		buffWrite.close();

	}
}