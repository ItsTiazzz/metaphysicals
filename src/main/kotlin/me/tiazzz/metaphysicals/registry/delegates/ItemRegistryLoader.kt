package me.tiazzz.metaphysicals.registry.delegates

import me.tiazzz.metaphysicals.Metaphysicals
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ItemRegistryLoader<T : Item>(
    val properties: Item.Properties,
    val itemFactory: (Item.Properties) -> T
) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyProperty<Any?, T> {
        Metaphysicals.LOGGER.info("Providing delegate for ${property.name.lowercase()} in $thisRef")
        val key = ResourceKey.create(Registries.ITEM, Metaphysicals.id(property.name.lowercase()))
        val item = itemFactory(properties.setId(key))
        Registry.register(BuiltInRegistries.ITEM, key, item)

        return ReadOnlyProperty { _, _ ->
            return@ReadOnlyProperty item
        }
    }
}

fun register(): ItemRegistryLoader<Item> {
    return register(::Item)
}

fun <T : Item> register(itemFactory: (Item.Properties) -> T): ItemRegistryLoader<T> {
    return register(Item.Properties(), itemFactory)
}

fun <T : Item> register(properties: Item.Properties, itemFactory: (Item.Properties) -> T): ItemRegistryLoader<T> {
    return ItemRegistryLoader(properties, itemFactory)
}