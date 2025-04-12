
public class Metod_Param {

	
	public static void main(String[] args) {
		
		int x =50;
		int y= 60;
		String resultado = "El resultado es:"+calcularResultado(x,y);
		System.out.println(resultado);
	}

private static int calcularResultado(int k, int s ){
	int resultado=k+s;
	k++;
	return resultado;
                                                    }
         }
