package net.sydgh.emergencytorch.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.sydgh.emergencytorch.EmergencyTorch;
import net.sydgh.emergencytorch.item.custom.TempTorchBlock;
import net.sydgh.emergencytorch.item.custom.WallTempTorchBlock;

public class ModBlocks {

    public static final Block TempTorch = registerBlock("temporarytorch",
            new TempTorchBlock(AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(state -> 10).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.FLAME));
    public static final Block TempTorchWall = registerBlock("walltemporarytorch",
            new WallTempTorchBlock(AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(state -> 10).sounds(BlockSoundGroup.WOOD).dropsLike(TempTorch).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.FLAME));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(EmergencyTorch.MOD_ID, name)
        , block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(EmergencyTorch.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        EmergencyTorch.LOGGER.info("Registering ModBlocks for "+EmergencyTorch.MOD_ID);
    }

}
