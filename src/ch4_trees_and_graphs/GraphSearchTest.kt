package ch4_trees_and_graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GraphSearchTest{

    @Test
    fun searchDFS(){
        val graph = createGraph()
        val visitData = mutableListOf<String>()
        GraphSearch.searchDFS(graph){visitData.add(it.name)}
        check(listOf("a","b","e","c","d"), visitData)
    }

    @Test
    fun searchBFS(){
        val graph = createGraph()
        val visitData = mutableListOf<String>()
        GraphSearch.searchBFS(graph){visitData.add(it.name)}
        check(listOf("a","b","c","d", "e"), visitData)
    }


    private fun check(expected: List<String>, actual: List<String>){
        for(i in 0 until actual.size){
            assertEquals(expected[i], actual[i])
        }
    }
    private fun createGraph() : NodeAdj{
        val a = NodeAdj("a")
        val b = NodeAdj("b")
        val c = NodeAdj("c")
        val d = NodeAdj("d")
        val e = NodeAdj("e")
        a.children.addAll(listOf(b,c,d))
        d.children.add(a)
        b.children.addAll(listOf(a,e))
        c.children.addAll(listOf(a,e))
        e.children.addAll(listOf(b,c))
        return a
    }

}