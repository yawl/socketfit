package socketfit

import java.lang.reflect.Type

internal class BuiltInConverters : Converter.Factory {
    override fun outgoingMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message>? {
        return when (type) {
            Message::class.java -> MessageConverter
            String::class.java -> TextOutgoingConverter
            ByteArray::class.java -> BinaryOutgoingConverter
            else -> null
        }
    }

    override fun incomingMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message, *>? {
        return when (type) {
            Message::class.java -> MessageConverter
            String::class.java -> TextIncomingConverter
            ByteArray::class.java -> BinaryIncomingConverter
            else -> null
        }
    }

    object MessageConverter : Converter<Message, Message> {
        override fun convert(value: Message): Message {
            return value
        }
    }

    object TextIncomingConverter : Converter<Message, String> {
        override fun convert(value: Message): String {
            return when (value) {
                is Message.Text -> value.value
                is Message.Binary -> {
                    throw IllegalStateException(
                        "This converter only supports text message"
                    )
                }
            }
        }
    }

    object TextOutgoingConverter : Converter<String, Message> {
        override fun convert(value: String): Message {
            return Message.Text(value)
        }
    }

    object BinaryIncomingConverter : Converter<Message, ByteArray> {
        override fun convert(value: Message): ByteArray {
            return when (value) {
                is Message.Binary -> value.value
                is Message.Text -> {
                    throw IllegalStateException(
                        "This converter only supports binary message"
                    )
                }
            }
        }
    }

    object BinaryOutgoingConverter : Converter<ByteArray, Message> {
        override fun convert(value: ByteArray): Message {
            return Message.Binary(value)
        }
    }
}
