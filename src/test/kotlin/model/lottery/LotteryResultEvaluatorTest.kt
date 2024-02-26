package model.lottery

import WinningLottery
import model.Quantity
import model.WinningRank
import model.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteryResultEvaluatorTest {
    @Test
    fun `로또들에 대해 당첨 로또(당첨 번호 & 보너스 번호)를 비교하여 최종 당첨 결과를 구한다`() {
        val lotteryResultEvaluator = LotteryResultEvaluator()
        val lotteries =
            Lotteries(
                listOf(
                    Lottery.of(1, 2, 3, 4, 5, 6),
                    Lottery.of(4, 5, 6, 7, 8, 9),
                    Lottery.of(4, 5, 6, 10, 11, 12),
                ),
            )
        val winningLottery =
            WinningLottery(
                Lottery.of(4, 5, 6, 10, 11, 12),
                LotteryNumber(7),
            )
        val expected =
            WinningResult(
                mapOf(
                    WinningRank.FIRST to Quantity(1),
                    WinningRank.SECOND to Quantity(0),
                    WinningRank.THIRD to Quantity(0),
                    WinningRank.FOURTH to Quantity(0),
                    WinningRank.FIFTH to Quantity(2),
                    WinningRank.NONE to Quantity(0),
                ),
            )

        val winningResult = lotteryResultEvaluator.evaluate(lotteries, winningLottery)

        assertThat(winningResult).isEqualTo(expected)
    }
}
