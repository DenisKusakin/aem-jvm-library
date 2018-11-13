package org.deniskusakin.aemlibrary

import org.deniskusakin.aemlibrary.jcr.JcrNode

interface AemSession {
    fun node(path: String): JcrNode
    fun commit()
}