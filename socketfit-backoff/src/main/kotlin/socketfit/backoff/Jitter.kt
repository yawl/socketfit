package socketfit.backoff

import kotlin.time.Duration

/**
 * Applies jitter to a backoff duration.
 *
 * @see <a href="https://aws.amazon.com/blogs/architecture/exponential-backoff-and-jitter/">
 * AWS, Exponential Backoff and Jitter
 * </a>
 */
interface Jitter {
    /**
     * Applies this jitter strategy to the specified backoff duration.
     *
     * @param backoff the backoff duration
     * @return the jittered backoff duration
     */
    fun apply(backoff: Duration): Duration
}
