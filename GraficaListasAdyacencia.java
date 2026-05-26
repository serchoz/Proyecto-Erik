import java.util.Iterator;

public class GraficaListasAdyacencia<T> implements Grafica<T>{
   private ListaDoblementeLigada <Vertice> vertices;
   private int numeroDeVertices;
   private int numeroDeAristas;
   
   public class Vertice{
        T elemento;
        ListaDoblementeLigada<Arista> aristas;
        
        public boolean visitado;
        
        public int distanciaOrigen;
        
        public Vertice(T elemento ){
            this.elemento=elemento;
            this.aristas= new ListaDoblementeLigada<Arista>();
            this.visitado=false;
            this.distanciaOrigen=Integer.MAX_VALUE;
            this.anterior=null;
        }
        public Vertice anterior;

        
    }
    public class Arista{
        Vertice vecino;
        public int peso;
        public Arista(Vertice vecino, int peso){
            this.vecino=vecino;
            this.peso=peso;
        }

    }
    public GraficaListasAdyacencia(){
    vertices= new ListaDoblementeLigada<>();
    numeroDeAristas=0;
    numeroDeVertices=0;
    }
    
    private class IteradorGrafica implements Iterator<T>{
        private Iterator<Vertice> iterador;
        public IteradorGrafica(){
            iterador= vertices.iterator();
        }
        @Override
        public boolean hasNext(){
            return iterador.hasNext();
        }
        @Override
        public T next(){
            Vertice v= iterador.next();
            return v.elemento;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new IteradorGrafica(); 
    }
    public boolean buscarVertice(T elemento){
        for(Vertice v: vertices){
            if (v.elemento.equals(elemento)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void agregarVertice(T elemento){
    if(elemento == null){
    throw new IllegalArgumentException("El elemento es nulo");
}
    if(buscarVertice(elemento)!=false){
        throw new IllegalArgumentException("Ya se encuentra el Vertice en la grafica");
    }
    Vertice verticito=new Vertice(elemento);
    vertices.agregarFinal(verticito);
    numeroDeVertices++;
    
 }
    public Vertice  obtenervertice(T elemento){
    for (Vertice v:vertices){
        if(v.elemento.equals(elemento)){
            return v;
        }
    }
    return null;
 }
    public boolean buscarArista(Vertice u, Vertice v){
    for(Arista a:v.aristas){
        if(a.vecino.equals(u)){
            return true;
        }
    }
    return false;
 }
    @Override
    public void agregarArista(T e1, T e2){
        if(e1==null||e2==null){
            throw new IllegalArgumentException("El vertice u o v son nulos");
        }
        if(buscarVertice(e1)==false||buscarVertice(e2)==false){
            throw new IllegalArgumentException("El vertice u o v no existen");
        }
        if(e1.equals(e2)){
            throw new IllegalArgumentException("Los vertices u y v son iguales , no se puede añadir la conexion");
        }
        Vertice v1= obtenervertice(e1);
        Vertice v2=obtenervertice(e2);
        if(buscarArista(v1, v2)==false&&buscarArista(v2, v1)==false){
            Arista arista1= new Arista(v1,1);
            Arista arista2=new Arista(v2, 1);  
            v1.aristas.agregarFinal(arista2); 
            v2.aristas.agregarFinal(arista1);    
            numeroDeAristas++;     
        }
    }
    @Override
    public void agregarAristaPonderada(T e1, T e2, int peso){
         if(e1==null||e2==null){
            throw new IllegalArgumentException("El vertice u o v son nulos");
        }
        if(buscarVertice(e1)==false||buscarVertice(e2)==false){
            throw new IllegalArgumentException("El vertice u o v no existen");
        }
        if(e1.equals(e2)){
            throw new IllegalArgumentException("El vertice u o v no existen");
        }
        if (peso <= 0) {
        throw new IllegalArgumentException("El peso debe ser positivo");
        }
        Vertice v1= obtenervertice(e1);
        Vertice v2=obtenervertice(e2);
        if(buscarArista(v1, v2)==false&&buscarArista(v2, v1)==false){
            Arista arista1= new Arista(v1,peso);
            Arista arista2=new Arista(v2, peso);  
            v1.aristas.agregarFinal(arista2); 
            v2.aristas.agregarFinal(arista1);    
            numeroDeAristas++;     
        }

    }
    @Override
    public ListaDoblementeLigada<T> devolverBfs(T inicio){
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<T>();
        if (numeroDeVertices==0){
            return recorrido;
        }
        Vertice inicial = obtenervertice(inicio);
        if (inicial == null) {
            throw new IllegalArgumentException("El vertice inicial no existe");
        }
        for (Vertice v : vertices) {
            v.visitado = false;
        }
        Cola<Vertice> colita = new Cola<Vertice>();
        colita.meter(inicial);
        inicial.visitado=true;
        while(!colita.estaVacia()){
            Vertice actual=colita.sacar();
            recorrido.agregarFinal(actual.elemento);
            for(Arista a:actual.aristas){
                if(a.vecino.visitado==false){
                    colita.meter(a.vecino);
                    a.vecino.visitado=true;
                }
                }
                
            }
            return recorrido;
    }
    @Override
    public ListaDoblementeLigada<T> devolverDfs(T inicio){
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<T>();
        if(numeroDeVertices==0){
            return recorrido;
        }
        Vertice inicial = obtenervertice(inicio);
        if (inicial == null) {
            throw new IllegalArgumentException("El vertice inicial no existe");
        }
                for (Vertice v : vertices) {
                     v.visitado = false;
                 }
                Pila <Vertice> pilita = new Pila<Vertice>();
                pilita.meter(inicial);
                inicial.visitado=true;
                while(!pilita.estaVacia()){
                Vertice actual=pilita.sacar();
                recorrido.agregarFinal(actual.elemento);
                for(Arista a:actual.aristas){
                if(a.vecino.visitado==false){
                    pilita.meter(a.vecino);
                    a.vecino.visitado=true;
                }
                }
            }
                return recorrido;
            }
    public void eliminarAristaAux(Vertice v, Vertice u){
    Arista aristaEliminar = null;

    for(Arista a : v.aristas){
        if(a.vecino.equals(u)){
            aristaEliminar = a;
            break;
        }
    }

    if(aristaEliminar != null){
        v.aristas.eliminar(aristaEliminar);
    }
}
    @Override
    public void eliminarVertice(T elemento){

        if(elemento == null){
            throw new IllegalArgumentException("El vertice a eliminar es nulo");
        }

        Vertice verticeEliminar = obtenervertice(elemento);

        if(verticeEliminar == null){
            throw new IllegalArgumentException("El vertice a eliminar no existe");
        }

        int aristasEliminadas = 0;

        for(Arista a : verticeEliminar.aristas){

            eliminarAristaAux(a.vecino, verticeEliminar);

            aristasEliminadas++;
        }

        vertices.eliminar(verticeEliminar);

        numeroDeVertices--;

        numeroDeAristas -= aristasEliminadas;
    }
    @Override
    public void eliminarArista(T e1, T e2){
    if(e1==null||e2==null){
        throw new IllegalArgumentException("EL vertice u o v son nulos");
    }
    if(buscarVertice(e1)==false||buscarVertice(e2)==false){
        throw new IllegalArgumentException("EL vertice u o v no existen en la Grafica");
    }
    if(e1.equals(e2)){
        throw new IllegalArgumentException("EL vertice u o v son el mismo");
    }
    Vertice v1= obtenervertice(e1);
            Vertice v2=obtenervertice(e2);
    if(buscarArista(v1, v2)==false||buscarArista(v2, v1)==false){
    throw new IllegalArgumentException("La arista a eliminar no existe");
    }
    eliminarAristaAux(v1, v2);
        eliminarAristaAux(v2, v1);
        numeroDeAristas--;
    }   
    @Override
    public ListaDoblementeLigada<T> devolverRutaMasCortaNoPonderada(T inicio, T fin){
        if (!buscarVertice(inicio) || !buscarVertice(fin)){
            throw new IllegalArgumentException("Checa tus vertices mono"); 
        }
        Vertice vertiD = new Vertice(null);
        Vertice vertiO = new Vertice(null);
        for (Vertice v : vertices){
            v.anterior = null; 
            v.visitado = false;
            
            if (v.elemento.equals(inicio)){
                vertiO = v;
            }
            if (v.elemento.equals(fin)){
                vertiD = v; 
            }
        }
        Cola<Vertice> colita = new Cola<Vertice>();
        vertiO.visitado = true;
        colita.meter(vertiO);
        while (!colita.estaVacia()){
            Vertice actual = colita.sacar();
            if (!actual.elemento.equals(fin)){
                for (Arista a : actual.aristas){
                    if(a.vecino.visitado == false){
                        a.vecino.anterior = actual;
                        a.vecino.visitado = true;
                        colita.meter(a.vecino);
                    }
                }
            }
        }
        if (vertiD.visitado == false){
            throw new IllegalArgumentException("Trono xd");
        }
        ListaDoblementeLigada<T> camino = new ListaDoblementeLigada<>();
        Vertice actual = vertiD;
        while (actual != null){
            camino.agregar(actual.elemento);
            actual = actual.anterior;
        }
        ListaDoblementeLigada<T> caminoCorrecto = new ListaDoblementeLigada<>();
        
        for (int i = camino.devolverLongitud() - 1; i >= 0; i--) {
            T elementoEeee = camino.acceder(i);
            caminoCorrecto.agregar(elementoEeee);
        }
        return caminoCorrecto; 
    }

    private void iniciarVertices() {
        for (Vertice v : vertices) {
            v.anterior = null;
            v.visitado = false;
            v.distanciaOrigen = Integer.MAX_VALUE;
        }
    }

    private Vertice menorDistancia() {
        Vertice minimo = null;
        int minDist = Integer.MAX_VALUE;
        for (Vertice v : vertices) {
            if (v.visitado == false && v.distanciaOrigen < minDist) {
                minDist = v.distanciaOrigen;
                minimo = v;
            }
        }
        return minimo;
    }

    private ListaDoblementeLigada<T> construirRuta(Vertice destino) {
        ListaDoblementeLigada<T> caminoInvertido = new ListaDoblementeLigada<>();
        Vertice rastreador = destino;
        while (rastreador != null) {
            caminoInvertido.agregar(rastreador.elemento);
            rastreador = rastreador.anterior;
        }

        ListaDoblementeLigada<T> caminoCorrecto = new ListaDoblementeLigada<>();
        for (int i = caminoInvertido.devolverLongitud() - 1; i >= 0; i--) {
            caminoCorrecto.agregar(caminoInvertido.acceder(i));
        }
        return caminoCorrecto;
    }

    @Override
    public ListaDoblementeLigada<T> rutaMasCortaPonderada(T inicio, T fin){
        if (!buscarVertice(inicio) || !buscarVertice(fin)) {
            throw new IllegalArgumentException("Checa tus vertices mono, no existen");
        }

        iniciarVertices();

        Vertice vertiO = null;
        Vertice vertiD = null;
        
        for (Vertice v : vertices) {
            if (v.elemento.equals(inicio)) vertiO = v;
            if (v.elemento.equals(fin)) vertiD = v;
        }
        
        vertiO.distanciaOrigen = 0;

        for (int i = 0; i < devolverNumeroDeVertices(); i++) {
            
            Vertice actual = menorDistancia();

            if (actual == null || actual.distanciaOrigen == Integer.MAX_VALUE || actual.elemento.equals(fin)) {
                break;
            }

            actual.visitado = true;

            for (Arista a : actual.aristas) {
                if (a.vecino.visitado == false) {
                    int nuevaDistancia = actual.distanciaOrigen + a.peso;
                    
                    if (nuevaDistancia < a.vecino.distanciaOrigen) {
                        a.vecino.distanciaOrigen = nuevaDistancia;
                        a.vecino.anterior = actual;
                    }
                }
            }
        }

        if (vertiD == null || vertiD.distanciaOrigen == Integer.MAX_VALUE) {
            return new ListaDoblementeLigada<>(); 
        }
        
        return construirRuta(vertiD);
    }

    public ListaDoblementeLigada<ListaDoblementeLigada<T>> componentesConexasDespuesDeEliminarVertice(T v) {
        
        ListaDoblementeLigada<ListaDoblementeLigada<T>> componentes = new ListaDoblementeLigada<>();

        if (v == null) {
            throw new IllegalArgumentException("Revisa el vertice lince");
        }
        if (!buscarVertice(v)) {
            throw new IllegalArgumentException("No existe el vertice xddd");
        }
        eliminarVertice(v);
        for (Vertice vertice : vertices) {
            vertice.visitado = false;
        }
        for (Vertice vertice : vertices) {
            if (vertice.visitado == false) {
                ListaDoblementeLigada<T> componente = new ListaDoblementeLigada<>();
                
                Cola<Vertice> cola = new Cola<>();
                vertice.visitado = true;
                cola.meter(vertice);

                while (!cola.estaVacia()) {
                    Vertice actual = cola.sacar();
                    componente.agregar(actual.elemento);
                    for (Arista a : actual.aristas) {
                        if (a.vecino.visitado == false) {
                            a.vecino.visitado = true;
                            cola.meter(a.vecino);
                        }
                    }
                }
                componentes.agregar(componente);
            }
        }
        return componentes;
    }

    @Override
    public int devolverNumeroDeVertices(){
        return numeroDeVertices;
    }
    @Override
    public int devolverNumeroDeAristas(){
        return numeroDeAristas;
    }
}