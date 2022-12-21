package com.revolvingmadness.scrolls;

import com.revolvingmadness.scrolls.block.ModBlocks;
import com.revolvingmadness.scrolls.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scrolls implements ModInitializer {
	public static final String MOD_ID = "scrolls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}
