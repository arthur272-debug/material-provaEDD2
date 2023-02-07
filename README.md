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

## Inserção

```python

class Node:
    def __init__(self, key, left=None, right=None, height=0):
        self.key = key
        self.left = left
        self.right = right
        self.height = height

class AVLTree:
    def __init__(self):
        self.root = None

    def height(self, node):
        if node is None:
            return -1
        return node.height

    def update_height(self, node):
        node.height = max(self.height(node.left), self.height(node.right)) + 1

    def left_rotate(self, node):
        new_root = node.right
        node.right = new_root.left
        new_root.left = node

        self.update_height(node)
        self.update_height(new_root)
        return new_root

    def right_rotate(self, node):
        new_root = node.left
        node.left = new_root.right
        new_root.right = node

        self.update_height(node)
        self.update_height(new_root)
        return new_root

    def balance_factor(self, node):
        return self.height(node.left) - self.height(node.right)

    def insert(self, key):
        self.root = self._insert(key, self.root)

    def _insert(self, key, node):
        if node is None:
            return Node(key)

        if key < node.key:
            node.left = self._insert(key, node.left)
        else:
            node.right = self._insert(key, node.right)

        node = self.rebalance(node)
        self.update_height(node)
        return node

    def rebalance(self, node):
        bf = self.balance_factor(node)

        if bf > 1:
            if self.balance_factor(node.left) < 0:
                node.left = self.left_rotate(node.left)
            node = self.right_rotate(node)
        elif bf < -1:
            if self.balance_factor(node.right) > 0:
                node.right = self.right_rotate(node.right)
            node = self.left_rotate(node)
        return node

```
A classe "Node" representa cada nó da árvore e tem 4 atributos: "key", "left", "right" e "height". O "key" é a chave do nó, "left" e "right" são referências para os filhos esquerdo e direito respectivamente, e "height" é a altura do nó.

A classe "AVLTree" representa a árvore AVL. Ela tem o atributo "root" que é a raiz da árvore. Ela implementa as seguintes funções:

-> "height": retorna a altura do nó. Se o nó é None, retorna -1.

-> "update_height": atualiza a altura do nó.

-> "left_rotate": realiza uma rotação para a esquerda.

-> "right_rotate": realiza uma rotação para a direita.

-> "balance_factor": retorna o fator de balanceamento do nó.

-> "insert": insere uma chave na árvore.

-> "_insert": ajuda a inserir uma chave na árvore.

-> "rebalance": rebalanceia a árvore se necessário.

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

O método insert é usado para inserir um novo nó na árvore, e é implementado de forma recursiva. Ele verifica se o nó raiz é nulo, e se for, retorna um novo nó com o valor desejado. Se não for, ele verifica se o valor desejado é menor que o valor da raiz, e se for, insere-o à esquerda. Caso contrário, insere-o à direita. Após a inserção, o método verifica se a árvore está desbalanceada, e se estiver, aplica rotações para mantê-la balanceada.

Os métodos get_height, get_balance e left_rotate e right_rotate são usados para ajudar na manutenção do balanceamento da árvore. O método get_height retorna a altura de um nó, ou zero se ele for nulo. O método get_balance retorna o fator de balanceamento de um nó, calculado como a diferença entre a altura dos filhos à esquerda e à direita. Os métodos left_rotate e right_rotate são usados para rotacionar a árvore à esquerda ou à direita, respectivamente, para mantê-la balanceada.

## Indução com Rotação Simples

### Rotação Simples à Esquerda

```python
   def left_rotate(self, node):
    y = node.right
    node.right = y.left
    y.left = node
    node.height = 1 + max(self.get_height(node.left), self.get_height(node.right))
    y.height = 1 + max(self.get_height(y.left), self.get_height(y.right))
    return y

```
Em uma rotação simples à esquerda, o nó desequilibrado é colocado como filho direito de seu filho esquerdo.

### Rotação Simples à Direita

```python
   def right_rotate(self, node):
    y = node.left
    node.left = y.right
    y.right = node
    node.height = 1 + max(self.get_height(node.left), self.get_height(node.right))
    y.height = 1 + max(self.get_height(y.left), self.get_height(y.right))
    return y

```
Em uma rotação simples à direita, o nó desequilibrado é colocado como filho esquerdo de seu filho direito.

## Indução com Rotação Dupla

### Rotação Dupla à Esquerda

```python
   def left_right_rotate(self, node):
    node.left = self.left_rotate(node.left)
    return self.right_rotate(node)

```
A Rotação Dupla à Esquerda (Left-Right Rotation) é realizada quando a subárvore à esquerda é muito pesada e a subárvore direita desta subárvore à esquerda também é pesada. Primeiro, a subárvore à esquerda é rotacionada para a esquerda, depois a subárvore raiz é rotacionada para a direita.

