import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LeaderboardNode {
    String playerId;
    int score;
    LeaderboardNode left, right;
    int height;
    int size;

    public LeaderboardNode(String playerId, int score) {
        this.playerId = playerId;
        this.score = score;
        this.height = 1;
        this.size = 1;
    }
}

class LeaderboardTree {
    private LeaderboardNode root;

    private int height(LeaderboardNode n) {
        return (n == null) ? 0 : n.height;
    }

    private int size(LeaderboardNode n) {
        return (n == null) ? 0 : n.size;
    }

    private void updateNode(LeaderboardNode n) {
        if (n != null) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
            n.size = 1 + size(n.left) + size(n.right);
        }
    }

    private int getBalance(LeaderboardNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private LeaderboardNode rightRotate(LeaderboardNode y) {
        LeaderboardNode x = y.left;
        LeaderboardNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateNode(y);
        updateNode(x);
        return x;
    }

    private LeaderboardNode leftRotate(LeaderboardNode x) {
        LeaderboardNode y = x.right;
        LeaderboardNode T2 = y.left;
        y.left = x;
        x.right = T2;
        updateNode(x);
        updateNode(y);
        return y;
    }

    public void insert(String playerId, int score) {
        root = insertNode(root, playerId, score);
    }

    private LeaderboardNode insertNode(LeaderboardNode node, String playerId, int score) {
        if (node == null) {
            return new LeaderboardNode(playerId, score);
        }

        if (score < node.score) {
            node.left = insertNode(node.left, playerId, score);
        } else {
            node.right = insertNode(node.right, playerId, score);
        }

        updateNode(node);
        int balance = getBalance(node);

        if (balance > 1 && score < node.left.score) return rightRotate(node);
        if (balance < -1 && score >= node.right.score) return leftRotate(node);
        if (balance > 1 && score >= node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && score < node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void delete(int score) {
        root = deleteNode(root, score);
    }

    private LeaderboardNode deleteNode(LeaderboardNode node, int score) {
        if (node == null) return null;

        if (score < node.score) {
            node.left = deleteNode(node.left, score);
        } else if (score > node.score) {
            node.right = deleteNode(node.right, score);
        } else {
            if ((node.left == null) || (node.right == null)) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                LeaderboardNode temp = findMin(node.right);
                node.playerId = temp.playerId;
                node.score = temp.score;
                node.right = deleteNode(node.right, temp.score);
            }
        }

        if (node == null) return null;
        
        updateNode(node);
        int balance = getBalance(node);
        
        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private LeaderboardNode findMin(LeaderboardNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int getRank(int score) {
        return getRankRecursive(root, score);
    }

    private int getRankRecursive(LeaderboardNode node, int score) {
        if (node == null) return 0;

        if (score < node.score) {
            return 1 + size(node.right) + getRankRecursive(node.left, score);
        } else if (score > node.score) {
            return getRankRecursive(node.right, score);
        } else {
            return 1 + size(node.right);
        }
    }

    public List<LeaderboardNode> getTopK(int k) {
        List<LeaderboardNode> topK = new ArrayList<>();
        getTopKRecursive(root, k, topK);
        return topK;
    }

    private void getTopKRecursive(LeaderboardNode node, int k, List<LeaderboardNode> topK) {
        if (node == null || topK.size() >= k) {
            return;
        }
        getTopKRecursive(node.right, k, topK);
        if (topK.size() < k) {
            topK.add(node);
        }
        getTopKRecursive(node.left, k, topK);
    }
}

public class AVLLeaderboardSystem {
    private final LeaderboardTree tree;
    private final Map<String, Integer> playerScores;

    public AVLLeaderboardSystem() {
        this.tree = new LeaderboardTree();
        this.playerScores = new HashMap<>();
    }

    public void addOrUpdatePlayer(String playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            int oldScore = playerScores.get(playerId);
            tree.delete(oldScore);
        }
        playerScores.put(playerId, score);
        tree.insert(playerId, score);
    }

    public int getPlayerRank(String playerId) {
        if (!playerScores.containsKey(playerId)) {
            System.out.println("Player " + playerId + " not found.");
            return -1;
        }
        int score = playerScores.get(playerId);
        return tree.getRank(score);
    }

    public List<String> getTopPlayers(int k) {
        List<LeaderboardNode> topNodes = tree.getTopK(k);
        List<String> topPlayers = new ArrayList<>();
        for (LeaderboardNode node : topNodes) {
            topPlayers.add(node.playerId + ": " + node.score);
        }
        return topPlayers;
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addOrUpdatePlayer("Alice", 1500);
        leaderboard.addOrUpdatePlayer("Bob", 1800);
        leaderboard.addOrUpdatePlayer("Charlie", 1200);
        leaderboard.addOrUpdatePlayer("Diana", 2200);
        leaderboard.addOrUpdatePlayer("Eve", 1850);

        System.out.println("Top 3: " + leaderboard.getTopPlayers(3));

        System.out.println("Alice's rank: " + leaderboard.getPlayerRank("Alice"));
        System.out.println("Diana's rank: " + leaderboard.getPlayerRank("Diana"));

        leaderboard.addOrUpdatePlayer("Charlie", 2300);

        System.out.println("Top 3 after update: " + leaderboard.getTopPlayers(3));
        System.out.println("Diana's new rank: " + leaderboard.getPlayerRank("Diana"));
        System.out.println("Charlie's new rank: " + leaderboard.getPlayerRank("Charlie"));
    }
}
