package org.deniskusakin.aemlibrary

import org.deniskusakin.aemlibrary.nodes.JcrNode
import org.deniskusakin.aemlibrary.nodes.impl.JcrNodeImpl
import javax.jcr.Session

class AemSessionImpl(private val jcrSession: Session) : AemSession {
    override fun node(path: String): JcrNode {
        if (jcrSession.nodeExists(path)) {
            return JcrNodeImpl(jcrSession.getNode(path))
        }
        throw Exception("Node does not exist")
    }

    override fun commit() {
        jcrSession.save()
    }
}