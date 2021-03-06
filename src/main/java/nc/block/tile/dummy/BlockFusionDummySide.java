package nc.block.tile.dummy;

import nc.NuclearCraft;
import nc.block.tile.BlockInventory;
import nc.block.tile.generator.BlockFusionCore;
import nc.tile.dummy.TileFusionDummySide;
import nc.tile.generator.TileFusionCore;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFusionDummySide extends BlockInventory {
	
	public BlockFusionDummySide(String unlocalizedName, String registryName) {
		super(unlocalizedName, registryName, Material.IRON);
		setHarvestLevel("pickaxe", 0);
		setHardness(2);
		setResistance(15);
	}
	
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileFusionDummySide();
	}
	
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (findCore(world, pos) != null) world.destroyBlock(findCore(world, pos), true);
		if (world.getTileEntity(pos) != null) world.removeTileEntity(pos);
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else if (player != null) {
			BlockPos corePos = findCore(world, pos);
			if (corePos != null) {
				TileEntity tileentity = world.getTileEntity(corePos);
				if (tileentity instanceof TileFusionCore) {
					FMLNetworkHandler.openGui(player, NuclearCraft.instance, 101, world, corePos.getX(), corePos.getY(), corePos.getZ());
				}
			}
		}
		return true;
	}
	
	public BlockPos findCore(World world, BlockPos pos) {
		if (isCore(world, pos, 0, -1, 0)) return getPos(pos, 0, -1, 0);
		else if (isCore(world, pos, 1, 0, 0)) return getPos(pos, 1, 0, 0);
		else if (isCore(world, pos, 1, 0, 1)) return getPos(pos, 1, 0, 1);
		else if (isCore(world, pos, 0, 0, 1)) return getPos(pos, 0, 0, 1);
		else if (isCore(world, pos, -1, 0, 1)) return getPos(pos, -1, 0, 1);
		else if (isCore(world, pos, -1, 0, 0)) return getPos(pos, -1, 0, 0);
		else if (isCore(world, pos, -1, 0, -1)) return getPos(pos, -1, 0, -1);
		else if (isCore(world, pos, 0, 0, -1)) return getPos(pos, 0, 0, -1);
		else if (isCore(world, pos, 1, 0, -1)) return getPos(pos, 1, 0, -1);
		
		else if (isCore(world, pos, 1, -1, 0)) return getPos(pos, 1, -1, 0);
		else if (isCore(world, pos, 1, -1, 1)) return getPos(pos, 1, -1, 1);
		else if (isCore(world, pos, 0, -1, 1)) return getPos(pos, 0, -1, 1);
		else if (isCore(world, pos, -1, -1, 1)) return getPos(pos, -1, -1, 1);
		else if (isCore(world, pos, -1, -1, 0)) return getPos(pos, -1, -1, 0);
		else if (isCore(world, pos, -1, -1, -1)) return getPos(pos, -1, -1, -1);
		else if (isCore(world, pos, 0, -1, -1)) return getPos(pos, 0, -1, -1);
		else if (isCore(world, pos, 1, -1, -1)) return getPos(pos, 1, -1, -1);
		else return null;
	}
	
	private BlockPos getPos(BlockPos pos, int x, int y, int z) {
		return new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
	}
	
	private boolean isCore(World world, BlockPos pos, int x, int y, int z) {
		return world.getBlockState(getPos(pos, x, y, z)).getBlock() instanceof BlockFusionCore;
	}
}
