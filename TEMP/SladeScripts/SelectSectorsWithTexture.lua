-- Copyright (c) DRRP-Team (PROPHESSOR) 2022

function regexpMatch(str, regexp)
    return string.match(str, regexp)
end

function execute(map)
    local texture = App.promptString("Texture regexp", "Enter regexp to find texture", "FLAT.*")
    
    if not texture then return end

    -- Get map sidedefs
    local sectors = map.sectors

    -- Loop through all sidedefs
    for i,sector in ipairs(sectors) do
        -- Replace the middle texture if it is not blank (-)
        if regexpMatch(sector.textureFloor, texture)
            or regexpMatch(sector.textureCeiling, texture)
        then
            App.mapEditor():select(sector, true)
        end
    end
end
