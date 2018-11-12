package org.deniskusakin.aemlibrary.nodes.impl

import org.deniskusakin.aemlibrary.nodes.JcrNode
import org.deniskusakin.aemlibrary.nodes.NodeProps
import javax.jcr.Node

class JcrNodeImpl(private val node: Node) : JcrNode {
    override val name: String = node.name
    override val path: String
        get() = node.path
    override val children: Collection<JcrNode>
        get() {
            val childrenIterator = node.nodes
            val childrenNodes = mutableListOf<JcrNode>()
            while (childrenIterator.hasNext()) {
                childrenNodes.add(JcrNodeImpl(childrenIterator.nextNode()))
            }
            return childrenNodes
        }
    override val props: NodeProps
        get() {
            return NodePropsImpl(node)
        }
}