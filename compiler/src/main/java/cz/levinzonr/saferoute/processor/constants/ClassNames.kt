package cz.levinzonr.saferoute.processor.constants

import com.squareup.kotlinpoet.ClassName

internal object ClassNames {
    val RouteArgsFactory = ClassName(Constants.LIB_PACKAGE_NAME, "RouteArgsFactory")
    val RouteSpec = ClassName(Constants.LIB_PACKAGE_NAME, Constants.FILE_ROUTE_SPEC)
    val EmptyArgsFactory = ClassName("${Constants.LIB_PACKAGE_NAME}.util", "EmptyArgsFactory")
    val Bundle = ClassName("android.os", "Bundle")
    val SavedStateHandle = ClassName("androidx.lifecycle", "SavedStateHandle")
    val NamedNavArgument =  ClassName(Constants.PACKAGE_NAVIGATION, "NamedNavArgument")
    val navArgument = ClassName(Constants.PACKAGE_NAVIGATION, "navArgument")
    val NavType = ClassName(Constants.PACKAGE_NAVIGATION, "NavType")
    val NavController = ClassName(Constants.PACKAGE_NAVIGATION, "NavController")
    val ProvidableCompositionLocal = ClassName(Constants.PACKAGE_COMPOSE_RUNTIME, "ProvidableCompositionLocal")
    val compositionLocalOf = ClassName(Constants.PACKAGE_COMPOSE_RUNTIME, "compositionLocalOf")
    val Composable = ClassName(Constants.PACKAGE_COMPOSE_RUNTIME, "Composable")
    val NavDeepLink = ClassName(Constants.PACKAGE_NAVIGATION, "NavDeepLink")
    val NavDeepLinkDSL = ClassName(Constants.PACKAGE_NAVIGATION, "navDeepLink")
}

