package socketfit.internal

import socketfit.Service
import socketfit.ServiceMethod
import java.lang.reflect.Method

internal class SocketfitService(
    private val serviceMethods: Map<Method, ServiceMethod>
) : Service {
    override fun invoke(method: Method, args: Array<Any>): Any {
        val serviceMethod = serviceMethods[method]
        require(serviceMethod != null) {
            buildString {
                append("Could not find service method for ")
                append(method.declaringClass.name)
                append(".")
                append(method.name)
            }
        }
        return serviceMethod(args)
    }
}
