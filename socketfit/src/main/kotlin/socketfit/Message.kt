package socketfit

/**
 * A WebSocket message as defined by RFC 6455.
 *
 * <p>A message is either a text message, whose payload is UTF-8 encoded text,
 * or a binary message, whose payload consists of arbitrary bytes.
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
 *   RFC 6455, Section 5.6, Data
 * </a>
 */
sealed class Message {
    /**
     * A WebSocket text message.
     *
     * <p>The payload of a text message is encoded as UTF-8.
     *
     * @param value the UTF-8 text payload
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
     *   RFC 6455, Section 5.6, Data
     * </a>
     */
    data class Text(
        val value: String,
    ) : Message()

    /**
     * A WebSocket binary message.
     *
     * <p>The payload of a binary message consists of arbitrary bytes and has
     * no encoding defined by the WebSocket protocol.
     *
     * @param value the binary message payload
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
     *   RFC 6455, Section 5.6, Data
     * </a>
     */
    class Binary(
        val value: ByteArray,
    ) : Message()
}
