
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoblementeLigada<T> implements Lista<T> {

     public class Nodo{
          public T elemento;

          public Nodo siguiente;

          public Nodo anterior;

          public Nodo(T e) {
               this.elemento = e;
          }
     }

     private class IteradorDoubleLinkedList implements Iterator<T> {

          public Nodo anterior;

          public Nodo siguiente;

          public IteradorDoubleLinkedList() {
               siguiente = cabeza;
          }

          @Override

          public boolean hasNext() {
               return siguiente != null;
          }

          @Override
          public T next() {
               if (siguiente == null) {
                    throw new NoSuchElementException("El elemento es null");
               }
               anterior = siguiente;
               siguiente = siguiente.siguiente;
               return anterior.elemento;
          }
     }

     public Nodo cabeza;

     public Nodo rabo;

     public  int longitud;

     public Iterator<T> iterator() {
          return new IteradorDoubleLinkedList();
     }

     public ListaDoblementeLigada(){
          this.cabeza = null;
          this.rabo = null;
          this.longitud = 0;
     }

     @Override
     public void agregar(T elemento) throws IllegalArgumentException {
          /*Aqui va tu código*/
          if (elemento==null){
               throw new IllegalArgumentException("Entrada invalida");
          }
          Nodo nuevo1= new Nodo(elemento);
          if (longitud==0){
               cabeza = nuevo1;
               rabo=nuevo1;
               longitud=longitud+1;
               return;
          }
          cabeza.anterior=nuevo1;
          nuevo1.siguiente=cabeza;
          cabeza=nuevo1;
          longitud=longitud+1;
          return;
     }

     @Override
     public void eliminar(T elemento){
          /*Aqui va tu código*/
           if (elemento==null){
               throw new IllegalArgumentException("Entrada invalida");
          }
          
           if (longitud==0){
               return;
          }
          if (longitud==1){
               if (elemento.equals(cabeza.elemento)){
                    cabeza=null;
                    rabo=null;
                    longitud=0;
                    return;
               }
          }
          if(cabeza.elemento.equals(elemento)){
                    cabeza=cabeza.siguiente;
                    cabeza.anterior=null;
                    longitud=longitud-1;
                    return;
               }
               if(rabo.elemento.equals(elemento)){
                    rabo=rabo.anterior;
                    rabo.siguiente=null;
                    longitud=longitud-1;
                    return;
               }

          Nodo actual=cabeza.siguiente;
          while(actual!=null){
               if(actual.elemento.equals(elemento)){
                    actual.anterior.siguiente=actual.siguiente;
                    actual.siguiente.anterior=actual.anterior;
                    longitud=longitud-1;
                    return;
               }
               actual=actual.siguiente;
          }
     }


     @Override
     public boolean buscar(T elemento) {
          /*Aqui va tu código*/
          if (elemento==null){
               throw new IllegalArgumentException("Entrada invalida");
          }
          
           if (longitud==0){
               return false;
          }

          Nodo actual=cabeza;
          while(actual!=null){
               if(actual.elemento.equals(elemento)){
                   
                    return true;
               }
               actual=actual.siguiente;
               
          }
          return false;
     }

     @Override
     public void eliminar(int i) {
          /*Aqui va tu código*/
          if(longitud==0){
               return;
          }
          if(i<0 || i>longitud-1){
               throw new IllegalArgumentException("Entrada invalida");
          }
          if(longitud==1){
               cabeza=null;
               rabo=null;
               longitud--;
               return;
          }
          if(i==0){
               cabeza=cabeza.siguiente;
               cabeza.anterior=null;
               longitud--;
               return;
          }
          
          if(i==longitud-1){
               rabo=rabo.anterior;
               rabo.siguiente=null;
               longitud--;
               return;
          }
          int contador=0;
          Nodo actual=cabeza;
          while(contador<i){
               actual=actual.siguiente;
               contador++;
          }
          actual.anterior.siguiente=actual.siguiente;
          actual.siguiente.anterior=actual.anterior;
          longitud--;
          return;
          
     }

     @Override
     public T acceder(int i) throws IllegalArgumentException {
          /*Aqui va tu código*/  
           if(i<0 || i>longitud-1){
               throw new IllegalArgumentException("Entrada invalida");
          }
          Nodo actual=cabeza;
          int cont=0;
          while(cont<i){
               actual=actual.siguiente;
               cont++;
          }
         return actual.elemento;
     }

     @Override
     public int devolverIndiceElemento(T elemento) throws IllegalArgumentException{
          /*Aqui va tu código*/
          if (elemento==null){
                throw new IllegalArgumentException("Elemento no valido");
          }
          Nodo actual=cabeza;
          int k=0;
          
          while(actual!=null){
               if(actual.elemento.equals(elemento)){
                    return  k;
               }
               actual=actual.siguiente;
               k++;
          }
          throw new IllegalArgumentException("No se encontro el elemento");
     }
     

     @Override
     public int devolverLongitud(){
          /*Aqui va tu código*/
          
          return longitud;
     }

     public void agregarFinal(T elemento) throws IllegalArgumentException {
          /*Aqui va tu código*/
          if (elemento==null){
               throw new IllegalArgumentException("Elemento invalido");
          }
          Nodo nuevo = new Nodo(elemento);
          if(longitud==0){
               cabeza= nuevo;
               rabo= nuevo;
               longitud++;
               return;
          }
          rabo.siguiente=nuevo;
          nuevo.anterior=rabo;
          rabo=nuevo;
          longitud ++;
         
     }

     public ListaDoblementeLigada<T> reversa(){
          /*Aqui va tu código*/
          ListaDoblementeLigada<T> nueva = new ListaDoblementeLigada<>();
           if(longitud==0){
               return nueva;
          }
          
               Nodo actual=cabeza;
               while(actual!= null){
                    nueva.agregar(actual.elemento);
                    actual=actual.siguiente;
               }
               
               return nueva;
          
     }

     
     private Nodo accederNodo(int i) {
          /*Aqui va tu código*/
            if(i<0 || i>longitud-1){
               throw new IllegalArgumentException("Entrada invalida");
          }
          Nodo actual=cabeza;
          int cont=0;
          while(cont<i){
               actual=actual.siguiente;
               cont++;
          }
         return actual;
     }

     public String toString(){
          String s = "[";
          int cont = 0;
          for(T elem : this){
               if(cont == 0){
                    s = s + elem.toString();
                    cont++;
               }else{
                    s = s + "," +elem.toString();
               }
          }
          s = s + "]";
          return s;
     }
}