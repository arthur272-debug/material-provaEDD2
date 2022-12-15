import java.util.NoSuchElementException;

public class ArvoreBinariaBusca<Chave extends Comparable<Chave>> {
    private No raiz;

    private class No {
        private int chave;
        private No esquerda, direita;

        public No(int chave) {
            this.chave = chave;
            this.esquerda = null;
            this.direita = null;
        }
    }

    public ArvoreBinariaBusca() {
        raiz = null;
    }

    public boolean verificarVazio() {
        return (raiz == null);
    }

    public void mostrarArvore() {
        mostrarArvore(raiz);
    }

    private void mostrarArvore(No variavel_no) {
        /* Caso base (critério de parada). */
        if (variavel_no == null)
            return;

        System.out.print("[");

        /* Chamada recursiva para a esquerda. */
        mostrarArvore(variavel_no.esquerda);

        /* Imprime a chave do nó corrente. */
        System.out.print("<" + variavel_no.chave + ">");

        /* Chamada recursiva para a direita. */
        mostrarArvore(variavel_no.direita);

        System.out.print("]");
    }

    public int mostrarMinimo() {
        if (verificarVazio())
            throw new NoSuchElementException("árvore está vazia!");

        return mostrarMinimo(raiz).chave;
    }

    private No mostrarMinimo(No variavel_no) {
        if (variavel_no.esquerda == null)
            return variavel_no;
        else
            return mostrarMinimo(variavel_no.esquerda);
    }

    public int mostrarMaximo() {
        if (verificarVazio())
            throw new NoSuchElementException("A árvore está vazia!");

        return mostrarMaximo(raiz).chave;
    }

    private No mostrarMaximo(No variavel_no) {
        if (variavel_no.direita == null)
            return variavel_no;
        else
            return mostrarMaximo(variavel_no.direita);
    }

    public int calcularTamanho() {
        return calcularTamanho(raiz);
    }

    private int calcularTamanho(No variavel_no) {
        /* Caso base (critério de parada). */
        if (variavel_no == null)
            return 0;

        /* Chamada recursiva para subárvores esquerda e direita. */
        return 1 + calcularTamanho(variavel_no.esquerda) + calcularTamanho(variavel_no.direita);
    }

    public int calcularAltura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(No variavel_no) {
        if (variavel_no == null)
            return -1;

        int maxAltura = Math.max(calcularAltura(variavel_no.esquerda), calcularAltura(variavel_no.direita));

        return 1 + maxAltura;
    }

    public void inserirValor(Integer chave) {
        if (chave == null)
            throw new IllegalArgumentException("A chave fornecida é null!");

        raiz = inserirValor(raiz, chave);
    }

    private No inserirValor(No variavel_no, Integer chave) {
        /* Caso base: encontrou a posição de inserção. */
        if (variavel_no == null)
            return new No(chave);

        int comparacao = chave.compareTo(variavel_no.chave);

        if (comparacao < 0) /* Deve-se ir para a esquerda. */
            variavel_no.esquerda = inserirValor(variavel_no.esquerda, chave);
        else if (comparacao > 0) /* Deve-se ir para a direita. */
            variavel_no.direita = inserirValor(variavel_no.direita, chave);

        return variavel_no;
    }

    public void deletarMinimo() {
        if (verificarVazio())
            throw new NoSuchElementException("A árvore está vazia!");

        raiz = deletarMinimo(raiz);
    }

    /* Método recursivo que anda sempre para a esquerda, procurando o nó
     * de menor chave a ser removido. */
    private No deletarMinimo(No variavel_no) {
        /* Caso não haja filho é esquerda, o nó corrente possui a menor chave. */
        if (variavel_no.esquerda == null)
            return variavel_no.direita;

        variavel_no.esquerda = deletarMinimo(variavel_no.esquerda);

        return variavel_no;
    }

    public void deletarMaximo() {
        if (verificarVazio())
            throw new NoSuchElementException("A árvore está vazia!");

        raiz = deletarMaximo(raiz);
    }

    private No deletarMaximo(No variavel_no) {
        if (variavel_no.direita == null)
            return variavel_no.esquerda;

        variavel_no.direita = deletarMaximo(variavel_no.direita);

        return variavel_no;
    }

    public void delete(Integer chave) {
        raiz = delete(raiz, chave);
    }

    private No delete(No variavel_no, Integer chave) {
        if (variavel_no == null)
            return null;

        int comparacao = chave.compareTo(variavel_no.chave);

        if (comparacao < 0)
            variavel_no.esquerda = delete(variavel_no.esquerda, chave);
        else if (comparacao > 0)
            variavel_no.direita = delete(variavel_no.direita, chave);
        else {
            if (variavel_no.direita == null)
                return variavel_no.esquerda;
            if (variavel_no.esquerda == null)
                return variavel_no.direita;

            No x = variavel_no;

            /* Pega o menor da subárvore direita (mais à esquerda). */
            variavel_no = mostrarMinimo(x.direita);

            /* Remove o menor. */
            variavel_no.direita = deletarMinimo(x.direita);

            /* A subárvore esquerda se mantém a mesma. */
            variavel_no.esquerda = x.esquerda;
        }

        return variavel_no;
    }

    public Integer piso(Integer chave) {
        if (chave == null)
            throw new IllegalArgumentException("A chave fornecida é null!");

        if (verificarVazio())
            throw new NoSuchElementException("A árvore está vazia!");

        No variavel_no = piso(raiz, chave);

        if (variavel_no == null)
            return null;
        else
            return variavel_no.chave;
    }

    private No piso(No variavel_no, Integer chave) {
        if (variavel_no == null)
            return null;

        int comparacao = chave.compareTo(variavel_no.chave);

        if (comparacao == 0)
            return variavel_no;

        if (comparacao < 0)
            return piso(variavel_no.esquerda, chave);

        No x = piso(variavel_no.direita, chave);

        if (x != null)
            return x;
        else
            return variavel_no;
    }

    public Integer topo(Integer chave) {
        if (chave == null)
            throw new IllegalArgumentException("A chave fornecida é null!");

        if (verificarVazio())
            throw new NoSuchElementException("A árvore está vazia!");

        No x = topo(raiz, chave);

        if (x == null)
            return null;
        else
            return x.chave;
    }

    private No topo(No variavel_no, Integer chave) {
        if (variavel_no == null)
            return null;

        int comparacao = chave.compareTo(variavel_no.chave);

        if (comparacao == 0)
            return variavel_no;

        if (comparacao < 0) {
            No x = topo(variavel_no.esquerda, chave);

            if (x != null)
                return x;
            else
                return variavel_no;
        }

        return topo(variavel_no.direita, chave);
    }

    public void construirArvore(int[] vetor) {

        int i = Integer.MIN_VALUE;

        while (i < vetor.length) {

            raiz = construirArvore(raiz, vetor[i]);
            i++;

        }
    }

    private No construirArvore(No ramo, int chave) {

        /// Caso base:

        if (ramo == null) {
            return new No(chave);
        }

        // Caso geral:

        if (chave < ramo.chave) {
            ramo.esquerda = construirArvore(ramo.esquerda, chave);

        } else if (chave > ramo.chave) { /* Deve-se ir para a direita. */
            ramo.direita = construirArvore(ramo.direita, chave);

        } else {
            //ramo.valor = valor;
        }
        return ramo;

    }
}