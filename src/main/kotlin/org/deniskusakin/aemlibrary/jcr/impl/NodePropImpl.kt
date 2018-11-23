package org.deniskusakin.aemlibrary.jcr.impl

import org.deniskusakin.aemlibrary.jcr.*
import java.util.*
import javax.jcr.Property
import javax.jcr.PropertyType

class NodePropImpl(private val property: Property) : NodeProp {
    override val name: String
        get() = property.name
    override val value: NodePropValue
        get() {
            if (property.isMultiple) {
                return when (property.type) {
                    PropertyType.STRING -> MultipleValue(property.values.map { StringValue(it.string) })
                    PropertyType.BOOLEAN -> MultipleValue(property.values.map { BooleanValue(it.boolean) })
                    PropertyType.LONG -> MultipleValue(property.values.map { LongValue(it.long) })
                    PropertyType.DATE -> MultipleValue(property.values.map { DateValue(it.date) })
                    else -> EmptyValue
                }
            }
            val propValType = property.value.type
            return when (propValType) {
                PropertyType.STRING -> StringValue(property.value.string)
                PropertyType.BOOLEAN -> BooleanValue(property.value.boolean)
                PropertyType.LONG -> LongValue(property.value.long)
                PropertyType.DATE -> DateValue(property.value.date)
                PropertyType.BINARY -> BinaryPropValue(property.value.binary)
                else -> EmptyValue
            }
        }

    override fun toString(): String {
        return "$name -> $value"
    }
}