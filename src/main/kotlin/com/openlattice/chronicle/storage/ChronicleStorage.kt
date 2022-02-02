package com.openlattice.chronicle.storage

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
enum class ChronicleStorage( val id: String ) {
    /**
     * Most things default to platform storage. It should only be used for collecting and storing smaller data sets.
     */
    PLATFORM("default"),

    /**
     * Chronicle storage is expected to handle larger data sets and in production is likely to live in a data warehouse
     * like redshift or snowflake
     */
    CHRONICLE("chronicle") //TODO: Consider to changing this to just redshift
}