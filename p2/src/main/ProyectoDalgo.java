package main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

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
    }
    
    public class Arista{
        Vertice origen;
        Vertice destino;
        int peso;
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
        public Vertice(int id){
            this.id = id;
            this.robot = false;
            this.poder = false;
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int numeroCasos = scanner.nextInt();
        for(int i = 0; i<= numeroCasos;i++){
            //inicio prueba
            int numPlat = scanner.nextInt();
            int energia = scanner.nextInt();
            
        }
        scanner.close();
    }
}
