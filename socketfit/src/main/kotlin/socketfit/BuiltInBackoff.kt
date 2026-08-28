package socketfit

import kotlin.time.Duration.Companion.seconds

internal class BuiltInBackoff : Backoff {
    override fun delay(attempt: Int) = 2.5.seconds
}
