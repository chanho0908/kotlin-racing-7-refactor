package racingcar.presentation.event

sealed interface UiEvent {
    val msg: String

    data class InputPlayer(override val msg: String) : UiEvent
    data class InputTryAmount(override val msg: String) : UiEvent
    data class PlayGame(override val msg: String) : UiEvent
    data class Award(override val msg: String) : UiEvent
}