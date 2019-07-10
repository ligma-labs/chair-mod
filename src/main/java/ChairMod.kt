import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = Utils.MODID)
class ChairMod {
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        MinecraftForge.EVENT_BUS.register(eventshandlers.RegistryEventsHandler())
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(eventshandlers.ItemEventsHandler())
    }
    companion object {
        lateinit var logger: Logger
    }
}