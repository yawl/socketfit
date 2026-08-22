package socketfit.ws

/**
 * Marks a WebSocket service method as sending data to the peer.
 *
 * <p>The annotated method represents the sending side of WebSocket communication.
 * The method's parameters define the data to be sent.
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-6.1">
 *     RFC 6455, Section 6.1, Sending Data
 * </a>
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Send
