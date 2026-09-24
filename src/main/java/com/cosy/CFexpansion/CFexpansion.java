package com.cosy.CFexpansion;

//Imports
import com.cosy.CFexpansion.events.MobDropHandler;
import com.cosy.CFexpansion.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;


@Mod(modid = "cfexpansion", name = "CF expansion", version = "A2 ")
public class CFexpansion {

    @Mod.Instance("cfexpansion") // MUSI być kropka w kropkę to samo co modid w @Mod!
    public static CFexpansion instance;

    //to pokazuje forgeowi gdzie leza pliki
    @SidedProxy(clientSide = "com.cosy.CFexpansion.proxy.ClientProxy", serverSide = "com.cosy.CFexpansion.proxy.CommonProxy")
    public static CommonProxy proxy;

//Items
    //...Item item(item name)
    //dev
    public static Item itemCFElogo;
    //mod
    public static Item itemCloth;
    public static Item itemBasket;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        //Item/Block init and registering
        //Config Handling

        //EXAMPLE itemName = new ItemName().setUnlocalizedName("ItemName").setTextureName("resilveredmod:itemName").setCreativeTab(tabCFexpansion);
        //        GameRegistry.registerItem(itemName, itemName.getUnlocalizedName().substring(5));

//DEV Items

        itemCFElogo = new ItemCFElogo().setUnlocalizedName("itemCFElogo").setTextureName("cfexpansionmod:itemcfelogo");
        GameRegistry.registerItem(itemCFElogo, itemCFElogo.getUnlocalizedName().substring(5));

        //ITEMS

        itemCloth = new ItemCloth().setUnlocalizedName("ItemCloth").setTextureName("cfexpansionmod:Cloth").setCreativeTab(tabCFexpansion);
        GameRegistry.registerItem(itemCloth, itemCloth.getUnlocalizedName().substring(5));

        itemBasket = new ItemBasket().setUnlocalizedName("ItemBasket").setTextureName("cfexpansionmod:Basket").setCreativeTab(tabCFexpansion);
        GameRegistry.registerItem(itemBasket, itemBasket.getUnlocalizedName().substring(5));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    //backpack
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        //rejestracja event dropow
        MinecraftForge.EVENT_BUS.register(new MobDropHandler());

        //rejestracja proxy
        proxy.registerRenderers();

        //usuwanie receptur

        RemoveRecipes.removeBedRecipe();

        //craftingi

        //Cloth
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(itemCloth),
                        "sss",
                        "sss",
                        's', new ItemStack(Items.string, 1, OreDictionary.WILDCARD_VALUE)
                )
        );

        //Basket
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(itemBasket),
                        " S ",
                        "SsS",
                        "SsS",
                        'S', new ItemStack(Items.stick, 1, OreDictionary.WILDCARD_VALUE),
                        's', new ItemStack(Items.string, 1, OreDictionary.WILDCARD_VALUE)
                )
        );

        //Bed(new)
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(Items.bed),
                        "CCW",
                        "PPP",
                        'C', new ItemStack(itemCloth),
                        'W', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE),
                        'P', new ItemStack(Blocks.planks, 1, OreDictionary.WILDCARD_VALUE)
                )
        );

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        //CREATIVE TAB
    }

    public static CreativeTabs tabCFexpansion = new CreativeTabs("TabCFexpansion") {
        @Override
        public Item getTabIconItem() {
            return new ItemStack(itemCFElogo).getItem();

        }

    };

}