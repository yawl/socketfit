package socketfit.ws

/**
 * Marks a WebSocket service method as receiving data from the peer.
 *
 * <p>The annotated method represents the receiving side of WebSocket communication.
 * The received data may be exposed through the method's return type, such as a
 * reactive stream.
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-6.2">
 *     RFC 6455, Section 6.2, Receiving Data
 * </a>
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Receive
