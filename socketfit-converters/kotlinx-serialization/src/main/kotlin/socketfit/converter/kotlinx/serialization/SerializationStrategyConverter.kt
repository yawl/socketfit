package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.SerializationStrategy
import socketfit.Converter
import socketfit.Message

internal class SerializationStrategyConverter<T>(
    private val saver: SerializationStrategy<T>,
    private val serializer: Serializer,
) : Converter<T, Message> {
    override fun convert(value: T) = serializer.toMessage(saver, value)
}
