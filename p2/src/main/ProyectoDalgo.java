package main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class ProyectoDalgo {
    public class Grafo{
        Collection<Arista> aristas;
        Collection<Vertice> vertices;
        int numAristas;
        int numVertices;
        public Grafo(int numVertices, int numAristas){
            this.numAristas = numAristas;
            this.numVertices = numVertices;
            this.aristas = new ArrayList<>();
            this.vertices = new ArrayList<>();
        }
        public void agregarArista(Arista arista){
            this.aristas.add(arista);
        }
        public void agregarVertice(Vertice vertice){
            this.vertices.add(vertice);
        }
        public void quitarVertice(Vertice vertice) {
            if (this.vertices.contains(vertice))
            {
                this.vertices.remove(vertice);
            }
        }
        
        //empezar implementacion de bfs
        public List<Vertice> bfs(Grafo grafo, Vertice vInicio) {
            List<Vertice> recorrido = new ArrayList<>();
            boolean[] visitado = new boolean[numVertices + 2]; // +2 por el vértice -1
            Queue<Vertice> cola = new LinkedList<>();

            cola.add(vInicio);
            visitado[vInicio.id + 1] = true; // para que -1 se mapee a 0, 0 a 1, etc.

            while (!cola.isEmpty()) {
                Vertice actual = cola.poll();
                recorrido.add(actual);
        
                // Buscar adyacentes desde las aristas
                for (Arista a : aristas) {
                    Vertice vecino = null;
                    if (a.origen.id == actual.id) {
                        vecino = a.destino;
                    } else if (a.destino.id == actual.id) {
                        vecino = a.origen;
                    }
        
                    if (vecino != null && !visitado[vecino.id + 1]) {
                        cola.add(vecino);
                        visitado[vecino.id + 1] = true;
                    }
                }
            }
        
            return recorrido;
        }

        public List<Vertice> bfs(Grafo grafo, Vertice vInicio) {
            List<Vertice> recorrido = new ArrayList<>();
            boolean[] visitado = new boolean[numVertices + 2]; // +2 por el vértice -1
            Queue<Vertice> cola = new LinkedList<>();
        
        
            cola.add(vInicio);
            visitado[vInicio.id + 1] = true; // para que -1 se mapee a 0, 0 a 1, etc.
        
            while (!cola.isEmpty()) {
                Vertice actual = cola.poll();
                recorrido.add(actual);
        
                // Buscar adyacentes desde las aristas
                for (Arista a : aristas) {
                    Vertice vecino = null;
                    if (a.origen.id == actual.id) {
                        vecino = a.destino;
                    } else if (a.destino.id == actual.id) {
                        vecino = a.origen;
                    }
        
                    if (vecino != null && !visitado[vecino.id + 1]) {
                        cola.add(vecino);
                        visitado[vecino.id + 1] = true;
                    }
                }
            }
        
            return recorrido;
        }
        
    }
    
    public class Arista{
        Vertice origen;
        Vertice destino;
        int peso;
        boolean tp = false;
        public Arista(Vertice origen, Vertice destino, int peso){
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;

        }
    }

    public class Vertice{
        boolean robot;
        boolean poder;
        int id;
        int pesoPoder;
        int energiaDisponible;
        public Vertice(int id){
            this.id = id;
            this.robot = false;
            this.poder = false;
            this.energiaDisponible = 0;
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int numeroCasos = scanner.nextInt();
        for(int i = 0; i<= numeroCasos;i++){
            //inicio prueba
            int numPlat = scanner.nextInt();
            int energia = scanner.nextInt();
            String linea = scanner.nextLine();
            //lista de los vertices que tienen robots
            List<Integer> robots = new ArrayList<>();
            for (String s : linea.split("\\s+")) {
                robots.add(Integer.parseInt(s));
            }
            List<Integer> plataPoder = new ArrayList<>();
            List<Integer> pesosPoder = new ArrayList<>();
            while(scanner.hasNext()){
                int plataformaPoder = scanner.nextInt();
                int pesoPoder = scanner.nextInt();
                plataPoder.add(plataformaPoder);
                pesosPoder.add(pesoPoder);
            }
            //crear lista de vertices
            List<Vertice> verts  = new ArrayList<>();
            //meter el nodo -1 donde inicia samus
            Vertice v0 = new ProyectoDalgo().new Vertice(-1);
            v0.energiaDisponible = energia;
            verts.add(v0);
            for(int e = 0;e<=numPlat;e++){
                if(plataPoder.contains(e)){
                    int peso = pesosPoder.get(plataPoder.indexOf(e));
                    Vertice vertice = new ProyectoDalgo().new Vertice(e);
                    vertice.pesoPoder = peso;
                    vertice.poder = true;
                    verts.add(vertice);
                }else if(robots.contains(e)){
                    Vertice vertice = new ProyectoDalgo().new Vertice(e);
                    vertice.robot = true;
                    verts.add(vertice);
                    }
                 else{ 
                    Vertice vertice = new ProyectoDalgo().new Vertice(e);
                    verts.add(vertice);
                }
            }
            //crear lista de aristas
            int indiceUltimo = 0;
            List<Arista> aristas = new ArrayList<>();
            if(verts.size()>1 && !verts.get(1).robot){
                Arista a0 = new ProyectoDalgo().new Arista(v0, verts.get(1), 1);
                Arista a0p = new ProyectoDalgo().new Arista(verts.get(1),v0, 1);
                aristas.add(a0);
                aristas.add(a0p);
            }
            for (int j=1;j<verts.size();j++){
                //es una arista normal
                if(!verts.get(j).poder && !verts.get(j).robot){
                    //verificar si hago un camino al siguiente vertice y este es normal
                    if(!verts.get(j+1).robot && !verts.get(j+1).poder){
                        Arista a1 = new ProyectoDalgo().new Arista(verts.get(j), verts.get(j+1), 1);
                        Arista a2 = new ProyectoDalgo().new Arista(verts.get(j+1), verts.get(j), 1);
                        aristas.add(a1);
                        aristas.add(a2);
                    }
                    //si el siguente nodo es un poder, se puede o no usar el poder
                    else if(verts.get(j+1).poder){
                        Arista a1 = new ProyectoDalgo().new Arista(verts.get(j), verts.get(j+1), 1);
                        Arista a2 = new ProyectoDalgo().new Arista(verts.get(j+1), verts.get(j), 1);
                        aristas.add(a1);
                        aristas.add(a2);
                    }
                }else if(verts.get(j).poder){
                    int peso = verts.get(j).pesoPoder;
                    if(j-peso>=0 && !verts.get(j-peso).robot){
                        Arista a1 = new ProyectoDalgo().new Arista(verts.get(j), verts.get(j-peso), 1);
                        aristas.add(a1);
                    }
                    if(j+peso<=verts.size() && !verts.get(j+peso).robot){
                        Arista a1 = new ProyectoDalgo().new Arista(verts.get(j), verts.get(j+peso), 1);
                        aristas.add(a1);
                    }
                }

                if(!verts.get(j).robot)
                {
                    if (j - indiceUltimo > 1) 
                    {
                        {
                        int peso =  j-indiceUltimo;
                        Arista a1 = new ProyectoDalgo().new Arista(verts.get(j), verts.get(indiceUltimo), peso);
                        Arista a2 = new ProyectoDalgo().new Arista(verts.get(indiceUltimo), verts.get(j), peso);
                        a1.tp = true;
                        a2.tp = true;
                        aristas.add(a1);
                        aristas.add(a2);
                    }
                    if(j + 1 < verts.size())
                    {
                        if(verts.get(j+1).robot){
                            indiceUltimo = j;
                        }
                    }

                }
            }
            Grafo grafo = new ProyectoDalgo().new Grafo(verts.size(), aristas.size());
            grafo.aristas = aristas;
            grafo.vertices = verts;

        //funcion de traduccion para salida.
        

        }
        scanner.close();
    }
}
}