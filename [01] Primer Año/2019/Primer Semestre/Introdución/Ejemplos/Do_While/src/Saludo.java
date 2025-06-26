import java.io.*;

class Saludo {

	public static void main(String[] a)throws IOException {
	char opcion;
	do {
		System.out.println("Hola");
		System.out.println("Desea otro tipo de saludo?");
		System.out.println("Presione s para si y n para no,");
		System.out.println("y a continuación presione enter(intro):");
		System.out.flush();
		System.in.skip(2);
		opcion = (char) System.in.read();
	} while (opcion== 's' || opcion == 'S');
	System.out.println("ADIOS");

	}

}
