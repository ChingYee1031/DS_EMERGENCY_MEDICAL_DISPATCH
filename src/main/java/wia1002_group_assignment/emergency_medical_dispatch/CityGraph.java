package wia1002_group_assignment.emergency_medical_dispatch;

import java.util.*;

public class CityGraph {
    // Adjacency list representation: Map<Source, List<Edge>>
    private final Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public static class Edge {
        String target;
        double weight; // Time/Distance

        public Edge(String target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public void addLocation(String location) {
        adjacencyList.putIfAbsent(location, new ArrayList<>());
    }

    public void addRoad(String source, String target, double weight) {
        addLocation(source);
        addLocation(target);
        adjacencyList.get(source).add(new Edge(target, weight));
        adjacencyList.get(target).add(new Edge(source, weight)); // Assuming two-way roads
    }

    // Dijkstra's Algorithm
    public DijkstraResult findShortestPath(String start, String end) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));

        for (String node : adjacencyList.keySet()) {
            distances.put(node, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        pq.add(new NodeDistance(start, 0.0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();

            if (current.node.equals(end)) break; // Found shortest path to destination

            if (current.distance > distances.get(current.node)) continue;

            for (Edge edge : adjacencyList.get(current.node)) {
                double newDist = distances.get(current.node) + edge.weight;
                if (newDist < distances.get(edge.target)) {
                    distances.put(edge.target, newDist);
                    predecessors.put(edge.target, current.node);
                    pq.add(new NodeDistance(edge.target, newDist));
                }
            }
        }

        // Reconstruct path
        List<String> path = new LinkedList<>();
        String step = end;
        if (predecessors.get(step) == null && !step.equals(start)) {
            return null; // Unreachable
        }
        while (step != null) {
            path.add(0, step);
            step = predecessors.get(step);
        }

        return new DijkstraResult(path, distances.get(end));
    }

    // Helper classes for Dijkstra
    private static class NodeDistance {
        String node;
        double distance;
        NodeDistance(String node, double distance) { this.node = node; this.distance = distance; }
    }

    public static class DijkstraResult {
        public List<String> path;
        public double totalETA;
        public DijkstraResult(List<String> path, double totalETA) { this.path = path; this.totalETA = totalETA; }
    }
}