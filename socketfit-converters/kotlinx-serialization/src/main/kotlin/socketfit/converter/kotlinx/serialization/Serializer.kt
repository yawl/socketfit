package socketfit.converter.kotlinx.serialization

import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.serializer
import socketfit.Message
import java.lang.reflect.Type

internal interface Serializer {
    interface Text : Serializer {
        fun <T> fromMessage(
            loader: DeserializationStrategy<T>,
            message: Message.Text
        ): T

        fun <T> toMessage(
            saver: SerializationStrategy<T>,
            value: T,
        ): Message.Text
    }

    interface Binary : Serializer {
        fun <T> fromMessage(
            loader: DeserializationStrategy<T>,
            message: Message.Binary
        ): T

        fun <T> toMessage(
            saver: SerializationStrategy<T>,
            value: T,
        ): Message.Binary
    }

    val format: SerialFormat

    fun serializer(type: Type): KSerializer<Any> = format.serializersModule.serializer(type)

    class FromString(override val format: StringFormat) : Text {
        override fun <T> fromMessage(
            loader: DeserializationStrategy<T>,
            message: Message.Text
        ): T {
            val string = message.value
            return format.decodeFromString(loader, string)
        }

        override fun <T> toMessage(
            saver: SerializationStrategy<T>,
            value: T,
        ): Message.Text {
            val string = format.encodeToString(saver, value)
            return Message.Text(string)
        }
    }

    class FromBytes(override val format: BinaryFormat) : Binary {
        override fun <T> fromMessage(
            loader: DeserializationStrategy<T>,
            message: Message.Binary
        ): T {
            val bytes = message.value
            return format.decodeFromByteArray(loader, bytes)
        }

        override fun <T> toMessage(
            saver: SerializationStrategy<T>,
            value: T,
        ): Message.Binary {
            val string = format.encodeToByteArray(saver, value)
            return Message.Binary(string)
        }
    }
}
