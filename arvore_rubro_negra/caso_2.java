public void fixCase2(Node n) {
    Color corDoPai = n.getParent().getColor();
    if (corDoPai.equals(Color.RED) { // Se o pai tiver cor vermelha, parte
        fixCase3(n); // para o caso 3. Se nao, a arvore esta correta.
    }
    
}
