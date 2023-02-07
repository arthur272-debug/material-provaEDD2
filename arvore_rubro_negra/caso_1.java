public void fixCase1(Node n) {
    if (n.getParent() == null) { // Se o no for raiz, pinta de preto.
        n.setColor(Color.BLACK);
    } else { // Se nao for, parte para o caso 2.
        fixCase2(n);
    }
}
