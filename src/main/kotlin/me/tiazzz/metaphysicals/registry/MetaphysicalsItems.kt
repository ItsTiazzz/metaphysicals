package me.tiazzz.metaphysicals.registry

import me.tiazzz.metaphysicals.Metaphysicals
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item

object MetaphysicalsItems {
    val EMPTY_POTION_FLASK = register("empty_potion_flask", ::Item)

    fun <T : Item> register(name: String, itemFactory: (Item.Properties) -> T): T {
        return register(name, Item.Properties(), itemFactory)
    }

    fun <T : Item> register(name: String, properties: Item.Properties, itemFactory: (Item.Properties) -> T): T {
        val key = ResourceKey.create(Registries.ITEM, Metaphysicals.id(name))
        val item = itemFactory(properties.setId(key))
        Registry.register(BuiltInRegistries.ITEM, key, item)
        return item
    }
}