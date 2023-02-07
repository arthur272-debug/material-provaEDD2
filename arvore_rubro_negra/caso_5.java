public void fixCase5(Node n) {
    Node avo = avo(n);
    Node tio = tio(n);
    Node pai = n.getParent();
    
    // pinta o pai de preto e o avo de vermelho
    avo.setColor(Color.RED);
    pai.setColor(Color.BLACK);
    
    if (n.compareTo(pai) < 0 && pai.compareTo(avo) < 0) {
        // se o no eh filho da esquerda e o pai tambem, 
        // rotaciona o avo a direita.
        rotaciona_direita(avo);
    } else {
        // se nao, quer dizer que o no eh filho da direita e o pai tambem,
        // e rotacionamos o avo a esquerda.
        rotaciona_esquerda(avo);
    }
}
