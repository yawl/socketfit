package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.StringFormat
import socketfit.Message
import socketfit.Converter
import socketfit.converter.kotlinx.serialization.Serializer.FromBytes
import socketfit.converter.kotlinx.serialization.Serializer.FromString
import java.lang.reflect.Type

internal class Factory(
    private val serializer: Serializer
) : Converter.Factory {
    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun outgoingMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message>? {
        val saver = serializer.serializer(type)
        return SerializationStrategyConverter(saver, serializer)
    }

    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun incomingMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message, *>? {
        val loader = serializer.serializer(type)
        return DeserializationStrategyConverter(loader, serializer)
    }
}

/**
 * Return a [Converter.Factory] which uses Kotlin serialization for string-based payloads.
 *
 * Because Kotlin serialization is so flexible in the types it supports, this converter assumes that
 * it can handle all types. If you are mixing this with something else, you must add this instance
 * last to allow the other converters a chance to see their types.
 */
fun StringFormat.asConverterFactory(): Converter.Factory {
    return Factory(FromString(this))
}

/**
 * Return a [Converter.Factory] which uses Kotlin serialization for byte-based payloads.
 *
 * Because Kotlin serialization is so flexible in the types it supports, this converter assumes that
 * it can handle all types. If you are mixing this with something else, you must add this instance
 * last to allow the other converters a chance to see their types.
 */
fun BinaryFormat.asConverterFactory(): Converter.Factory {
    return Factory(FromBytes(this))
}
