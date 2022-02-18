package com.openlattice.chronicle.survey

import com.google.common.base.Preconditions
import com.google.common.base.Preconditions.checkArgument
import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class Questionnaire(
    val id: UUID?,
    val title: String,
    val dateCreated: OffsetDateTime?,
    val description: String = "",
    val active: Boolean = true,
    val questions: List<Question>
) {
    init {
        check(questions.isNotEmpty()) { "questions must be non-empty" }
        check(title.isNotBlank()) { "title cannot be blank" }
        val questionTitles = questions.map { it.title }
        check(questionTitles.none { it.isBlank() }) { "question titles cannot be blank"}
        check(questionTitles.distinct().size == questionTitles.size) { "question titles should be unique" }
    }
}

data class Question(
    val title: String,
    val choices: Set<String> = setOf() //empty if question is open-ended
)

data class QuestionnaireResponse(
    val questionTitle: String,
    val value: Set<String>
) {
    init {
        check(questionTitle.isNotBlank()) { "question title must be non-blank"}
    }
}

