public void fixCase4(Node n) {
    Node pai = n.getParent();
    Node avo = avo.getParent();
    
    if (n.compareTo(pai) > 0 && pai.compareTo(avo) < 0) {
        // Se o no eh filho da direita e o pai eh filho da esquerda
        rotaciona_esquerda(pai);
        
        n = n.getLeft(); // o no passa a ser o seu filho da esquerda, que
        // antes da rotacao era o seu pai.
    } else if (n.compareTo(pai) < 0 && pai.compareTo(avo) > 0) {
        // Se o no eh filho da esquerda e o pai eh filho da direita
        rotaciona_direita(pai);
        
        n = n.getRight(); // o no passa a ser o seu filho da direita, que
        // antes da rotacao era o seu pai.
    }
    fixCase5(n);
}
