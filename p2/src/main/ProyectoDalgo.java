package main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        public void quitarVertice(Vertice vertice) {
            if (this.vertices.contains(vertice))
            {
                this.vertices.remove(vertice);
            }
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
        //empezar implementacion de bfs
        //implementar la logica de la matriz
        //funcion de traduccion para salida.


        }
        scanner.close();
    }
}
