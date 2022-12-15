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
