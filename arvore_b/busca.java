public BNodePosition<T> search(T element) {
	return searchAux(root, element);
}

private BNodePosition<T> searchAux(BNode<T> node, T element) {
	int i = 0;
	BNodePosition<T> nodePosition = new BNodePosition<T>();
	while (i <= node.elements.size() && element.compareTo(node.elements.get(i)) > 0) {
		i++;
	}
	if (i <= node.elements.size() && element.equals(node.elements.get(i))) {
		nodePosition.position = i;
		nodePosition.node = node;
		return nodePosition;
	}
	if (node.isLeaf()) {
		return new BNodePosition<T>();
	}
	return searchAux(node.children.get(i), element);
}
