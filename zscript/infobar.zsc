/**
 *Copyright (c) 2018-2019 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

class InfobarItem: Inventory {
    String InfoString;
    int FontColor;
}

class InfobarHandler: EventHandler {

    Actor tClass; // "tracedClass".
    PlayerPawn pTrace; // "playerTrace".
    InfobarItem InfobarInv;

    void SetInfobarString( String infoStr ) {
        pTrace.GiveInventory( "InfobarItem", 1 );
        InfobarInv = InfobarItem( pTrace.FindInventory( "InfobarItem" ) );
        InfobarInv.InfoString = infoStr;
    } // of void SetInfobarString( String infoStr )

    override void WorldTick() {
        for ( int i = 0; i < MAXPLAYERS; i++ ) {
            if ( PlayerInGame[ i ] ) {
                CVar InfobarCVar = CVar.GetCVar( "drrp_enable_infobar", Players[ i ] );
                if ( ( InfobarCVar == null ) || !( InfobarCVar.GetBool() ) ) return;

                FLineTraceData InfobarTraceRay;

                pTrace = Players[ i ].mo;
                pTrace.TakeInventory( "InfobarItem", 1 );
                pTrace.LineTrace( pTrace.Angle, 2048, pTrace.Pitch, TRF_ALLACTORS, offsetz: pTrace.height - 12, data: InfobarTraceRay );

                int InfobarFontColor = Font.CR_GRAY;

                tClass = InfobarTraceRay.HitActor;

                if ( ( InfobarTraceRay.HitType == TRACE_HitActor ) && !tClass.CheckClass( "DRRPTrigger", AAPTR_DEFAULT, true ) ) {

                    if ( tClass.bIsMonster )InfobarFontColor = Font.CR_RED;
                    if ( tClass.bFriendly ) InfobarFontColor = Font.CR_GREEN;
                    if ( tClass.bSpecial )  InfobarFontColor = Font.CR_SAPPHIRE;
                    if ( tClass.bCorpse )   InfobarFontColor = Font.CR_DARKGRAY;

                    SetInfobarString( tClass.GetTag() );
                    if ( tClass.bCorpse ) InfobarInv.InfoString = InfobarInv.InfoString .. " ("
                                          .. StringTable.Localize( "$DRRP_M_CORPSE" ) .. ")";
                    InfobarInv.FontColor = InfobarFontColor;

                } // of if ( InfobarTraceRay.HitType == TRACE_HitActor )


/*                if ( ( InfobarTraceRay.HitType == TRACE_HitWall ) ) {
                    Line tLine = InfobarTraceRay.HitLine; // "tracedLine".

                    InfobarInv.FontColor = InfobarFontColor;
                    SetInfobarString( tLine.Side.GetTexture.GetName() );
                    // See "mapdata.txt" in (g/q)zdoom.pk3.
                    // Also, don't forget to check ML_BLOCKING flag.

                }/* */
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