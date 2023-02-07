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

## Traversal

### Em ordem

```python
class BST:
    # ...
    def in_order_traversal(self, node):
        if node:
            self.in_order_traversal(node.left)
            print(node.data)
            self.in_order_traversal(node.right)
            
    def in_order(self):
        self.in_order_traversal(self.root)

```

Nesta função, o percorrimento em ordem é realizado usando a recursão. A função in_order_traversal é chamada para cada nó da árvore, começando pela raiz. Em cada chamada, o percurso é feito primeiro na subárvore à esquerda, em seguida o valor do nó é impresso, e por fim na subárvore à direita.

### Pré-ordem

```python
class BST:
    # ...
    def pre_order_traversal(self, node):
        if node:
            print(node.data)
            self.pre_order_traversal(node.left)
            self.pre_order_traversal(node.right)
            
    def pre_order(self):
        self.pre_order_traversal(self.root)

```

Nesta função, o percorrimento em pré-ordem é realizado usando a recursão. A função pre_order_traversal é chamada para cada nó da árvore, começando pela raiz. Em cada chamada, o valor do nó é impresso, em seguida o percurso é feito na subárvore à esquerda, e por fim na subárvore à direita.

### Pós-ordem

```python
class BST:
    # ...
    def post_order_traversal(self, node):
        if node:
            self.post_order_traversal(node.left)
            self.post_order_traversal(node.right)
            print(node.data)
            
    def post_order(self):
        self.post_order_traversal(self.root)

```

Nesta função, o percorrimento em pós-ordem é realizado usando a recursão. A função post_order_traversal é chamada para cada nó da árvore, começando pela raiz. Em cada chamada, o percurso é feito primeiro na subárvore à esquerda, em seguida na subárvore à direita, e por fim o valor do nó é impresso.

# Árvore AVL

## Balanceamento

```python
class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
        self.height = 1

class AVL:
    def insert(self, root, key):
        if not root:
            return Node(key)
        elif key < root.data:
            root.left = self.insert(root.left, key)
        else:
            root.right = self.insert(root.right, key)
 
        root.height = 1 + max(self.get_height(root.left),
                              self.get_height(root.right))
 
        balance = self.get_balance(root)
 
        # Left Left Case
        if balance > 1 and key < root.left.data:
            return self.right_rotate(root)
 
        # Right Right Case
        if balance < -1 and key > root.right.data:
            return self.left_rotate(root)
 
        # Left Right Case
        if balance > 1 and key > root.left.data:
            root.left = self.left_rotate(root.left)
            return self.right_rotate(root)
 
        # Right Left Case
        if balance < -1 and key < root.right.data:
            root.right = self.right_rotate(root.right)
            return self.left_rotate(root)
 
        return root
 
    def get_height(self, root):
        if not root:
            return 0
 
        return root.height
 
    def get_balance(self, root):
        if not root:
            return 0
 
        return self.get_height(root.left) - self.get_height(root.right)
 
    def left_rotate(self, z):
        y = z.right
        T2 = y.left
 
        # Perform rotation
        y.left = z
        z.right = T2
 
        # Update heights
        z.height = 1 + max(self.get_height(z.left),
                           self.get_height(z.right))
        y.height = 1 + max(self.get_height(y.left),
                           self.get_height(y.right))
 
        # Return the new root
        return y
 
    def right_rotate(self, z):
        y = z.left
        T3 = y.right
 
        # Perform rotation
        y.right = z
        z.left = T3
 
        # Update heights
        z.height = 1 + max(self.get_height(z.left),
                           self.get_height(z.right))
        y.height = 1 + max(self.get_height(y.left),
                           self.get_height(y.right))
 
        # Return the new root
        return y

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

##  Remoção

```python
class Node:
    def __init__(self, key, color, left=None, right=None, parent=None):
        self.key = key
        self.color = color
        self.left = left
        self.right = right
        self.parent = parent
        
class RedBlackTree:
    def __init__(self, root=None):
        self.root = root
    
    def transplant(self, u, v):
        if u.parent is None:
            self.root = v
        elif u == u.parent.left:
            u.parent.left = v
        else:
            u.parent.right = v
        v.parent = u.parent

    def minimum(self, x):
        while x.left:
            x = x.left
        return x
    
    def delete(self, z):
        if z.left is None:
            self.transplant(z, z.right)
        elif z.right is None:
            self.transplant(z, z.left)
        else:
            y = self.minimum(z.right)
            if y.parent != z:
                self.transplant(y, y.right)
                y.right = z.right
                y.right.parent = y
            self.transplant(z, y)
            y.left = z.left
            y.left.parent = y
            y.color = z.color
        if z.color == "BLACK":
            self.delete_fixup(z.parent)
            
    def delete_fixup(self, node):
    while node != self.root and node.color == BLACK:
        if node == node.parent.left:
            sibling = node.parent.right
            if sibling.color == RED:
                sibling.color = BLACK
                node.parent.color = RED
                self.left_rotate(node.parent)
                sibling = node.parent.right
            if sibling.left.color == BLACK and sibling.right.color == BLACK:
                sibling.color = RED
                node = node.parent
            else:
                if sibling.right.color == BLACK:
                    sibling.left.color = BLACK
                    sibling.color = RED
                    self.right_rotate(sibling)
                    sibling = node.parent.right
                sibling.color = node.parent.color
                node.parent.color = BLACK
                sibling.right.color = BLACK
                self.left_rotate(node.parent)
                node = self.root
        else:
            sibling = node.parent.left
            if sibling.color == RED:
                sibling.color = BLACK
                node.parent.color = RED
                self.right_rotate(node.parent)
                sibling = node.parent.left
            if sibling.right.color == BLACK and sibling.left.color == BLACK:
                sibling.color = RED
                node = node.parent
            else:
                if sibling.left.color == BLACK:
                    sibling.right.color = BLACK
                    sibling.color = RED
                    self.left_rotate(sibling)
                    sibling = node.parent.left
                sibling.color = node.parent.color
                node.parent.color = BLACK
                sibling.left.color = BLACK
                self.right_rotate(node.parent)
                node = self.root
    node.color = BLACK

```
Esse código implementa a remoção de um nó de uma árvore rubro-negra.

A função "delete" é responsável por remover o nó "z" da árvore. Se o nó "z" não tiver nenhum filho, ele é simplesmente retirado da árvore. Se o nó "z" tiver um filho, ele é substituído pelo seu filho. Se o nó "z" tiver dois filhos, o nó mínimo da subárvore direita é encontrado e substitui o nó "z".

Após a remoção do nó, a função "delete_fixup" é chamada para restaurar as propriedades da árvore rubro-negra, se necessário. A função "delete_fixup" usa um loop para percorrer a árvore a partir do nó pai do nó removido até a raiz. A função verifica se a cor do nó é preta. Se for vermelho, a propriedade da árvore rubro-negra não é violada e o loop é interrompido. Se for preto, são realizadas as rotações e as mudanças de cor necessárias para manter as propriedades da árvore rubro-negra.

## Busca

```python

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
