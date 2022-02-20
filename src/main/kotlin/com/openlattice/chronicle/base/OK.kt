package com.openlattice.chronicle.base

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class OK( val msg : String = "SUCCESS" ) {
    companion object {
        @JvmField
        val ok = OK()
    }
}