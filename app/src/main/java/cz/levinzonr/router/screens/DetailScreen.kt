package cz.levinzonr.router.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.levinzonr.router.components.Placeholder
import cz.levinzonr.router.screens.args.DetailsRouteArgs

@Composable
fun DetailsScreen(args: DetailsRouteArgs) {
    Placeholder(color = Color.Green, title = "Details $args")
}