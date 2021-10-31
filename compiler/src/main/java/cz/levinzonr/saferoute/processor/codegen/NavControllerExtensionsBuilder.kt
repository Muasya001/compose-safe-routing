package cz.levinzonr.saferoute.processor.codegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import cz.levinzonr.saferoute.annotations.Route
import cz.levinzonr.saferoute.processor.constants.ClassNames
import cz.levinzonr.saferoute.processor.models.ModelData
import cz.levinzonr.saferoute.processor.models.RouteData

internal class NavControllerExtensionsBuilder(
    private val modelData: ModelData
) {
    fun build(): FileSpec {
        val builder = FileSpec.builder(modelData.packageName, "NavController+RouteActions")
        modelData.routes.forEach { builder.addFunction(createActionFunSpec(it)) }
        return builder.build()
    }

    private fun createActionFunSpec(route: RouteData) : FunSpec {
        val arguments = route.arguments.joinToString(",") { it.name }
        return FunSpec.builder("navigateTo${route.name.capitalize()}")
            .addParameters(route.arguments.map { it.toParamSpec() })
            .receiver(ClassNames.NavController)
            .returns(Unit::class)
            .addCode("navigate(RoutesActions.to${route.name.capitalize()}($arguments))")
            .build()
    }
}