package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.DeserializationStrategy
import socketfit.Converter
import socketfit.Message

internal class DeserializationStrategyConverter<T>(
    private val loader: DeserializationStrategy<T>,
    private val serializer: Serializer,
) : Converter<Message, T> {
    override fun convert(value: Message) = serializer.fromMessage(loader, value)
}
