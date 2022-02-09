package com.openlattice.chronicle.candidates

import com.openlattice.chronicle.ids.IdConstants
import java.time.LocalDate
import java.util.UUID

class Candidate(
    val firstName: String? = null,
    val lastName: String? = null,
    val name: String? = null,
    val dateOfBirth: LocalDate? = null,
    val email: String? = null,
    val phoneNumber: String? = null
) {
    var id: UUID = IdConstants.UNINITIALIZED.id

    constructor(
        id: UUID,
        firstName: String? = null,
        lastName: String? = null,
        name: String? = null,
        dateOfBirth: LocalDate? = null,
        email: String? = null,
        phoneNumber: String? = null
    ) : this(firstName, lastName, name, dateOfBirth, email, phoneNumber) {
        this.id = id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Candidate

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (name != other.name) return false
        if (dateOfBirth != other.dateOfBirth) return false
        if (email != other.email) return false
        if (phoneNumber != other.phoneNumber) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName?.hashCode() ?: 0
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (dateOfBirth?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        result = 31 * result + id.hashCode()
        return result
    }

    override fun toString(): String {
        return "Candidate(" +
                " id=$id" +
                " firstName=$firstName," +
                " lastName=$lastName," +
                " name=$name," +
                " dateOfBirth=$dateOfBirth," +
                " email=$email," +
                " phoneNumber=$phoneNumber," +
                ")"
    }
}
