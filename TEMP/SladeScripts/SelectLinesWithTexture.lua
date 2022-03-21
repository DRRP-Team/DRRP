-- Copyright (c) DRRP-Team (PROPHESSOR) 2022

function regexpMatch(str, regexp)
    return string.match(str, regexp)
end

function execute(map)
    local texture = App.promptString("Texture regexp", "Enter regexp to find texture", "STON.*")
    
    if not texture then return end

    -- Get map sidedefs
    local sides = map.sidedefs

    -- Loop through all sidedefs
    for i,side in ipairs(sides) do
        -- Replace the middle texture if it is not blank (-)
        if regexpMatch(side.textureMiddle, texture)
            or regexpMatch(side.textureTop, texture)
            or regexpMatch(side.textureBottom, texture)
        then
            App.mapEditor():select(side.line, true)
        end
    end
end
