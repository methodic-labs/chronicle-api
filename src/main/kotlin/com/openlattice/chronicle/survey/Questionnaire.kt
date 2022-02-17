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
    val title: String,
    val type: QuestionType,
    val options: Set<String>
)

enum class QuestionType{
    MULTIPLE_CHOICE,
    TEXT_INPUT
}
