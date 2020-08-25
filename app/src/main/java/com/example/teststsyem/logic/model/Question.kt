package com.example.teststsyem.logic.model

open class Question(val questionID: Int, val questionText: String, var correction: String, protected val correctAnswer: String) {
    protected var yourAnswer: String? = null

    @JvmName("setYourAnswer1")
    fun setYourAnswer(yourAnswerText: String) {
        yourAnswer = yourAnswerText
    }

    open fun judge() {
        if (yourAnswer == correctAnswer) {
            correction = "正确"
        } else {
            correction = "错误"
        }
    }
}

class SingleChoice(questionID: Int, questionText: String, correction: String, val choices: List<Choice>, correctAnswer: String) : Question(questionID, questionText, correction, correctAnswer)

class MutableChoice(questionID: Int, questionText: String, correction: String, val choices: List<Choice>, correctAnswer: String) : Question(questionID, questionText, correction, correctAnswer) {
    override fun judge() {
        if (yourAnswer == null) {
            correction = "错误"
        } else {
            if (yourAnswer == correctAnswer) {
                correction = "正确"
            } else {
                for (i in (yourAnswer!!.indices)) {
                    if (correctAnswer.indexOf(yourAnswer!![i]) == -1) {
                        correction = "错误"
                        return
                    }
                }
                correction = "不完全正确"
            }
        }
    }
}

class JudgeChoice(questionID: Int, questionText: String, correction: String, correctAnswer: String) : Question(questionID, questionText, correction, correctAnswer)