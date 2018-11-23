package org.deniskusakin.aemlibrary.jcr

import java.io.InputStream
import java.util.*
import javax.jcr.Binary

sealed class NodePropValue

data class StringValue(val value: String) : NodePropValue()
data class BooleanValue(val value: Boolean) : NodePropValue()
data class LongValue(val value: Long) : NodePropValue()
data class DateValue(val value: Calendar) : NodePropValue()
data class MultipleValue<out T : NodePropValue>(val value: List<T>) : NodePropValue()

class BinaryPropValue(private val binary: Binary) : NodePropValue() {

    //TODO: rework?
    fun getStream(): InputStream {
        try {
            return binary.stream
        } finally {
            binary.dispose()
        }
    }

}

object EmptyValue : NodePropValue() {
    override fun toString(): String {
        return "Empty"
    }
}