
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 12  @  2 : 9

~ import net.eaglerforge.gui.ModGUI;
~ import net.lax1dude.eaglercraft.v1_8.Mouse;
~ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
~ import net.lax1dude.eaglercraft.v1_8.sp.SingleplayerServerController;
~ import net.lax1dude.eaglercraft.v1_8.sp.gui.GuiScreenLANInfo;
~ import net.lax1dude.eaglercraft.v1_8.sp.gui.GuiScreenLANNotSupported;
~ import net.lax1dude.eaglercraft.v1_8.sp.gui.GuiShareToLan;
~ import net.lax1dude.eaglercraft.v1_8.sp.lan.LANServerController;
~ import net.lax1dude.eaglercraft.v1_8.update.GuiUpdateCheckerOverlay;
~ import net.minecraft.client.audio.PositionedSoundRecord;

> CHANGE  4 : 7  @  4 : 5

~ import net.minecraft.util.ChatComponentText;
~ import net.minecraft.util.EnumChatFormatting;
~ import net.minecraft.util.ResourceLocation;

> DELETE  2  @  2 : 4

> INSERT  1 : 11  @  1

+ 	private GuiButton lanButton;
+ 
+ 	boolean hasSentAutoSave = !SingleplayerServerController.isWorldRunning();
+ 
+ 	private GuiUpdateCheckerOverlay updateCheckerOverlay;
+ 
+ 	public GuiIngameMenu() {
+ 		updateCheckerOverlay = new GuiUpdateCheckerOverlay(true, this);
+ 	}
+ 

> DELETE  1  @  1 : 2

> INSERT  1 : 2  @  1

+ 		this.updateCheckerOverlay.setResolution(mc, width, height);

> CHANGE  12 : 14  @  12 : 15

~ 		this.buttonList.add(lanButton = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + b0, 98, 20,
~ 				I18n.format(LANServerController.isLANOpen() ? "menu.closeLan" : "menu.openToLan", new Object[0])));

> CHANGE  4 : 11  @  4 : 5

~ 		this.buttonList.add(new GuiButton(69420, this.width / 2 - 100, this.height / 4 + 73 + b0,
~ 				I18n.format("eaglerforge.menu.mods", new Object[0])));
~ 		lanButton.enabled = SingleplayerServerController.isWorldRunning();
~ 		if (!hasSentAutoSave) {
~ 			hasSentAutoSave = true;
~ 			SingleplayerServerController.autoSave();
~ 		}

> CHANGE  2 : 3  @  2 : 3

