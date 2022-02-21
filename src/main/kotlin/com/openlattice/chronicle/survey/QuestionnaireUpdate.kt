package com.openlattice.chronicle.survey

import org.dmfs.rfc5545.recur.RecurrenceRule

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */

data class QuestionnaireUpdate(
    val title: String,
    val description: String?,
    val recurrenceRule: RecurrenceRule?,
    val active: Boolean?,
    val questions: List<Question>?
)
