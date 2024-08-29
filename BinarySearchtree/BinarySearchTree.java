//Clemens Osbahr // Matrikelnummer: 23453430

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
//import audmt.TreeViewer;


// Klasse, die einen Bin?rbaum implementiert
public class BinarySearchTree implements BSTInterface {
    private TreeNode root; // Wurzel des Baumes

    // Innere Klasse, die einen Knoten des Baumes darstellt
    private class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value) {
            this.value = value;
        }
    }


    // L?scht den gesamten Baum
    @Override
    public void clear() {
        root = null;
    }
    // Pr?ft, ob ein Wert im Baum existiert
    @Override
    public boolean exists(int value) {
        return exists(root, value);
    }


    // Rekursive Hilfsmethode zur Pr?fung, ob ein Wert im Baum existiert
    private boolean exists(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (value < node.value) {
            return exists(node.left, value);
        } else if (value > node.value) {
            return exists(node.right, value);
        } else {
            return true;
        }
    }
    // F?gt einen Wert in den Baum ein
    @Override
    public void insert(int value) throws ElementExistsException  {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            insert(root, value);
        }
    }
    // Rekursive Hilfsmethode zum Einf?gen eines Wertes
    private void insert(TreeNode node, int value) throws ElementExistsException {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new TreeNode(value);// F?gt den Wert als linken Kindknoten ein
                node.left.parent = node; // Setzt den Elternknote
            } else {
                insert(node.left, value);
            }
        } else if (value > node.value) {
            if (node.right == null) {
                node.right = new TreeNode(value);// F?gt den Wert als rechten Kindknoten ein
                node.right.parent = node;// Setzt den Elternknoten
            } else {
                insert(node.right, value);
            }
        } else {
            throw new ElementExistsException("Element " + value + " already exists.");
        }
    }
    // Entfernt einen Wert aus dem Baum
    @Override
    public void remove(int value) throws NoSuchElementException {
        TreeNode node = find(root, value);
        if (node == null) {
            throw new NoSuchElementException("Element " + value + " not found.");
        }
        remove(node);
    }
    // Hilfsmethode zum Entfernen eines Knotens
    private void remove(TreeNode node) {
        int children = numChildren(node);
        if (children == 0) {
            replaceInParent(node, null);
        } else if (children == 1) {
            TreeNode child = (node.left != null) ? node.left : node.right;
            replaceInParent(node, child);
        } else {
            TreeNode successor = findMin(node.right);
            node.value = successor.value;
            remove(successor);
        }
    }
    // Z?hlt die Kinder eines Knotens
    private int numChildren(TreeNode node) {
        int count = 0;
        if (node.left != null) count++;
        if (node.right != null) count++;
        return count;
    }
    // Findet einen Knoten mit einem bestimmten Wert
    private TreeNode find(TreeNode node, int value) {
        if (node == null || node.value == value) {
            return node;
        }
        if (value < node.value) {
            return find(node.left, value);
        } else {
            return find(node.right, value);
        }
    }
    // Ersetzt einen Knoten durch einen neuen Knoten im Elternknoten
    private void replaceInParent(TreeNode node, TreeNode newNode) {
        if (node.parent == null) {
            root = newNode;
        } else if (node == node.parent.left) {
            node.parent.left = newNode;
        } else {
            node.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = node.parent;
        }
    }
    // Findet den Knoten mit dem minimalen Wert im Teilbaum
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    // Gibt eine In-Order-Liste der Baumwerte zur?ck
    @Override
    public List<Integer> inOrderList() {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }
    // Rekursive Hilfsmethode f?r In-Order-Durchlauf
    private void inOrder(TreeNode node, List<Integer> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.value);
            inOrder(node.right, result);
        }
    }
    // Gibt eine Pre-Order-Liste der Baumwerte zur?ck
    @Override
    public List<Integer> preOrderList() {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }
    // Rekursive Hilfsmethode f?r Pre-Order-Durchlauf
    private void preOrder(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrder(node.left, result);
            preOrder(node.right, result);
        }
    }
    // Gibt eine Post-Order-Liste der Baumwerte zur?ck
    @Override
    public List<Integer> postOrderList() {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }
    // Rekursive Hilfsmethode f?r Post-Order-Durchlauf
    private void postOrder(TreeNode node, List<Integer> result) {
        if (node != null) {
            postOrder(node.left, result);
            postOrder(node.right, result);
            result.add(node.value);
        }
    }
    // Hauptmethode zur Demonstration der Baumoperationen
    public static void main(String[] args) {
        try {
            BinarySearchTree bst = new BinarySearchTree();
            //TreeViewer TreeViewer = null;

            bst.insert(10);
            bst.insert(5);
            bst.insert(15);
            bst.insert(3);
            bst.insert(7);
          //  TreeViewer.displayTree(bst);

            System.out.println("Inorder: " + bst.inOrderList());
            System.out.println("Preorder: " + bst.preOrderList());
            System.out.println("Postorder: " + bst.postOrderList());

            bst.remove(5);
            System.out.println("Inorder after removing 5: " + bst.inOrderList());
           // TreeViewer.displayTree(bst);

        } catch (ElementExistsException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
