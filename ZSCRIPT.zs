/**
 * Copyright (c) 2017-2018 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

version "2.5"

#include "zscript/weapons.zs"
#include "zscript/doorcode.zs"
#include "zscript/conversation.zs"
#include "zscript/shaders.zs"
#include "zscript/flashlight.zs"
#include "zscript/hud.zs"
#include "zscript/notebook.zs"
#include "zscript/monsters.zs"

class DRRPFire_zspatch : Actor {
    override bool CanCollideWith (Actor other, bool passive) {
        String cn = other.getClassName();
        if(cn == "DoomRPGPlayer"
                || cn == "Phantom"
                || cn == "DRRPLostSoul"
                || cn == "Nightmare"

                || cn == "Beholder"
                || cn == "Rahovart"
                || cn == "DRRPPainElemental"

                || cn == "Infernis"
                || cn == "DRRPArchVile"
                || cn == "Apollyon"
                //      || cn == "FireExtPuff"
                || (other.bMISSILE == true && cn != "FireExtPuff")) {
            return false;
        }
        return true;
    }
}

class InfobarItem: Inventory {
    String InfoString;
    int FontColor;
}

class InfobarHandler: EventHandler {

    override void WorldTick() {
        for ( int i = 0; i < MAXPLAYERS; i++ ) {
            if ( PlayerInGame[ i ] ) {
                CVar InfobarCVar = CVar.GetCVar( "drrp_enable_infobar", Players[ i ] );
                if ( ( InfobarCVar == null ) || !( InfobarCVar.GetBool() ) ) return;

                FLineTraceData InfobarTraceRay;
                PlayerPawn pTrace; // "playerTrace".

                pTrace = Players[ i ].mo;
                pTrace.TakeInventory( "InfobarItem", 1 );
                pTrace.LineTrace( pTrace.Angle, 2048, pTrace.Pitch, TRF_ALLACTORS, offsetz: pTrace.height - 12, data: InfobarTraceRay );

                Actor tClass = InfobarTraceRay.HitActor; // "tracedClass".

                if ( InfobarTraceRay.HitType == TRACE_HitActor ) {

                    int InfobarFontColor = Font.CR_GRAY;
                    if ( tClass.bIsMonster )InfobarFontColor = Font.CR_RED;
                    if ( tClass.bFriendly ) InfobarFontColor = Font.CR_GREEN;
                    if ( tClass.bSpecial )  InfobarFontColor = Font.CR_SAPPHIRE;
                    if ( tClass.bCorpse )   InfobarFontColor = Font.CR_DARKGRAY;

                    //console.printf( "[:zscript.cpp] " .. tClass.GetTag() .. " / " .. tClass.GetClassName() );

                    pTrace.GiveInventory( "InfobarItem", 1 );
                    InfobarItem InfobarInv = InfobarItem( pTrace.FindInventory( "InfobarItem" ) );
                    InfobarInv.InfoString = tClass.GetTag();
                    if ( tClass.bCorpse ) InfobarInv.InfoString = InfobarInv.InfoString .. " ("
                                          .. StringTable.Localize( "$DRRP_M_CORPSE" ) .. ")";
                    InfobarInv.FontColor = InfobarFontColor;

                } // of if ( InfobarTraceRay.HitType == TRACE_HitActor )
            } // of if ( PlayerInGame[ i ] )

        } // of for ( uint8 i = 0; i < MAXPLAYERS; i++ ) {

        Super.WorldTick();
    } // override void WorldTick()


    override void RenderOverlay( RenderEvent e ) {

        for ( int i = 0; i < MAXPLAYERS; i++ ) {
            if ( PlayerInGame[ i ] && Players[ i ].mo.FindInventory( "InfobarItem" ) ) {
                InfobarItem InfobarInv = InfobarItem( Players[ i ].mo.FindInventory( "InfobarItem" ) );
                Font InfobarFont = Font.FindFont( "SMALLFONT" );

                screen.DrawText( InfobarFont, InfobarInv.FontColor, 
                                 320 - ( InfobarInv.InfoString.Length() * 4 ), 30,
                                 InfobarInv.InfoString, 
                                 DTA_VirtualWidth, 640, DTA_VirtualHeight, 480 ); /* */

            } // of if ( PlayerInGame[ i ] && Players[ i ].FindInventory( "InfobarItem" ) ) {
        } // of for ( int i = 0; i < MAXPLAYERS; i++ ) {

        //Super.RenderOverlay();
    } // of override void RenderOverlay( RenderEvent e ) {

}