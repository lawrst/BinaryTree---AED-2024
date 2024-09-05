package tree;

import java.util.ArrayList;
import java.util.List;

import estrut.Tree;

public class BinarySearchTree implements Tree{
    
    private No raiz;

    public BinarySearchTree() {
        raiz = null;
    }

    @Override
    public boolean buscaElemento(int valor) {
        
        return buscaElementoRecursivo(raiz, valor);
    }

    
    private boolean buscaElementoRecursivo(No no, int valor) {
        
        if (no == null) {
        return false;
        }

        
        if (valor == no.valor) {
        return true;
        }

        
        if (valor < no.valor) {
        return buscaElementoRecursivo(no.esquerda, valor);
        }

        
        return buscaElementoRecursivo(no.direita, valor);
    }

    @Override
    public int minimo() {
        return minimo(this.raiz).valor;
    }
    
    private No minimo(No no) {
        return no.esquerda == null ? no : minimo(no.esquerda);
    }
    

    @Override
    public int maximo() {
        return maximo(this.raiz).valor;
    }
    
    private No maximo(No no) {
        return no.direita == null ? no : maximo(no.direita);
    }
    @Override
    public void insereElemento(int valor) {
        raiz = insereElementoRecursivo(raiz, valor);
    }

    
    private No insereElementoRecursivo(No atual, int valor) {
    
        if (atual == null) {
            return new No(valor);
        }

        
        if (valor < atual.valor) {
            atual.esquerda = insereElementoRecursivo(atual.esquerda, valor);
        }
       
        else if (valor > atual.valor) {
            atual.direita = insereElementoRecursivo(atual.direita, valor);
        }
        
        else {
            return atual;
        }
        
        return atual;
    }

    @Override
    public void remove(int valor) {
        raiz = removeRecursivo(raiz, valor);
    }


    private No removeRecursivo(No atual, int valor) {
        
        if (atual == null) {
          return null;
        }
    
        
        if (valor < atual.valor) {
          atual.esquerda = removeRecursivo(atual.esquerda, valor);
        }
        
        else if (valor > atual.valor) {
          atual.direita = removeRecursivo(atual.direita, valor);
        }
        
        else {
          
          if (atual.esquerda == null && atual.direita == null) {
            return null;
          }
          
          if (atual.esquerda == null) {
            return atual.direita;
          }
          if (atual.direita == null) {
            return atual.esquerda;
          }
          
          atual.valor = menorValor(atual.direita);
          atual.direita = removeRecursivo(atual.direita, atual.valor);
        }
    
        
        return atual;
    }    

    
    private int menorValor(No no) {
        
        if (no.esquerda != null) {
        return menorValor(no.esquerda);
        }
        
        return no.valor;
    }

    @Override
    public int[] preOrdem() {
        
        List<Integer> lista = new ArrayList<>();
        
        preOrdemRecursivo(raiz, lista);
        
        return lista.stream().mapToInt(i -> i).toArray();
    }

    
    private void preOrdemRecursivo(No no, List<Integer> lista) {
        
        if (no != null) {
        lista.add(no.valor);
        preOrdemRecursivo(no.esquerda, lista);
        preOrdemRecursivo(no.direita, lista);
        }
    }

    @Override
    public int[] emOrdem() {
        
        List<Integer> lista = new ArrayList<>();
        
        emOrdemRecursivo(raiz, lista);
        
        return lista.stream().mapToInt(i -> i).toArray();
    }

    
    private void emOrdemRecursivo(No no, List<Integer> lista) {
        
        if (no != null) {
        emOrdemRecursivo(no.esquerda, lista);
        lista.add(no.valor);
        emOrdemRecursivo(no.direita, lista);
        }
    }

    @Override
    public int[] posOrdem() {
        
        List<Integer> lista = new ArrayList<>();
        
        posOrdemRecursivo(raiz, lista);
        
        return lista.stream().mapToInt(i -> i).toArray();
    }

    
    private void posOrdemRecursivo(No no, List<Integer> lista) {
        
        if (no != null) {
        posOrdemRecursivo(no.esquerda, lista);
        posOrdemRecursivo(no.direita, lista);
        lista.add(no.valor);
        }
    }

}