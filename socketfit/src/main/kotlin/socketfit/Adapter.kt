package socketfit

import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Type

/**
 * Adapts a [Flow] with event type [R] into the type of [T]. Instances are
 * created by [a factory][Factory] which is [installed][Socketfit.Builder.addAdapterFactory]
 * into the [Socketfit] instance.
 */
interface Adapter<R, T> {
    /**
     * Returns the value type that this adapter uses when converting the WebSocket event to a Java
     * object. For example, the response type for `Flow<Repo>` is `Repo`. This type is
     * used to prepare the `flow` passed to [adapt].
     *
     * Note: This is typically not the same type as the `returnType` provided to this
     * adapter's factory.
     */
    fun responseType(): Type

    /**
     * Returns an instance of [T] which delegates to [flow].
     */
    fun adapt(flow: Flow<R>): T

    /**
     * Creates [Adapter] instances based on the return type of
     * [the service interface][Socketfit.create] methods.
     */
    interface Factory {
        /**
         * Returns an adapter for interface methods that return [returnType],
         * or null if it cannot be handled by this factory.
         */
        fun get(
            returnType: Type,
            annotations: Array<Annotation>
        ): Adapter<Any, Any>?
    }
}
