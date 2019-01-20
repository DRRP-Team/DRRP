--[[

    Texture changer
    by PROPHESSOR
    (c) Copyright 2018-2019

--]]

function execute(map)
    App.logMessage('Texture changer by PROPHESSOR')
    
    -- Define
    
    local P_textureType = nil
    
    while true do
        P_textureType = App.promptString('Texture type', 'Enter texture type (middle/top/bottom/ceiling/floor', 'middle')
        if         P_textureType == 'middle'
                or P_textureType == 'top'
                or P_textureType == 'bottom'
                or P_textureType == 'ceiling'
                or P_textureType == 'floor' then
            break
        elseif     P_textureType == '' then
            App.logMessage('Canseled')
            return
        end
    end
    
    P_textureType = 'texture' .. P_textureType
    
    App.logMessage('Using ' .. P_textureType .. ' as a texture type')
    
    local P_textureFrom = App.promptString('Texture from', 'Enter the texture which you want to replace [FROM]', '-')
    if P_textureFrom == '' then
        App.logMessage('Canseled')
        return
    end
    
    App.logMessage('Using ' .. P_textureFrom .. ' as a from texture')
    
    local P_textureTo = App.promptString('Texture to', 'Enter the final texture [TO]', '-')
    if P_textureTo == '' then
        App.logMessage('Canseled')
        return
    end

    App.logMessage('Using ' .. P_textureTo .. ' as a to texture')
    
    -- Process
    
    local sides = map.sidedefs
    
    -- Loop through all sidedefs
    for i, side in ipairs(sides) do
        -- Replace the middle texture
        if P_textureType == 'texturemiddle' and side.textureMiddle == P_textureFrom then
            side:setStringProperty('texturemiddle', P_textureTo)
        end

        -- Replace the upper texture
        if P_textureType == 'texturetop' and side.textureTop == P_textureFrom then
            side:setStringProperty('texturetop', P_textureTo)
        end

        -- Replace the lower texture
        if P_textureType == 'texturebottom' and side.textureBottom == P_textureFrom then
            side:setStringProperty('texturebottom', P_textureTo)
        end
    end

    -- Get map sectors
    local sectors = map.sectors

    -- Loop through all sectors
    for i, sector in ipairs(sectors) do
        -- Set ceiling texture
        if P_textureType == 'textureceiling' and sector.textureCeiling == P_textureFrom then
            sector:setStringProperty('textureceiling', P_textureEnd)
        end

        -- Set floor texture
        if P_textureType == 'texturefloor' and sector.textureFloor == P_textureFrom then
            sector:setStringProperty('texturefloor', P_textureEnd)
        end
    end
    
    App.messageBox('Success', 'OK!')
    
    
end

