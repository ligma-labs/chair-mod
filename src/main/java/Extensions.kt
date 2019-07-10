import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

fun Block.toItemBlock(): Item {
    return ItemBlock(this).setRegistryName(this.registryName)
}