package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.serializer
import socketfit.Message
import java.lang.reflect.Type

internal sealed class Serializer {
    abstract fun <T> fromMessage(
        loader: DeserializationStrategy<T>,
        message: Message
    ): T

    abstract fun <T> toMessage(
        saver: SerializationStrategy<T>,
        value: T,
    ): Message

    protected abstract val format: SerialFormat

    fun serializer(type: Type): KSerializer<Any> = format.serializersModule.serializer(type)

    class FromString(override val format: StringFormat) : Serializer() {
        override fun <T> fromMessage(
            loader: DeserializationStrategy<T>,
            message: Message
        ): T {
            val string = when (message) {
                is Message.Text -> message.value
                is Message.Binary -> {
                    throw SerializationException(
                        "Expected a text WebSocket message, but received a binary message"
                    )
                }
            }
            return format.decodeFromString(loader, string)
        }

        override fun <T> toMessage(
            saver: SerializationStrategy<T>,
            value: T,
        ): Message {
            val string = format.encodeToString(saver, value)
            return Message.Text(string)
        }
    }

    class FromBytes(override val format: BinaryFormat) : Serializer() {
        override fun <T> fromMessage(
            loader: DeserializationStrategy<T>,
            message: Message
        ): T {
            val bytes = when (message) {
                is Message.Binary -> message.value
                is Message.Text -> {
                    throw SerializationException(
                        "Expected a binary WebSocket message, but received a text message"
                    )
                }
            }
            return format.decodeFromByteArray(loader, bytes)
        }

        override fun <T> toMessage(
            saver: SerializationStrategy<T>,
            value: T,
        ): Message {
            val string = format.encodeToByteArray(saver, value)
            return Message.Binary(string)
        }
    }
}
