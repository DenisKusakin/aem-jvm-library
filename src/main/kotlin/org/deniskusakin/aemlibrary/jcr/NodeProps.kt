package org.deniskusakin.aemlibrary.jcr

interface NodeProps {
    operator fun get(name: String): NodeProp
    operator fun set(name: String, value: String)
    operator fun set(name: String, value: Boolean)
    operator fun set(name: String, value: Long)
    operator fun <T> set(name: String, value: List<T>)
    fun list(): List<NodeProp>
}