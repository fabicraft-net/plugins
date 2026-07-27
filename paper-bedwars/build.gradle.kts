import xyz.jpenilla.resourcefactory.paper.PaperPluginYaml

plugins {
	id("fabicraft.paper-conventions")
}

description = "Bedwars plugin"
version = "1"

dependencies {
	implementation(project(":paper-common"))
	compileOnly(libs.plugin.mbedwars)
	compileOnly(libs.plugin.carbon)
}

paperPluginYaml {
	main = "net.fabicraft.paper.bedwars.FabiCraftPaperBedwars"
	name = prefixedPluginName
	author = "FabianAdrian"
	apiVersion = "26.1.2"
	dependencies {
		server {
			register("CarbonChat") {
				required = true
				load = PaperPluginYaml.Load.BEFORE
			}
			register("MBedwars") {
				required = true
				load = PaperPluginYaml.Load.BEFORE
			}
		}
	}
}