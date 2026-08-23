package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.SerializationStrategy
import socketfit.Converter
import socketfit.Message

internal class TextSerializationStrategyConverter<T>(
    private val saver: SerializationStrategy<T>,
    private val serializer: Serializer.Text,
) : Converter<T, Message.Text> {
    override fun convert(value: T) = serializer.toMessage(saver, value)
}

internal class BinarySerializationStrategyConverter<T>(
    private val saver: SerializationStrategy<T>,
    private val serializer: Serializer.Binary,
) : Converter<T, Message.Binary> {
    override fun convert(value: T) = serializer.toMessage(saver, value)
}
