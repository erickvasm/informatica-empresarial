/*
 * Sistema de Aduana
 * 
 * @author Cris Taisigue
 * @author Erick Vasquez
 * 
 * @version 0.1
 * */

package tareaprogramada;//paquete al que pertenece la clase TareaProgramaTres


import java.io.*;/* importamos este paquete para obtener el objeto BufferedReader y asi poder pedir
*					datos al usuario.
*/
import java.util.*;/*importamos este paquete para obtener el objeto Date() y asi poder obtener la fecha
*					y la hora del sistema desde donde se ejecuta el programa				
*/
import java.text.*;/*Este paquete nos permitira obtener el objeto SimpleDateFormat, el cual nos ayudara
*					a colocarle un formato a la fecha y la hora
*/


//Clase principal
public class TareaProgramadaTres{

	/*asignacion de objetos de los paquetes importados:
	 *entrada:tendra el objeto BufferedReader() para poder leer datos.
	 *paraFecha: establecemos mediante SimpleDateFormat() el formato que debera llevar la fecha.
	 *paraHora: establecemos mediante SimpleDateFormat() el formato que debera llevar la hora.
	 * */
	
	static BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in));
	static SimpleDateFormat paraFecha=new SimpleDateFormat("dd/MM/y");
	static SimpleDateFormat paraHora=new SimpleDateFormat("hh:mm:ss:a");
	
	/*Declaracion de constantes, descuento segun categoria.
	 * las siguientes constantes hacen referencia al porcentaje de impuesto segun
	 * la clasificacion de cada articulo:
	 * ARTICULO_VESTIDO=0.2995F; --> 29.25%
	 * ARTICULO_ELECTRONICO=0.5571F; --> 55.71%
	 * ARTICULOS_LINEA_BLANCA= 0.4927F; --> 49.27%
	 * PRODUCTOS_EXONERADOS=0.13F; --> 13%
	 * ARTICULOS_CONSTRUCCION=0.15F; -->15%;
	 * 
	 * Con la finalidad de poder realizar calculos de aranceles en el metodo ingresarInformacion()
	 */
	static float ARTICULO_VESTIDO=0.2995F,ARTICULO_ELECTRONICO=0.5571F,ARTICULOS_LINEA_BLANCA= 0.4927F,
	PRODUCTOS_EXONERADOS=0.13F,ARTICULOS_CONSTRUCCION=0.15F;
	
	
	/*Asignacion, total de aranceles por categoria
	 * en el metodo ingresarInformacion() se calcula el arancel para cada venta segun la clasificacion
	 * ese valor calculado se añadira (el valor actual mas el calculado) a alguna de estas variables 
	 * globales dependiendo de la clasificacion asignada al articulo:
	 * totalProEx ---> para Productos exonerados
	 * totalHerraC ---> para Herramientas manuales construcción
	 * totalArtLineaB ---> para Artículos línea blanca para el hogar
	 * totalArtE ---> para Artículos electrónicos para entretenimiento
	 * totalArtV ---> para Artículos para vestir
	 * Se da estas asignaciones para poder mostrar los totales de aranceles por categoria al final del dia
	 * con el metodo totalDiario() 
	 */
	static float totalProEx=0,totalHerraC=0,totalArtLineaB=0,totalArtE=0,totalArtV=0;
		
	/*Asignacion,cantidad de paquetes por categoria (clasificacion)
	* en el metodo ingresarInformacion() segun la clasificacion especificada (el caso) se le sumara 
	* un valor (1) a alguna de estas variables globales, es decir,se le sumara un valor al numero
	* de paquetes para la clasificacion a la que pertenezca el articulo registrado:
	*  cantidadProEx ---> cantidad de Productos exonerados
	 * cantidadHerraC ---> cantidad de Herramientas manuales construcción
	 * cantidadArtLineaB ---> cantidad de Artículos línea blanca para el hogar
	 * cantidadArtE ---> cantidad de Artículos electrónicos para entretenimiento
	 * cantidadArtV ---> cantidad de Artículos para vestir
	 * Se da estas asignaciones para poder mostrar la cantidad de paquetes por categoria al final del dia
	 * con el metodo totalDiario()
	*/
	static int cantidadProEx=0,cantidadHerraC=0,cantidadArtE=0,cantidadArtV=0,
	cantidadArtLineaB=0;
	
	
	
	/*Asignacion de totales sin impuesto por categoria
	 * Dependiendo de la clasificacion del articulo se sumara el valor actual de esa variable mas el precio 
	 * base del articulo a alguna de estas variables.
	 * 
	 * proExSinIm ---> total de los Productos exonerados sin arancel
	 * herraConsSinIm ---> total de las Herramientas manuales construcción sin arancel
	 * artLineaBlancaSinIm ---> total de los Artículos línea blanca para el hogar sin arancel
	 * artElectSinIm ---> total de los Artículos electrónicos para entretenimiento sin arancel
	 * artVestidoSinIm ---> total de los Artículos para vestir sin arancel
	 * 
	 * Se da estas asignaciones para poder mostrar el total de precios (sin arancel) por categoría al final del dia
	 * con el metodo totalDiario()
	 */
	static int proExSinIm=0, herraConsSinIm=0,artLineaBlancaSinIm=0,artElectSinIm=0,artVestidoSinIm=0;
	
	
	/*Metodo Principal, se encargara de inicializar nuestro programa
	 * dando una bienvenida y llamando al metodo menu().
	 * 
	 * */
	public static void main(String[] args) throws IOException {
		System.out.flush();
		System.out.println("\t"+"\t"+"Bienvenido al Sistema de Aduana"+"\n");
		
		//Se llama al metodo menu()
		menu(); 
		
	}

	/*metodo menu(), en este se ingresa alguna opcion de las 4 existentes
	 * si se ingresa (1) se llamara el metodo ingresarInformacion(), si se ingresa (2) se llama
	 * al metodo totalDiario(), si (3) es la opcion se muestran las instrucciones de uso del sistema y si
	 * se ingresa (4) se sale del sistema mendiante un 'System.exit(0)'
	 * 
	 * 
	 * */
	public static void menu() throws IOException {
		/*Se incializa la variable opcion en 0 para entrar en el ciclo while y que el usuario ingrese
		*alguna opcion.
		*/
		int opcion=0;
		
		/*Por defecto se ingresa al ciclo while debido a la variable opcion esta incializada en 0
		* no corresponde a ninguna de las opciones especificadas del menu 1,2,3,4.
		*/
		while((opcion!=1) && (opcion!=2) && (opcion!=3) && (opcion!=4)) {

			System.out.println("Ingrese una de las siguientes opciones:"+"\n"+"1)Para ingresar la informacion."+"\n"+"2)Para mostrar los totales diarios."+"\n"
			+"3)Instrucciones de uso"+"\n"+"4)Salir del sistema");
			
			//Se le pide al usario que ingrese una opcion de las 4 existentes
			opcion=Integer.parseInt(entrada.readLine());
			
			/*usando un switch() evaluamos el valor dado (opcion) por el usuario, es decir la opcion que ingreso
			 * acorde a las opciones dadas en el menu.
			 * */
			switch(opcion) {
			
				//En el caso que la opcion (opcion) dada sea (1), se llamara al metodo ingresarInformacion().
				case 1: 
					{
						ingresarInformacion();
					}
					break;
				//En el caso que la opcion (opcion) dada sea (2), se llamara al metodo totalDiario().
				case 2:
					{
						totalDiario();
					}
					break;
				/*En el caso que la opcion (opcion) dada sea (3), se muestran las Instrucciones de uso del Sistema
				*y se llama a menu(), (se regresa al menu).
				*/	
				case 3:
					{
						System.out.println("\t"+"\t"+"Sistema de Aduana (Instrucciones de uso)");
						System.out.println("En la parte del menu usted dispone de cuatro opciones"+"\n");
						
						System.out.println("1)Para ingresar la informacion referente al comprador y el articulo:");
						System.out.println("\t"+"*Se le pedira el nombre del comprador, no debe dejar este campo vacio."
						+"\n\t"+"*Debera ingresar un número de cedula, minimo 5 caracteres"
						+"\n\t"+"*Tambien se le pedira el monto del articulo, este debe "+"\n\t"+"ingresarlo de forma entera, "
						+"no se debera ingresar texto o dejar este espacio omitido."
						+"\n\t"+"Luego de ingresar nombre, cedula y monto del articulo se le"
						+"\n\t"+"pedira la categoria de ese articulo siendo:"+
						"\n\t"+"1) Productos exonerados."+
						"\n\t"+"2) Artículos para vestir."
						+"\n\t"+"3) Artículos electrónicos."
						+"\n\t"+"4) Artículos línea blanca para el hogar."
						+"\n\t"+"5) Articulos de construcción.");
						System.out.println("\t"+"Debera ingresar alguna de estas.");
						
						System.out.println("2)Para calcular y mostrar los totales del dia:");
						System.out.println("\t"+"Antes de acceder a esta opcion debera registrar al menos un articulo."
						+"\n"+"\t"+"Si usted accede a esta opcion, al terminar su proposito,"+"\n\t"+" terminara el programa por defecto.");
						
						System.out.println("3)Instrucciones de uso:"+"\n\t"+"Muestra las instrucciones de uso referentes a este sistema.");
						System.out.println("4)Salir:"+"\n\t"+"Si asi lo desea puede salir en cualquier momento, "+
						"\n\t"+"tambien es posible finalizar el programa con la opcion (2) pero "+"\n\t"+"debera registrar algun articulo.");
					
						System.out.println("Gracias por consultar las instrucciones de uso."+"\n");
						//Se vuelve a menu (llamado a menu())
						menu();
					}
					break;
				
				/*En el caso que la opcion (opcion) dada sea (4),
				* se terminara el programa mediante un System.exit(0)
				*/
				case 4:
					{
						System.out.println("\n"+"Gracias por utilizar este servicio.");
						System.exit(0);
					}
					break;
		
				/*En el caso que la opcion (opcion) dada, no corresponda a ninguno de los casos (1,2,3,4), y se vuelve
				* a repetir el ciclo while().
				*/
				default:
					{
						System.out.println("\n"+"Ingrese una opcion valida:"+"\n");
					}
					break;
			}
	
		}
		
	}
	
	
	
	/*el metodo totalDiaraio() como lo indica mostrara los totales diarios de cantidad
	 * de paquetes, total de aranceles y total de precios sin aranceles por categoria
	 * (clasificacion), a si como tambien el total recaudado de todos los aranceles de las
	 * categorias
	 * 
	 * */
	public static void totalDiario() throws IOException {
		//A la variable totales, es la que se le asignara la suma de todos los aranceles.
		float totales;
		
		/*En esta condicional if() evaluamos si se ha registrado algun producto
		 * Si se registraron productos se mostrara, la cantidad de paquetes, total de aranceles y 
		 * total de precios sin aranceles por categoria ademas los totales de los aranceles de las
		 * categorias, se finaliza el programa por defecto
		 * 
		 * 
		 * Si no es asi, se le indica al usuario que ingrese minimo un articulo
		 * y se vueleve al menu (llamado a menu())
		 * */
		if((cantidadProEx==0) && (cantidadHerraC==0) && (cantidadArtE==0) && (cantidadArtV==0) && (cantidadArtLineaB==0)) {
			System.out.println("No se han registrado ventas todavia."+"\n");
			//Llamado a menu(), regreso al menu.
			menu();
		}else {
			
			/*Se calcula el total recaudado de todos los aranceles mediante la suma de
			 * los aranceles por categoria (clasificacion) guardando este valor en la variable (totales).
			 */
			totales=totalProEx+totalHerraC+totalArtLineaB+totalArtE+totalArtV;
			
			System.out.println("\n"+"\t"+"\t"+"Totales del dia por categoria:");

			System.out.println("Productos Exonerados:"+"\n"+"\t"+"Cantidad de paquetes:"+cantidadProEx+
					"\n"+"\t"+"Total de arancel:"+totalProEx+"\n"+"\t"+"Total de precios sin aranceles:"+proExSinIm);
			
			System.out.println("Articulos para vestir:"+"\n"+"\t"+"Cantidad de paquetes:"+cantidadArtV+
					"\n"+"\t"+"Total de arancel:"+totalArtV+"\n"+"\t"+"Total de precios sin aranceles:"+artVestidoSinIm);
			
			System.out.println("Articulos electronicos:"+"\n"+"\t"+"Cantidad de paquetes:"+cantidadArtE+
					"\n"+"\t"+"Total de arancel:"+totalArtE+"\n"+"\t"+"Total de precios sin aranceles:"+artElectSinIm);
			
			System.out.println("Articulo Linea Blanca para el hogar:"+"\n"+"\t"+"Cantidad de paquetes:"+cantidadArtLineaB+
					"\n"+"\t"+"Total de arancel:"+totalArtLineaB+"\n"+"\t"+"Total de precios sin aranceles:"+artLineaBlancaSinIm);
			
			System.out.println("Articulos Herramientas manuales construcción:"+"\n"+"\t"+"Cantidad de paquetes:"+cantidadHerraC+
					"\n"+"\t"+"Total de arancel:"+totalHerraC+"\n"+"\t"+"Total de precios sin aranceles:"+herraConsSinIm);
			
			System.out.println("\n"+"Total recaudado de los aranceles:"+totales);
			 
			System.out.println("Gracias por utilizar este sistema, hasta luego.");
			
			//Se finaliza el programa por defecto
		}
		
	}
	
	/* metodo ingresarInformacio()
	 * se pide el nombre del comprador(nombre) y cedula (cedula), tambien la hora y la fecha se 
	 * asigna aqui , este metodo nos permite hacer el calculo de los aranceles
	 * dependiendo del impuesto de este segun la clasificacion y el precio base ingresado
	 * aqui
	 * 
	 * */
	public static void ingresarInformacion() throws IOException {

		/*
		 * Las variables locales de tipo int en este metodo tendran distintos papeles:
		 * opcion: esta variable incializada en (0) permitira al sistema ingresar en
		 * un ciclo while que se encuentra posterior a esta asignacion donde se pedira
		 *  la clasificacion del articulo mientras opcion sea distinto a las opciones
		 * existentes (1,2,3,4,5) el ciclo while() se repetira.
		 * preArticulo:esta variable local contendra el precio base del articulo
		 * nombreExtension: para poder verificar que el nombre sea valido , a esta variable
		 * se le asigna el extension del texto del nombre dado por el usuario.
		 * cedulaExtension:para poder verificar que la cedula sea valida , a esta variable
		 * se le asigna el extension del texto de la cedula dada por el usuario.
		 * */
	    int opcion=0,preArticulo=0,nombreExtension=0,cedulaExtension=0;
		
	    /*Especificacion de estas variables:
	     * precioImpuesto:esta almacenara el arancel calculado dependiendo de la clasificacion
	     * escogida.
	     * precioArticuloImpuesto:esta variable almacenara el valor del precio base del articulo
	     * mas el arancel que se calculó.
	     * 
	     * */
		float precioImpuesto=0,precioArticuloImpuesto=0;
	
		/*Especificacion de variables:
		 * nombre: como bien lo indica guaradara el nombre del comprador
		 * cedula: guaradara la cedula del comprador
		 * fecha: se guaradara aqui es la fecha del sistema
		 * hora: se le asignara mas adelante la hora del sistema
		 * porcentaje: segun la clasificicacion del articulo se guardara
		 * su respectivo impuesto
		 * 
		 * */
		String nombre="",cedula="",fecha="",categoriaSelecionada="",hora="",porcentaje="";
		
		System.out.println("\n"+">Ingrese la informacion del comprador:");

		/*Para validar el nombre
		 * como la variable nombreExtension se inicializo en (0)
		 * el ciclo while() se ejecuta
		 * */
		while(nombreExtension==0) {
			System.out.print("Digite su nombre: ");
			
			//En esta entrada se ingresa el nombre del comprador
			nombre= entrada.readLine();
			
			/*Una vez ingresado el nombre del comprador (nombreExtension) recivira
			 * el valor de la extension (tamaño) del nombre dado (nombre)
			 */
			nombreExtension=nombre.length();

			/*Si el tamaño del nombre sigue siendo (0), entonces se repite el ciclo()
			 * y se volvera a pedir el nombre, hasta que este sea valido.
			 * 
			 * */
			if(nombreExtension==0) {
				System.out.println("\n"+"---Porfavor digite un nombre valido");
			}
		}
		
		
		/*Para validar la cedula
		 * como la variable cedulaExtension se inicializo en (0)
		 * el ciclo while() se ejecuta
		 * */
		while(cedulaExtension<5) {
			System.out.println("Digite su cedula:");
			
			//En esta entrada se ingresa el nombre del comprador
			cedula= entrada.readLine();
			
			/*Una vez ingresada la cedula del comprador (cedulaExtension) recivira
			 * el valor de la extension (tamaño) de la cedula dada (cedula)
			 */
			cedulaExtension=cedula.length();
			
			/*Si el tamaño de la cedula es menor a cinco digitos se pide que ingres una cedula
			* valida minimo de 5 caracteres, y se vuelve a repetir el ciclo  while() hasta que
			* el extension de la cedula sea mayor o igual a 5
			*/
			if(cedulaExtension<5) {
				System.out.println("---Porfavor ingrese una cedula valida, minimo 5 caracteres");
			}
		}
		
		/*Para validar que el precio base del articulo ingresado sea valido
		 * como la variable preArticulo se inicializo en (0)
		 * el ciclo while() se ejecuta
		 * */
		while(preArticulo==0) {
			System.out.println("Ingrese el precio del articulo (Número Entero):");
			
			//Aqui se ingresa el precio del articulo.
			preArticulo=Integer.parseInt(entrada.readLine());
			
			/* si el monto registrado (preArticulo) sigue siendo, o es menor a (0)
			 * se le pide al usuario que ingrese un monto valido
			 * y se vuelve a repetir el ciclo while()
			 * 
			 * */
			if((preArticulo==0) || (preArticulo<0)) {
				System.out.println("---Porfavor ingrese un monto valido");

				/* al saber que el monto ingresado es invalido 
				 * el valor de preArticulo se resetea a (0) para poder repetir el cliclo while()
				 * hasta que el usuario ingrese un valor valido.
				 * 
				 * */
				preArticulo=0;
			}
		}
		
		/* una vez se ingreso correctamente el nombre, cedula y monto del articulo
		 * se le pide al usario que ingrese una categoria de las cinco existentes
		 * 
		 * */
		while((opcion!=1) && (opcion!=2) && (opcion!=3) && (opcion!=4) && (opcion!=5)) {
			
			//Se especifican las categorias (las clasificaciones)
			System.out.println("\n"+"Ingrese la categoria del articulo:");
			System.out.println("1) Productos exonerados.");
			System.out.println("2) Artículos para vestir.");
			System.out.println("3) Artículos electrónicos.");
			System.out.println("4) Artículos línea blanca para el hogar.");
			System.out.println("5) Articulos de construcción.");
			
			//El usuario debera ingresar una opcion con respecto a lo anterior especificado
			opcion= Integer.parseInt(entrada.readLine());
			
			System.out.println("");
			
			//reloj:tendra las funciones del objeto Date() para poder obtener la hora y la fecha.
			Date reloj=new Date();
			
			/*En el switch() se elejira con base a lo anterior solicitado en
			 * la variable (opcion) determinara que 
			 * 
			 * 1 ---> Productos Exonerados
			 * 2 ---> Articulos para vestir
			 * 3 ---> Articulos elecctronicos
			 * 4 ---> Articulos de Linea Blanca
			 * 5 ---> Articulos de herramientas de construccion
			 * 
			 * */
			switch(opcion){
				
				/*
				 * Si el usuario elije que la categoria del articulo ingresado es
				 * Productos Exonerados
				 * */
				case 1:
					{
						System.out.println("Categoria: Productos Exonerados");
						
						/*Se le asigna a (categoriaSeleccionada) la clasificacion del caso
						 * para asi poder mostrarla despues en el metodo 
						 * presentarInformeSobrePaquete(Param...)
						 * */
						categoriaSelecionada="Productos Exonerados";
						
						/*Se calcula el arancel del producto, con la mutiplicacion
						 * del impuesto (respecto a la clasificacion (constante)) por el monto del 
						 * articulo (preArticulo) ingresado y este valor se guarda 
						 * en (precioImpuesto)
						 * 
						 * */
						precioImpuesto=PRODUCTOS_EXONERADOS*preArticulo;
						
						/*en esta variable (precioArticuloImpuesto) es la suma del
						 * precio base del articulo (preArticulo) más el arancel de este
						 * articulo (precioImpuesto).
						 * 
						 * */
						precioArticuloImpuesto=precioImpuesto+preArticulo;
						
						/* se le suma a la cantidad de paquetes para la clasificacion
						 * segun el caso (cantidadProEx) un valor.
						 * */
						cantidadProEx+=1;

						/* se le suma al total de aranceles para la clasificacion
						 * segun el caso (totalProEx) el arancel calculado en este caso 
						 * (precioImpuesto).
						 * */
						totalProEx=totalProEx+precioImpuesto;
						

						/* se le suma al total de precios sin impuesto para la clasificacion
						 * segun el caso (proExSinIm) el  precio base del articulo (preArticulo)
						 * */
						proExSinIm=proExSinIm+preArticulo;
						
						/*Segun sea el caso, la variable (porcentaje) obtendra
						 * el impuesto que se debe aplicar para esa clasificacion
						 */
						porcentaje="13%";
						
						//Se obtiene la fecha del sistema donde se ejectuta este programa
						fecha =paraFecha.format(reloj);
					
						//se obtiene la hora del sistema donde se ejecuta este programa
						hora=paraHora.format(reloj);
						
						/*Se llama al metodo presentarInformeSobrePaquete() para mostrar el
						 * informe sobre el paquete brindando los parametros necesarios:
						 * Parametro necesario         Variable local en este metodo:
						 *      laHora	 					hora
						 *      laFecha						fecha
						 *      laCedula					cedula
						 *      elNombre					nombre
						 *      laCategoria 				categoriaSelecionada
						 *      elPrecioBase:               preArticulo
						 * 		elArancel:					precioImpuesto
						 * 		elArticulo 					precioArticuloImpuesto
						 * 		elPorcentaje				porcentaje
						 * */
						presentarInformeSobrePaquete(hora,fecha,cedula,nombre,categoriaSelecionada,preArticulo,precioImpuesto,precioArticuloImpuesto,porcentaje);
					}
					break;
		
				/*
				 * Si el usuario elije que la categoria del articulo ingresado es Articulos
				 * vestir
				 * */
				case 2:
					{
						System.out.println("Categoria: Articulos para vestir");
						
						/*Se le asigna a (categoriaSeleccionada) la clasificacion del caso
						 * para asi poder mostrarla despues en el metodo 
						 * presentarInformeSobrePaquete(Param...)
						 * */
						categoriaSelecionada="Articulos para vestir";
						
						/*Se calcula el arancel del producto, con la mutiplicacion
						 * del impuesto (respecto a la clasificacion (constante) por el monto del 
						 * articulo (preArticulo) ingresado y este valor se guarda 
						 * en (precioImpuesto)
						 * 
						 * */
						precioImpuesto=ARTICULO_VESTIDO*preArticulo;
						
						/*en esta variable (precioArticuloImpuesto) es la suma del
						 * precio base del articulo (preArticulo) más el arancel de este
						 * articulo (precioImpuesto).
						 * 
						 * */
						precioArticuloImpuesto=precioImpuesto+preArticulo;
						
						/* se le suma a la cantidad de paquetes para la clasificacion
						 * segun el caso (cantidadArtV) un valor.
						 * */
						cantidadArtV+=1;
						
						/* se le suma al total de aranceles para la clasificacion
						 * segun el caso (totalArtV) el arancel calculado en este caso 
						 * (precioImpuesto).
						 * */
						totalArtV=totalArtV+precioImpuesto;
						
						/* se le suma al total de precios sin impuesto para la clasificacion
						 * segun el caso (artVestidoSinIm) el  precio base del articulo (preArticulo)
						 * */
						artVestidoSinIm=artVestidoSinIm+preArticulo;
						
						/*Segun sea el caso, la variable (porcentaje) obtendra
						 * el impuesto que se debe aplicar para esa clasificacion
						 */
						porcentaje="29.96%";
						
						//se obtiena la fecha el sistema desde donde se ejecuta el programa
						fecha =paraFecha.format(reloj);
						
						//se obtiena la hora el sistema desde donde se ejecuta el programa
						hora=paraHora.format(reloj);
						
						/*Se llama al metodo presentarInformeSobrePaquete() para mostrar el
						 * informe sobre el paquete brindando los parametros necesarios:
						 * Parametro necesario         Variable local en este metodo:
						 *      laHora	 					hora
						 *      laFecha						fecha
						 *      laCedula					cedula
						 *      elNombre					nombre
						 *      laCategoria 				categoriaSelecionada
						 *      elPrecioBase:               preArticulo
						 * 		elArancel:					precioImpuesto
						 * 		elArticulo 					precioArticuloImpuesto
						 * 		elPorcentaje				porcentaje
						 * */
						presentarInformeSobrePaquete(hora,fecha,cedula,nombre,categoriaSelecionada,preArticulo,precioImpuesto,precioArticuloImpuesto,porcentaje);
					}
					break;
					
				/*
				 * Si el usuario elije que la categoria del articulo ingresado es
				 * Articulos Electronicos para Entretenimiento
				 * */
				case 3:
					{
						System.out.println("Categoria: Articulos Electronicos para entretenimiento");
						
						/*Se le asigna a (categoriaSeleccionada) la clasificacion del caso
						 * para asi poder mostrarla despues en el metodo 
						 * presentarInformeSobrePaquete(Param...)
						 * */
						categoriaSelecionada="Articulos Electronicos para entretenimiento";
						
						/*Se calcula el arancel del producto, con la mutiplicacion
						 * del impuesto (respecto a la clasificacion (constante) por el monto del 
						 * articulo (preArticulo) ingresado y este valor se guarda 
						 * en (precioImpuesto)
						 * 
						 * */
						precioImpuesto=ARTICULO_ELECTRONICO*preArticulo;
						
						/*en esta variable (precioArticuloImpuesto) es la suma del
						 * precio base del articulo (preArticulo) más el arancel de este
						 * articulo (precioImpuesto).
						 * 
						 * */
						precioArticuloImpuesto=precioImpuesto+preArticulo;
												
						/* se le suma a la cantidad de paquetes para la clasificacion
						 * segun el caso (cantidadArtE) un valor.
						 * */
						cantidadArtE+=1;
						
						/* se le suma al total de aranceles para la clasificacion
						 * segun el caso (totalArtE) el arancel calculado en este caso 
						 * (precioImpuesto).
						 * */
						totalArtE=totalArtE+precioImpuesto;
						
						//Agregar al total de precios sin aranceles
						artElectSinIm=artElectSinIm+preArticulo;
						
						/*Segun sea el caso, la variable (porcentaje) obtendra
						 * el impuesto que se debe aplicar para esa clasificacion
						 */
						porcentaje="55.71%";
						
						//se obtiene la fecha desde donde se ejecuta el programa
						fecha =paraFecha.format(reloj);
						
						//se obtiene la hora desde donde se ejecuta el programa
						hora=paraHora.format(reloj);
						
						/*Se llama al metodo presentarInformeSobrePaquete() para mostrar el
						 * informe sobre el paquete brindando los parametros necesarios:
						 * Parametro necesario         Variable local en este metodo:
						 *      laHora	 					hora
						 *      laFecha						fecha
						 *      laCedula					cedula
						 *      elNombre					nombre
						 *      laCategoria 				categoriaSelecionada
						 *      elPrecioBase:               preArticulo
						 * 		elArancel:					precioImpuesto
						 * 		elArticulo 					precioArticuloImpuesto
						 * 		elPorcentaje				porcentaje
						 * */
						presentarInformeSobrePaquete(hora,fecha,cedula,nombre,categoriaSelecionada,preArticulo,precioImpuesto,precioArticuloImpuesto,porcentaje);
					}
					break;
				
				/*
				 * Si el usuario elije que la categoria del articulo ingresado es
				 * Articulos linea blanca para el hogar
				 * */
				case 4:
					{
						System.out.println("Categoria: Artículos línea blanca para el hogar");

						/*Se le asigna a (categoriaSeleccionada) la clasificacion del caso
						 * para asi poder mostrarla despues en el metodo 
						 * presentarInformeSobrePaquete(Param...)
						 * */
						categoriaSelecionada="Articulo de linea blanca";
						
						/*Se calcula el arancel del producto, con la mutiplicacion
						 * del impuesto (respecto a la clasificacion (constante) por el monto del 
						 * articulo (preArticulo) ingresado y este valor se guarda 
						 * en (precioImpuesto)
						 * 
						 * */
						precioImpuesto=ARTICULOS_LINEA_BLANCA*preArticulo;
						
						/*en esta variable (precioArticuloImpuesto) es la suma del
						 * precio base del articulo (preArticulo) más el arancel de este
						 * articulo (precioImpuesto).
						 * 
						 * */
						precioArticuloImpuesto=precioImpuesto+preArticulo;
						
						/* se le suma a la cantidad de paquetes para la clasificacion
						 * segun el caso (cantidadArtLineaB) un valor.
						 * */
						cantidadArtLineaB+=1;
						
						/* se le suma al total de aranceles para la clasificacion
						 * segun el caso (totalArtLineaB) el arancel calculado en este caso 
						 * (precioImpuesto).
						 * */
						totalArtLineaB=totalArtLineaB+precioImpuesto;
						
						//Agregar al total de precios sin aranceles
						artLineaBlancaSinIm=artLineaBlancaSinIm+preArticulo;
						
						/*Segun sea el caso, la variable (porcentaje) obtendra
						 * el impuesto que se debe aplicar para esa clasificacion
						 */
						porcentaje="49.27%";
						
						//se obtiene la fecha desde donde se ejecuta el programa
						fecha =paraFecha.format(reloj);
						
						//se obtiene la hora desde donde se ejecuta el programa
						hora=paraHora.format(reloj);
						
						/*Se llama al metodo presentarInformeSobrePaquete() para mostrar el
						 * informe sobre el paquete brindando los parametros necesarios:
						 * Parametro necesario         Variable local en este metodo:
						 *      laHora	 					hora
						 *      laFecha						fecha
						 *      laCedula					cedula
						 *      elNombre					nombre
						 *      laCategoria 				categoriaSelecionada
						 *      elPrecioBase:               preArticulo
						 * 		elArancel:					precioImpuesto
						 * 		elArticulo 					precioArticuloImpuesto
						 * 		elPorcentaje				porcentaje
						 * */
						presentarInformeSobrePaquete(hora,fecha,cedula,nombre,categoriaSelecionada,preArticulo,precioImpuesto,precioArticuloImpuesto,porcentaje);
					}
					break;
				
				/*
				 * Si el usuario elije que la categoria del articulo ingresado es
				 * Herramientas manuales de construccion
				 * */
				case 5:
					{
						System.out.println("Categoria: Herramientas manuales construcción");
						
						/*Se le asigna a (categoriaSeleccionada) la clasificacion del caso
						 * para asi poder mostrarla despues en el metodo 
						 * presentarInformeSobrePaquete(Param...)
						 * */
						categoriaSelecionada="Herramientas manuales construcción";
						
						/*Se calcula el arancel del producto, con la mutiplicacion
						 * del impuesto (respecto a la clasificacion (constante) por el monto del 
						 * articulo (preArticulo) ingresado y este valor se guarda 
						 * en (precioImpuesto)
						 * 
						 * */
						precioImpuesto=ARTICULOS_CONSTRUCCION*preArticulo;
						
						/*en esta variable (precioArticuloImpuesto) es la suma del
						 * precio base del articulo (preArticulo) más el arancel de este
						 * articulo (precioImpuesto).
						 * 
						 * */
						precioArticuloImpuesto=precioImpuesto+preArticulo;
						
						/* se le suma a la cantidad de paquetes para la clasificacion
						 * segun el caso (cantidadHerraC) un valor.
						 * */
						cantidadHerraC+=1;
						
						/* se le suma al total de aranceles para la clasificacion
						 * segun el caso (totalHerraC) el arancel calculado en este caso 
						 * (precioImpuesto).
						 * */
						totalHerraC=totalHerraC+precioImpuesto;
						
						//Agregar al total de precios sin aranceles
						herraConsSinIm=herraConsSinIm+preArticulo;
						
						/*Segun sea el caso, la variable (porcentaje) obtendra
						 * el impuesto que se debe aplicar para esa clasificacion
						 */
						porcentaje="15%";
						
						//se obtiene la fecha desde donde se ejecuta el programa
						fecha =paraFecha.format(reloj);
						
						//se obtiene la hora desde donde se ejecuta el programa
						hora=paraHora.format(reloj);
						
						/*Se llama al metodo presentarInformeSobrePaquete() para mostrar el
						 * informe sobre el paquete brindando los parametros necesarios:
						 * Parametro necesario         Variable local en este metodo:
						 *      laHora	 					hora
						 *      laFecha						fecha
						 *      laCedula					cedula
						 *      elNombre					nombre
						 *      laCategoria 				categoriaSelecionada
						 *      elPrecioBase:               preArticulo
						 * 		elArancel:					precioImpuesto
						 * 		elArticulo 					precioArticuloImpuesto
						 * 		elPorcentaje				porcentaje
						 * */
						presentarInformeSobrePaquete(hora,fecha,cedula,nombre,categoriaSelecionada,preArticulo,precioImpuesto,precioArticuloImpuesto,porcentaje);
					}
					break;
					
				/*si no se selecciona alguno de los anteriores casos (opciones),
				 * se indicara que ingrese un valor valido
				 * el ciclo while se vuelve a repetir hasta que se ingrese una opcion
				 * de las anteriores propuestas.
				 * */
				default:
					System.out.println("Ingrese una opcion valida.");
					break;
			
			}
		}
		
		//Se regresa al menu()
		menu();
	}
	
	
	
	/*presentarInformeSobrePaquete(String laHora,String laFecha,String laCedula, String elNombre, String laCategoria,int elPrecioBase,float elArancel, float elArticulo,String elPorcentaje)
	 * 
	 * el metodo presentarInformeSobrePaquete(Param...) presentara un informe de cada paquete, con valores  dependientes
	 *  del caso selecionadado en el metodo ingresarInformacion() la informacion calculada dependiendo de diferentes
	 * valores segun el caso seleccionado, para el caso seleccionado se llama presentarInformeSobrePaquete(Param...) y los parametros que se
	 * le ingresan se calculan y asignan en el caso elejido dentro del metodo ingresarInformacion()
	 * solicita los siguientes parametros:
	 * laHora: hace referencia a una variable local (hora) del metodo ingresarInformacion(), esta corresponde a la hora del sistema.
	 * laFecha:hace referencia a una variable local (fecha) del metodo ingresarInformacion(), esta corresponde a la fecha del sistema.
	 * laCedula:hace referencia a una variable local (cedula) del metodo ingresarInformacion(), es la cedula que
	 * 	suministra el comprador.
	 * elNombre:hace referencia a una variable local (nombre) del metodo ingresarInformacion(), es el nombre brindado
	 * 	por el comprador.
	 * laCategoria:hace referencia a una variable local (categoriaSelecionada) del metodo ingresarInformacion(),
	 * el usuario al ingresar en algunos de los casos, se asigna la categoria a la cual corresponde dicho caso.
	 * elPrecioBase:hace referencia a una variable local (preArticulo) del metodo ingresarInformacion(), es el
	 *  monto del articulo suministrado por el comprador. 
	 * elArancel:hace referencia a una variable local (precioImpuesto) del metodo ingresarInformacion(), es el arancel que se 
	 * cobró y se calculó en el caso selecionado.
	 * elArticulo:hace referencia a una variable local (precioArticuloImpuesto) del metodo ingresarInformacion(), es el precio base
	 * del articulo más el arancel que se calculó en el caso selecionado.
	 * elPorcentaje:hace referencia a una variable local (porcentaje) del metodo ingresarInformacion(), hace referencia a los impuestos segun
	 * la clasificacion del articulo, se asigna dependiendo del caso.
	 * 
	 * Quedando el metodo asi con la implementacion de variables locales en el metodo ingresarInformacion (para cada caso):
	 * presentarInformeSobrePaquete(hora,fecha,cedula,nombre,categoriaSelecionada,preArticulo,precioImpuesto,precioArticuloImpuesto,porcentaje)
	 * */
	public static void presentarInformeSobrePaquete(String laHora,String laFecha,String laCedula, String elNombre, String laCategoria,int elPrecioBase,float elArancel, float elArticulo,String elPorcentaje) {
		
		/*
		 * Se imprime la informacion del paquete, valores (parametros suministrados) como:
		 * Fecha, hora, nombre del comprador, cedula del comprador,
		 * categoria del articulo, el porcentaje del arancel para la categoria selecionada,
		 * el monto base del articulo que se registro, el monto de arancel cobrado y el monto
		 * base con el arancel para cada el articulo.
		 * */
		System.out.println("\n"+"\t\t"+"Informe Sobre Paquete"+"\n");
		System.out.println("\t"+"Fecha:"+laFecha);
		System.out.println("\t"+"Hora:"+laHora);
		System.out.println("\t"+"Nombre del comprador:"+elNombre);
		System.out.println("\t"+"Cedula del comprador:"+laCedula);
		System.out.println("\t"+"Categoria del articulo:"+laCategoria);
		System.out.println("\t"+"El porcentaje de arancel para esta categoria:"+elPorcentaje);
		System.out.println("\t"+"Precio base del articulo:"+elPrecioBase);
		System.out.println("\t"+"Monto del arancel cobrado:"+elArancel);
		System.out.println("\t"+"Monto del articulo con el arancel:"+elArticulo);
		System.out.println("\n");
	}
	
}
//Cierre de la clase