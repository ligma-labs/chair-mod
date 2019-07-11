package blocks

import Utils
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class ChairBlock : Block(Material.WOOD) {

    companion object {
        const val TAG = "chair"
        const val INITIAL_METADATA = 0
        val CHAIR_AABB = AxisAlignedBB(2.0 / 16, 0.0, 2.0 / 16, 14.0 / 16, 11.0 / 16, 14.0 / 16)
        val PROPERTYFACING: PropertyDirection = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL)
    }

    init {
        unlocalizedName = "${Utils.MODID}.$TAG"
        setRegistryName(Utils.MODID, TAG)
        setCreativeTab(CreativeTabs.REDSTONE)
        setLightLevel(10 / 16f)
        setHardness(5f)

        defaultState = this.blockState.baseState.withProperty(PROPERTYFACING, EnumFacing.NORTH)
    }

    override fun isOpaqueCube(state: IBlockState): Boolean = false

    override fun getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL

    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB? {
        return CHAIR_AABB
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return CHAIR_AABB
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

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, PROPERTYFACING)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return when (meta) {
            0 -> this.defaultState.withProperty(PROPERTYFACING, EnumFacing.NORTH)
            1 -> this.defaultState.withProperty(PROPERTYFACING, EnumFacing.EAST)
            2 -> this.defaultState.withProperty(PROPERTYFACING, EnumFacing.SOUTH)
            3 -> this.defaultState.withProperty(PROPERTYFACING, EnumFacing.WEST)
            else -> this.defaultState
        }
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return when (state.getValue(PROPERTYFACING) as EnumFacing) {
            EnumFacing.NORTH -> 0
            EnumFacing.EAST -> 1
            EnumFacing.SOUTH -> 2
            EnumFacing.WEST -> 3
            else -> 0
        }
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
        return defaultState.withProperty(PROPERTYFACING, placer.horizontalFacing)
    }
}