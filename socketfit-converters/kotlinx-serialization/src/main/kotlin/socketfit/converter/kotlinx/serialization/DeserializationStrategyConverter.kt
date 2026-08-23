package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.DeserializationStrategy
import socketfit.Converter
import socketfit.Message

internal class TextDeserializationStrategyConverter<T>(
    private val loader: DeserializationStrategy<T>,
    private val serializer: Serializer.Text,
) : Converter<Message.Text, T> {
    override fun convert(value: Message.Text) = serializer.fromMessage(loader, value)
}

internal class BinaryDeserializationStrategyConverter<T>(
    private val loader: DeserializationStrategy<T>,
    private val serializer: Serializer.Binary,
) : Converter<Message.Binary, T> {
    override fun convert(value: Message.Binary) = serializer.fromMessage(loader, value)
}
