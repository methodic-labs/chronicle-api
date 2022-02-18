package com.openlattice.chronicle.survey

import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class Questionnaire(
    val id: UUID,
    val title: String,
    val description: String,
    val active: Boolean = true,
    val questions: List<Question>
)

data class Question(
    val id: UUID,
    val title: String,
    val choices: Set<String> //
)

data class QuestionnaireResponse(
    val questionId: UUID,
    val value: Set<String>
)

