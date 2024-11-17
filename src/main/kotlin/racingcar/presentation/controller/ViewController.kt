package racingcar.presentation.controller

import racingcar.presentation.event.UiEvent
import racingcar.presentation.view.InputView
import racingcar.presentation.view.OutputView
import racingcar.presentation.viewmodel.ViewModel

class ViewController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val viewModel: ViewModel
) {
    init {
        checkUiEvent()
    }

    private fun checkUiEvent() {
        when (val event = viewModel.state.uiEvent) {
            is UiEvent.InputPlayer -> onUiEventInputPlayer(event.msg)
            is UiEvent.InputTryAmount -> onUiEventInputTryAmount(event.msg)
            is UiEvent.PlayGame -> onUiEventPlayGme(event.msg)
            is UiEvent.Award -> onUiEventAward(event.msg)
        }
    }

    private fun onUiEventInputPlayer(msg: String) {
        outputView.printMessage(msg)
        val input = inputView.readItem()
        viewModel.onUserInputPlayerNameComplete(input)
        checkUiEvent()
    }

    private fun onUiEventInputTryAmount(msg: String) {
        outputView.printMessage(msg)
        val input = inputView.readItem()
        viewModel.onUserInputTryAmountComplete(input)
        checkUiEvent()
    }

    private fun onUiEventPlayGme(msg: String) {
        outputView.printMessage(msg)
        viewModel.setAwardCeremony()
        checkUiEvent()
    }

    private fun onUiEventAward(msg: String) {
        outputView.printMessage(msg)
    }
}
 // 1차 시도 : 2시간 20분