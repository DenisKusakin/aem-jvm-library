package org.deniskusakin.aemlibrary.jcr

interface JcrNode {
    val name: String
    val path: String
    val children: Collection<JcrNode>
    val props: NodeProps
}