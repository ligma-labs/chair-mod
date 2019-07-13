package eventshandlers

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import toItemBlock
import blocks.ChairBlock
import entities.ChairRideableEntity
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.common.registry.EntityEntryBuilder

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

    @SubscribeEvent
    fun registerEntity(event: RegistryEvent.Register<EntityEntry>) {
        val chairEntityEntry = EntityEntryBuilder.create<ChairRideableEntity>()
                .id(ResourceLocation(Utils.MODID, "chair_rideable"), 0)
                .entity(ChairRideableEntity::class.java)
                .name("chair_rideable")
                .build()

        event.registry.register(chairEntityEntry)
    }
}