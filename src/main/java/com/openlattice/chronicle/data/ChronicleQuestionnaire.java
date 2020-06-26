package com.openlattice.chronicle.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.SetMultimap;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
public class ChronicleQuestionnaire {
    private Map<FullQualifiedName, Set<Object>>          questionnaireDetails;
    private List<Map<FullQualifiedName, Set<Object>>> questions;

    @JsonCreator
    public ChronicleQuestionnaire() {
    }

    @JsonCreator
    public ChronicleQuestionnaire(
            @JsonProperty( SerializationConstants.QUESTIONNAIRE_DETAILS )
                    Map<FullQualifiedName, Set<Object>> questionnaireDetails,
            @JsonProperty( SerializationConstants.QUESTIONS ) List<Map<FullQualifiedName, Set<Object>>> questions
    ) {
        this.questions = questions;
        this.questionnaireDetails = questionnaireDetails;
    }

    @JsonProperty( SerializationConstants.QUESTIONNAIRE_DETAILS )
    public Map<FullQualifiedName, Set<Object>> getQuestionnaireDetails() {
        return questionnaireDetails;
    }

    public void setQuestionnaireDetails( Map<FullQualifiedName, Set<Object>> questionnaireDetails ) {
        this.questionnaireDetails = questionnaireDetails;
    }

    @JsonProperty( SerializationConstants.QUESTIONS )
    public List<Map<FullQualifiedName, Set<Object>>> getQuestions() {
        return questions;
    }

    public void setQuestions( List<Map<FullQualifiedName, Set<Object>>> questions ) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "ChronicleQuestionnaire{" +
                "questionnaireDetails=" + questionnaireDetails +
                ", questions=" + questions +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        ChronicleQuestionnaire that = (ChronicleQuestionnaire) o;
        return Objects.equals( questionnaireDetails, that.questionnaireDetails ) &&
                Objects.equals( questions, that.questions );
    }

    @Override
    public int hashCode() {
        return Objects.hash( questionnaireDetails, questions );
    }
}
