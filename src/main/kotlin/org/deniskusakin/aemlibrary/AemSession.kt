package org.deniskusakin.aemlibrary

import org.deniskusakin.aemlibrary.nodes.JcrNode

interface AemSession {
    fun node(path: String): JcrNode
    fun commit()
}