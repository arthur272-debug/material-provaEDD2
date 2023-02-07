  	public int blackHeightRecursive(Node no) {
  		int altura = 0;
  		if (!node.isEmpty()) {
  			if (no.getColour() == Colour.BLACK) {
  				altura = 1 + Math.max(blackHeightRecursive(no.getLeft()), blackHeightRecursive(no.getRight()));
  			} else {
  				altura = Math.max(blackHeightRecursive(no.getLeft()), blackHeightRecursive(no.getRight()));
  			}
  		}
  		return altura;
  	}
