package socketfit.converter.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import socketfit.Converter
import socketfit.Message
import java.lang.reflect.Type

internal class GsonBinaryFactory(
    private val gson: Gson
) : Converter.BinaryFactory {
    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun outgoingBinaryMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message.Binary>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonOutgoingBinaryConverter(gson, adapter)
    }

    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun incomingBinaryMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message.Binary, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonIncomingBinaryConverter(gson, adapter)
    }
}

fun createGsonBinaryConverterFactory(): Converter.BinaryFactory {
    return GsonBinaryFactory(Gson())
}
