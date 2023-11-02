import java.util.*;

class Node {
    State state;
    Node parent;
    int cost;
    int heuristic;

    public Node(State state, Node parent, int cost, int heuristic) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }
}

class State {
    // Implementasikan representasi status 
}

class Problem {
    // Implementasikan detail yang khusus untuk masalah
    public State getInitialState() {
        // Implementasikan status awal masalah
        return new State();
    }

    public ArrayList<Node> expand(Node node) {
        // Implementasikan ekspansi node di sini
        return new ArrayList<>();
    }

    public boolean isGoal(State state) {
        // Implementasikan pemeriksaan status tujuan 
        return false;
    }

    public int calculateHeuristic(State state) {
        // Implementasikan perhitungan heuristik 
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem();  
        Node initialNode = new Node(problem.getInitialState(), null, 0, problem.calculateHeuristic(problem.getInitialState()));

        // A* Search
        Node aStarSolution = aStarSearch(problem, initialNode);
        if (aStarSolution != null) {
            printSolution(aStarSolution);
        } else {
            System.out.println("A* Search tidak menemukan solusi.");
        }

        // Greedy Best-First Search
        Node gbfsSolution = gbfsSearch(problem, initialNode);
        if (gbfsSolution != null) {
            printSolution(gbfsSolution);
        } else {
            System.out.println("Greedy Best-First Search tidak menemukan solusi.");
        }
    }

    public static Node aStarSearch(Problem problem, Node initialNode) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost + node.heuristic));
        Set<State> closedList = new HashSet<>();
        openList.add(initialNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            if (problem.isGoal(currentNode.state)) {
                return currentNode;
            }

            closedList.add(currentNode.state);
            ArrayList<Node> children = problem.expand(currentNode);
            for (Node child : children) {
                if (!closedList.contains(child.state)) {
                    openList.add(child);
                }
            }
        }

        return null;
    }

    public static Node gbfsSearch(Problem problem, Node initialNode) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Set<State> closedList = new HashSet<>();
        openList.add(initialNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            if (problem.isGoal(currentNode.state)) {
                return currentNode;
            }

            closedList.add(currentNode.state);
            ArrayList<Node> children = problem.expand(currentNode);
            for (Node child : children) {
                if (!closedList.contains(child.state)) {
                    openList.add(child);
                }
            }
        }

        return null;
    }

    public static void printSolution(Node node) {
        // mengimplementasikan pencetakan solusi di sini
    }
}
