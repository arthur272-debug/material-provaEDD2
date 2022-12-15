# Árvore Binária de Busca

## Inserção

```python
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
```

## Remoção

```python
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

```

# Árvore AVL

## Balanceamento

## Indução com Rotação Simples e Dupla

## Remoção

# Árvore Rubro-Negra

## Inserção

##  Deleção

## Busca

# Árovre 2-3

## Inserção

## Deleção

## Busca
