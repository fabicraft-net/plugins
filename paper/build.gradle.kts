import xyz.jpenilla.resourcefactory.paper.PaperPluginYaml.Load

plugins {
	id("fabicraft.paper-conventions")
}

version = "1"
description = "Main paper plugin"

dependencies {
	api(project(":common"))
	compileOnlyApi(libs.platform.paper)

	compileOnly(libs.plugin.miniplaceholders)
	api(libs.cloud.paper)
	implementation(libs.cloud.minecraftExtras)
}

paperPluginYaml {
	main = "net.fabicraft.paper.FabiCraftPaper"
	name = prefixedPluginName
	author = "FabianAdrian"
	apiVersion = "1.21.11"
	dependencies {
		server {
			register("MiniPlaceholders") {
				Load.BEFORE
				required = true
			}
		}
	}
}