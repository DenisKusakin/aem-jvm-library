package org.deniskusakin.aemlibrary.jcr.impl

import org.deniskusakin.aemlibrary.jcr.*
import javax.jcr.Node

class EmptyNodePropImpl(private val node: Node, private val propName: String) : NodeProp {
    override val name: String
        get() = propName
    override var value: NodePropValue
        get() = EmptyValue
        set(value) {
            when (value) {
                is StringValue -> node.setProperty(propName, value.value)
                is BooleanValue -> node.setProperty(propName, value.value)
                is LongValue -> node.setProperty(propName, value.value)
            }
        }
}