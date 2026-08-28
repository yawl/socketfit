package socketfit.backoff

import socketfit.Backoff
import socketfit.Jitter
import kotlin.math.pow
import kotlin.time.Duration

/**
 * An exponential backoff strategy with a configurable jitter algorithm.
 *
 * The backoff duration grows exponentially with each reconnect attempt:
 *
 * `min(cap, base * 2^attempt)`
 *
 * The calculated backoff is then passed to [jitter], which determines the
 * actual delay.
 *
 * @param base the initial backoff duration
 * @param cap the maximum backoff duration
 * @param jitter the strategy used to randomize the calculated backoff
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6455#section-7.2.3">
 * RFC 6455 §7.2.3, Recovering from Abnormal Closure
 * </a>
 * @see <a href="https://aws.amazon.com/blogs/architecture/exponential-backoff-and-jitter/">
 * AWS, Exponential Backoff and Jitter
 * </a>
 */
class ExponentialBackoffWithJitter(
    private val base: Duration,
    private val cap: Duration,
    private val jitter: Jitter,
) : Backoff {
    init {
        require(base.isPositive()) {
            "Base duration must be positive"
        }
        require(cap.isPositive()) {
            "Cap duration must be positive"
        }
        require(cap > base) {
            "Cap duration must be > than base"
        }
    }

    override fun delay(attempt: Int): Duration {
        require(attempt >= 0) {
            "Attempt must be non-negative"
        }

        val backoff = minOf(
            cap,
            base * 2.0.pow(attempt.toDouble()),
        )

        return jitter.apply(backoff)
    }
}
