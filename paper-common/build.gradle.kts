plugins {
	id("fabicraft.java-conventions")
}

description = "Common code shared by paper plugins"

dependencies {
	api(project(":common"))
	api(libs.cloud.paper)
	compileOnlyApi(libs.platform.paper)
	compileOnlyApi(libs.plugin.miniplaceholders)
}