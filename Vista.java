import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase encargada de manejar la interacción con el usuario mediante la consola.
 * Despliega menús, captura los datos ingresados y muestra los resultados en pantalla.
 */
public class Vista {

    /** Objeto escáner para la lectura. */
    private Scanner sc;

    /**
     * Inicializa una nueva instancia de la vista para el lector de consola.
     */
    public Vista(){
        this.sc=new Scanner(System.in);
    }

    /**
     * Muestra el título principal del programa.
     */
    public void titulo(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("   Menu de Rutas del Metro   ");
        System.out.println("=============================");
    }

    /**
     * Despliega la lista completa de opciones disponibles para gestionar el metro.
     */
    public void menu(){
        System.out.println();
        System.out.println("1. Mostrar estaciones del metro (completamente)");
        System.out.println("2. Mostrar estaciones por línea");
        System.out.println("3. Agregar estación (agrega vértice y arista)");
        System.out.println("4. Eliminar estación (elimina vétice)");
        System.out.println("5. Agregar transborde o tunel (agregar arista)");
        System.out.println("6. Eliminar transborde o tunel (eliminar arista)");
        System.out.println("7. Camino más corto sin considerar estaciones");
        System.out.println("8. Camino más corto considerando estaciones o tramos cerrados");
        System.out.println("9. Devolver número de estaciones (vértices)");
        System.out.println("10. Devolver número de tuneles (aristas)");
        System.out.println("11. Mostrar componentes conexas tras eliminar estación");
        System.out.println("12. Mostrar estaciones eliminadas");
        System.out.println("13. Salir");
        System.out.println();
    }

    /**
     * Solicita una cadena de texto al usuario imprimiendo previamente un mensaje.
     * @param mensaje Declara la indicación a mostrar en consola.
     * @return La línea de texto ingresada por el usuario.
     */
    public String texto(String mensaje){
        System.out.println(mensaje);
        return sc.nextLine();
    }

    /**
     * Captura el número correspondiente a la opción del menú elegida.
     * Gestiona las excepciones en caso de que el usuario ingrese texto en lugar de números.
     * @return El valor entero introducido o -1 si ocurre un error.
     */
    public int opcion(){
        try{
            int opcion=sc.nextInt();
            sc.nextLine();
            return opcion; // para el controlador
        }catch(InputMismatchException e){ // para cuando se escribe letras
            sc.nextLine();
            return -1; //codigo de error para el controlador
        }
    }

    /**
     * Solicita un dato numérico al usuario validando que la entrada sea correcta.
     * @param mensaje Declara la indicación a mostrar antes de solicitar el número.
     * @return El entero capturado o -1 si es inválido.
     */
    public int entero(String mensaje){
        System.out.println(mensaje);
        try{
            int numero=sc.nextInt();
            sc.nextLine();
            return numero;
        }catch(InputMismatchException e){
            sc.nextLine();
            return -1;
        }
    }

    /**
     * Imprime de forma directa un bloque de texto en la consola.
     * @param texto Declara el contenido a imprimir.
     */
    public void imprimir(String texto){
        System.out.println(texto);
    }

    /**
     * Cierra el escáner al terminar la ejecución.
     */
    public void apagarAuto(){
        sc.close();
    }

}