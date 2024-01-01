package net.sydgh.emergencytorch.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.sydgh.emergencytorch.EmergencyTorch;
import net.minecraft.registry.Registry;
import net.sydgh.emergencytorch.block.ModBlocks;
import net.sydgh.emergencytorch.item.custom.TempTorchItem;

public class ModItems {

    public static final Item PrepStick = registerItem("preparedstick", new TempTorchItem(new FabricItemSettings()));
    public static final Item TempTorch = registerItem("temporarytorchitem", new VerticallyAttachableBlockItem(ModBlocks.TempTorch, ModBlocks.TempTorchWall, new Item.Settings(), Direction.DOWN));
    //Items
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){
        entries.add(PrepStick);
    }

    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries){
        entries.add(TempTorch);
    }

    public static void registerModItems(){

        EmergencyTorch.LOGGER.info("Registering Mod Items for " + EmergencyTorch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);

    }

    private static Item registerItem(String name, Item item){

        return Registry.register(Registries.ITEM, new Identifier(EmergencyTorch.MOD_ID, name), item);

    }

}
