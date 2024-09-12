package tree;

import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements Tree {
    private Node root;

    private class Node {
        int value;
        Node left, right;
        int height;

        Node(int value) {
            this.value = value;
            this.height = 1;  // Todo novo nó começa com altura 1
        }
    }

    // Método para inserir um elemento na AVL Tree
    public void insereElemento(int value) {
        root = insereElemento(root, value);
    }

    private Node insereElemento(Node node, int value) {
        // Caso base: chegou num ponto nulo, insere o nó aqui
        if (node == null) {
            return new Node(value);
        }

        // Regras da inserção em uma árvore binária de busca
        if (value < node.value) {
            node.left = insereElemento(node.left, value);
        } else if (value > node.value) {
            node.right = insereElemento(node.right, value);
        } else {
            return node; // Valores duplicados não são permitidos
        }

        // Atualiza a altura deste nó ancestral
        node.height = 1 + Math.max(altura(node.left), altura(node.right));

        // Obtém o fator de balanceamento deste nó
        int balance = getBalance(node);

        // Se o nó estiver desbalanceado, temos 4 casos:

        // Caso 1 - Rotação à direita
        if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        }

        // Caso 2 - Rotação à esquerda
        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        }

        // Caso 3 - Rotação dupla à direita
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Caso 4 - Rotação dupla à esquerda
        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Função de busca na árvore AVL
    public boolean buscaElemento(int value) {
        return buscaElemento(root, value);
    }

    private boolean buscaElemento(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        }
        if (value < node.value) {
            return buscaElemento(node.left, value);
        } else {
            return buscaElemento(node.right, value);
        }
    }

    // Método para retornar a travessia pré-ordem da árvore
    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdem(root, result);
        // Converter a lista de inteiros para um array de inteiros
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void preOrdem(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);        // Visitar o nó
            preOrdem(node.left, result);   // Percorrer a subárvore esquerda
            preOrdem(node.right, result);  // Percorrer a subárvore direita
        }
    }

    // Funções auxiliares para rotação e balanceamento

    private int altura(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return altura(node.left) - altura(node.right);
    }

    // Rotação à direita
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Rotacionar
        x.right = y;
        y.left = T2;

        // Atualizar alturas
        y.height = Math.max(altura(y.left), altura(y.right)) + 1;
        x.height = Math.max(altura(x.left), altura(x.right)) + 1;

        return x;
    }

    // Rotação à esquerda
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Rotacionar
        y.left = x;
        x.right = T2;

        // Atualizar alturas
        x.height = Math.max(altura(x.left), altura(x.right)) + 1;
        y.height = Math.max(altura(y.left), altura(y.right)) + 1;

        return y;
    }

    @Override
    public int[] emOrdem() {
        // Método stub
        return null;
    }

    @Override
    public int[] posOrdem() {
        // Método stub
        return null;
    }
}
