package com.openlattice.chronicle.android

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */

class ChronicleData(data: List<ChronicleSample>) : List<ChronicleSample> by data {
    private val h: Int = data.hashCode()

    override fun toString(): String {
        return "[" + this.joinToString(",") + "]"
    }

    override fun equals(other: Any?): Boolean {
        return if (other !is ChronicleData) false
        else other.mapIndexed { index, chronicleSample -> this[index] == chronicleSample }.all { it }
    }

    /**
     * This hash code will be valid as long the hashcode of each ChronicleSample cannot be mutated. At the moment
     * this is true, but if it ever changes in the future we will have to make hashcode computation dynamic.
     */
    override fun hashCode(): Int = h
}