package racingcar.domain.usecase

import racingcar.domain.rules.Game.MAX_TRY_AMOUNT
import racingcar.domain.rules.Exception

class CheckTryAmountUseCase {
    operator fun invoke(input: String){
        isEmpty(input)
        isNumeric(input)
        isOverMaxTryAmount(input)
        isPositive(input)
    }

    private fun isEmpty(input: String){
        require(input.isNotBlank()){ Exception.EMPTY_INPUT }
    }

    private fun isNumeric(input: String){
        requireNotNull(input.toIntOrNull()){ Exception.GUIDE_INPUT_ONLY_DIGIT }
    }

    private fun isOverMaxTryAmount(input: String){
        require(input.toInt() <= MAX_TRY_AMOUNT.getValue()){ Exception.MAX_TRY_AMOUNT }
    }

    private fun isPositive(input: String) {
        require(input.toInt() > 0) { Exception.GUIDE_POSITIVE_ONLY }
    }
}