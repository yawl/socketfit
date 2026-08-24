package socketfit

import kotlinx.coroutines.flow.Flow

/**
 * A WebSocket connection providing bidirectional, message-oriented
 * communication over a single connection.
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455">
 *   RFC 6455, The WebSocket Protocol
 * </a>
 */
interface WebSocket {
    /**
     * A stream of events produced by this WebSocket connection.
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-6.2">
     *   RFC 6455, Section 6.2, Receiving Data
     * </a>
     */
    val events: Flow<WebSocketEvent>

    /**
     * Initiates the WebSocket opening handshake as defined by RFC 6455,
     * Section 1.3.
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-1.3">
     *   RFC 6455, Section 1.3, Opening Handshake
     * </a>
     */
    fun open()

    /**
     * Sends a WebSocket text message.
     *
     * @param message the WebSocket text message to send
     * @return `true` if the message was enqueued, or `false` if the WebSocket is
     * not open or has failed
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
     *   RFC 6455, Section 5.6, Data
     * </a>
     */
    fun send(message: Message.Text): Boolean

    /**
     * Sends a WebSocket binary message.
     *
     * @param message the WebSocket binary message to send
     * @return `true` if the message was enqueued, or `false` if the WebSocket is
     * not open or has failed
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.6">
     *   RFC 6455, Section 5.6, Data
     * </a>
     */
    fun send(message: Message.Binary): Boolean

    /**
     * Initiates the WebSocket closing handshake with the given status code.
     *
     * <p>The Close frame contains the status code and no reason.
     *
     * @param code the WebSocket close status code
     * @return `true` if the close frame was enqueued, or `false` if
     * the WebSocket is already closing or has failed
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.5.1">
     *   RFC 6455, Section 5.5.1, Close
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-7.1.2">
     *   RFC 6455, Section 7.1.2, Start the WebSocket Closing Handshake
     * </a>
     */
    fun close(code: Int): Boolean

    /**
     * Initiates the WebSocket closing handshake with the given status code
     * and reason.
     *
     * @param code the WebSocket close status code
     * @param reason the UTF-8 encoded reason for closing
     * @return `true` if the close frame was enqueued, or `false` if
     * the WebSocket is already closing or has failed
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-5.5.1">
     *   RFC 6455, Section 5.5.1, Close
     * </a>
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-7.1.2">
     *   RFC 6455, Section 7.1.2, Start the WebSocket Closing Handshake
     * </a>
     */
    fun close(code: Int, reason: String): Boolean
}
