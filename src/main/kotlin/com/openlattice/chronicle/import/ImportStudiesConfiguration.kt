package com.openlattice.chronicle.import

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class ImportStudiesConfiguration(
    val dataSourceName : String,
    val candidatesTable: String,
    val studiesTable: String,
    val timeUseDiaryTable: String? = ""
)