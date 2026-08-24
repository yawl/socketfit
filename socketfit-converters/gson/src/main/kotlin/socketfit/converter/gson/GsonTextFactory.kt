package socketfit.converter.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import socketfit.Converter
import socketfit.Message
import java.lang.reflect.Type

internal class GsonTextFactory(
    private val gson: Gson
) : Converter.TextFactory {
    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun outgoingTextMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<*, Message.Text>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonOutgoingTextConverter(gson, adapter)
    }

    @Suppress("RedundantNullableReturnType") // Retaining interface contract.
    override fun incomingTextMessageConverter(
        type: Type,
        annotations: Array<Annotation>
    ): Converter<Message.Text, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonIncomingTextConverter(gson, adapter)
    }
}

fun createGsonTextConverterFactory(): Converter.TextFactory {
    return GsonTextFactory(Gson())
}
