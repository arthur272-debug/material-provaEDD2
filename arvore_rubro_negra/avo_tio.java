public Node avo(Node n) {
    if (n.getParent() == null) {
        return null; // Nao ter pai significa nao ter avo
    }
    Node pai = n.getParent();
    return pai.getParent(); // Se o pai tiver pai, retorna-o. Caso contrario, o 
    // retorno do metodo getParent sera null, e o retorno será null.
}

public Node tio(Node n) {
    Node avo = avo(n);
    if (avo == null) {
        return null;
    }
    Node pai = n.getParent();
    // Nao existe como saber se o pai eh maior ou menor que o avo, entao
    // para recuperar o tio, comparamos os dois.
    
    if (pai.compareTo(avo) > 0) { // Se pai > avo
        return avo.getLeft(); // retorna o filho da esquerda, que eh menor.
        // Caso nao exista, retorna null.
    } else { // Se pai < avo
        return avo.getRight(); // retorna o filho da direita, que eh maior.
        // Caso nao exista, retorna null.
    }
    
}
