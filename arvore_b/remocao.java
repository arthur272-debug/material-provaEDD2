 protected void split() {
         int mediana = (size()) / 2;

         BNode<T> leftChildren = this.copyLeftChildren(mediana);
         BNode<T> rightChildren = this.copyRightChildren(mediana);

         if (parent == null) {
             parent = new BNode<T>(maxChildren);
             parent.children.addFirst(this);
         }

         BNode<T> parent = this.parent;

         int index = parent.indexOfChild(this);
        parent.removeChild(this);

         parent.addChild(index, leftChildren);
         parent.addChild(index + 1, rightChildren);

         leftChildren.setParent(parent);
         rightChildren.setParent(parent);

         this.promote(mediana);

         if (parent.size() >= maxChildren) {
             parent.split();
         }
     }
     protected void promote(int mid) {
         T element = elements.get(mid);
         this.parent.addElement(element);
     }
