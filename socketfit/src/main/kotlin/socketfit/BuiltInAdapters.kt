package socketfit

import java.lang.reflect.Type

internal class BuiltInAdapters : Adapter.Factory {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>
    ): Adapter<Any, Any>? {
        return null
    }
}
