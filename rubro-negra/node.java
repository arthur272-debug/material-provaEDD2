private static final boolean RED = true;
private static final boolean BLACK = false;
   
  private class Node {
    Key     key;
    Value   val;
    Node    left, right;
    int     N;             // número de nós nesta subárvore
    boolean color;         // cor do link que aponta para este nó
   
    Node(Key key, Value val, int N, boolean color) {
      this.key   = key;
      this.val   = val;
      this.N     = N;
      this.color = color;
    }
    

    private boolean isRed(Node x) {
         if (x == null) return false;
         return x.color == RED;
    }

    Node rotateLeft(Node h) {
         Node x = h.right;
         h.right = x.left;
         x.left = h;
         x.color = h.color;
         h.color = RED;
         x.N = h.N;
         h.N = 1 + size(h.left) + size(h.right);
         return x;
    }

    Node rotateRight(Node h) {
         Node x = h.left;
         h.left = x.right;
         x.right = h;
         x.color = h.color;
         h.color = RED;
         x.N = h.N;
         h.N = 1 + size(h.left) + size(h.right);
         return x;
    }

    void flipColors(Node h) {
      h.color = RED;
      h.left.color = BLACK;
      h.right.color = BLACK;
    }
}
