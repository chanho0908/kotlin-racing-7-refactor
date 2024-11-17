package racingcar.domain.rules

enum class Game(private val value: Int) {
    MAX_TRY_AMOUNT(10000),
    MAX_NAME_LENGTH(5),
    RANDOM_MIN(0),
    RANDOM_MAX(9),
    MOVE_COUNT(1),
    MIN_MOVEABLE_CONDITION(4);

    fun getValue(): Int = value
}