
import services.MinSpanningTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class PrimAlgorithm implements MinSpanningTree {

    private static class Line {

        String dest;
        int weight;

        public Line(String dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

    }

    private static class Connection {

        String source;
        String dest;
        int weight;

        public Connection(String source, String dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class ConnectComparator implements Comparator<Connection> {
        public int compare(Connection c1, Connection c2) {
            return c1.weight - c2.weight;
        }
    }

    HashMap<String, LinkedList<Line>> graph = new HashMap<>();

    public String findMST(String pathToFile) {
        readGraph(pathToFile);

        PriorityQueue<Connection> pq = new PriorityQueue<>(new ConnectComparator());
        Set<String> visited = new HashSet<>();
        List<Connection> MST = new LinkedList<>();

        String curr = (String) graph.keySet().toArray()[0];

        for (Line line : graph.get(curr)) {
            pq.add(new Connection(curr, line.dest, line.weight));
        }
        visited.add(curr);

        while (!pq.isEmpty()) {
            Connection first = pq.poll();
            curr = first.source;
            String dest = first.dest;
            if (!visited.contains(curr) || !visited.contains(dest)) {
                MST.add(new Connection(curr, dest, first.weight));
                visited.add(curr);
                visited.add(dest);
                curr = dest;
                for (Line con : graph.get(curr)) {
                    if (!visited.contains(con.dest))
                        pq.add(new Connection(curr, con.dest, con.weight));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int size = MST.size();
        for (Connection con : MST) {
            size--;
            sb.append(con.source);
            sb.append('_');
            sb.append(con.weight);
            sb.append('_');
            sb.append(con.dest);
            if (size != 0)
                sb.append('|');
        }
        return sb.toString();
    }

    private void readGraph(String pathToFile) {

        if (pathToFile == null)
            throw new IllegalArgumentException("Path to file can not be null!");
        if (pathToFile.isEmpty()) {
            throw new IllegalArgumentException("Path to file con not be empty!");
        }
        File file = new File(pathToFile);
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File with graph not found!");
        }
        int linenum = 0;
        while (sc.hasNextLine()) {
            linenum++;

            String line = sc.nextLine();
            String pattern = "[A-Za-z]+ [A-Za-z]+ \\d+";

            if (!Pattern.matches(pattern, line)) {
                throw new IllegalArgumentException("Wrong graph representation in line " + linenum + ": \"" + line + "\"! Right pattern: [A-Za-z]+_[A-Za-z]+_[int]\\n");

            }

            String[] elements = line.split(" ");
            String curr = elements[0];
            String next = elements[1];
            int weight = Integer.parseInt(elements[2]);

            if (Objects.equals(curr, next)) {
                throw new IllegalArgumentException("Graph can not contain loops!");
            }
            LinkedList<Line> tmp = new LinkedList<>();
            LinkedList<Line> tmprev = new LinkedList<>();
            if (graph.containsKey(curr)) {
                tmp = graph.get(curr);
            }
            tmp.add(new Line(next, weight));
            graph.put(curr, tmp);
            if (graph.containsKey(next)) {
                tmprev = graph.get(next);
            }
            tmprev.add(new Line(curr, weight));
            graph.put(next, tmprev);
        }
        sc.close();

        if (graph.isEmpty())
            throw new IllegalArgumentException("Graph was empty!");

        if (!isConnected())
            throw new IllegalArgumentException("Graph is not connected!");
    }

    private boolean isConnected() {
        List<String> visited = new ArrayList<>();
        String curr = (String) graph.keySet().toArray()[0];
        dfs(curr, visited);
        return visited.size() == graph.size();
    }

    private void dfs(String node, List<String> visited) {
        visited.add(node);
        for (Line line : graph.get(node)) {
            String next = line.dest;
            if (!visited.contains(next)) {
                dfs(next, visited);
            }
        }
    }

}
