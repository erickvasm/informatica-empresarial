import java.util.Scanner;


public class EstructuraWhile {

	public static void main(String[] args){
		System.out.println("Programa para sumar numeros introducidos por el usuario");
		Scanner scanner = new Scanner(System.in);
		int acum = 0;
		int nuevoNumero = 0;
		while (nuevoNumero >= 0){
			System.out.print("Digite el numero a sumar o -1 para salir del programa: ");
			nuevoNumero = scanner.nextInt();
			if (nuevoNumero > 0){
				acum = acum + nuevoNumero;
			}
			
		}
		System.out.println("El total de la suma por los numeros que ingreso es de: "+ acum);
	}
}
