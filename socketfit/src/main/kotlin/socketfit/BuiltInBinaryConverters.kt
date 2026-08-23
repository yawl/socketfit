package socketfit

import java.lang.reflect.Type
import kotlin.jvm.java

internal class BuiltInBinaryConverters : Converter.BinaryFactory {
    override fun outgoingBinaryMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message.Binary>? {
        return when (type) {
            Message::class.java -> MessageConverter
            ByteArray::class.java -> OutgoingConverter
            else -> null
        }
    }

    override fun incomingBinaryMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message.Binary, *>? {
        return when (type) {
            Message::class.java -> MessageConverter
            ByteArray::class.java -> IncomingConverter
            else -> null
        }
    }

    object MessageConverter : Converter<Message.Binary, Message.Binary> {
        override fun convert(value: Message.Binary): Message.Binary {
            return value
        }
    }

    object IncomingConverter : Converter<Message.Binary, ByteArray> {
        override fun convert(value: Message.Binary): ByteArray {
            return value.value
        }
    }

    object OutgoingConverter : Converter<ByteArray, Message.Binary> {
        override fun convert(value: ByteArray): Message.Binary {
            return Message.Binary(value)
        }
    }
}
