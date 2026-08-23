package socketfit

import java.lang.reflect.Type

/**
 * Convert objects to and from their representation in WebSocket. Instances are created by
 * [a factory][TextFactory] which is [installed][Socketfit.Builder.addTextConverterFactory]
 * into the [Socketfit] instance.
 */
interface Converter<F, T> {
    /**
     * Converts [value] to an instance of [T].
     */
    fun convert(value: F): T

    /**
     * Creates [Converter] instances based on a type and target usage.
     */
    interface TextFactory {
        /**
         * Returns a converter for converting [type] to an outgoing WebSocket text message,
         * or null if [type] cannot be handled by this factory.
         */
        fun outgoingTextMessageConverter(
            type: Type,
            annotations: Array<Annotation>,
        ): Converter<*, Message.Text>?

        /**
         * Returns a converter for converting an incoming WebSocket text message to [type],
         * or null if [type] cannot be handled by this factory.
         */
        fun incomingTextMessageConverter(
            type: Type,
            annotations: Array<Annotation>,
        ): Converter<Message.Text, *>?
    }

    /**
     * Creates [Converter] instances based on a type and target usage.
     */
    interface BinaryFactory {
        /**
         * Returns a converter for converting [type] to an outgoing WebSocket binary message,
         * or null if [type] cannot be handled by this factory.
         */
        fun outgoingBinaryMessageConverter(
            type: Type,
            annotations: Array<Annotation>,
        ): Converter<*, Message.Binary>?

        /**
         * Returns a converter for converting an incoming WebSocket binary message to [type],
         * or null if [type] cannot be handled by this factory.
         */
        fun incomingBinaryMessageConverter(
            type: Type,
            annotations: Array<Annotation>,
        ): Converter<Message.Binary, *>?
    }
}
