package racingcar

import racingcar.app.DependencyInjector

fun main() {
    val di = DependencyInjector()
    di.injectViewController()
}
