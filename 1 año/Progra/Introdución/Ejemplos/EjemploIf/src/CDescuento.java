import java.util.*;

public class CDescuento
{
  public static void main(String[] args)
  {
    Scanner Leer = new Scanner(System.in);
    String a; 
    int cc; // código y cantidad
    float pu;   // precio unitario
    float desc; // descuento
    
    System.out.print("Código artículo....... ");
    a = Leer.nextLine();
    System.out.print("Cantidad comprada..... ");
    cc = Leer.nextInt();
    System.out.print("Precio unitario....... ");
    pu = Leer.nextFloat();
    System.out.println();

    if (cc > 100)
      desc = 40F;      // descuento 40%
    else if (cc >= 25)
      desc = 20F;      // descuento 20%
    else if (cc >= 10)
      desc = 10F;      // descuento 10%
    else
      desc = 0.0F;     // descuento 0%
    System.out.println("Descuento............. " + desc + "%");
    System.out.println("Total................. " +
                       cc * pu * (1 - desc / 100));
  }
}
