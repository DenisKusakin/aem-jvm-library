package org.deniskusakin.aemlibrary.osgi.impl

import org.deniskusakin.aemlibrary.osgi.Bundle
import org.deniskusakin.aemlibrary.osgi.BundlesInfo

class BundlesInfoImpl(private val bundles: Collection<Bundle>) : BundlesInfo {
    override fun asCollection(): Collection<Bundle> {
        return bundles
    }
}