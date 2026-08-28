package socketfit.backoff

import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Applies Full Jitter to a backoff duration.
 *
 * @param random the source of randomness used to select the delay
 *
 * @see <a href="https://aws.amazon.com/blogs/architecture/exponential-backoff-and-jitter/">
 * AWS, Exponential Backoff and Jitter
 * </a>
 */
class FullJitter(
    private val random: Random,
) : Jitter {
    override fun apply(backoff: Duration): Duration {
        val milliseconds = backoff.inWholeMilliseconds
        return random.nextLong(0, milliseconds).milliseconds
    }
}
