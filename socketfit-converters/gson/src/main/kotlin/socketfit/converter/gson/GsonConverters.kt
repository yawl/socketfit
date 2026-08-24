package socketfit.converter.gson

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonToken
import okio.Buffer
import socketfit.Converter
import socketfit.Message
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.StringReader
import kotlin.text.Charsets.UTF_8

internal class GsonOutgoingTextConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<T, Message.Text> {
    override fun convert(value: T): Message.Text {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return Message.Text(buffer.readByteString().utf8())
    }
}

internal class GsonIncomingTextConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<Message.Text, T> {
    override fun convert(value: Message.Text): T {
        val value = value.value
        val jsonReader = gson.newJsonReader(StringReader(value))
        try {
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.");
            }
            return result
        } finally {
            // do nothing
        }
    }
}

internal class GsonOutgoingBinaryConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<T, Message.Binary> {
    override fun convert(value: T): Message.Binary {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream())
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return Message.Binary(buffer.readByteArray())
    }
}

internal class GsonIncomingBinaryConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<Message.Binary, T> {
    override fun convert(value: Message.Binary): T {
        val value = value.value
        val reader = InputStreamReader(ByteArrayInputStream(value))
        val jsonReader = gson.newJsonReader(reader)
        try {
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.");
            }
            return result
        } finally {
            // do nothing
        }
    }
}
