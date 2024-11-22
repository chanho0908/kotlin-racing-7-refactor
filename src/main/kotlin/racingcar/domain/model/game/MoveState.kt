package racingcar.domain.model.game

sealed class MoveState {
    object UnMoveAble: MoveState()
    data class MoveAble(val amount: Int): MoveState()
}