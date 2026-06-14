import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Product
class Product {
    private int id;
    private String name;
    private String category;
    private String subCategory;
    private double price;
    private String color;
    private String fit;
    private ArrayList<String> tags;

    public Product(int id, String name, String category, String subCategory, double price, String color, String fit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.price = price;
        this.color = color;
        this.fit = fit;
        tags = new ArrayList<>();
        tags.add(color.toLowerCase());
        tags.add(fit.toLowerCase());
        tags.add(subCategory.toLowerCase());
    }
    // Add tag
    public void addTag(String tag) {
        tag = tag.toLowerCase();
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getSubCategory() {
        return subCategory;
    }
    public double getPrice() {
        return price;
    }
    public ArrayList<String> getTags() {
        return tags;
    }
    @Override
    public String toString() {
        return "[" + id + "] " + name +
                " | " + category +
                " | $" + price;
    }
}
// Queue
class NewArrivalsFeed {
    private Queue<Product> feed;
    public NewArrivalsFeed() {
        feed = new LinkedList<>();
    }
    // Add item
    public void addNewArrival(Product p) {
        feed.add(p);
    }
    // Show feed
    public void showFeed() {
        if (feed.isEmpty()) {
            System.out.println("No arrivals");
            return;
        }
        System.out.println();
        System.out.println("New Arrivals");
        int i = 1;
        for (Product p : feed) {
            System.out.println(i + ". " + p);
            i++;
        }
    }
    // Next item
    public Product peekNext() {
        return feed.peek();
    }
    // Remove item
    public Product pollNext() {
        return feed.poll();
    }
    public int size() {
        return feed.size();
    }
    public boolean isEmpty() {
        return feed.isEmpty();
    }
}
// ArrayList
class StyleTagPreferences {
    private String username;
    private ArrayList<String> tags;
    public StyleTagPreferences(String username) {
        this.username = username;
        tags = new ArrayList<>();
    }
    // Add tag
    public void addTag(String tag) {
        tag = tag.toLowerCase().trim();
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }
    // Remove tag
    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase().trim());
    }
    // Move tag
    public void moveTag(String tag, int index) {
        tag = tag.toLowerCase().trim();
        if (tags.contains(tag)) {
            tags.remove(tag);
            if (index >= 0 && index <= tags.size()) {
                tags.add(index, tag);
            } else {
                tags.add(tag);
            }
        }
    }
    // Match score
    public int scoreProduct(Product product) {
        int score = 0;
        for (String tag : tags) {
            if (product.getTags().contains(tag)) {
                score++;
            }
        }
        return score;
    }
    // Recommendations
    public ArrayList<Product> getMatchedProducts(ArrayList<Product> catalog) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product p : catalog) {
            if (scoreProduct(p) > 0) {
                results.add(p);
            }
        }
        return results;
    }
    // Show tags
    public void showTags() {
        System.out.println();
        System.out.println("Style Tags");
        for (int i = 0; i < tags.size(); i++) {
            System.out.println((i + 1) + ". " + tags.get(i));
        }
    }
    public ArrayList<String> getTags() {
        return tags;
    }
    public String getUsername() {
        return username;
    }
}
// Linked List
class BrowseHistory {
    private class Node {
        Product product;
        String action;
        Node prev;
        Node next;

