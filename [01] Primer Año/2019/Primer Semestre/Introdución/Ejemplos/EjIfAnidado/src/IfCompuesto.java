
import java.io.*;
public class IfCompuesto {
	
	public static void main(String[] a) throws IOException {
		int numero;
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		int edad=10;
		String nombre;
		double precio=5500;
		double precio_descuento;
		System.out.print("Digite su edad: ");
		System.out.flush();
		edad = Integer.parseInt(entrada.readLine());
		
		
		if (edad>= 10)
		{
			System.out.print("Digite su nombre: ");
			nombre= entrada.readLine();
			System.out.println("El valo del boleto es de: "+precio);
			precio_descuento= precio*0.60;
			precio= precio-precio_descuento;
			System.out.println("Monto del descuento es de: "+precio_descuento);
			System.out.println("El valor del boleto con el descuento es de: "+precio);
			
		}
	}
	
}
