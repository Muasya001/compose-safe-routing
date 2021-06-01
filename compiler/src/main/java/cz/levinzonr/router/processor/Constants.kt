package cz.levinzonr.router.processor

import com.squareup.kotlinpoet.ClassName

object Constants {
    const val FILE_ARGS_EXTENSIONS = "args-extensions"
    const val FILE_ACTIONS = "RoutesActions"
    const val FILE_ARGS_POSTFIX = "RouteArgs"
    const val FILE_ROUTES = "Routes"

    const val FILE_ARG_EXTENSIONS_PREFIX = "NavBackStackEntry+"
    const val FILE_ARGS_DIR = "args"
    const val ACTIONS_PREFIX = "to"

    const val PACKAGE_NAVIGATION = "androidx.navigation"
    const val CLASSNAME_NAV_BACK_STACK_ENTRY = "NavBackStackEntry"
    val CLASS_BACK_STACK_ENTRY = ClassName(PACKAGE_NAVIGATION, CLASSNAME_NAV_BACK_STACK_ENTRY)

    const val PACKAGE_LIFECYCLE = "androidx.lifecycle"
    const val CLASSNAME_SAVED_STATE_HANDLE = "SavedStateHandle"
    val CLASS_SAVED_STATE_HANDLE = ClassName(PACKAGE_LIFECYCLE, CLASSNAME_SAVED_STATE_HANDLE)
}


