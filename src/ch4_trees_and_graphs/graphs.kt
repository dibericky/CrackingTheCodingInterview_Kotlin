package ch4_trees_and_graphs

import ch3_stacks_and_queue.MyQueue
import ch3_stacks_and_queue.Queue

/**
 * A tree is actually a type of graph, but not all graphs are trees.
 * A tree is a connected graph without cycles.
 *
 * A graph is simply a collection of nodes with edges between some of them.
 * Graphs can be either directed or undirected. While directed edges are like a one-way street,
 * undirected edges are like a two-way street.
 * The graph might consist of multiple isolated subgraphs. If there is a path between
 * every pair of vertices, it is called a "connected graph".
 * The graph can also have cycles. An "acyclic graph" is one without cycles.
 *
 * In term of programming there are two common ways to represent a graph.
 *
 * Graph is used because, unlike in a tree, you can't necessarily reach all nodes from a single node.
 */

/**
 * Adjacency List
 *      This is the common way to represent a graph.
 *      Every vertex (or node) stores a list of adjacent vertices.
 *      In an undirected graph, an edge like (a,b) would be store twice: once in a's adjacent vertices
 *      and once in b's adjacent vertices.
 *
 * Adjacency Matrices
 *      It's a NxN boolean matrix, where N is the number of nodes, where a true value at matrix[i][j]
 *      indicates an edge from node i to node j.
 *      In an undirected graph, an adjacency matrix will be symmetric. In a directed graph, it will note
 *      necessarily be.
 *
 */
class GraphAdj(val nodes: List<NodeAdj>)
class NodeAdj(val name:String, val children: MutableList<NodeAdj> = mutableListOf()){
    var visited = false
}
/**
 * You could also represent a graph with an HashTable of lists.
 * 0: 1
 * 1: 2
 * 2: 0, 3
 * 3: 2
 * 4: 6
 * 5: 4
 * 5: 5
 */

/**
 * Graph Search
 *      The two most common ways to search a graph are depth-first search and breadth-first search.
 *
 *      In DFS, we start at the root and explore each branch completely before moving on to the next branch.
 *      We go deep first before we go wide. It's often preferred if we want to visit every node in the graph.
 *
 *      In BFS, we start at the root and explore each neighbor before going on to any of their children. We go wide before
 *      we go deep.
 *      If we wanna find the shortest path (or just any path) between two nodes, BFS is generally better.
 *
 */
class GraphSearch{

    companion object {
        fun searchDFS(root: NodeAdj, visit: (NodeAdj) -> Unit) {
            fun searchDFSRecursive(node: NodeAdj, visited: MutableList<NodeAdj>) {
                visit(node)
                node.visited = true
                node.children.forEach { if(!it.visited)searchDFSRecursive(it, visited) }
            }

            val visited = mutableListOf<NodeAdj>()
            searchDFSRecursive(root, visited)
        }

        /**
         * BFS is NOT recursive. It uses a queue.
         * In BFS, visit each of a's neighbors before visiting any of THEIR neighbors.
         * You can think of this as searching level by level out form a.
         * An iterative solution involving a queue usually works best.
         */
        fun searchBFS(root: NodeAdj, visit: (NodeAdj) -> Unit) {
           val queue = MyQueue<NodeAdj>()
            root.visited = true
            queue.add(root) //starting point

            while(!queue.isEmpty()){
                val r = queue.remove()!! //it's not empty
                visit(r) //visit the oldest element in queue
                for(n in r.children){
                    if(!n.visited){ //if it's not been added in queue
                        n.visited = true //in this case 'visited' means "added in queue"
                        queue.add(n) //add element in queue.
                    }
                }
            }
        }

        /**
         * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
         */
        fun isThereRoute(a: NodeAdj, b: NodeAdj) : Boolean{
            val visited = mutableListOf<NodeAdj>()
            val toVisit = MyQueue<NodeAdj>()

            toVisit.add(a)

            while(!toVisit.isEmpty()){
                val element = toVisit.remove()!!
                visited.add(element)
                for(n in element.children){
                    if(n == b)return true
                    if(!visited.contains(n))toVisit.add(n)
                }
            }
            return false
        }

    }
}
