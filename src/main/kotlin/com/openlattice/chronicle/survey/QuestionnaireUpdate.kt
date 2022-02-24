package com.openlattice.chronicle.survey

import org.dmfs.rfc5545.recur.RecurrenceRule

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */

data class QuestionnaireUpdate(
    val title: String,
    val description: String?,
    var recurrenceRule: String?,
    val active: Boolean?,
    val questions: List<Question>?
) {
    init {
        recurrenceRule?.let {
            recurrenceRule = RecurrenceRule(it).toString()
        }
    }
}
