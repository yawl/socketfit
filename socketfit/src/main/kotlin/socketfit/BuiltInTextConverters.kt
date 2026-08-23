package socketfit

import java.lang.reflect.Type
import kotlin.jvm.java

internal class BuiltInTextConverters : Converter.TextFactory {
    override fun outgoingTextMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message.Text>? {
        return when (type) {
            Message::class.java -> MessageConverter
            String::class.java -> OutgoingConverter
            else -> null
        }
    }

    override fun incomingTextMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message.Text, *>? {
        return when (type) {
            Message::class.java -> MessageConverter
            String::class.java -> IncomingConverter
            else -> null
        }
    }

    object MessageConverter : Converter<Message.Text, Message.Text> {
        override fun convert(value: Message.Text): Message.Text {
            return value
        }
    }

    object IncomingConverter : Converter<Message.Text, String> {
        override fun convert(value: Message.Text): String {
            return value.value
        }
    }

    object OutgoingConverter : Converter<String, Message.Text> {
        override fun convert(value: String): Message.Text {
            return Message.Text(value)
        }
    }
}
