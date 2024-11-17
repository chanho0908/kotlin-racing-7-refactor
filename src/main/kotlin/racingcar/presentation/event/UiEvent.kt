package racingcar.presentation.event

sealed class UiEvent {
    data class InputPlayer(val msg: String) : UiEvent()
    data class InputTryAmount(val msg: String) : UiEvent()
    data class PlayGame(val msg: String) : UiEvent()
    data class Award(val msg: String) : UiEvent()
}