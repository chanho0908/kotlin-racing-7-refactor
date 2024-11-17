package racingcar.domain.rules

enum class Exception(private val msg: String) {
    EMPTY_INPUT("빈 값을 입력 하셨습니다."),
    DUPLICATED_NAME("이름은 중복될 수 없어요"),
    ONE_PLAYER("플레이어는 한 명 이상 입력해주세요."),
    INVALID_NAME_FORMAT("이름은 한,영,숫자만 입력해 주세요"),
    OVER_MAX_NAME_LENGTH("이름은 최대 5글자 입니다."),
    GUIDE_POSITIVE_ONLY("시도 횟수는 1 이상의 정수여야 합니다."),
    GUIDE_INPUT_ONLY_DIGIT("시도 횟수는 숫자만 입력해 주세요."),
    MAX_TRY_AMOUNT("시도 횟수는 최대 1만회입니다.");

    override fun toString(): String = "$ERROR $msg"

    companion object {
        const val ERROR = "[ERROR]"
    }
}