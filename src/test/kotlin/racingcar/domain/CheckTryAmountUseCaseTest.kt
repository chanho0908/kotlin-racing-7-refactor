package racingcar.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import racingcar.domain.rules.Exception
import racingcar.domain.usecase.CheckTryAmountUseCase

class CheckTryAmountUseCaseTest {
    private lateinit var useCase: CheckTryAmountUseCase

    @BeforeEach
    fun setUp(){
        useCase = CheckTryAmountUseCase()
    }

    @Test
    fun `입력값이 비어있을 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            useCase(" ")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.EMPTY_INPUT}")
    }

    @Test
    fun `시도 횟수 입력값이 음수일 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            useCase("-1")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.GUIDE_POSITIVE_ONLY}")
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다, abc, ###, 2147483648"])
    fun `시도 횟수 입력값이 숫자가 아닐 때 테스트`(value: String){
        Assertions.assertThatThrownBy {
            useCase(value)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.GUIDE_INPUT_ONLY_DIGIT}")
    }

    @Test
    fun `시도 횟수가 1만회를 초과할 때 테스트`(){
        Assertions.assertThatThrownBy {
            useCase("100000")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.MAX_TRY_AMOUNT}")
    }
}