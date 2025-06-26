
import java.io.*;
public class suma
{
   public static void main (String [] args)
   {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader (isr);
      try
      {
         System.out.print("Ingrese primer sumando  : ");
         int s1 = Integer.parseInt(br.readLine());
         System.out.print("Ingrese segundo sumando : ");
         int s2 = Integer.parseInt(br.readLine());
         int suma=s1+s2;
         System.out.println ("La suma es " + s1 + "+" + s2 +"="+ suma);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}