### Rotação Dupla à Direita

```python
   def right_left_rotate(self, node):
    node.right = self.right_rotate(node.right)
    return self.left_rotate(node)

```
Rotação Dupla à Direita (Right-Left Rotation) é realizada quando a subárvore à direita é muito pesada e a subárvore esquerda desta subárvore à direita também é pesada. Primeiro, a subárvore à direita é rotacionada para a direita, depois a subárvore raiz é rotacionada para a esquerda.


## Remoção

```python
class AVL:
    def delete(self, root, key):
        if not root:
            return root
        
        if key < root.data:
            root.left = self.delete(root.left, key)
        elif key > root.data:
            root.right = self.delete(root.right, key)
        else:
            if root.left is None:
                return root.right
            elif root.right is None:
                return root.left
            
            min_larger_node = self.get_min_node(root.right)
            root.data = min_larger_node.data
            root.right = self.delete(root.right, min_larger_node.data)
        
        root.height = 1 + max(self.get_height(root.left), self.get_height(root.right))
        balance = self.get_balance(root)
        
        # Left Left
        if balance > 1 and self.get_balance(root.left) >= 0:
            return self.right_rotate(root)
        
        # Left Right
        if balance > 1 and self.get_balance(root.left) < 0:
            root.left = self.left_rotate(root.left)
            return self.right_rotate(root)
        
        # Right Right
        if balance < -1 and self.get_balance(root.right) <= 0:
            return self.left_rotate(root)
        
        # Right Left
        if balance < -1 and self.get_balance(root.right) > 0:
            root.right = self.right_rotate(root.right)
            return self.left_rotate(root)
        
        return root
    
    def get_min_node(self, root):
        if root is None or root.left is None:
            return root
        return self.get_min_node(root.left)

```
Esse código implementa a funcionalidade de remoção de um nó de uma árvore AVL.

A função delete é responsável por remover o nó da árvore. Ela começa verificando se a raiz é nula (se a árvore está vazia). Se for, a função retorna a raiz (não faz nada).

Em seguida, se a chave do nó a ser removido é menor que a chave da raiz, a função chama ela mesma passando como raiz o filho à esquerda da raiz. Se a chave for maior, a função chama ela mesma passando como raiz o filho à direita. Se a chave da raiz for igual à chave a ser removida, a função remove a raiz.

Se a raiz não tem filhos à esquerda, a função retorna o filho à direita. Se não tem filhos à direita, retorna o filho à esquerda. Se tem filhos tanto à esquerda quanto à direita, a função encontra o nó com a menor chave no filho à direita, troca a chave da raiz com a chave desse nó, e remove esse nó.

A função atualiza a altura da raiz após a remoção, e verifica se a árvore está desbalanceada. Se estiver, a função realiza a rotação adequada (simples ou dupla) para balanceá-la. Por fim, retorna a raiz.

A função get_min_node encontra o nó com a menor chave na subárvore cuja raiz é dada. A função é utilizada para encontrar o nó que vai substituir a raiz removida.

## Busca

```python
class AVL:
    def search(self, root, key):
        if root is None or root.data == key:
            return root
        elif root.data < key:
            return self.search(root.right, key)
        else:
            return self.search(root.left, key)

```

A função search procura pelo nó com a chave dada na árvore AVL. A busca começa na raiz da árvore e verifica se a raiz atual é nula ou se a chave da raiz é igual a chave desejada. Se for igual, a função retorna a raiz atual. Se a chave da raiz for menor que a chave desejada, a busca continua na subárvore da direita. Se a chave da raiz for maior que a chave desejada, a busca continua na subárvore da esquerda. A busca é recursiva e continua até encontrar o nó com a chave desejada ou até chegar a uma folha (nó nulo), o que significa que a chave não existe na árvore.

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

    def search(self, x, k):
        if x is None or k == x.key:
            return x
        if k < x.key:
            return self.search(x.left, k)
        else:
            return self.search(x.right, k)

```
Para usar o método de busca, você precisa chamá-lo a partir da raiz da árvore passando o valor que deseja buscar:

```python
tree = RedBlackTree()
node = tree.search(tree.root, key)

```
Se o nó for encontrado, ele será retornado e pode ser usado posteriormente. Caso contrário, será retornado None.

Assim, este código é para buscar um nó com chave "k" na árvore rubro negra. Ele começa com uma verificação para saber se a árvore está vazia ou se a chave do nó atual é igual a "k". Se for verdadeiro, ele retorna o nó atual. Caso contrário, ele verifica se "k" é menor que a chave do nó atual. Se for verdadeiro, ele busca na subárvore esquerda; caso contrário, na subárvore direita. A busca continua até acharmos o nó desejado ou chegarmos a uma folha (nó nulo).

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
