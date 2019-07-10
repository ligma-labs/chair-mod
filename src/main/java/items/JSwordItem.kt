package items

import ChairMod
import Utils
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemAxe
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class JSwordItem : ItemAxe(ToolMaterial.GOLD) {
    init {
        setRegistryName(Utils.MODID, "jsword")
    }

    override fun hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean {
        ChairMod.logger.info("Entity hit with jSword")
        return true
    }

    override fun getUnlocalizedName(): String = "${Utils.MODID}:jsword"

    override fun getRarity(stack: ItemStack): EnumRarity = EnumRarity.EPIC

    override fun getCreativeTab(): CreativeTabs? = CreativeTabs.COMBAT

    override fun getDamage(stack: ItemStack): Int {
        return 30
    }

    override fun onBlockDestroyed(stack: ItemStack, worldIn: World, state: IBlockState, pos: BlockPos, entityLiving: EntityLivingBase): Boolean {
        ChairMod.logger.info("Block destroyed with jSword")
        return true
    }
}