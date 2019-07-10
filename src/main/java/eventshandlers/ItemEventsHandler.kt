package eventshandlers

import ChairMod
import net.minecraftforge.event.entity.item.ItemTossEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ItemEventsHandler {

    @SubscribeEvent
    fun pickupItem(event: EntityItemPickupEvent) {
        ChairMod.logger.info("Item picked up! ${event.item.item.count}x ${event.item.item.unlocalizedName}")
    }

    @SubscribeEvent
    fun tossItem(event: ItemTossEvent) {
        ChairMod.logger.info("${event.player.name} tossed ${event.entityItem.item.count}x ${event.entityItem.item.unlocalizedName}")
    }

}