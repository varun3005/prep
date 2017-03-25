package org.vp.prep.ds.tree;

class TreeList {
   public static void join(Node a, Node b) {
        a.right = b;
        b.left = a;
    }

    
   public static Node append(Node a, Node b) {
        // if either is null, return the other
        if (a==null) return(b);
        if (b==null) return(a);
        
        // find the last node in each using the .previous pointer
        Node aLast = a.left;
        Node bLast = b.left;
        
        // join the two together to make it connected and circular
        join(aLast, b);
        join(bLast, a);
        
        return(a);
    }

    
    public static Node treeToList(Node root) {
        if (root==null) return(null);
        Node aList = treeToList(root.left);
        Node bList = treeToList(root.right);
        
        root.left = root;
        root.right = root;
        
        // At this point we have three lists, and it's
        // just a matter of appending them together
        // in the right order (aList, root, bList)
        aList = append(aList, root);
        aList = append(aList, bList);
        return(aList);
    }


    public static void treeInsert(Node root, int newData) {
        if (newData<=root.data) {
            if (root.left!=null) treeInsert(root.left, newData);
            else root.left = new Node(newData);
        }
        else {
            if (root.right!=null) treeInsert(root.right, newData);
            else root.right = new Node(newData);
        }
    }
    
    
    public static void printTree(Node root) {
        if (root==null) return;
        printTree(root.left);
        System.out.print(Integer.toString(root.data) + " ");
        printTree(root.right);
    }
    
    public static void printList(Node head) {
        Node current = head;
        
        while (current != null) {
            System.out.print(Integer.toString(current.data) + " ");
            current = current.right;
            if (current == head) break;
        }
        
        System.out.println();
    }
            
    
    public static void main(String[] args) {
    
        Node root = new Node(4);
        treeInsert(root, 2);
        treeInsert(root, 1);
        treeInsert(root, 3);
        treeInsert(root, 5);
        
        System.out.println("tree:");
        printTree(root);   // 1 2 3 4 5
        System.out.println();
        
        System.out.println("list:");
        Node head = treeToList(root);
        printList(head);   // 1 2 3 4 5   yay!
    }
}

