package blocks

import Utils
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.MoverType
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class ChairBlock : Block(Material.WOOD) {
    init {
        setCreativeTab(CreativeTabs.REDSTONE)
        setRegistryName(Utils.MODID, "chair")
        unlocalizedName = "${Utils.MODID}:chair"
        setLightLevel(10 / 16f)
        setHardness(5f)
    }

    override fun isOpaqueCube(state: IBlockState): Boolean = false

    override fun isFullCube(state: IBlockState): Boolean = false

    override fun getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL

    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB? {
        return NULL_AABB
    }

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        // TODO: take sides

        if (worldIn.isRemote) {
            // Do it only for logical client. It will desync otherwise

            if (!playerIn.isRiding) {
                // Move player to chair
                playerIn.moveToBlockPosAndAngles(pos, playerIn.rotationYaw, playerIn.rotationPitch)

                // Adjust player sitting position
                playerIn.setPositionAndUpdate(playerIn.posX, playerIn.posY - 0.3, playerIn.posZ - 0.1)

                // Mount
                playerIn.startRiding(playerIn)

                // Prevent movement
                // TODO: find a better way to do this
                playerIn.updateBlocked = true

            } else {
                // Dismount and allow for movement
                playerIn.dismountRidingEntity()
                playerIn.updateBlocked = false
            }
        }

        return true
    }
}