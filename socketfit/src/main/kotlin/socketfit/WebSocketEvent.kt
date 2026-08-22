package socketfit

/**
 * An event produced by a WebSocket connection.
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455">
 *   RFC 6455, The WebSocket Protocol
 * </a>
 */
sealed class WebSocketEvent {
    /**
     * Indicates that the WebSocket opening handshake has completed
     * successfully and the WebSocket connection is established.
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-1.3">
     *   RFC 6455, Section 1.3, Opening Handshake
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-4">
     *   RFC 6455, Section 4, Opening Handshake
     * </a>
     */
    data object Opened : WebSocketEvent()

    /**
     * Indicates that a text WebSocket message has been received.
     *
     * A text message contains UTF-8 encoded text.
     *
     * @param data the received text message
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
     *   RFC 6455, Section 5.6, Data
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-6.2">
     *   RFC 6455, Section 6.2, Receiving Data
     * </a>
     */
    data class TextMessage(
        val data: Message.Text,
    ) : WebSocketEvent()

    /**
     * Indicates that a binary WebSocket message has been received.
     *
     * @param data the received binary message
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
     *   RFC 6455, Section 5.6, Data
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-6.2">
     *   RFC 6455, Section 6.2, Receiving Data
     * </a>
     */
    data class BinaryMessage(
        val data: Message.Binary,
    ) : WebSocketEvent()

    /**
     * Indicates that the WebSocket closing handshake has been started.
     *
     * The code and reason identify the close status received from the
     * peer or used to initiate the closing handshake.
     *
     * @param code the WebSocket close status code
     * @param reason the WebSocket close reason
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-1.4">
     *   RFC 6455, Section 1.4, Closing Handshake
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-7.1.2">
     *   RFC 6455, Section 7.1.2, Start the WebSocket Closing Handshake
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.5.1">
     *   RFC 6455, Section 5.5.1, Close
     * </a>
     */
    data class Closing(
        val code: Int,
        val reason: String,
    ) : WebSocketEvent()

    /**
     * Indicates that the WebSocket connection has been closed.
     *
     * @param code the WebSocket close status code
     * @param reason the WebSocket close reason
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-7.1.4">
     *   RFC 6455, Section 7.1.4, The WebSocket Connection is Closed
     * </a>
     */
    data class Closed(
        val code: Int,
        val reason: String,
    ) : WebSocketEvent()

    /**
     * Indicates that the WebSocket connection failed due to an error.
     *
     * @param throwable the error that caused the WebSocket connection to fail
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-7.1.7">
     *   RFC 6455, Section 7.1.7, Fail the WebSocket Connection
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-8">
     *   RFC 6455, Section 8, Error Handling
     * </a>
     */
    data class Error(
        val throwable: Throwable,
    ) : WebSocketEvent()
}
