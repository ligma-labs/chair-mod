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

    private val chairBlock = ChairBlock()

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        val chairBlockItem = chairBlock.toItemBlock()

        ModelLoader.setCustomModelResourceLocation(
                chairBlockItem,
                ChairBlock.INITIAL_METADATA,
                ModelResourceLocation(chairBlockItem.registryName.toString())
        )

        event.registry.register(chairBlockItem)
    }

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.register(chairBlock)
    }
}