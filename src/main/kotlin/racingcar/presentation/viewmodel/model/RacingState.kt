package racingcar.presentation.viewmodel.model

import racingcar.domain.model.game.Player
import racingcar.domain.rules.OutPut
import racingcar.domain.rules.OutPut.Companion.stateResultFormat
import racingcar.presentation.event.UiEvent

data class RacingState(
    val players: List<Player>,
    val tryAmount: Int,
    val stageResult: String,
    val uiEvent: UiEvent
) {
    fun addStageResult(): String {
        val message = "\n${
            players.joinToString("\n") { stateResultFormat(it.player, "-".repeat(it.pos)) }
        }\n"
        return message
    }

    fun makeStageResultMessage(): String{
        return "${OutPut.PLAY_RESULT}${this.stageResult}"
    }

    fun makeWinnerMessage(): String{
        val winner = findWinner().joinToString(", ")
        return "${OutPut.WINNER}$winner"
    }

    private fun findWinner(): List<String> {
        val max = players.maxOf { it.pos }
        val winner = players.filter { it.pos == max }.map { it.player }
        return winner
    }

    companion object {
        fun create(): RacingState {
            return RacingState(
                players = emptyList(),
                tryAmount = 0,
                stageResult = "",
                uiEvent = UiEvent.InputPlayer("${OutPut.INPUT_NAME_MESSAGE}")
            )
        }
    }
}