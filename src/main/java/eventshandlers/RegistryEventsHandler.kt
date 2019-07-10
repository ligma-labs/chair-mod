package eventshandlers

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import toItemBlock
import blocks.ChairBlock
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader

class RegistryEventsHandler {
    private val items: MutableList<Item> = mutableListOf()
    private val blocks: MutableList<Block> = mutableListOf()

    init {
//        items.add(JSwordItem())
        blocks.add(ChairBlock())

        items.addAll(blocks.map { it.toItemBlock() })
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        items.forEach {
            ModelLoader.setCustomModelResourceLocation(it, 0, ModelResourceLocation(it.registryName.toString()))
            event.registry.register(it)
        }
    }

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        blocks.forEach { event.registry.register(it) }
    }
}