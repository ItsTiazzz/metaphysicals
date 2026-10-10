package me.tiazzz.metaphysicals

import me.tiazzz.metaphysicals.registry.MetaphysicalsItems
import net.fabricmc.api.ModInitializer
import net.minecraft.resources.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Metaphysicals : ModInitializer {
	const val MOD_ID: String = "metaphysicals"

	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		MetaphysicalsItems.initialize()
	}

	fun id(path: String): Identifier
		= Identifier.fromNamespaceAndPath(MOD_ID, path)
}
