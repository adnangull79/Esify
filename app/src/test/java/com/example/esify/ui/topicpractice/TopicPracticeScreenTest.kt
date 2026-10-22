package com.example.esify.ui.topicpractice

import com.example.esify.data.question.McqQuestion
import com.example.esify.data.question.PracticeMode
import com.example.esify.data.question.QuestionCatalogue
import com.example.esify.data.question.QuestionOption
import com.example.esify.data.question.ShortQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class TopicPracticeScreenTest {

    @Test
    fun mcqs_csTopic1ContainsAtLeast8ValidQuestions() {
        val mcqs = QuestionCatalogue.getMcqs("cs_ch1_t1")
        assertTrue("Must contain at least 8 MCQs for the primary topic", mcqs.size >= 8)

        mcqs.forEachIndexed { index, q ->
            assertTrue("Question $index must not be blank", q.question.isNotBlank())
            assertEquals("Question $index must have exactly 4 options", 4, q.options.size)
            assertTrue("Question $index explanation must not be blank", q.explanation.isNotBlank())

            val optionIds = q.options.map { it.id }
            assertEquals("Question $index options must be A, B, C, D", listOf("A", "B", "C", "D"), optionIds)
            assertTrue("Question $index correct option ID must be in options", optionIds.contains(q.correctOptionId))
            assertEquals(q.correctOptionId, q.correctOption.id)
            assertTrue("Question $index correct option text must not be blank", q.correctOption.text.isNotBlank())
        }
    }

    @Test
    fun mcq1_matchesReferenceData() {
        val mcqs = QuestionCatalogue.getMcqs("cs_ch1_t1")
        val q1 = mcqs[0]

        assertEquals("mcq_1", q1.id)
        assertTrue(q1.question.contains("What is an electronic machine that accepts data"))
        assertEquals("B", q1.correctOptionId)
        assertEquals("Computer", q1.correctOption.text)
        assertTrue(q1.explanation.contains("processes it according to stored instructions"))
    }

    @Test
    fun shortQuestions_allFourSubjectsContainAtLeast5QuestionsEach() {
        val csQuestions = QuestionCatalogue.getShortQuestions("cs_ch1_t1")
        val phyQuestions = QuestionCatalogue.getShortQuestions("phy_ch1_t1")
        val chemQuestions = QuestionCatalogue.getShortQuestions("chem_ch1_t1")
        val bioQuestions = QuestionCatalogue.getShortQuestions("bio_ch1_t1")

        assertEquals("Computer Science must contain exactly 5 short questions", 5, csQuestions.size)
        assertEquals("Physics must contain exactly 5 short questions", 5, phyQuestions.size)
        assertEquals("Chemistry must contain exactly 5 short questions", 5, chemQuestions.size)
        assertEquals("Biology must contain exactly 5 short questions", 5, bioQuestions.size)

        val totalSampleQuestions = csQuestions.size + phyQuestions.size + chemQuestions.size + bioQuestions.size
        assertEquals("Total sample questions across all 4 subjects must be at least 20", 20, totalSampleQuestions)

        val allSubjectLists = listOf(
            "Computer Science" to csQuestions,
            "Physics" to phyQuestions,
            "Chemistry" to chemQuestions,
            "Biology" to bioQuestions
        )

        allSubjectLists.forEach { (subjectName, list) ->
            list.forEachIndexed { index, sq ->
                assertEquals("$subjectName question number must match index + 1", index + 1, sq.questionNumber)
                assertTrue("$subjectName Question $index ID must not be blank", sq.id.isNotBlank())
                assertTrue("$subjectName Question $index text must not be blank", sq.question.isNotBlank())
                assertTrue("$subjectName Question $index model answer must not be blank", sq.modelAnswer.isNotBlank())
                assertTrue("$subjectName Question $index must contain numbered answer points", sq.answerPoints.isNotEmpty())
                sq.answerPoints.forEach { point ->
                    assertTrue("$subjectName point must not be blank", point.isNotBlank())
                }
            }
        }
    }

    @Test
    fun shortQuestions_csTopic1MatchesCurriculumData() {
        val csQuestions = QuestionCatalogue.getShortQuestions("cs_ch1_t1")
        val sq1 = csQuestions[0]
        assertEquals("sq_cs_1", sq1.id)
        assertTrue(sq1.question.contains("What is an electronic computer? Define its basic functions"))
        assertTrue(sq1.modelAnswer.contains("accepts raw data as input"))
        assertEquals(4, sq1.answerPoints.size)
        assertTrue(sq1.answerPoints[0].startsWith("Input:"))
        assertTrue(sq1.answerPoints[1].startsWith("Processing:"))
        assertTrue(sq1.answerPoints[2].startsWith("Output:"))
        assertTrue(sq1.answerPoints[3].startsWith("Storage:"))
    }

    @Test
    fun shortQuestions_physicsTopic1MatchesCurriculumData() {
        val phyQuestions = QuestionCatalogue.getShortQuestions("phy_ch1_t1")
        val sq1 = phyQuestions[0]
        assertEquals("sq_phy_1", sq1.id)
        assertTrue(sq1.question.contains("Define physics and briefly describe any four of its main branches"))
        assertTrue(sq1.modelAnswer.contains("study of matter, energy"))
        assertEquals(4, sq1.answerPoints.size)
    }

    @Test
    fun shortQuestions_chemistryTopic1MatchesCurriculumData() {
        val chemQuestions = QuestionCatalogue.getShortQuestions("chem_ch1_t1")
        val sq1 = chemQuestions[0]
        assertEquals("sq_chem_1", sq1.id)
        assertTrue(sq1.question.contains("Define chemistry and briefly describe any four of its major branches"))
        assertTrue(sq1.modelAnswer.contains("reactions of matter"))
        assertEquals(4, sq1.answerPoints.size)
    }

    @Test
    fun shortQuestions_biologyTopic1MatchesCurriculumData() {
        val bioQuestions = QuestionCatalogue.getShortQuestions("bio_ch1_t1")
        val sq1 = bioQuestions[0]
        assertEquals("sq_bio_1", sq1.id)
        assertTrue(sq1.question.contains("Define biology and explain its three major divisions"))
        assertTrue(sq1.modelAnswer.contains("study of life"))
        assertEquals(3, sq1.answerPoints.size)
    }

    @Test
    fun shortQuestions_returnsEmptyForUnrelatedTopicToPreventStaleData() {
        val unknownTopicQuestions = QuestionCatalogue.getShortQuestions("cs_ch1_t2")
        assertTrue("Topics without custom short questions must return an empty list so empty state is shown", unknownTopicQuestions.isEmpty())

        val unpopulatedChapterQuestions = QuestionCatalogue.getShortQuestions("phy_ch2_t1")
        assertTrue("Unpopulated chapters must return empty list", unpopulatedChapterQuestions.isEmpty())
    }

    @Test
    fun shortQuestions_modelBackwardCompatibility() {
        val legacyShortQuestion = ShortQuestion(
            id = "legacy_1",
            topicId = "topic_1",
            question = "Legacy question without points?",
            modelAnswer = "Legacy model answer.",
            explanation = "Legacy note."
        )

        assertEquals("legacy_1", legacyShortQuestion.id)
        assertEquals(1, legacyShortQuestion.questionNumber)
        assertTrue(legacyShortQuestion.answerPoints.isEmpty())
        assertEquals("Legacy model answer.", legacyShortQuestion.modelAnswer)
    }

    @Test
    fun resolveTopic_resolvesAllFourSubjectsCorrectly() {
        val (csSub, csCh, csTop) = QuestionCatalogue.resolveTopic("computer_science", "cs_ch1", "cs_ch1_t1")
        assertEquals("Computer Science", csSub.name)
        assertEquals("Introduction to Computer", csCh.title)
        assertEquals("What is a Computer?", csTop.title)

        val (phySub, phyCh, phyTop) = QuestionCatalogue.resolveTopic("physics", "phy_ch1", "phy_ch1_t1")
        assertEquals("Physics", phySub.name)
        assertEquals("Physical Quantities & Measurement", phyCh.title)
        assertEquals("Physical Quantities & Measurement", phyTop.title)

        val (chemSub, chemCh, chemTop) = QuestionCatalogue.resolveTopic("chemistry", "chem_ch1", "chem_ch1_t1")
        assertEquals("Chemistry", chemSub.name)
        assertEquals("Fundamentals of Chemistry", chemCh.title)
        assertEquals("Fundamentals of Chemistry", chemTop.title)

        val (bioSub, bioCh, bioTop) = QuestionCatalogue.resolveTopic("biology", "bio_ch1", "bio_ch1_t1")
        assertEquals("Biology", bioSub.name)
        assertEquals("Introduction to Biology", bioCh.title)
        assertEquals("Introduction to Biology", bioTop.title)
    }

    @Test
    fun resolveTopic_fallsBackGracefullyForNullOrUnknown() {
        val (sub1, ch1, top1) = QuestionCatalogue.resolveTopic(null, null, null)
        assertNotNull(sub1)
        assertNotNull(ch1)
        assertNotNull(top1)
        assertEquals("Computer Science", sub1.name)
        assertEquals("Introduction to Computer", ch1.title)
        assertEquals("What is a Computer?", top1.title)

        val (_, _, topUnknown) = QuestionCatalogue.resolveTopic("computer_science", "cs_ch1", "unknown_topic_id")
        assertNotNull(topUnknown)
        assertEquals("What is a Computer?", topUnknown.title)
    }

    @Test
    fun practiceMode_fromRouteParsesCorrectly() {
        assertEquals(PracticeMode.MCQ, PracticeMode.fromRoute("mcq"))
        assertEquals(PracticeMode.MCQ, PracticeMode.fromRoute("MCQS"))
        assertEquals(PracticeMode.MCQ, PracticeMode.fromRoute("  mcq  "))

        assertEquals(PracticeMode.SHORT_QUESTIONS, PracticeMode.fromRoute("short_questions"))
        assertEquals(PracticeMode.SHORT_QUESTIONS, PracticeMode.fromRoute("short"))
        assertEquals(PracticeMode.SHORT_QUESTIONS, PracticeMode.fromRoute("questions"))

        assertEquals(PracticeMode.QUIZ, PracticeMode.fromRoute("quiz"))
        assertEquals(PracticeMode.QUIZ, PracticeMode.fromRoute("take_quiz"))

        assertEquals(PracticeMode.MCQ, PracticeMode.fromRoute(null))
        assertEquals(PracticeMode.MCQ, PracticeMode.fromRoute("invalid_mode"))
    }

    @Test
    fun mcqQuestion_validationThrowsOnInvalidOptionsOrCorrectId() {
        // Less than 4 options throws
        assertThrows(IllegalArgumentException::class.java) {
            McqQuestion(
                id = "test_1",
                topicId = "topic_1",
                question = "Test Question",
                options = listOf(
                    QuestionOption("A", "Option A"),
                    QuestionOption("B", "Option B"),
                    QuestionOption("C", "Option C")
                ),
                correctOptionId = "A",
                explanation = "Explanation"
            )
        }

        // Correct option ID not in options throws
        assertThrows(IllegalArgumentException::class.java) {
            McqQuestion(
                id = "test_2",
                topicId = "topic_1",
                question = "Test Question",
                options = listOf(
                    QuestionOption("A", "Option A"),
                    QuestionOption("B", "Option B"),
                    QuestionOption("C", "Option C"),
                    QuestionOption("D", "Option D")
                ),
                correctOptionId = "E",
                explanation = "Explanation"
            )
        }
    }
}