        Node(Product product, String action) {
            this.product = product;
            this.action = action;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public BrowseHistory() {
        head = null;
        tail = null;
        size = 0;
    }
    // Add history
    public void addItem(Product product, String action) {
        Node newNode = new Node(product, action);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    // Recent items
    public ArrayList<Product> getRecentItems(int count) {
        ArrayList<Product> items = new ArrayList<>();
        Node current = tail;
        while (current != null && count > 0) {
            items.add(current.product);
            current = current.prev;
            count--;
        }
        return items;
    }
    // Style pattern
    public String detectStylePattern(int count) {
        ArrayList<Product> items = getRecentItems(count);
        if (items.isEmpty()) {
            return "No history";
        }
        HashMap<String, Integer> tagMap = new HashMap<>();
        for (Product p : items) {
            for (String tag : p.getTags()) {
                if (tagMap.containsKey(tag)) {
                    tagMap.put(tag, tagMap.get(tag) + 1);
                } else {
                    tagMap.put(tag, 1);
                }
            }
        }
        String bestTag = "";
        int max = 0;
        for (String tag : tagMap.keySet()) {
            if (tagMap.get(tag) > max) {
                max = tagMap.get(tag);
                bestTag = tag;
            }
        }
        return "Favorite style: " + bestTag;
    }
    // Most viewed category
    public String getMostViewedCategory() {
        if (tail == null) {
            return "None";
        }
        HashMap<String, Integer> catMap = new HashMap<>();
        Node current = tail;
        while (current != null) {
            String cat = current.product.getCategory();
            if (catMap.containsKey(cat)) {
                catMap.put(cat, catMap.get(cat) + 1);
            } else {
                catMap.put(cat, 1);
            }
            current = current.prev;
        }
        String bestCat = "None";
        int max = 0;
        for (String cat : catMap.keySet()) {
            if (catMap.get(cat) > max) {
                max = catMap.get(cat);
                bestCat = cat;
            }
        }
        return bestCat;
    }
    // Show history
    public void showHistory() {
        if (tail == null) {
            System.out.println("No history");
            return;
        }
        Node current = tail;
        while (current != null) {
            System.out.println(
                    current.action + " -> " +
                            current.product.getName()
            );
            current = current.prev;
        }
    }
    public int getSize() {
        return size;
    }
}
// Stack
class OutfitBuilder {
    private String outfitName;
    private Stack<Product> outfit;
    private ArrayList<Stack<Product>> savedOutfits;
    public OutfitBuilder(String outfitName) {
        this.outfitName = outfitName;
        outfit = new Stack<>();
        savedOutfits = new ArrayList<>();
    }
    // Add item
    public void addItem(Product product) {
        outfit.push(product);
    }
    // Undo item
    public void undoLastItem() {
        if (!outfit.isEmpty()) {
            outfit.pop();
        }
    }
    // Top item
    public Product seeTopItem() {
        if (outfit.isEmpty()) {
            return null;
        }
        return outfit.peek();
    }
    // Save outfit
    public void saveOutfit() {
        if (outfit.isEmpty()) {
            return;
        }
        Stack<Product> copy = new Stack<>();
        copy.addAll(outfit);
        savedOutfits.add(copy);
    }
    // Show outfit
    public void showCurrentOutfit() {
        System.out.println("\n" + outfitName);
        if (outfit.isEmpty()) {
            System.out.println("Empty outfit");
            return;
        }
        for (Product p : outfit) {
            System.out.println("- " + p.getName());
        }
    }
    // Show saved
    public void showSavedOutfits() {
        if (savedOutfits.isEmpty()) {
            System.out.println("No saved outfits");
            return;
        }
        for (int i = 0; i < savedOutfits.size(); i++) {
            System.out.println("\nOutfit " + (i + 1));
            for (Product p : savedOutfits.get(i)) {
                System.out.println("- " + p.getName());
            }
        }
    }
    // Get current outfit items
    public Stack<Product> getCurrentOutfit() {
        return outfit;
    }
    // Clear outfit
    public void clearOutfit() {
        outfit.clear();
    }
    public boolean isEmpty() {
        return outfit.isEmpty();
    }
    public int size() {
        return outfit.size();
    }
}
// Graph
class ProductGraph {
    private HashMap<Integer, Product> products;
    private HashMap<Integer, ArrayList<Integer>> graph;
    public ProductGraph() {
        products = new HashMap<>();
        graph = new HashMap<>();
    }
    // Add product
    public void addProduct(Product p) {
        products.put(p.getId(), p);
        graph.put(p.getId(), new ArrayList<>());
    }
    // Add edge
    public void addCompatibility(int id1, int id2) {
        if (graph.containsKey(id1) && graph.containsKey(id2)) {
            graph.get(id1).add(id2);
            graph.get(id2).add(id1);
        }
    }
    // BFS search
    public ArrayList<Product> findMatchesBFS(int startId) {
        ArrayList<Product> matches = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(startId);
        visited.add(startId);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                    matches.add(products.get(next));
                }
            }
        }
        return matches;
    }
    // DFS path
    public ArrayList<Product> buildOutfitPathDFS(int startId) {
        ArrayList<Product> path = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        dfs(startId, visited, path);
        if (!path.isEmpty()) {
            path.remove(0);
        }
        return path;
    }
    // DFS helper
    private void dfs(int id, HashSet<Integer> visited, ArrayList<Product> path) {
        visited.add(id);
        path.add(products.get(id));
        for (int next : graph.get(id)) {
            if (!visited.contains(next)) {
                dfs(next, visited, path);
            }
        }
    }
    // Smart recommendations (tag + price + category)
    public void showSmartRecommendations(int productId, BrowseHistory history, double maxPrice) {
        System.out.println();
        System.out.println("Smart Recommendations");
        ArrayList<Product> bfsMatches = findMatchesBFS(productId);
        String topCategory = history.getMostViewedCategory();
        for (Product p : bfsMatches) {
            int score = 0;
            // Score: graph connected
            score += 1;
            // Score: price within budget
            if (p.getPrice() <= maxPrice) {
                score += 1;
            }
            // Score: matches most viewed category
            if (p.getCategory().equalsIgnoreCase(topCategory)) {
                score += 1;
            }
            System.out.println("- " + p.getName() + " (score: " + score + ")");
        }
    }
    // Check if product ID exists
    public boolean hasProduct(int id) {
        return products.containsKey(id);
    }
}
// Tree (Dynamic)
class CategoryTree {
    static class TreeNode {
        String name;
        ArrayList<TreeNode> children;
        ArrayList<Product> products;
        public TreeNode(String name) {
            this.name = name;
            children = new ArrayList<>();
            products = new ArrayList<>();
        }
    }
    private TreeNode root;
    public CategoryTree() {
        root = new TreeNode("All Clothing");
    }
    // Add category
    public void addCategory(String parentName, String childName) {
        TreeNode parent = findNode(root, parentName);
        if (parent == null) {
            System.out.println("Parent category not found: " + parentName);
            return;
        }
        for (TreeNode child : parent.children) {
            if (child.name.equalsIgnoreCase(childName)) {
                return;
            }
        }
        parent.children.add(new TreeNode(childName));
    }
    // Add product
    public void addProduct(Product product, String category, String subCategory) {
        TreeNode categoryNode = findNode(root, category);
        if (categoryNode == null) {
            return;
        }
        TreeNode subNode = findNode(categoryNode, subCategory);
        if (subNode != null) {
            subNode.products.add(product);
        }
    }
    // Find node
    private TreeNode findNode(TreeNode node, String name) {
        if (node.name.equalsIgnoreCase(name)) {
            return node;
        }
        for (TreeNode child : node.children) {
            TreeNode result = findNode(child, name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    // Get products
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> list = new ArrayList<>();
        TreeNode node = findNode(root, category);
        if (node != null) {
            collectProducts(node, list);
        }
        return list;
    }
    // Collect products
    private void collectProducts(TreeNode node, ArrayList<Product> list) {
        list.addAll(node.products);
        for (TreeNode child : node.children) {
            collectProducts(child, list);
        }
    }
    // Price filter
    public ArrayList<Product> filterByPrice(String category, double min, double max) {
        ArrayList<Product> result = new ArrayList<>();
        ArrayList<Product> products = getProductsByCategory(category);
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }
    // Check category exists
    public boolean categoryExists(String name) {
        return findNode(root, name) != null;
    }
    // Show tree
    public void showTree() {
        printTree(root, 0);
    }
    // Print tree
    private void printTree(TreeNode node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.name);
        for (TreeNode child : node.children) {
            printTree(child, level + 1);
        }
    }
}
// User
class User {
    private String userId;
    private String name;
    private StyleTagPreferences styleTags;
    private BrowseHistory history;
    private OutfitBuilder outfit;
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        styleTags = new StyleTagPreferences(name);
        history = new BrowseHistory();
        outfit = new OutfitBuilder(name + "'s Outfit");
    }
    // View product
    public void viewProduct(Product product) {
        history.addItem(product, "viewed");
    }
    // Buy product
    public void purchaseProduct(Product product) {
        history.addItem(product, "purchased");
    }
    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public StyleTagPreferences getStyleTags() {
        return styleTags;
    }
    public BrowseHistory getHistory() {
        return history;
    }
    public OutfitBuilder getOutfitBuilder() {
        return outfit;
    }
    @Override
    public String toString() {
        return name + " (" + userId + ")";
    }
}
// File Handler
class FileHandler {
    // Load products from txt file
    public static ArrayList<Product> loadProducts(String fileName) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 7) {
                    System.out.println("Skipping bad line: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String category = parts[2].trim();
                    String subCategory = parts[3].trim();
                    double price = Double.parseDouble(parts[4].trim());
                    String color = parts[5].trim();
                    String fit = parts[6].trim();
                    products.add(new Product(id, name, category, subCategory, price, color, fit));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping bad line: " + line);
                }
            }
            reader.close();
            System.out.println(products.size() + " products loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("Could not read " + fileName + ": " + e.getMessage());
            System.out.println("Please make sure products.txt is in the same folder as StyleMatch.java");
        }
        return products;
    }
    // Save outfit to file
    public static void saveOutfitToFile(String userName, Stack<Product> outfit) {
        String fileName = userName + "_outfit.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("Outfit for: " + userName);
            writer.newLine();
            writer.write("----------------------");
            writer.newLine();
            for (Product p : outfit) {
                writer.write(p.toString());
                writer.newLine();
            }
            writer.close();
            System.out.println("Outfit saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving outfit: " + e.getMessage());
        }
    }
    // Save recommendations to file
    public static void saveRecommendationsToFile(String userName, ArrayList<Product> recs) {
        String fileName = userName + "recommendations.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("Recommendations for: " + userName);
            writer.newLine();
            writer.write("...................");
            writer.newLine();
            for (Product p : recs) {
                writer.write(p.toString());
                writer.newLine();
            }
            writer.close();
            System.out.println("Recommendations saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving recommendations: " + e.getMessage());
        }
    }
}
public class StyleMatch {
    // Safe int input with range check
    static int readInt(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val < min || val > max) {
                    System.out.print("Invalid! Enter a number between " + min + " and " + max + ": ");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a number: ");
            }
        }
    }
    // Safe double input
    static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a valid number: ");
            }
        }
    }
    // Find product by ID from list
    static Product findById(ArrayList<Product> products, int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("StyleMatch Demo\n");

        // User input
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();

        // Load products from file
        ArrayList<Product> products = FileHandler.loadProducts("products.txt");
        if (products.isEmpty()) {
            System.out.println("No products loaded. Exiting.");
            scanner.close();
            return;
        }
        int maxId = products.size();

        // Queue - first 5 products as new arrivals
        System.out.println();
        System.out.println("QUEUE");
        NewArrivalsFeed feed = new NewArrivalsFeed();
        for (int i = 0; i < 5 && i < products.size(); i++) {
            feed.addNewArrival(products.get(i));
        }
        feed.showFeed();

        // User
        User user = new User(userId, userName);

        // ArrayList
        System.out.println();
        System.out.println("ARRAYLIST");
        StyleTagPreferences tags = user.getStyleTags();
        System.out.print("Enter style tag 1 (e.g. casual, white, slim): ");
        tags.addTag(scanner.nextLine());
        System.out.print("Enter style tag 2 (e.g. casual, white, slim): ");
        tags.addTag(scanner.nextLine());
        tags.showTags();
        System.out.println();
        System.out.println("Matches");
        for (Product p : tags.getMatchedProducts(products)) {
            System.out.println(p);
        }

        // Linked List
        System.out.println();
        System.out.println("LINKED LIST");
        System.out.println("Available products:");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.print("Enter product ID to view (1-" + maxId + "): ");
        Product viewed1 = findById(products, readInt(scanner, 1, maxId));
        user.viewProduct(viewed1);
        System.out.print("Enter product ID to view (1-" + maxId + "): ");
        Product viewed2 = findById(products, readInt(scanner, 1, maxId));
        user.viewProduct(viewed2);
        System.out.print("Enter product ID to purchase (1-" + maxId + "): ");
        Product purchased = findById(products, readInt(scanner, 1, maxId));
        user.purchaseProduct(purchased);
        user.getHistory().showHistory();
        System.out.println(user.getHistory().detectStylePattern(3));

        // Stack
        System.out.println();
        System.out.println("STACK");
        OutfitBuilder outfit = user.getOutfitBuilder();
        System.out.print("Enter product ID to add to outfit (1-" + maxId + "): ");
        outfit.addItem(findById(products, readInt(scanner, 1, maxId)));
        System.out.print("Enter product ID to add to outfit (1-" + maxId + "): ");
        outfit.addItem(findById(products, readInt(scanner, 1, maxId)));
        System.out.print("Enter product ID to add to outfit (1-" + maxId + "): ");
        outfit.addItem(findById(products, readInt(scanner, 1, maxId)));
        outfit.showCurrentOutfit();
        outfit.undoLastItem();
        System.out.println();
        System.out.println("After Undo");
        outfit.showCurrentOutfit();
        // Save outfit to file
        FileHandler.saveOutfitToFile(userName, outfit.getCurrentOutfit());

        // Graph
        System.out.println();
        System.out.println("GRAPH");
        ProductGraph graph = new ProductGraph();
        for (Product p : products) {
            graph.addProduct(p);
        }
        // Auto-connect products in same subCategory
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).getSubCategory().equals(products.get(j).getSubCategory())) {
                    graph.addCompatibility(products.get(i).getId(), products.get(j).getId());
                }
            }
        }
        System.out.print("Enter your price budget for recommendations: ");
        double budget = readDouble(scanner);
        System.out.print("Enter product ID for recommendations (1-" + maxId + "): ");
        int recId = readInt(scanner, 1, maxId);
        graph.showSmartRecommendations(recId, user.getHistory(), budget);
        // Save recommendations to file
        FileHandler.saveRecommendationsToFile(userName, graph.findMatchesBFS(recId));
        System.out.println();
        System.out.println("DFS Path");
        System.out.print("Enter product ID to start DFS path (1-" + maxId + "): ");
        int dfsId = readInt(scanner, 1, maxId);
        for (Product p : graph.buildOutfitPathDFS(dfsId)) {
            System.out.println(p.getName());
        }

        // Tree (Dynamic)
        System.out.println();
        System.out.println("TREE");
        CategoryTree tree = new CategoryTree();
        // Build tree dynamically from loaded products
        for (Product p : products) {
            tree.addCategory("All Clothing", p.getCategory());
            tree.addCategory(p.getCategory(), p.getSubCategory());
            tree.addProduct(p, p.getCategory(), p.getSubCategory());
        }
        // User can add a new sub-category dynamically
        System.out.print("Add a new sub-category under Men (or press Enter to skip): ");
        String newCat = scanner.nextLine().trim();
        if (!newCat.isEmpty()) {
            tree.addCategory("Men", newCat);
            System.out.println("Category added!");
        }
        tree.showTree();
        System.out.println();
        System.out.print("Enter category to browse (Men / Women / Accessories): ");
        String category = scanner.nextLine().trim();
        if (!tree.categoryExists(category)) {
            System.out.println("Category not found. Showing Men by default.");
            category = "Men";
        }
        System.out.println(category + " Products");
        for (Product p : tree.getProductsByCategory(category)) {
            System.out.println(p);
        }
        System.out.print("Enter max price to filter by: ");
        double maxPrice = readDouble(scanner);
        System.out.println("Under $" + maxPrice);
        for (Product p : tree.filterByPrice(category, 0, maxPrice)) {
            System.out.println(p);
        }

        System.out.println();
        System.out.println("Demo Complete");
        scanner.close();
    }
}