package racingcar.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import racingcar.domain.rules.Exception
import racingcar.domain.usecase.CheckPlayerNameUseCase

class CheckPlayerNameUseCaseTest {
    private lateinit var useCase: CheckPlayerNameUseCase

    @BeforeEach
    fun setUp(){
        useCase = CheckPlayerNameUseCase()
    }

    @Test
    fun `입력값이 비어있을 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            useCase(" ")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.EMPTY_INPUT}")
    }

    @ParameterizedTest
    @CsvSource(
        "###,@@@,!!!,",
        "asd123@,chan,#poby,c#h#a(*)n",
        "c!b@,c3,,"
    )
    fun `입력값이 한,영,숫자,구분자로 외일 때`(value: String){
        Assertions.assertThatThrownBy {
            useCase(" ")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.EMPTY_INPUT}")
    }

    @Test
    fun `이름이 중복될 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            useCase("poby,chan,poby,hoby,cuby")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.DUPLICATED_NAME}")
    }

    @Test
    fun `플레이어가 한 명일 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            useCase("poby")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.ONE_PLAYER}")
    }

    @Test
    fun `이름 길이가 5를 초과할 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            useCase("pobyyyyyyy,chan,pobyg")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Exception.OVER_MAX_NAME_LENGTH}")
    }

    @Test
    fun `이름 정상 입력 테스트`(){
        Assertions.assertThatCode { useCase("poby,chan") }
            .doesNotThrowAnyException()
    }
}