package socketfit

import java.lang.reflect.Type

/**
 * Convert objects to and from their representation in WebSocket. Instances are created by
 * [a factory][Factory] which is [installed][Socketfit.Builder.addConverterFactory]
 * into the [Socketfit] instance.
 */
interface MessageConverter<F, T> {
    /**
     * Converts [value] to an instance of [T].
     */
    fun convert(value: F): T

    /**
     * Creates [MessageConverter] instances based on a type and target usage.
     */
    interface Factory {
        /**
         * Returns a converter for converting [type] to an outgoing WebSocket message,
         * or null if [type] cannot be handled by this factory.
         */
        fun outgoingMessageConverter(
            type: Type,
            annotations: Array<Annotation>,
        ): MessageConverter<*, Message>?

        /**
         * Returns a converter for converting an incoming WebSocket message to [type],
         * or null if [type] cannot be handled by this factory.
         */
        fun incomingMessageConverter(
            type: Type,
            annotations: Array<Annotation>,
        ): MessageConverter<Message, *>?
    }
}
