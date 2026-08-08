package com.cosy.resilveredmod;

//Imports
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;


@Mod(modid = "resilvered", name = "ReSilvered", version = "A1")
public class ReSilvered {

//Items
    //...Item item(item name)
    public static Item itemCloth;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        //Item/Block init and registering
        //Config Handling

        //EXAMPLE itemName = new ItemName().setUnlocalizedName("ItemName").setTextureName("resilveredmod:itemName");
        //        GameRegistry.registerItem(itemName, itemName.getUnlocalizedName().substring(5));

        itemCloth = new ItemCloth().setUnlocalizedName("ItemCloth").setTextureName("resilveredmod:Cloth");
        GameRegistry.registerItem(itemCloth, itemCloth.getUnlocalizedName().substring(5));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Proxy, TileEntity, entity, GUI and packet registering

        Minecraft.getMinecraft().gameSettings.ambientOcclusion = 0;

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}