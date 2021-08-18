package cz.levinzonr.saferoute.processor.subprocessors

import cz.levinzonr.saferoute.annotations.Route
import cz.levinzonr.saferoute.annotations.RouteArg
import cz.levinzonr.saferoute.annotations.RouteArgType
import cz.levinzonr.saferoute.processor.constants.Constants
import cz.levinzonr.saferoute.processor.models.ArgumentData
import cz.levinzonr.saferoute.processor.models.ModelData
import cz.levinzonr.saferoute.processor.models.OptionalArgData
import cz.levinzonr.saferoute.processor.models.RouteData
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment

internal object DataProcessor {

    fun process(processingEnv: ProcessingEnvironment, environment: RoundEnvironment?) : ModelData? {
        try {
            var packageName: String = ""
            val routes = environment?.getElementsAnnotatedWith(Route::class.java)?.map { element ->

                packageName = processingEnv.elementUtils.getPackageOf(element).toString()
                val annotation = element.getAnnotation(Route::class.java)
                val arguments = annotation.args.map { it.toArgumentData()  }
                RouteData(annotation.name, arguments, packageName + "." + Constants.FILE_ARGS_DIR)
            }?.takeIf { it.isNotEmpty() } ?: return null

            return ModelData(packageName, routes)
        } catch (e: Exception) {
            throw Exception("Error while processing annotations: ${e.stackTraceToString()}")
        }
    }

    private fun RouteArg.toArgumentData() : ArgumentData {
        val optional = if (isOptional) buildOptionData(type, defaultValue) else null
        return ArgumentData(name, type.clazz, optional, type == RouteArgType.StringNullableType)
    }


    private fun buildOptionData(type: RouteArgType, value: String) : OptionalArgData<*> {
        when(type) {
            RouteArgType.StringType -> {
                require(value != RouteArg.VALUE_NULL)
                return OptionalArgData.OptionalString(value)
            }
            RouteArgType.StringNullableType -> {
                val value = if (value == RouteArg.VALUE_NULL) null else value
                return OptionalArgData.OptionalString(value)
            }
            RouteArgType.IntType -> {
                val intValue = requireNotNull(value.toIntOrNull()) { "Provided arg value ($value) is not matching type $type"}
                return OptionalArgData.OptionalInt(intValue)
            }
            RouteArgType.FloatType -> {
                val floatValue = requireNotNull(value.toFloatOrNull()) { "Provided arg value ($value) is not matching type $type"}
                return OptionalArgData.OptionalFloat(floatValue)

            }
            RouteArgType.LongType -> {
                val longValue = requireNotNull(value.toLongOrNull()) { "Provided arg value ($value) is not matching type $type"}
                return OptionalArgData.OptionalLong(longValue)
            }
            RouteArgType.BooleanType -> {
                val boolValue = requireNotNull(value.toBoolean()) { "Provided arg value ($value) is not matching type $type"}
                return OptionalArgData.OptionalBool(boolValue)
            }
        }
    }
}