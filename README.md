# Árvore Binária de Busca

## Inserção

```java
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

```java
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

```java
public void verificarBalanceamento(No no_atual) {
        setBalanceamento(no_atual);
        int balanceamento = no_atual.getBalanceamento();

        if (balanceamento == -2) {

            if (calcularAltura(no_atual.getEsquerda().getEsquerda()) >= calcularAltura(no_atual.getEsquerda().getDireita())) {
                no_atual = rotacaoDireita(no_atual);

            } else {
                no_atual = duplaRotacaoEsquerdaDireita(no_atual);
            }

        } else if (balanceamento == 2) {

            if (calcularAltura(no_atual.getDireita().getDireita()) >= calcularAltura(no_atual.getDireita().getEsquerda())) {
                no_atual = FazerRotacaoEsquerda(no_atual);

            } else {
                no_atual = duplaRotacaoDireitaEsquerda(no_atual);
            }
        }

        if (no_atual.getPai() != null) {
            verificarBalanceamento(no_atual.getPai());
        } else {
            this.raiz = no_atual;
        }
    }
```

## Indução com Rotação Simples e Dupla

```java
   public No FazerRotacaoEsquerda(No variavel_no) {

        No direita = variavel_no.getDireita();
        direita.setPai(variavel_no.getPai());

        variavel_no.setDireita(direita.getEsquerda());

        if (variavel_no.getDireita() != null) {
            variavel_no.getDireita().setPai(variavel_no);
        }

        direita.setEsquerda(variavel_no);
        variavel_no.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDireita() == variavel_no) {
                direita.getPai().setDireita(direita);

            } else if (direita.getPai().getEsquerda() == variavel_no) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(variavel_no);
        setBalanceamento(direita);

        return direita;
    }

    public No rotacaoDireita(No variavel_no) {

        No esquerda = variavel_no.getEsquerda();
        esquerda.setPai(variavel_no.getPai());

        variavel_no.setEsquerda(esquerda.getDireita());

        if (variavel_no.getEsquerda() != null) {
            variavel_no.getEsquerda().setPai(variavel_no);
        }

        esquerda.setDireita(variavel_no);
        variavel_no.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == variavel_no) {
                esquerda.getPai().setDireita(esquerda);

            } else if (esquerda.getPai().getEsquerda() == variavel_no) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(variavel_no);
        setBalanceamento(esquerda);

        return esquerda;
    }

    public No duplaRotacaoEsquerdaDireita(No variavel_no) {
        variavel_no.setEsquerda(FazerRotacaoEsquerda(variavel_no.getEsquerda()));
        return rotacaoDireita(variavel_no);
    }

    public No duplaRotacaoDireitaEsquerda(No variavel_no) {
        variavel_no.setDireita(rotacaoDireita(variavel_no.getDireita()));
        return FazerRotacaoEsquerda(variavel_no);
    }

```

## Remoção

```java
public void removerNo(int k) {
        removerNo(this.raiz, k);
    }

    public void removerNo(No no_atual, int variavel_no) {
        if (no_atual == null) {
            return;

        } else {

            if (no_atual.getChave() > variavel_no) {
                removerNo(no_atual.getEsquerda(), variavel_no);

            } else if (no_atual.getChave() < variavel_no) {
                removerNo(no_atual.getDireita(), variavel_no);

            } else if (no_atual.getChave() == variavel_no) {
                removerNoEncontrado(no_atual);
            }
        }
    }

    public void removerNoEncontrado(No variavel_no) {
        No r;

        if (variavel_no.getEsquerda() == null || variavel_no.getDireita() == null) {

            if (variavel_no.getPai() == null) {
                this.raiz = null;
                variavel_no = null;
                return;
            }
            r = variavel_no;

        } else {
            r = sucessor(variavel_no);
            variavel_no.setChave(r.getChave());
        }

        No p;
        if (r.getEsquerda() != null) {
            p = r.getEsquerda();
        } else {
            p = r.getDireita();
        }

        if (p != null) {
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            this.raiz = p;
        } else {
            if (r == r.getPai().getEsquerda()) {
                r.getPai().setEsquerda(p);
            } else {
                r.getPai().setDireita(p);
            }
            verificarBalanceamento(r.getPai());
        }
        r = null;
    }
```

# Árvore Rubro-Negra

## Inserção

```java
private Node put(Node h, Key key, Value val) {
      if (h == null) 
         return new Node(key, val, 1, RED);

      int cmp = key.compareTo(h.key);
      if      (cmp < 0) h.left  = put(h.left, key, val);
      else if (cmp > 0) h.right = put(h.right, key, val);
      else              h.val   = val;

      if (isRed(h.right) && !isRed(h.left))      
        h = rotateLeft(h);
      if (isRed(h.left)  &&  isRed(h.left.left)) 
        h = rotateRight(h);
      if (isRed(h.left)  &&  isRed(h.right))     
        flipColors(h);
      h.N = size(h.left) + size(h.right) + 1;
      return h;
   }
```

##  Deleção

## Busca

# Árovre 2-3

## Inserção

## Deleção

## Busca
