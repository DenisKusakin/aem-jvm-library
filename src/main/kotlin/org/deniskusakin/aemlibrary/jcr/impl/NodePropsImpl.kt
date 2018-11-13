package org.deniskusakin.aemlibrary.jcr.impl

import org.apache.jackrabbit.value.ValueFactoryImpl
import org.deniskusakin.aemlibrary.jcr.NodeProp
import org.deniskusakin.aemlibrary.jcr.NodeProps
import javax.jcr.Node

class NodePropsImpl(private val node: Node) : NodeProps {
    override fun get(name: String): NodeProp {
        if (node.hasProperty(name)) {
            return NodePropImpl(node.getProperty(name))
        }
        return EmptyNodePropImpl(node, name)
    }

    override fun set(name: String, value: String) {
        node.setProperty(name, value)
    }

    override fun set(name: String, value: Boolean) {
        node.setProperty(name, value)
    }

    override fun set(name: String, value: Long) {
        node.setProperty(name, value)
    }

    override fun <T> set(name: String, value: List<T>) {
        val valueFactory = ValueFactoryImpl.getInstance()
        val array = value.map {
            when (it) {
                is String -> valueFactory.createValue(it)
                is Boolean -> valueFactory.createValue(it)
                is Long -> valueFactory.createValue(it)
                else -> throw Exception("Unsupported value class")
            }
        }.toTypedArray()
        node.setProperty(name, array)
    }

    override fun list(): List<NodeProp> {
        val propertyIterator = node.properties
        val props = mutableListOf<NodeProp>()
        while (propertyIterator.hasNext()) {
            props.add(NodePropImpl(propertyIterator.nextProperty()))
        }
        return props
    }
}