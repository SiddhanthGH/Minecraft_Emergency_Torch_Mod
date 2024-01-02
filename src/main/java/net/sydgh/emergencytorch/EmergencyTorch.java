package net.sydgh.emergencytorch;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.sydgh.emergencytorch.block.ModBlocks;
import net.sydgh.emergencytorch.item.ModItemGroups;
import net.sydgh.emergencytorch.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmergencyTorch implements ModInitializer {
    public static final String MOD_ID = "emergencytorch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();

	}
}