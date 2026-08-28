package socketfit.backoff

import socketfit.Backoff
import kotlin.time.Duration

/**
 * A linear backoff strategy that uses a constant delay for every attempt.
 *
 * @param delay the delay between reconnect attempts
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6455#section-7.2.3">
 * RFC 6455 §7.2.3, Recovering from Abnormal Closure
 * </a>
 */
class FixedBackoff(
    private val delay: Duration,
) : Backoff {
    override fun delay(attempt: Int) = delay
}
