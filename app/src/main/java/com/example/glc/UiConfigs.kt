package com.example.glc

data class UiConfig(val toolbarConfig: ToolbarConfig, val bottomNavitaionConfig: BottomNavigationConfig)

data class ToolbarConfig(val showToolbar: Boolean, val title: String, val menu: Int)

data class BottomNavigationConfig(val showBottomNavigation: Boolean)

object UiConfigs {
    val uiConfig = mapOf(
        R.id.add_fragment to UiConfig(ToolbarConfig(true, "AddFragment", R.menu.add_menu), BottomNavigationConfig(true)),
        R.id.current_game_list to UiConfig(ToolbarConfig(true, "CurrentGameList", R.menu.top_menu), BottomNavigationConfig(true)),
        )
}