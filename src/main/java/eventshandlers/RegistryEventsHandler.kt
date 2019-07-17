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
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class RegistryEventsHandler {

    private val chairBlock = ChairBlock()
    private val chairBlockItem = chairBlock.toItemBlock()

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.register(chairBlockItem)
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun registerItemModels(event: RegistryEvent.Register<Item>) {
        ModelLoader.setCustomModelResourceLocation(
                chairBlockItem,
                ChairBlock.INITIAL_METADATA,
                ModelResourceLocation(chairBlockItem.registryName.toString())
        )
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