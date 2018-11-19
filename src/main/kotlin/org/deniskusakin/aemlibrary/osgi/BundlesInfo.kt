package org.deniskusakin.aemlibrary.osgi

interface BundlesInfo {
    fun asCollection(): Collection<Bundle>
}