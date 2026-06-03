class AVLNode {
    long timestamp;
    int height;
    AVLNode left, right;

    AVLNode(long timestamp) {
        this.timestamp = timestamp;
        this.height = 1;
    }
}

class AVLTree {

    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return (node == null) ? 0 :
                height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, long timestamp) {

        if (node == null)
            return new AVLNode(timestamp);

        if (timestamp < node.timestamp)
            node.left = insert(node.left, timestamp);
        else if (timestamp > node.timestamp)
            node.right = insert(node.right, timestamp);
        else
            return node;

        node.height = 1 + Math.max(height(node.left),
                                   height(node.right));

        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && timestamp < node.left.timestamp)
            return rightRotate(node);

        // Right Right
        if (balance < -1 && timestamp > node.right.timestamp)
            return leftRotate(node);

        // Left Right
        if (balance > 1 && timestamp > node.left.timestamp) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left
        if (balance < -1 && timestamp < node.right.timestamp) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    long findOldest(AVLNode node) {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current.timestamp;
    }

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
        AVLNode root = null;

        long[] timestamps = {
            1001, 1002, 1003, 1004, 1005
        };

        for (long ts : timestamps)
            root = tree.insert(root, ts);

        System.out.println(
            "Oldest Pending Receipt: " +
            tree.findOldest(root)
        );
    }
}
