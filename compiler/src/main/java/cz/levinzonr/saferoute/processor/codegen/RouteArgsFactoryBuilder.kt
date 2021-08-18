package cz.levinzonr.saferoute.processor.codegen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import cz.levinzonr.saferoute.processor.constants.ClassNames
import cz.levinzonr.saferoute.processor.constants.Constants
import cz.levinzonr.saferoute.processor.models.RouteData

internal class RouteArgsFactoryBuilder(
    private val data: RouteData
) {
    fun build() : TypeSpec {
        return TypeSpec.objectBuilder(data.getArgsFactoryName())
            .addSuperinterface(ClassNames.RouteArgsFactory.parameterizedBy(data.argsTypeClassName))
            .addFunction(buildNavBackStackEntryInitilizer())
            .addFunction(buildSavedStateHandleInitlizer())
            .build()
    }



    private fun buildNavBackStackEntryInitilizer() : FunSpec {
        val code = CodeBlock.builder()
        data.arguments.forEach {
            if (it.isNullable) {
                code.addStatement("val ${it.name} = bundle?.get%T(%S)", it.type, it.name)
            } else {
                code.addStatement("val ${it.name} = requireNotNull(bundle?.get%T(%S))", it.type, it.name)

            }
        }

        code.addStatement("return ${data.argumentsConstructor}")
        return FunSpec.builder("fromBundle")
            .returns(ClassName(data.packageName, data.argumentsName))
            .addParameter("bundle", ClassNames.Bundle.copy(nullable = true))
            .addModifiers(KModifier.OVERRIDE)
            .addKdoc("A Helper function to obtain an instance of ${data.argumentsName} from Bundle")
            .addCode(code.build())
            .build()
    }


    private fun buildSavedStateHandleInitlizer() : FunSpec {
        val code = CodeBlock.builder()

        data.arguments.forEach {
            if (it.isNullable) {
                code.addStatement("val ${it.name} = handle?.get<%T>(%S)", it.type, it.name)
            } else {
                code.addStatement("val ${it.name} = requireNotNull(handle?.get<%T>(%S))", it.type, it.name)
            }
        }

        code.addStatement("return ${data.argumentsConstructor}")
        return FunSpec.builder("fromSavedStateHandle")
            .returns(ClassName(data.packageName, data.argumentsName))
            .addKdoc("A Helper function to obtain an instance of ${data.argumentsName} from SavedStateHandle")
            .addParameter("handle", ClassNames.SavedStateHandle.copy(nullable = true))
            .addModifiers(KModifier.OVERRIDE)
            .addCode(code.build())
            .build()
    }

}