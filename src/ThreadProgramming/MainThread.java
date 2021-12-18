package ThreadProgramming;

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
		String directory = "C:\\Users\\Maximus\\Desktop\\PROGRAMAÇÃO CONCORRENTE\\MatrixMultiplicationWithThread\\Matrizes";
		String pathA = directory + "A" + args[0] + "x" + args[0] + ".txt";
		String pathB = directory + "B" + args[0] + "x" + args[0] + ".txt";
		String pathC = directory + "C" + args[0] + "x" + args[0] + ".txt";

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
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		System.out.print("\nMultiplicação de AxB:");
//		MatrixC = OperateMatrix.multiply(MatrixA, MatrixB, dimensao);
		
//		OperateMatrix.print(MatrixC, dimensao);
		
//		MyFileWriter.writeMatrix(pathC, MatrixC, dimensao);
		
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("\nTempo de execução em Milisegundos: " + (elapsedTime/1000000));
		if((elapsedTime/1000000) >= 1000) {
			System.out.println("Tempo de execução em Segundos: " + (elapsedTime/1000000000));
		}
	}
	
	public static void multiply(Integer index, List<Integer> MatrizA, List<Integer> MatrizB, int dimensao){
		int i, j, k, soma;
		
		for(i = 0; i < dimensao; i++) {
			for(j = 0; j < dimensao; j++) {
				soma = 0;
				for(k = index; k < dimensao; k += 4) {
					soma = soma +
						   MatrizA.get(k+i*dimensao) * 
						   MatrizB.get(j+k*dimensao);
				}
				MatrizC.put(null, soma);
			}
		}
	}
}