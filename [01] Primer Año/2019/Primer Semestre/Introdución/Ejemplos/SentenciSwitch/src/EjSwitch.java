import java.io.*;

public class EjSwitch {

	public static void main(String[] a)throws IOException  {
		int tipo_vehiculo,peaje;
BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Ingrese el tipo de vehiculo: ");
		System.out.flush();
		tipo_vehiculo = Integer.parseInt(entrada.readLine());
		switch (tipo_vehiculo)
		{
		case 1:
			System.out.println("TURISMO");
			peaje = 500;
			System.out.print("Este vehiculo paga:" + peaje + " de peaje");
			break;
		case 2:
			System.out.println("AUTOBUS");
			peaje = 3000;
			System.out.print("Este vehiculo paga:" + peaje + " de peaje");
			//break;
		case 3:
			System.out.println("MOTO");
			peaje = 300;
			System.out.print("Este vehiculo paga:" + peaje + " de peaje");
			break;
			default:
				System.out.println("Vehiculo NO autorizado");
		}
		

	}

}
