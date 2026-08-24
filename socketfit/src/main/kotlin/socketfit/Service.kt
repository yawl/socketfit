package socketfit

import java.lang.reflect.Method

/**
 * Represents the runtime implementation of a service created by [Socketfit.create].
 */
internal interface Service {
    /**
     * Invokes a service method with the supplied arguments.
     *
     * @param method the method being invoked
     * @param args the arguments passed to the method
     * @return the result of the invocation
     */
    operator fun invoke(method: Method, args: Array<Any>): Any

    /**
     * Creates a [Service] implementation for the specified service interface.
     */
    interface Factory {
        /**
         * Creates a service for the given service interface.
         *
         * @param service the service interface to create
         * @return the service implementation
         * @throws IllegalArgumentException if the service type is not supported
         */
        @Throws(IllegalArgumentException::class)
        fun create(service: Class<*>): Service
    }
}
