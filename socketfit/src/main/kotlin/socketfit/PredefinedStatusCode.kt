package socketfit

/**
 * Pre-defined status codes when sending a Close frame.
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6455#section-7.4.1">
 *   RFC 6455, Section 7.4.1, Defined Status Codes
 * </a>
 */
enum class PredefinedStatusCode(val value: Int) {
    /**
     * 1000 indicates a normal closure, meaning that the purpose for
     * which the connection was established has been fulfilled.
     */
    NormalClosure(1000),

    /**
     * 1001 indicates that an endpoint is "going away", such as a server
     * going down or a browser having navigated away from a page.
     */
    GoingAway(1001),

    /**
     * 1002 indicates that an endpoint is terminating the connection due
     * to a protocol error.
     */
    ProtocolError(1002),

    /**
     * 1003 indicates that an endpoint is terminating the connection
     * because it has received a type of data it cannot accept (e.g., an
     * endpoint that understands only text data MAY send this if it
     * receives a binary message).
     */
    UnsupportedData(1003),

    /**
     * 1007 indicates that an endpoint is terminating the connection
     * because it has received data within a message that was not
     * consistent with the type of the message (e.g., non-UTF-8
     * data within a text message).
     */
    InvalidData(1007),

    /**
     * 1008 indicates that an endpoint is terminating the connection
     * because it has received a message that violates its policy.  This
     * is a generic status code that can be returned when there is no
     * other more suitable status code (e.g., 1003 or 1009) or if there
     * is a need to hide specific details about the policy.
     */
    PolicyViolation(1008),

    /**
     * 1009 indicates that an endpoint is terminating the connection
     * because it has received a message that is too big for it to
     * process.
     */
    MessageTooBig(1009),

    /**
     * 1010 indicates that an endpoint (client) is terminating the
     * connection because it has expected the server to negotiate one or
     * more extension, but the server didn't return them in the response
     * message of the WebSocket handshake.  The list of extensions that
     * are needed SHOULD appear in the /reason/ part of the Close frame.
     * Note that this status code is not used by the server, because it
     * can fail the WebSocket handshake instead.
     */
    MissingExtension(1010),

    /**
     * 1011 indicates that a server is terminating the connection because
     * it encountered an unexpected condition that prevented it from
     * fulfilling the request.
     */
    InternalError(1011),
}
