package org.deniskusakin.aemlibrary.jcr

import org.apache.commons.io.IOUtils

private fun NodePropValue.propertyValueToString(): String {
    return when (this) {
        is StringValue -> this.value
        is BooleanValue -> this.value.toString()
        is LongValue -> this.value.toString()
        is BinaryPropValue -> IOUtils.toString(this.getStream())
        is DateValue -> this.value.toString()
        is MultipleValue<*> -> throw Exception("Smt went wrong")
        EmptyValue -> ""
    }
}

/**
 * @author Denis_Kusakin. 11/22/2018.
 */
fun NodeProp.asString(): String {
    return when (this.value) {
        is StringValue -> this.value.propertyValueToString()
        is BooleanValue -> this.value.propertyValueToString()
        is LongValue -> this.value.propertyValueToString()
        is BinaryPropValue -> this.value.propertyValueToString()
        is DateValue -> this.value.propertyValueToString()
        is MultipleValue<*> -> {
            val multipleValue = this.value as MultipleValue<NodePropValue>
            if (multipleValue.value.isEmpty()) {
                return ""
            }
            if (multipleValue.value.size > 1) {
                throw Exception("Property is multi-value with more than one value")
            }
            return (multipleValue.value[0].propertyValueToString())
        }
        EmptyValue -> ""
    }
}

fun NodeProp.asLong(): Long {
    return when (this.value) {
        is LongValue -> (this.value as LongValue).value
        is StringValue -> (this.value as StringValue).value.toLong()
        else -> throw Exception("Property could not be represented as long")
    }
}