package entities

import net.minecraft.entity.Entity
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ChairRideableEntity(worldIn: World) : Entity(worldIn) {

    override fun writeEntityToNBT(compound: NBTTagCompound) {
        // Nothing will be saved for now
    }

    override fun readEntityFromNBT(compound: NBTTagCompound) {
        // Nothing will be saved for now
    }

    override fun entityInit() {
        ChairMod.logger.info("Initializing chair entity")
    }
}