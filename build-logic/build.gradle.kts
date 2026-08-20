plugins {
	`kotlin-dsl`
}

dependencies {
	implementation(plugin(libs.plugins.spotless))
	implementation(plugin(libs.plugins.shadow))
	implementation(plugin(libs.plugins.resourcefactory.paper))
	implementation(plugin(libs.plugins.resourcefactory.velocity))
}

fun plugin(plugin: Provider<PluginDependency>) =
	plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }