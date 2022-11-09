package com.openlattice.chronicle.android

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */

class ChronicleData(data: List<ChronicleSample>) : List<ChronicleSample> by data {


    override fun toString(): String {
        return "[" + this.joinToString(",") + "]"
    }

    override fun equals(other: Any?): Boolean {
        return if (other !is ChronicleData) false
        else other.mapIndexed { index, chronicleSample -> this[index] == chronicleSample }.all { it }
    }

    override fun hashCode(): Int {
        return this.hashCode()
    }
}