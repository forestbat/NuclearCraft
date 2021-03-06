package nc.container.processor;

import nc.container.SlotProcessorInput;
import nc.container.SlotSpecificInput;
import nc.crafting.processor.IsotopeSeparatorRecipes;
import nc.tile.processor.TileEnergyItemProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

public class ContainerIsotopeSeparator extends ContainerEnergyItemProcessor {

	public ContainerIsotopeSeparator(EntityPlayer player, TileEnergyItemProcessor tileEntity) {
		super(tileEntity, IsotopeSeparatorRecipes.instance());
		
		addSlotToContainer(new SlotProcessorInput(tileEntity, recipes, 0, 42, 35));
		
		addSlotToContainer(new SlotFurnaceOutput(player, tileEntity, 1, 102, 35));
		addSlotToContainer(new SlotFurnaceOutput(player, tileEntity, 2, 130, 35));
		
		addSlotToContainer(new SlotSpecificInput(tileEntity, 3, 132, 64, speedUpgrade));
		addSlotToContainer(new SlotFurnaceOutput(player, tileEntity, 4, 152, 64));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(player.inventory, j + 9*i + 9, 8 + 18*j, 84 + 18*i));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player.inventory, i, 8 + 18*i, 142));
		}
	}
}
