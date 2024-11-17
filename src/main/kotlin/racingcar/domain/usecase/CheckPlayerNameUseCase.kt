package racingcar.domain.usecase

import racingcar.domain.rules.Exception
import racingcar.domain.rules.Constants.DELIMITER
import racingcar.domain.rules.Game.MAX_NAME_LENGTH

class CheckPlayerNameUseCase {
    operator fun invoke(input: String): List<String> {
        isEmpty(input)
        val result = isInvalidFormEachValue(input)
        return result
    }

    private fun isInvalidFormEachValue(input: String): List<String> {
        val spliterator = input.split("$DELIMITER")
            .map { it.trim() }
            .filter { it.isNotBlank() }
        isValidForm(spliterator)
        isOnePlayer(spliterator)
        isDuplicate(spliterator)
        isOverMaxNameLength(spliterator)
        return spliterator
    }

    private fun isEmpty(input: String) {
        require(input.isNotBlank()) { Exception.EMPTY_INPUT }
    }

    private fun isValidForm(input: List<String>) {
        input.forEach {
            require(it.matches(regex)) { Exception.INVALID_NAME_FORMAT }
        }
    }

    private fun isDuplicate(input: List<String>) {
        val mapped = input.groupingBy { it }.eachCount()
        require(mapped.values.all { it == 1 }) { Exception.DUPLICATED_NAME }
    }

    private fun isOnePlayer(input: List<String>) {
        require(input.filter { it.isNotBlank() }.size > 1) {
            Exception.ONE_PLAYER
        }
    }

    private fun isOverMaxNameLength(input: List<String>) {
        require(input.all { it.length <= MAX_NAME_LENGTH.getValue() }) {
            Exception.OVER_MAX_NAME_LENGTH
        }
    }

    companion object {
        private val regex = Regex("^[가-핳a-zA-Z0-9,]*\$")
    }
}