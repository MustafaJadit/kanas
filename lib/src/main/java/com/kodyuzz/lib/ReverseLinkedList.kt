package com.kodyuzz.lib

//reverse linkedList


class MyClass {
}

fun main() {
    var head = LinkedList.Companion.Node(1)
    var linkedList = LinkedList(head)

    linkedList.add(LinkedList.Companion.Node(2))
    linkedList.add(LinkedList.Companion.Node(3))

    linkedList.print()

    linkedList.reverse()

    linkedList.print()
}


class LinkedList(var head: Node) {

    companion object {

        class Node(var data: Int) {
            var next: Node? = null


        }
    }

    fun add(node: Node) {
        var current: Node? = head
        while (current != null) {
            if (current.next == null) {
                current.next = node
                break
            }
            current = current.next
        }
    }

    fun print() {
        var node: Node? = head
        while (node != null) {
            print("${node.data} ")
            node = node.next
        }
        println("")
    }

    fun reverse() {
        var pointer: Node? = head
        var previous: Node? = null
        var current: Node

        while (pointer != null) {
            current = pointer
            pointer = pointer.next

            current.next = previous
            previous = current
            head = current
        }


    }

}