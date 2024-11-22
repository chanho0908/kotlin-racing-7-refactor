package racingcar.app

import racingcar.domain.usecase.CheckPlayerNameUseCase
import racingcar.domain.usecase.CheckTryAmountUseCase
import racingcar.domain.usecase.PlayRacingUseCase
import racingcar.presentation.controller.ViewController
import racingcar.presentation.view.InputView
import racingcar.presentation.view.OutputView
import racingcar.presentation.viewmodel.ViewModel

class DependencyInjector {
    private fun injectViewModel(): ViewModel {
        val checkPlayerNameUseCase = CheckPlayerNameUseCase()
        val checkTryAmountUseCase = CheckTryAmountUseCase()
        val playRacingUseCase = PlayRacingUseCase()
        val viewModel = ViewModel(checkPlayerNameUseCase, checkTryAmountUseCase, playRacingUseCase)
        return viewModel
    }

    fun injectViewController(): ViewController {
        val inputView = injectInputView()
        val outputView = injectOutputView()
        val viewModel = injectViewModel()
        val viewController = ViewController(inputView, outputView, viewModel)
        return viewController
    }

    private fun injectInputView() = InputView()
    private fun injectOutputView() = OutputView()
}