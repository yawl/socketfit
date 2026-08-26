package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.StringFormat
import socketfit.Converter
import socketfit.Message
import socketfit.converter.kotlinx.serialization.Serializer.FromBytes
import socketfit.converter.kotlinx.serialization.Serializer.FromString
import java.lang.reflect.Type

internal class TextFactory(
    private val serializer: Serializer.Text
) : Converter.TextFactory {
    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun outgoingTextMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message.Text>? {
        val saver = serializer.serializer(type)
        return TextSerializationStrategyConverter(saver, serializer)
    }

    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun incomingTextMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message.Text, *>? {
        val loader = serializer.serializer(type)
        return TextDeserializationStrategyConverter(loader, serializer)
    }
}

internal class BinaryFactory(
    private val serializer: Serializer.Binary
) : Converter.BinaryFactory {
    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun outgoingBinaryMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message.Binary>? {
        val saver = serializer.serializer(type)
        return BinarySerializationStrategyConverter(saver, serializer)
    }

    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun incomingBinaryMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message.Binary, *>? {
        val loader = serializer.serializer(type)
        return BinaryDeserializationStrategyConverter(loader, serializer)
    }
}

/**
 * Return a [Converter.TextFactory] which uses Kotlin serialization for string-based payloads.
 *
 * Because Kotlin serialization is so flexible in the types it supports, this converter assumes that
 * it can handle all types. If you are mixing this with something else, you must add this instance
 * last to allow the other converters a chance to see their types.
 */
@JvmName("create")
fun StringFormat.asConverterFactory(): Converter.TextFactory {
    return TextFactory(FromString(this))
}

/**
 * Return a [Converter.BinaryFactory] which uses Kotlin serialization for byte-based payloads.
 *
 * Because Kotlin serialization is so flexible in the types it supports, this converter assumes that
 * it can handle all types. If you are mixing this with something else, you must add this instance
 * last to allow the other converters a chance to see their types.
 */
@JvmName("create")
fun BinaryFormat.asConverterFactory(): Converter.BinaryFactory {
    return BinaryFactory(FromBytes(this))
}
