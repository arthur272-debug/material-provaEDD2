public void fixCase3(Node n) {
    // So se chega a este metodo se o pai do no for vermelho.
    // Verificaremos agora se o tio dele tambem eh vermelho.
    Node tio = tio(n); // Caso nao exista tio, a variavel recebe null.
    if (tio != null && tio.getColor().equals(Color.RED)) {
        // se existe tio e ele eh vermelho:
        // pinta o pai e o tio de preto, o avo de vermelho e roda o fixcase1 no
        // avo para fazer os ajustes, caso estes sejam necessarios.
        Node pai = n.getParent();
        pai.setColor(Color.BLACK);
        tio.setColor(Color.BLACK);
        
        Node avo = avo(n);
        avo.setColor(Color.RED);
        fixCase1(avo);
    } else {
        fixCase4(n); // Caso nao haja tio ou ele seja preto, parte ao caso 4.
    }
}
