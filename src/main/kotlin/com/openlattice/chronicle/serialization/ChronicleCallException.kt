package com.openlattice.chronicle.serialization

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
class ChronicleCallException : RuntimeException {
    val body: String
    val code: Int
    val url: String

    constructor(message: String, url: String, body: String, code: Int) : super(message) {
        this.code = code
        this.body = body
        this.url = url
    }

    constructor(message: String, cause: Throwable, url:String, body: String, code: Int) : super(message, cause) {
        this.code = code
        this.body = body
        this.url = url
    }
}