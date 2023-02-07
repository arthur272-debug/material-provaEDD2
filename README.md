# Árvore Binária de Busca

## Inserção

```python
class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None

class BST:
    def __init__(self):
        self.root = None
        
    def insert(self, data):
        if not self.root:
            self.root = Node(data)
        else:
            current = self.root
            while True:
                if data < current.data:
                    if not current.left:
                        current.left = Node(data)
                        break
                    else:
                        current = current.left
                else:
                    if not current.right:
                        current.right = Node(data)
                        break
                    else:
                        current = current.right
                        
```

Neste código, a função insert adiciona um novo nó à árvore. Se a raiz da árvore estiver vazia, o novo nó será definido como raiz. Se não, o novo nó será adicionado a esquerda ou à direita da raiz, dependendo se o seu valor for menor ou maior que o valor da raiz.

## Remoção

```python
class BST:
    # ...
    def remove(self, data):
        parent = None
        current = self.root
        while current and current.data != data:
            parent = current
            if data < current.data:
                current = current.left
            else:
                current = current.right
        if not current:
            return
        if not current.right:
            if not parent:
                self.root = current.left
            else:
                if current.data < parent.data:
                    parent.left = current.left
                else:
                    parent.right = current.right
        else:
            successor = current.right
            while successor.left:
                successor = successor.left
            if not parent:
                self.root = successor
            else:
                if current.data < parent.data:
                    parent.left = successor
                else:
                    parent.right = successor
            successor.left = current.left


```
Nesta função, a remoção é realizada começando pela raiz. Primeiro, o nó a ser removido é localizado usando a busca. Em seguida, há três casos possíveis para remover o nó:

1. Se o nó não tiver filho à direita, ele pode ser substituído pelo seu filho à esquerda (se houver).
2. Se o nó tiver um filho à direita, o sucessor do nó (o nó mais à esquerda na subárvore à direita) é encontrado e usado para substituir o nó removido.
3. Se o nó tiver dois filhos, o sucessor do nó é encontrado e usado para substituir o nó removido, e o sucessor original é removido.

## Busca

```python
class BST:
    # ...
    def search(self, data):
        current = self.root
        while current:
            if data == current.data:
                return True
            elif data < current.data:
                current = current.left
            else:
                current = current.right
        return False

```
Nesta função, a busca é realizada começando pela raiz. Se o valor for igual ao valor da raiz, a busca é bem-sucedida. Se for menor, a busca é continuada na subárvore à esquerda. Se for maior, a busca é continuada na subárvore à direita. A busca termina se o nó atual for nulo, o que indica que o valor não foi encontrado na árvore.

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

```java

```

## Busca

```java

```

# Árvore B

## Inserção

```java

```

## Deleção

```java

```
## Busca

```java

```
