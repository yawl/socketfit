package socketfit

import java.lang.reflect.Method

/**
 * Represents the runtime implementation of a service method.
 */
interface ServiceMethod {
    /**
     * Invokes this service method with the supplied arguments.
     *
     * @param args the arguments passed to the method
     * @return the result of the invocation
     */
    fun invoke(args: Array<Any>): Any

    /**
     * Factory for creating [ServiceMethod] instances from service method declarations.
     */
    interface Factory {
        /**
         * Creates a [ServiceMethod] for the specified method.
         *
         * @param method the service method to create an implementation for
         * @return the runtime implementation of the service method
         * @throws IllegalArgumentException if the method type is not supported
         */
        @Throws(IllegalArgumentException::class)
        fun create(method: Method): ServiceMethod
    }
}