~ 	protected void actionPerformed(GuiButton parGuiButton) {

> CHANGE  5 : 6  @  5 : 7

~ 			boolean flag = this.mc.isIntegratedServerRunning() || this.mc.isDemo();

> CHANGE  4 : 5  @  4 : 8

~ 				this.mc.shutdownIntegratedServer(new GuiMainMenu());

> CHANGE  1 : 2  @  1 : 2

~ 				this.mc.shutdownIntegratedServer(new GuiMultiplayer(new GuiMainMenu()));

> INSERT  9 : 13  @  9

+ 		case 69420:
+ 			// this.mc.displayGuiScreen(new GuiMods(this));
+ 			ModGUI.displayGui();
+ 			break;

> CHANGE  7 : 21  @  7 : 8

~ 			if (!LANServerController.supported()) {
~ 				mc.displayGuiScreen(new GuiScreenLANNotSupported(this));
~ 			} else if (LANServerController.isLANOpen()) {
~ 				if (LANServerController.hasPeers()) {
~ 					mc.displayGuiScreen(new GuiYesNo(this, I18n.format("networkSettings.delete"),
~ 							I18n.format("lanServer.wouldYouLikeToKick"), 0));
~ 				} else {
~ 					confirmClicked(false, 0);
~ 				}
~ 			} else {
~ 				this.mc.displayGuiScreen(GuiScreenLANInfo.showLANInfoScreen(
~ 						new GuiShareToLan(this, this.mc.playerController.getCurrentGameType().getName())));
~ 			}
~ 			break;

> CHANGE  6 : 9  @  6 : 7

~ 		if (Mouse.isActuallyGrabbed()) {
~ 			Mouse.setGrabbed(false);
~ 		}

> CHANGE  4 : 5  @  4 : 5

~ 		this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 20,

> INSERT  1 : 35  @  1

+ 
+ 		this.updateCheckerOverlay.drawScreen(i, j, f);
+ 
+ 		if (LANServerController.isLANOpen()) {
+ 			String str = I18n.format("lanServer.pauseMenu0");
+ 			drawString(fontRendererObj, str, 6, 32, 0xFFFF55);
+ 
+ 			if (mc.gameSettings.hideJoinCode) {
+ 				GlStateManager.pushMatrix();
+ 				GlStateManager.translate(7.0f, 47.0f, 0.0f);
+ 				GlStateManager.scale(0.75f, 0.75f, 0.75f);
+ 				str = I18n.format("lanServer.showCode");
+ 				int w = fontRendererObj.getStringWidth(str);
+ 				boolean hover = i > 6 && i < 8 + w * 3 / 4 && j > 46 && j < 47 + 8;
+ 				drawString(fontRendererObj, EnumChatFormatting.UNDERLINE + str, 0, 0, hover ? 0xEEEEAA : 0xCCCC55);
+ 				GlStateManager.popMatrix();
+ 			} else {
+ 				int w = fontRendererObj.getStringWidth(str);
+ 				GlStateManager.pushMatrix();
+ 				GlStateManager.translate(6 + w + 3, 33, 0.0f);
+ 				GlStateManager.scale(0.75f, 0.75f, 0.75f);
+ 				str = I18n.format("lanServer.hideCode");
+ 				int w2 = fontRendererObj.getStringWidth(str);
+ 				boolean hover = i > 6 + w + 2 && i < 6 + w + 3 + w2 * 3 / 4 && j > 33 - 1 && j < 33 + 6;
+ 				drawString(fontRendererObj, EnumChatFormatting.UNDERLINE + str, 0, 0, hover ? 0xEEEEAA : 0xCCCC55);
+ 				GlStateManager.popMatrix();
+ 
+ 				drawString(fontRendererObj, EnumChatFormatting.GRAY + I18n.format("lanServer.pauseMenu1") + " "
+ 						+ EnumChatFormatting.RESET + LANServerController.getCurrentURI(), 6, 47, 0xFFFFFF);
+ 				drawString(fontRendererObj, EnumChatFormatting.GRAY + I18n.format("lanServer.pauseMenu2") + " "
+ 						+ EnumChatFormatting.RESET + LANServerController.getCurrentCode(), 6, 57, 0xFFFFFF);
+ 			}
+ 		}
+ 

> INSERT  2 : 42  @  2

+ 
+ 	public void confirmClicked(boolean par1, int par2) {
+ 		mc.displayGuiScreen(this);
+ 		LANServerController.closeLANNoKick();
+ 		if (par1) {
+ 			LANServerController.cleanupLAN();
+ 			SingleplayerServerController.configureLAN(this.mc.theWorld.getWorldInfo().getGameType(), false);
+ 		}
+ 		this.mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(I18n.format("lanServer.closed")));
+ 		this.lanButton.displayString = I18n.format("menu.openToLan");
+ 	}
+ 
+ 	protected void mouseClicked(int par1, int par2, int par3) {
+ 		if (par3 == 0) {
+ 			if (mc.gameSettings.hideJoinCode) {
+ 				String str = I18n.format("lanServer.showCode");
+ 				int w = fontRendererObj.getStringWidth(str);
+ 				if (par1 > 6 && par1 < 8 + w * 3 / 4 && par2 > 46 && par2 < 47 + 8) {
+ 					mc.gameSettings.hideJoinCode = false;
+ 					this.mc.getSoundHandler()
+ 							.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
+ 					mc.gameSettings.saveOptions();
+ 				}
+ 			} else {
+ 				String str = I18n.format("lanServer.pauseMenu0");
+ 				int w = fontRendererObj.getStringWidth(str);
+ 				str = I18n.format("lanServer.hideCode");
+ 				int w2 = fontRendererObj.getStringWidth(str);
+ 				if (par1 > 6 + w + 2 && par1 < 6 + w + 3 + w2 * 3 / 4 && par2 > 33 - 1 && par2 < 33 + 6) {
+ 					mc.gameSettings.hideJoinCode = true;
+ 					this.mc.getSoundHandler()
+ 							.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
+ 					mc.gameSettings.saveOptions();
+ 				}
+ 			}
+ 
+ 		}
+ 		this.updateCheckerOverlay.mouseClicked(par1, par2, par3);
+ 		super.mouseClicked(par1, par2, par3);
+ 	}

> EOF
