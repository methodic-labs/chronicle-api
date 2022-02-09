package com.openlattice.chronicle

import com.openlattice.chronicle.candidates.Candidate
import com.openlattice.chronicle.ids.IdConstants
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.util.UUID

class CandidateTests {

    @Test
    fun testNewCandidate() {
        val c1 = Candidate("iron", "man", "ironman", LocalDate.parse("2008-05-02"))
        Assert.assertEquals(IdConstants.UNINITIALIZED.id, c1.id)
        c1.id = UUID.randomUUID()
        val c2 = Candidate(c1.id, "iron", "man", "ironman", LocalDate.parse("2008-05-02"))
        Assert.assertEquals(c1, c2)
    }

    @Test
    fun testCandidateEquality() {
        val c1 = Candidate("iron", "man", "ironman", LocalDate.parse("2008-05-02"))
        val c2 = Candidate("iron", "man", "ironman", LocalDate.parse("2008-05-02"))
        Assert.assertEquals(c1, c2)
        val id = UUID.randomUUID()
        val c3 = Candidate(id, "black", "panther")
        val c4 = Candidate(id, "black", "panther")
        Assert.assertEquals(c3, c4)
        Assert.assertNotEquals(c1, c3)
    }
}
