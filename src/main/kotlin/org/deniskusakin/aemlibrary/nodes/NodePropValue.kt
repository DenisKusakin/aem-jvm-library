package org.deniskusakin.aemlibrary.nodes

sealed class NodePropValue

data class StringValue(val value: String) : NodePropValue()
data class BooleanValue(val value: Boolean) : NodePropValue()
data class LongValue(val value: Long) : NodePropValue()

data class StringMultipleValue(val value: List<String>) : NodePropValue()
data class BooleanMultipleValue(val value: List<Boolean>) : NodePropValue()
data class LongMultipleValue(val value: List<Long>) : NodePropValue()

object EmptyValue : NodePropValue() {
    override fun toString(): String {
        return "Empty"
    }
}