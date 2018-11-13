package org.deniskusakin.aemlibrary.jcr.impl

import org.deniskusakin.aemlibrary.jcr.*
import javax.jcr.Property
import javax.jcr.PropertyType

class NodePropImpl(private val property: Property) : NodeProp {
    override val name: String
        get() = property.name
    override var value: NodePropValue
        get() {
            if (property.isMultiple) {
                return when (property.type) {
                    PropertyType.STRING -> StringMultipleValue(property.values.map { it.string })
                    PropertyType.BOOLEAN -> BooleanMultipleValue(property.values.map { it.boolean })
                    PropertyType.LONG -> LongMultipleValue(property.values.map { it.long })
                    else -> EmptyValue
                }
            }
            val propValType = property.value.type
            return when (propValType) {
                PropertyType.STRING -> StringValue(property.value.string)
                PropertyType.BOOLEAN -> BooleanValue(property.value.boolean)
                PropertyType.LONG -> LongValue(property.value.long)
                else -> EmptyValue
            }
        }
        set(value) {
            when (value) {
                is StringValue -> property.setValue(value.value)
                is BooleanValue -> property.setValue(value.value)
                is LongValue -> property.setValue(value.value)
            }
        }

    override fun toString(): String {
        return "$name -> $value"
    }
}