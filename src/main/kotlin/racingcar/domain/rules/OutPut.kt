package racingcar.domain.rules

enum class OutPut(private val msg: String) {
    INPUT_NAME_MESSAGE("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    INPUT_TRY_AMOUNT_MESSAGE("시도할 횟수는 몇 회인가요?"),
    PLAY_RESULT("\n실행 결과"),
    STAGE_RESULT("%s : %s"),
    WINNER("최종 우승자 : ");

    override fun toString(): String  = msg
    companion object{
        fun stateResultFormat(name: String, currentStep: String): String{
            return "$STAGE_RESULT".format(name, currentStep)
        }
    }
}