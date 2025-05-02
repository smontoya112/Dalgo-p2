import java.util.*;
import java.io.*;

public class ProblemaP2 {
    static class State {
        int platform;
        int energy;
        int actions;
        String path;
        
        State(int platform, int energy, int actions, String path) {
            this.platform = platform;
            this.energy = energy;
            this.actions = actions;
            this.path = path;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < testCases; t++) {
            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]); // Número de plataformas (sin contar la inicial)
            int e = Integer.parseInt(firstLine[1]); // Unidades de energía inicial
            
            Set<Integer> robots = new HashSet<>();
            String[] robotsLine = br.readLine().split(" ");
            for (String robot : robotsLine) {
                if (!robot.isEmpty()) {
                    robots.add(Integer.parseInt(robot));
                }
            }
            
            Map<Integer, Integer> powers = new HashMap<>();
            String[] powersLine = br.readLine().split(" ");
            for (int i = 0; i < powersLine.length; i += 2) {
                int platform = Integer.parseInt(powersLine[i]);
                int jump = Integer.parseInt(powersLine[i+1]);
                powers.put(platform, jump);
            }
            
            // BFS modificado
            Queue<State> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n+2][e+1]; // +1 para plataforma 0 y energía extra
            queue.add(new State(0, e, 0, ""));
            visited[0][e] = true;
            
            String result = "NO SE PUEDE";
            
            while (!queue.isEmpty()) {
                State current = queue.poll();
                
                // Verificar si llegamos a la última plataforma
                if (current.platform == n) {
                    result = current.actions + " " + current.path.trim();
                    break;
                }
                
                // Movimientos posibles:
                // 1. Caminar hacia adelante (C+)
                int nextPlatform = current.platform + 1;
                if (nextPlatform <= n && !robots.contains(nextPlatform) && 
                    (nextPlatform > n || !visited[nextPlatform][current.energy])) {
                    visited[nextPlatform][current.energy] = true;
                    queue.add(new State(nextPlatform, current.energy, current.actions + 1, 
                                      current.path + "C+ "));
                }
                
                // 2. Caminar hacia atrás (C-)
                nextPlatform = current.platform - 1;
                if (nextPlatform >= 0 && !robots.contains(nextPlatform) && 
                    (nextPlatform > n || !visited[nextPlatform][current.energy])) {
                    visited[nextPlatform][current.energy] = true;
                    queue.add(new State(nextPlatform, current.energy, current.actions + 1, 
                                      current.path + "C- "));
                }
                
                // 3. Usar poder de salto (S+ o S-)
                if (powers.containsKey(current.platform)) {
                    int jump = powers.get(current.platform);
                    
                    // Salto hacia adelante (S+)
                    nextPlatform = current.platform + jump;
                    if (nextPlatform <= n && !robots.contains(nextPlatform) && 
                        (nextPlatform > n || !visited[nextPlatform][current.energy])) {
                        visited[nextPlatform][current.energy] = true;
                        queue.add(new State(nextPlatform, current.energy, current.actions + 1, 
                                          current.path + "S+ "));
                    }
                    
                    // Salto hacia atrás (S-)
                    nextPlatform = current.platform - jump;
                    if (nextPlatform >= 0 && !robots.contains(nextPlatform) && 
                        (nextPlatform > n || !visited[nextPlatform][current.energy])) {
                        visited[nextPlatform][current.energy] = true;
                        queue.add(new State(nextPlatform, current.energy, current.actions + 1, 
                                          current.path + "S- "));
                    }
                }
                
                // 4. Teletransportarse (Tx)
                for (int target = 0; target <= n; target++) {
                    if (target != current.platform && !robots.contains(target)) {
                        int distance = Math.abs(target - current.platform);
                        int newEnergy = current.energy - distance;
                        
                        if (newEnergy >= 0 && (target > n || !visited[target][newEnergy])) {
                            visited[target][newEnergy] = true;
                            String direction = (target > current.platform) ? "T" + distance : "T-" + distance;
                            queue.add(new State(target, newEnergy, current.actions + 1, 
                                              current.path + direction + " "));
                        }
                    }
                }
            }
            
            System.out.println(result);
        }
    }
}