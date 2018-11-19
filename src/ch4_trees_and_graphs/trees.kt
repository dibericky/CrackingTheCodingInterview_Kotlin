package ch4_trees_and_graphs

/**
 * A tree is a data structure composed of nodes.
 *      Each tree has a root node.
 *      The root has zero or more child nodes.
 *      Each child node has zero or more child nodes.
 * The tree cannot contain cycle. The nodes may or may not be in a particular order.
 * They may or may not have links back to their parent nodes.
 * A node is called "leaf" node if it has no children.
 */
class Tree(val root: Node)

class Node(val name: String, val children: Array<Node>)

/**
 * Trees vs Binary Trees
 *      A binary tree is a tree in which each node has up to two children. Not all trees are binary trees.
 *      A Binary Search tree is a binary tree in which every node fits a specific ordering property: ALL left
 *      descendents <= n < ALL right descendents. This must be true for each node n.
 *
 * A balancing tree does not mean the left and right subtrees are exactly the same size.
 * A balanced tree really means something more like "not terribly imbalanced". It's balanced enough to
 * ensure O(log N) times fot insert and find.
 * Two common types of balanced trees are red-black trees and AVL trees.
 *
 * Complete Binary Trees
 *      A complete binary tree is a binary tree in which every level of the tree is fully filled, except for
 *      perhaps the last level. It's filled left to right.
 *      Last level:
 *                  ([3] [7]) ([_] [30]) not a complete binary tree
 *                  ([3] [7]) ([15] [_]) a complete binary tree
 *
 * Full Binary Tree
 *      A full binary tree is a binary tree in which every node has either zero or two children.
 *      That is, no nodes have only one child.
 *
 * Perfect Binary Tree
 *      A perfect binary tree is one that is both full and complete. All leaf nodes will be at the same level,
 *      and this level has the maximum number of nodes.
 *
 */

class TreeNode(var left: TreeNode?, var right: TreeNode?, var value: Int, var parent: TreeNode? = null)

/**
 * Binary Tree Visit.
 */
class BinaryTreeVisit(val visit: (TreeNode) -> Unit) {

    /**
     * Visit the left beanch, then the current node, and finally, the right branch.
     */
    fun inOrderVisit(node: TreeNode?) {
        node?.let {
            inOrderVisit(it.left)
            visit(it)
            inOrderVisit(it.right)
        }
    }

    /**
     * Visit the current node before its child node.
     * The root is always the first node visited.
     */
    fun preOrderVisit(node: TreeNode?) {
        node?.let {
            visit(it)
            preOrderVisit(it.left)
            preOrderVisit(it.right)
        }
    }

    /**
     * Visit the current node after its child node.
     * The root is always the last node visited.
     */
    fun postOrderVisit(node: TreeNode?) {
        node?.let {
            postOrderVisit(it.left)
            postOrderVisit(it.right)
            visit(it)
        }
    }
}


/**
 * Binary Heaps (Min-Heaps and Max-Heaps)
 *      A min-heap is a complete binary tree (totally filled other than the rightmot elements on the last level) where
 *      each node is smaller than its children. The root, therefore, is the minimum element in the tree.
 *      We have two key operations on a min-heap: insert and extract_min.
 *
 *      Insert
 *          When we insert into a min-heap, we always start by inserting the element at the bottom.
 *          We insert rightmost spot so as to maintain the complete tree property.
 *          Then, we "fix" the tree by swapping the new element with its parent, until we find an appropriate
 *          spot for the element.
 *          This takes O(log N) time, where N is the number of nodes in the heap.
 *
 *      Extract Minimum Element
 *          The minimum element is always at the top.
 *          To remove it, first we remove the minimum element and swap it with the last element in the heap.
 *          Then, we bubble down this element, swapping it with one of it's children until the min-heap property is restored.
 *          Do we swap it with the left child or right child? That depends on their value. You'll need to take the smaller
 *          one in order to maintain the min-heap ordering.
 *          The algorithm will also take O(log N) time.
 */
class MinHeap {
    companion object {
        fun insert(root: TreeNode, node: TreeNode) {
            insertRecursive(root, node)
            bubbleUp(node)
        }

        private fun insertRecursive(root: TreeNode, node: TreeNode) {
            if (root.right == null) {
                root.right = node
                node.parent = root
                return
            }
            insertRecursive(root.right!!, node)
        }

        private fun bubbleUp(node: TreeNode) {
            fun swap(a: TreeNode, b: TreeNode) {
                val sup = a.value
                a.value = b.value
                b.value = sup
            }

            val parent = node.parent ?: return

            if (parent.value > node.value) {
                swap(parent, node)
                bubbleUp(parent)
            }
        }

        fun extractMin(root: TreeNode): Int {
            val min = root.value
            val last = getLast(root)

            root.value = last.value
            removeLast(last)
            bubbleDown(root)

            return min
        }

        private fun getLast(root: TreeNode): TreeNode {
            val nodesOrdered = mutableListOf<TreeNode>()
            BinaryTreeVisit { nodesOrdered.add(it) }.preOrderVisit(root)
            return nodesOrdered.last()
        }

        private fun removeLast(node: TreeNode) {
            val parent = node.parent
            node.parent = null
            if (parent?.left == node) {
                parent.left = null
            }
            if (parent?.right == node) {
                parent.right = null
            }
        }

        private fun bubbleDown(node: TreeNode) {
            fun swap(a: TreeNode, b: TreeNode) {
                val sup = a.value
                a.value = b.value
                b.value = sup
            }

            fun getMin(node: TreeNode): TreeNode? {
                if (node.left == null && node.right == null) return null

                if (node.left?.value ?: Int.MAX_VALUE < node.right?.value ?: Int.MAX_VALUE) return node.left

                return node.right
            }


            val min = getMin(node) ?: return
            if (node.value > min.value) {
                swap(node, min)
                bubbleUp(min)
            }
        }
    }
}