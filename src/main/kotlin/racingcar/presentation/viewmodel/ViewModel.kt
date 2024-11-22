package racingcar.presentation.viewmodel

import racingcar.domain.usecase.CheckPlayerNameUseCase
import racingcar.domain.usecase.CheckTryAmountUseCase
import racingcar.domain.rules.OutPut
import racingcar.presentation.event.UiEvent
import racingcar.domain.model.game.Player
import racingcar.domain.usecase.PlayRacingUseCase
import racingcar.presentation.viewmodel.model.RacingState

class ViewModel(
    private val checkPlayerNameUseCase: CheckPlayerNameUseCase,
    private val checkTryAmountUseCase: CheckTryAmountUseCase,
    private val playRacingUseCase: PlayRacingUseCase
) {
    private var _state = RacingState.create()
    val state get() = _state

    fun onUserInputPlayerNameComplete(input: String){
        val spliterator = checkPlayerNameUseCase(input)
        val players = spliterator.map { Player(player = it, pos = 0) }
        _state = _state.copy(
            players = players,
            uiEvent = UiEvent.InputTryAmount("${OutPut.INPUT_TRY_AMOUNT_MESSAGE}")
        )
    }

    fun onUserInputTryAmountComplete(input: String){
        checkTryAmountUseCase(input)
        _state = _state.copy(tryAmount = input.toInt())
        playGame()
    }

    private fun playGame(){
        repeat(_state.tryAmount){
            val result = playRacingUseCase(_state.players)
            _state = _state.copy(players = result)
            _state = _state.copy(stageResult = _state.stageResult + _state.addStageResult())
        }
        onCompletePlayGame()
    }

    private fun onCompletePlayGame(){
        _state = _state.copy(uiEvent = UiEvent.PlayGame(_state.makeStageResultMessage()))
    }

    fun setAwardCeremony(){
        _state = _state.copy(uiEvent = UiEvent.Award(_state.makeWinnerMessage()))
    }
}