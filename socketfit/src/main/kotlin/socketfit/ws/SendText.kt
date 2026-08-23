package socketfit.ws

/**
 * Marks a WebSocket service method as sending a text message to the peer.
 *
 * A text message consists of UTF-8 encoded text. The message is transmitted
 * using a WebSocket data frame with the Text opcode (0x1).
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.2">
 *     RFC 6455, Section 5.2, Base Framing Protocol
 * </a>
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
 *     RFC 6455, Section 5.6, Data Frames
 * </a>
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-6.1">
 *     RFC 6455, Section 6.1, Sending Data
 * </a>
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SendText
