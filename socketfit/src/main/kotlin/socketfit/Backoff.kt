package socketfit

import kotlin.time.Duration

/**
 * Calculates the delay before a reconnect attempt after an abnormal closure.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6455#section-7.2.3">
 * RFC 6455 §7.2.3, Recovering from Abnormal Closure
 * </a>
 */
interface Backoff {
    /**
     * Returns the delay before the specified reconnect attempt.
     *
     * @param attempt the reconnect attempt number, starting at 0
     */
    fun delay(attempt: Int): Duration
}
