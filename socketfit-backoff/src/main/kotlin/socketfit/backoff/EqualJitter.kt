package socketfit.backoff

import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Applies Equal Jitter to a backoff duration.
 *
 * @param random the source of randomness used to select the delay
 *
 * @see <a href="https://aws.amazon.com/blogs/architecture/exponential-backoff-and-jitter/">
 * AWS, Exponential Backoff and Jitter
 * </a>
 */
class EqualJitter(
    private val random: Random,
) : Jitter {
    override fun apply(backoff: Duration): Duration {
        val halfMilliseconds = backoff.inWholeMilliseconds / 2
        return (halfMilliseconds + random.nextLong(0, halfMilliseconds)).milliseconds
    }
}
