package racingcar.domain.usecase

import racingcar.domain.model.game.Player
import camp.nextstep.edu.missionutils.Randoms.pickNumberInRange
import racingcar.domain.model.game.MoveState
import racingcar.domain.rules.Game.RANDOM_MIN
import racingcar.domain.rules.Game.RANDOM_MAX
import racingcar.domain.rules.Game.MIN_MOVEABLE_CONDITION
import racingcar.domain.rules.Game.MOVE_COUNT

class PlayRacingUseCase {
    operator fun invoke(player: List<Player>): List<Player>{
        return player.map { modifyStateByMoveAble(it) }
    }

    private fun modifyStateByMoveAble(currentPlayer: Player): Player{
        return when(getMoveState()){
            is MoveState.UnMoveAble -> currentPlayer
            is MoveState.MoveAble -> {
                Player(
                    player = currentPlayer.player,
                    pos = currentPlayer.pos + MOVE_COUNT.getValue()
                )
            }
        }
    }

    private fun getMoveState(): MoveState {
        val random = pickNumberInRange(RANDOM_MIN.getValue(), RANDOM_MAX.getValue())
        if(random >= MIN_MOVEABLE_CONDITION.getValue()) {
            return MoveState.MoveAble(random)
        }
        return MoveState.UnMoveAble
    }
}