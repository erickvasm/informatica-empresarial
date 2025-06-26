
public class Ejemplo2 {

	 public static void main(String[] args)
	 {
		 
		 int k=100;
		 int s = 40;
		 System.out.printf("Nº Mayor: %d\n",mayor(k,s));
		 imprimeMayor(k,s);
	 }
	
	
	public static int mayor(int a, int b){
	if (a>b){
		return a;
		}else {
			return b;
		}
}
 
	public static void imprimeMayor(int a, int b){
	 int max;
	 max = mayor(a,b);
	 System.out.printf("Mayor:"+  max);
	  }

}
