package org.deniskusakin.aemlibrary.sling.impl

import org.deniskusakin.aemlibrary.sling.SlingRunModes

class SlingRunModesImpl(private val runModes: List<String>) : SlingRunModes {
    override fun asStrings(): List<String> {
        return runModes
    }
}