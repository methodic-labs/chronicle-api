package com.openlattice.chronicle.authorization

import java.util.*

/**
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
class AclKey(ids: List<UUID>) : List<UUID> by ids {
    constructor(vararg ids: UUID) : this(ids.asList())

    val index: String

    @Transient
    private val h: Int = super.hashCode()

    init {
        val joiner = StringJoiner(",")
        for (uuid in this) {
            joiner.add(uuid.toString())
        }
        index = joiner.toString()
    }

    operator fun compareTo(o: AclKey): Int {
        var result = 0
        var i = 0
        while (result == 0 && i < size) {

            //If everything has been equal up to the point o ran out of entries.
            if (i > o.size) {
                return 1
            }

            //Compare the next two
            val a = get(i)
            val b: UUID = o[i]
            result = a.compareTo(b)
            ++i
        }
        return result
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        if (!super.equals(o)) {
            return false
        }
        val uuids = o as AclKey
        return hashCode() == uuids.hashCode()
    }

    override fun hashCode(): Int = h
}