package socketfit.internal

import socketfit.Service
import socketfit.ServiceMethod
import java.lang.reflect.Method

internal class SocketfitServiceFactory(
    private val serviceMethodFactory: ServiceMethod.Factory,
) : Service.Factory {
    override fun create(service: Class<*>): Service {
        validateServiceInterface(service)
        return SocketfitService(
            loadServiceMethods(service)
        )
    }

    private fun validateServiceInterface(
        service: Class<*>
    ) {
        require(service.isInterface) {
            "API declarations must be interfaces."
        }

        val check = ArrayDeque<Class<*>>(1)
        check.addLast(service)

        while (check.isNotEmpty()) {
            val candidate = check.removeFirst()

            require(candidate.typeParameters.isEmpty()) {
                buildString {
                    append("Type parameters are unsupported on ")
                    append(candidate.name)

                    if (candidate != service) {
                        append(" which is an interface of ")
                        append(service.name)
                    }
                }
            }

            candidate.declaredMethods.forEach { method ->
                require(!method.isDefault) {
                    buildString {
                        append("Default methods are unsupported on ")
                        append(candidate.name)
                        append(".")
                        append(method.name)
                    }
                }
            }

            candidate.interfaces.forEach(check::addLast)
        }
    }

    private fun loadServiceMethods(
        service: Class<*>
    ): Map<Method, ServiceMethod> {
        return service
            .declaredMethods
            .filterNot { method ->
                method.isSynthetic
            }
            .associateWith { method ->
                serviceMethodFactory.create(method)
            }
    }
}
