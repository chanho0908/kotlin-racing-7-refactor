package racingcar.domain.rules

enum class Constants(private val value: String) {
    DELIMITER(",");

    override fun toString(): String = value
}